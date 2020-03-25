/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.productPurchaseListReport;

import java.io.Serializable;
import java.util.Date;

public class ProductPurchaseListReportLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3544484564890413577L;
    private String outletName;
    private Date settlementDate;
    private String agentName;
    private Date claimDate;
    private String agreementCode;
    private Double amount;
    private Double processingFees;
    private Double compulsorySaving;
    private Double settlementAmount;
    private Date date;

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getProcessingFees() {
        return processingFees;
    }

    public void setProcessingFees(Double processingFees) {
        this.processingFees = processingFees;
    }

    public Double getCompulsorySaving() {
        return compulsorySaving;
    }

    public void setCompulsorySaving(Double compulsorySaving) {
        this.compulsorySaving = compulsorySaving;
    }

    public Double getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Double settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
