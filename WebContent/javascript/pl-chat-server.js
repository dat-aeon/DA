"use strict";

process.title = 'node-chat';

// Port where we'll run the websocket server
var webSocketsServerPort = 8085;
var ipAddress = '10.1.9.69'

// websocket and http servers
var webSocketServer = require('websocket').server;
var http = require('http');
const JSON1 = require('circular-json');


// list of currently connected clients (users)
var clients = new Array();

var listroom = new Array();

var hasUser = false;

const pg  = require('pg')


var oldRoom;

var db_host="10.1.9.69";
var db_port="5432";
var db_name="vcs";
var db_user="vcs";
var db_password="vcs";

var image_path = "/mnt/jboss-eap-6.4/standalone/deployments/VCS.war/PhotoImage/MessageImage/";

var image_link = "https://ass.aeoncredit.com.mm/vcsm2/message-image-files/";

const opts = {
		logDirectory:'/home/pl-chat-server/log', // NOTE: folder must exist and be writable...
        fileNamePattern:'vcs-pl-chat-server-<DATE>.log',
        dateFormat:'YYYY.MM.DD'
};
const log = require('simple-node-logger').createRollingFileLogger( opts );
/**
 * HTTP server
 */
var server = http.createServer(function(request, response) {
	// Not important for us. We're writing WebSocket server, not HTTP server
});
server.listen(webSocketsServerPort, function() {
	log.info("Server Start : On Port - " + webSocketsServerPort + ".");
});

/**
 * WebSocket server
 */

var wsServer = new webSocketServer({
	// WebSocket server is tied to a HTTP server.
	httpServer : server,
	maxReceivedFrameSize: 2493644,
    	maxReceivedMessageSize: 10 * 1024 * 1024,
    	autoAcceptConnections: false
});

function forEach(array, action) {
	try {
		for ( var i = 0; i < array.length; i++)
			action(array[i], i);
	} catch (exception) {
		if (exception != Break) {
			throw exception;
		}
	}
}
// move connection to assigned room, new roomIndex is also be assigned
function moveToRoom(room, newroom, user) {

	var tmp = [ newroom, [ user ] ];
	var ri = -1, ui = -1;
	
	clients[user.ri][1].splice(user.ui, 1);
	clients[user.ri][1].ui -= 1;
	
	for ( var i = user.ui; i < clients[user.ri][1].length; i++) {
		clients[user.ri][1][i].ui -= 1;

	}
	for ( var i = 0; i < clients.length; i++) {
		if (clients[i][0] == newroom) {
			ui = clients[i][1].push(user) - 1;
			ri = i;
			return [ ri, ui ];
		}

	}
	
	ri = clients.push(tmp) - 1;
	ui = 0;
	return [ ri, ui ];
}

//function to encode file data to base64 encoded string
function base64_encode(file) {
    // read binary data
	var fs = require('fs');
    var bitmap = fs.readFileSync(file);
    // convert binary data to base64 encoded string
    return new Buffer(bitmap).toString('base64');
}

