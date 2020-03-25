/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.uploadedApplicationListSearch;

import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "UploadedApplicationInfo")
public class UploadedApplicationSelectCountReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5376898487674767104L;

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

    @Override
    public DaoType getDaoType() {
        return DaoType.COUNT;
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
