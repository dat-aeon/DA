/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.custEditRequestList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CustEditReqListHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8854792234264418775L;

    private Integer custEditReqId;
    private Integer customerId;
    private String name;
    private Date dob;
    private Integer status;
    private String phone_no;
    private String nrc_no;
    private Timestamp updatedTime;
    private boolean isUpdate;
    
    private Integer current_customerId;
    private String current_name;
    private String current_phoneNo;
    private String current_nrcNo;
    private Date current_dob;

    
    public Integer getCurrent_customerId() {
        return current_customerId;
    }

    public void setCurrent_customerId(Integer current_customerId) {
        this.current_customerId = current_customerId;
    }

    public String getCurrent_name() {
        return current_name;
    }

    public void setCurrent_name(String current_name) {
        this.current_name = current_name;
    }

    public String getCurrent_phoneNo() {
        return current_phoneNo;
    }

    public void setCurrent_phoneNo(String current_phoneNo) {
        this.current_phoneNo = current_phoneNo;
    }

    public String getCurrent_nrcNo() {
        return current_nrcNo;
    }

    public void setCurrent_nrcNo(String current_nrcNo) {
        this.current_nrcNo = current_nrcNo;
    }

    public Date getCurrent_dob() {
        return current_dob;
    }

    public void setCurrent_dob(Date current_dob) {
        this.current_dob = current_dob;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getCustEditReqId() {
        return custEditReqId;
    }

    public void setCustEditReqId(Integer custEditReqId) {
        this.custEditReqId = custEditReqId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

   public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
 

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getNrc_no() {
        return nrc_no;
    }

    public void setNrc_no(String nrc_no) {
        this.nrc_no = nrc_no;
    }

}
