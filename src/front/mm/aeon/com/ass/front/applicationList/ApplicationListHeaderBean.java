/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.applicationList;

import java.io.Serializable;
import java.util.Date;

public class ApplicationListHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5140501612125318475L;

    private String applicationNo;

    private Integer status;

    private String applicantName;

    private String applicantPhoneNo;

    private Date newApplicationDateFrom;

    private Date newApplicationDateTo;

    private String applicantNrcNo;

    private Integer finalJudgementStatus;

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getFinalJudgementStatus() {
        return finalJudgementStatus;
    }

    public void setFinalJudgementStatus(Integer finalJudgementStatus) {
        this.finalJudgementStatus = finalJudgementStatus;
    }

}
