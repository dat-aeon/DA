/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentSaleClaimFinishedDetailReportSearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentSaleClaimFinishedDetailAppInfo implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5578964690693393044L;

    private String applicationNo;
    private Double financialAmt;
    private Integer financialTerm;
    private Double compulsoryAmount;

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

}
