/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.applicationInfoStatus;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class ApplicationInfoStatusUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -6516890605947986640L;
    private String applicationNo;
    private Integer judgementStatus;
    private Timestamp judgementDate;
    private Integer financialStatus;
    private Integer applicationStatus;
    private Double financialAmount;
    private Integer financialTerm;
    private Timestamp updatedTime;

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "AISU001";
    }

    public Integer getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getJudgementStatus() {
        return judgementStatus;
    }

    public void setJudgementStatus(Integer judgementStatus) {
        this.judgementStatus = judgementStatus;
    }

    public Timestamp getJudgementDate() {
        return judgementDate;
    }

    public void setJudgementDate(Timestamp judgementDate) {
        this.judgementDate = judgementDate;
    }

    public Integer getFinancialStatus() {
        return financialStatus;
    }

    public void setFinancialStatus(Integer financialStatus) {
        this.financialStatus = financialStatus;
    }

    public Double getFinancialAmount() {
        return financialAmount;
    }

    public Integer getFinancialTerm() {
        return financialTerm;
    }

    public void setFinancialAmount(Double financialAmount) {
        this.financialAmount = financialAmount;
    }

    public void setFinancialTerm(Integer financialTerm) {
        this.financialTerm = financialTerm;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
