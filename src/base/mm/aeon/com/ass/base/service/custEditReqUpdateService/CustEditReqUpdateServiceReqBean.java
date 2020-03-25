/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.custEditReqUpdateService;

import java.sql.Timestamp;
import java.util.Date;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class CustEditReqUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = 9127700691958278402L;

    /**
     * 
     */

    private Integer custEditReqId;

    private Integer customerId;

    private String name;

    private Date dob;

    private String nrc_no;

    private String phone_no;
    
    private Integer status;

    private String updatedBy;

    private Timestamp updatedTime;

    @Override
    public String getServiceId() {
        return "EDITREQUESTSU";
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public Integer getCustEditReqId() {
        return custEditReqId;
    }

    public String getNrc_no() {
        return nrc_no;
    }

    public void setNrc_no(String nrc_no) {
        this.nrc_no = nrc_no;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
