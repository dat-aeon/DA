/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.custEditRequestSearch;

import java.sql.Timestamp;
import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class CustEditReqSelectListResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 3324699963916792848L;

    private Integer custEditReqId;
    private String createdBy;
    private Date createdTime;
    private Integer customerId;
    private Date dob;
    private String name;
    private String nrc_no;
    private String phone_no;
    private Integer status;
    private String updatedBy;
    private Timestamp updatedTime;

    private Integer current_customerId;
    private String current_name;
    private String current_phoneNo;
    private Date current_dob;
    private String current_nrcNo;

    private Timestamp lockTime;
    private String lockBy;
    private Boolean lockFlag;

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

    public Date getCurrent_dob() {
        return current_dob;
    }

    public void setCurrent_dob(Date current_dob) {
        this.current_dob = current_dob;
    }

    public String getCurrent_nrcNo() {
        return current_nrcNo;
    }

    public void setCurrent_nrcNo(String current_nrcNo) {
        this.current_nrcNo = current_nrcNo;
    }

    public String getNrc_no() {
        return nrc_no;
    }

    public void setNrc_no(String nrc_no) {
        this.nrc_no = nrc_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getLockTime() {
        return lockTime;
    }

    public void setLockTime(Timestamp lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockBy() {
        return lockBy;
    }

    public void setLockBy(String lockBy) {
        this.lockBy = lockBy;
    }

    public Boolean getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

}