// This callback function is called every time someone tries to connect to
// the WebSocket server
wsServer.on('request', function(request) {
	
	var userName = false;
	var userId = false;
	var conn = getConnection();

	function getConnection() {
		// accept connection
		var connection = request.accept(null, request.origin);
		var roomName = false;
		var roomIndex = 0;
		
		log.info("New Connection is accepted.");
		
		listroom=[];
		userName = true;
		connection.on('message', function(message) {
			if (message.type === 'utf8') { 
				var t="message = "+message.utf8Data+"";
				log.info(t);
				var brand_id;
				var brand_name;

				const req_data = JSON.parse(message.utf8Data);
				log.info("Request Data API :" + req_data.api);
				
				if (req_data.api =="socket-connect"){ // first message sent by user is
					var userName = req_data.param.phoneNo
					var userId = req_data.param.customerId
					
					//userName = message.utf8Data.substring(9,message.utf8Data.indexOf("userId:"));
					//userId = message.utf8Data.substring( message.utf8Data.indexOf("userId:")+7);
					this.name = userName;
					log.info("User Connect : '" + userName +"' is successfully connected.");
					log.info("Change Room : Room changing was requested.");
					
					var roomName = req_data.param.phoneNo
					var oldRoom = req_data.param.oldRoom
					var userWithAgency = req_data.param.userWithAgency
					
					//roomName = message.utf8Data.substring( 3,message.utf8Data.indexOf("or:"));
					//oldRoom = message.utf8Data.substring( message.utf8Data.indexOf("or:")+3,message.utf8Data.indexOf("userWithAgency:"));
					//var userWithAgency = message.utf8Data.substring( message.utf8Data.indexOf("userWithAgency:")+15);
					
					hasUser = true;
					
					if(oldRoom == ""){
						var isExistRoom = false;
						for ( var i = 0; i < clients.length; i++) {
							if (clients[i][0] == roomName) {
								isExistRoom = true;
								this.ri = i;
								this.name = userName;
								this.ui = clients[i][1].push(connection) - 1;
								break;
							}
						}
						if(isExistRoom == false)
						{
							clients.push([ roomName, [] ]);
							this.ri = clients.length-1;
							this.name = userName;
							this.ui = clients[clients.length-1][1].push(connection) -1;
							
						}
						
					}else{
						var inds = moveToRoom(oldRoom, roomName, connection);
						this.ri = inds[0];
						this.ui = inds[1];
						
					}
					
					connection.sendUTF(JSON.stringify({
						type : 'at-room',
						data : userWithAgency
					}));
					this.room = roomName;
					
					log.info("Change Room : Room changing was successfull.");
					
				}
				//(1) Get categories and brands.
				else if(req_data.api == 'get-device-brands'){
					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});
					
					pool.connect(function(err, client, done){
						if(err){
							log.error("Connection Error in get-device-brands : " + err);
							done();
						} else {
							client.query('SELECT brand_id, brand_name FROM vcs.at_brand WHERE del_flag = 0', [], function(error, result) {
								if(error) {
									log.error('Query Failed : Get Brands Information. Error : ' + error);
								} else {
									if(result != undefined) {
										var brands = new Array();
										for(var i=0; i<result.rows.length; i++) {
											var obj = {
												brand_id : result.rows[i].brand_id,
												brand_name : result.rows[i].brand_name
											}
											brands.push(obj)
										}
										connection.sendUTF(JSON.stringify({
											type : 'get-brands',
											data : brands
										}));
									}
								}
							});
						}
						done();
					});
				} 
				else if(req_data.api == 'get-device-categories'){
					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});
					pool.connect(function(err, client, done) {
						if(err){
							log.error("Connection Error in get-device-categories : "+ err);
							done();
						} else {
							client.query('SELECT da_product_type_id, name FROM vcs.da_product_type WHERE del_flag = false', [], function(error, result) {
								if(error) {
									log.error('Query Failed : Get Categories Information. Error : ' + error);
								} else {
									if(result != undefined) {
										var categories = new Array();
										for(var i=0; i<result.rows.length; i++) {
											var obj = {
												product_type_id : result.rows[i].da_product_type_id,
												product_name : result.rows[i].name
											}
											categories.push(obj)
										}
										connection.sendUTF(JSON.stringify({
											type : 'get-categories',
											data : categories
										}));
									}
								}
							});
						}
						done();
					});
					
				} else if(req_data.api == 'post-buy-info'){ 

					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});

					pool.connect(function(err, client, done){
						if(err){
							log.error("Connection Error in post-buy-info : "+ err);
							done();
						} else {

							var category_id = req_data.param.categoryId
							var brand_id = req_data.param.brandId
							var location = req_data.param.location
							var msg_content = req_data.param.additionalText
							var user_id = req_data.param.userId
							var op_send_flag = req_data.param.sendFlag
							var send_time = req_data.param.sendTime
							var read_flag = req_data.param.readFlag

							client.query('INSERT INTO vcs.at_chat_message(da_product_type_id, customer_id, brand_id, customer_location, message_content, send_time, agent_level_id, op_send_flag, del_flag, read_flag) VALUES ($1,$2,$3,$4,$5,now(),$6,$7,$8,$9)', [category_id, user_id, brand_id, location, msg_content, 1, op_send_flag, 0, read_flag], function(error, result) {
								if(error) {
									connection.sendUTF(JSON.stringify({
										type : 'post-buy-not-ok',
										data : JSON.stringify(error)
									}));
								} else {
									connection.sendUTF(JSON.stringify({
										type : 'post-buy-ok',
										data : JSON.stringify(result)
									}));
								}
							})
						}
						done();
					});

				} else if(req_data.api == 'get-message-history'){
					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});
						
					pool.connect(function(err, client, done){
						if(err){
							log.error("Connection Error in get-message-history : "+ err);
							done();
						} else {
							var customer_id = req_data.param.customerId
							client.query('SELECT cm.op_send_flag,cm.agent_level_id,cm.chat_message_id,cm.customer_id,cm.agent_name,cm.brand_id,bn.brand_name,cm.price,cm.message_content,cm.customer_location,cm.da_product_type_id,dpt.name,cm.phone_no,cm.url_link,cm.send_time FROM vcs.at_chat_message as cm LEFT JOIN vcs.at_brand as bn ON cm.brand_id = bn.brand_id LEFT JOIN vcs.da_product_type as dpt ON cm.da_product_type_id = dpt.da_product_type_id LEFT JOIN vcs.at_agent_level as tai ON cm.agent_level_id = tai.agent_level_id WHERE cm.del_flag=0 AND cm.customer_id=$1 ORDER BY cm.send_time',[customer_id], function(error, result){
								if(error){
									log.error('Query Failed : Get Message History. Error : ' + error);
								} else {
									if(result != undefined){
										var chat_msg_list = new Array();

										for(var i=0; i<result.rows.length; i++){
											var obj = {
												opSendFlag : result.rows[i].op_send_flag,
												agentId : result.rows[i].agent_level_id,
												messageId : result.rows[i].chat_message_id,
												senderId : result.rows[i].customer_id,
												agentName : result.rows[i].agent_name,
												brandId : result.rows[i].brand_id,
												brandName : result.rows[i].brand_name,
												price : result.rows[i].price,
												contentMessage : result.rows[i].message_content,
												location : result.rows[i].customer_location,
												categoryId : result.rows[i].da_product_type_id,
												categoryName : result.rows[i].name,
												phoneNo : result.rows[i].phone_no,
												urlLink : result.rows[i].url_link,
												sendTime : result.rows[i].send_time
											}
											chat_msg_list.push(obj)
										}

										connection.sendUTF(JSON.stringify({
											type : 'get-msg-history',
											data : chat_msg_list
										}));

									}
								}
							});
						}
						done();
					});

				} else if(req_data.api == 'get-unread-messages'){

					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});
					pool.connect(function(err, client, done){
						if(err){
							log.error("Connection Error in get-unread-messages : "+ err);
							done();
						} else {
							var customer_id = req_data.param.customerId
							client.query('SELECT cm.op_send_flag,cm.agent_level_id,cm.chat_message_id,cm.customer_id,cm.agent_name,cm.brand_id,bn.brand_name,cm.price,cm.message_content,cm.customer_location,cm.da_product_type_id,dpt.name,cm.phone_no,cm.url_link,cm.send_time FROM vcs.at_chat_message as cm LEFT JOIN vcs.at_brand as bn ON cm.brand_id = bn.brand_id LEFT JOIN vcs.da_product_type as dpt ON cm.da_product_type_id = dpt.da_product_type_id LEFT JOIN vcs.at_agent_level as tai ON cm.agent_level_id = tai.agent_level_id WHERE cm.del_flag=0 AND cm.op_send_flag=1 AND cm.read_flag=0 AND cm.customer_id=$1 ORDER BY cm.send_time',[customer_id], function(error, result){
								if(error){
									log.error('Query Failed : Get Unread Message History. Error : ' + error);
								} else {
									if(result != undefined){
										var old_msg_list = new Array();
										for(var i=0; i<result.rows.length; i++){

											var obj = {
												opSendFlag : result.rows[i].op_send_flag,
												agentId : result.rows[i].agent_level_id,
												messageId : result.rows[i].chat_message_id,
												senderId : result.rows[i].customer_id,
												agentName : result.rows[i].agent_name,
												brandId : result.rows[i].brand_id,
												brandName : result.rows[i].brand_name,
												price : result.rows[i].price,
												contentMessage : result.rows[i].message_content,
												location : result.rows[i].customer_location,
												categoryId : result.rows[i].da_product_type_id,
												categoryName : result.rows[i].name,
												phoneNo : result.rows[i].phone_no,
												urlLink : result.rows[i].url_link,
												sendTime : result.rows[i].send_time
											}
											old_msg_list.push(obj)
										}

										connection.sendUTF(JSON.stringify({
											type : 'get-unread-messages',
											data : old_msg_list
										}));
									}
								}
							});
						}
						done();
					});

				} else if(req_data.api == 'read-messages'){
					var message_id = req_data.param.messageId
					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});
					
					pool.connect(function(err, client, done){
						if(err){
							log.error("Connection Error in update read-messages : "+ err);
							done();
						} else {
							client.query('UPDATE vcs.at_chat_message SET read_flag=1 WHERE chat_message_id=$1',[message_id], function(error, result){
								if(error){
									log.error('Query Failed : Get Update Read Message History. Error : ' + error);
								}
							});
						}
						done();
					});
				
				} else if(req_data.api == 'get-unread-message-count'){
					var customer_id = req_data.param.customerId
					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});
					pool.connect(function(err, client, done){
						if(err){
							log.error('Connection Error in update read-messages : ' + err);
							done();
						} else {
							client.query('BEGIN',function(err){
								if(err){
									log.error('Query Failed : Select Unread Message Count. Error : ' + err);
									return done(true);
								} else {
									client.query('SELECT COUNT(*) FROM vcs.at_chat_message WHERE customer_id=$1 AND del_flag=0 AND read_flag=0 AND op_send_flag=1',[customer_id], function(error, result){
										if(error){
											log.error('Query Failed : Select Unread Message Count. Error : ' + err);
										} else {
											var msg_count = result.rows[0].count
											connection.sendUTF(JSON.stringify({
												type : 'get-unread-message-count',
												data : msg_count
											}));
										}
									});
								}
							});
						}
						done();
					});
				
				}else if(req_data.api == 'update-call-count'){
					var agent_id = req_data.param.agentId
					var message_id = req_data.param.messageId
					
					const pool = new pg.Pool({
						user: db_user,
						host: db_host,
						database: db_name,
						password: db_password,
						port: db_port});
					pool.connect(function(err, client, done){
						if(err){
							log.error('Connection Error in update-call-count : ' + err);
							done();
						} else {
							client.query('UPDATE vcs.at_chat_message SET call_count=call_count+1 WHERE op_send_flag=1 AND agent_level_id=$1 AND chat_message_id=$2',[agent_id, message_id], function(error, result){
								if(error){
									log.error('Query Failed : Update call count. Error : ' + err);
								} else {
									if(result != undefined){
										connection.sendUTF(JSON.stringify({
											type : 'update-call-count',
											data : 'updated.'
										}));
									} else {
										connection.sendUTF(JSON.stringify({
											type : 'update-call-count',
											data : 'not-updated. | result not defined.'
										}));
									}
								}
							});
						}
						done();
					});
				}			} else{
				
				log.error("BAD REQUEST.");
			}
		});

		// user disconnected
		connection.on('close', function(connection) {
			if (hasUser !== false ) {
				log.info("User Disconnect : '" + userName +"' is disconnected.");
				if(clients[this.ri] != undefined){
					clients[this.ri][1].splice(this.ui, 1);
					for ( var i = this.ui; i < clients[this.ri][1].length; i++) {
						clients[this.ri][1].ui -= 1;
					}
				}
				hasUser=false;
			}
		});
		return connection;
	}
});
