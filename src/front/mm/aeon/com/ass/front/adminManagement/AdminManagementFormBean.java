/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.adminManagement;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.operatorManagement.OperatorManagementHeaderBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("adminManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AdminManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 2561404663068363440L;

    @In(required = false, value = "adminUpdateParam")
    @Out(required = false, value = "adminUpdateParam")
    private AdminManagementHeaderBean updateParam;
    
    @In(required = false, value = "adminRegisterParam")
    @Out(required = false, value = "adminRegisterParam")
    private AdminManagementHeaderBean registerManagementHeaderBean;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private AdminManagementHeaderBean registerHeaderBean;
    
    private AdminManagementHeaderBean groupHeaderBean;
    
    private AdminManagementHeaderBean backUpHeaderBean;

    private boolean isUpdate;

    private boolean isValidStatusUpdate;

    private boolean isError = false;
    
    private boolean cancel = false;    

    @Begin(join = true)
    public void init() {
        ASSLogger logger = new ASSLogger();
        
        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.ADMIN_LIST);
        if(result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
            LogLevel.ERROR); throw new InvalidScreenTransitionException();
        }
        init = false;
        isUpdate = false;
        cancel = true;
        this.getMessageContainer().clearAllMessages(true);
        registerHeaderBean = new AdminManagementHeaderBean();
        groupHeaderBean = new AdminManagementHeaderBean();
        groupHeaderBean
        .setGroupSelectItemList(registerManagementHeaderBean.getGroupSelectItemList());
    }

    @Begin(join = true)
    public void updateInit() {
        this.getMessageContainer().clearAllMessages(true);

        init = false;
        isUpdate = true;
        cancel = false;
        registerHeaderBean = new AdminManagementHeaderBean();
        groupHeaderBean = new AdminManagementHeaderBean();
        this.registerHeaderBean.setUserId(updateParam.getUserId());
        this.registerHeaderBean.setLoginId(updateParam.getLoginId());
        this.registerHeaderBean.setName(updateParam.getName());
        this.registerHeaderBean.setUpdatedTime(updateParam.getUpdatedTime());
        this.registerHeaderBean.setGroupSelectItemList(updateParam.getGroupSelectItemList());
        this.groupHeaderBean.setGroupSelectItemList(updateParam.getGroupSelectItemList());
        this.registerHeaderBean.setGroupId(updateParam.getGroupId());
        
        this.backUpHeaderBean = new AdminManagementHeaderBean().copyAdminManagementHeaderBean(this.registerHeaderBean);
    }

    @Action
    public String register() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            registerHeaderBean = new AdminManagementHeaderBean();
            init = true;
            cancel = true;
            return LinkTarget.SEARCH;
        }
        doReload = new Boolean(true);
        registerHeaderBean = new AdminManagementHeaderBean();
        return LinkTarget.OK;
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.updateParam = null;
        this.registerHeaderBean = null;

        return LinkTarget.SEARCH;
    }
    
    public void clear() {
        this.registerHeaderBean = new AdminManagementHeaderBean();
    }
    
    public void reset() {
        this.registerHeaderBean = registerHeaderBean.copyAdminManagementHeaderBean(backUpHeaderBean);
    }

    public AdminManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(AdminManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AdminManagementHeaderBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(AdminManagementHeaderBean registerHeaderBean) {
        this.registerHeaderBean = registerHeaderBean;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean isValidStatusUpdate() {
        return isValidStatusUpdate;
    }

    public void setValidStatusUpdate(boolean isValidStatusUpdate) {
        this.isValidStatusUpdate = isValidStatusUpdate;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public AdminManagementHeaderBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(AdminManagementHeaderBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

    public AdminManagementHeaderBean getRegisterManagementHeaderBean() {
        return registerManagementHeaderBean;
    }

    public void setRegisterManagementHeaderBean(AdminManagementHeaderBean registerManagementHeaderBean) {
        this.registerManagementHeaderBean = registerManagementHeaderBean;
    }

    public AdminManagementHeaderBean getGroupHeaderBean() {
        return groupHeaderBean;
    }

    public void setGroupHeaderBean(AdminManagementHeaderBean groupHeaderBean) {
        this.groupHeaderBean = groupHeaderBean;
    }

}
