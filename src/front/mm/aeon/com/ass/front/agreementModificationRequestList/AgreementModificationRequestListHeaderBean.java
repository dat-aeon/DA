/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationRequestList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class AgreementModificationRequestListHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5140501612125318475L;
    private Integer applicationId;

    private Boolean lockFlag;

    private Timestamp lockTime;

    private String lockBy;
    
    private String applicationNo;

    private String applicantName;

    private String applicantPhoneNo;

    private Date newApplicationDateFrom;

    private Date newApplicationDateTo;

    private String applicantNrcNo;
    
    

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Boolean getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
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

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantPhoneNo() {
        return applicantPhoneNo;
    }

    public void setApplicantPhoneNo(String applicantPhoneNo) {
        this.applicantPhoneNo = applicantPhoneNo;
    }

    public Date getNewApplicationDateFrom() {
        return newApplicationDateFrom;
    }

    public void setNewApplicationDateFrom(Date newApplicationDateFrom) {
        this.newApplicationDateFrom = newApplicationDateFrom;
    }

    public Date getNewApplicationDateTo() {
        return newApplicationDateTo;
    }

    public void setNewApplicationDateTo(Date newApplicationDateTo) {
        this.newApplicationDateTo = newApplicationDateTo;
    }

    public String getApplicantNrcNo() {
        return applicantNrcNo;
    }

    public void setApplicantNrcNo(String applicantNrcNo) {
        this.applicantNrcNo = applicantNrcNo;
    }

}
