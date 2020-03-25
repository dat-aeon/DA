/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentAndProductInfoUploadedReportSearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentAndProductAppInfo implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5578964690693393044L;

    private String applicationNo;
    private Double financialAmt;
    private Integer financialTerm;
    private Double compulsoryAmount;
    private Integer approvedFinanceAmount;
    private Double approvedFinanceTerm;

    public String getApplicationNo() {
        return applicationNo;
    }

    public Double getFinancialAmt() {
        return financialAmt;
    }

    public Integer getFinancialTerm() {
        return financialTerm;
    }

    public Double getCompulsoryAmount() {
        return compulsoryAmount;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setFinancialAmt(Double financialAmt) {
        this.financialAmt = financialAmt;
    }

    public void setFinancialTerm(Integer financialTerm) {
        this.financialTerm = financialTerm;
    }

    public void setCompulsoryAmount(Double compulsoryAmount) {
        this.compulsoryAmount = compulsoryAmount;
    }

    public Integer getApprovedFinanceAmount() {
        return approvedFinanceAmount;
    }

    public Double getApprovedFinanceTerm() {
        return approvedFinanceTerm;
    }

    public void setApprovedFinanceAmount(Integer approvedFinanceAmount) {
        this.approvedFinanceAmount = approvedFinanceAmount;
    }

    public void setApprovedFinanceTerm(Double approvedFinanceTerm) {
        this.approvedFinanceTerm = approvedFinanceTerm;
    }

}
