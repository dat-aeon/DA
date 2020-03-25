/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationCancelReport;

import java.io.Serializable;

public class AgreementModificationCancelReportAppBean implements Serializable {

    /**
     * 
     */
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
