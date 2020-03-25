/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.uploadedApplicationList;

import java.io.Serializable;
import java.util.Date;

public class UploadedApplicationListHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5140501612125318475L;

    private Integer applicationId;

    private String applicationNo;

    private String applicantName;

    private String applicantPhoneNo;

    private Date newApplicationDateFrom;

    private Date newApplicationDateTo;

    private String applicantNrcNo;

    private Date uploadedApplicationFrom;

    private Date uploadedApplicationTo;

    private Date finalJudgementDateFrom;

    private Date finalJudgementDateTo;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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

    public Date getUploadedApplicationFrom() {
        return uploadedApplicationFrom;
    }

    public void setUploadedApplicationFrom(Date uploadedApplicationFrom) {
        this.uploadedApplicationFrom = uploadedApplicationFrom;
    }

    public Date getUploadedApplicationTo() {
        return uploadedApplicationTo;
    }

    public void setUploadedApplicationTo(Date uploadedApplicationTo) {
        this.uploadedApplicationTo = uploadedApplicationTo;
    }

    public Date getFinalJudgementDateFrom() {
        return finalJudgementDateFrom;
    }

    public void setFinalJudgementDateFrom(Date finalJudgementDateFrom) {
        this.finalJudgementDateFrom = finalJudgementDateFrom;
    }

    public Date getFinalJudgementDateTo() {
        return finalJudgementDateTo;
    }

    public void setFinalJudgementDateTo(Date finalJudgementDateTo) {
        this.finalJudgementDateTo = finalJudgementDateTo;
    }

}
