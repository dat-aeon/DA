/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.applicationUpdateStatus;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ApplicationUpdateStatusSearchResBean implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1595535941963957635L;
    private String applicationNo;
    private Integer judgementStatus;
    private Timestamp judgementDate;
    private Integer financialStatus;
    private Integer applicationStatus;
    private Double financialAmount;
    private Integer financialTerm;
    private Timestamp updatedTime;

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

    public Integer getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
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
