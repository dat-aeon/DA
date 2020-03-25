/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentSaleClaimFinishedDetailReportSearch;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentSaleClaimFinishedDetailReportSearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5578964690693393044L;

    private Integer agentId;
    private String agentName;
    private Integer outletId;
    private String outletName;
    private String customerName;
    private String nrcNo;
    private String invoiceNo;
    private String agreementNo;
    private Double financeAmount;
    private Double processingFeeAmount;
    private Double compulsorySaving;
    private Double settlementAmount;
    private String staffName;
    private Timestamp saleClaimFinishedDate;
    private Double approvedAmount;
    private Integer approvedFinanceTerm;
    private Double approvedFinanceAmount;

    public Integer getAgentId() {
        return agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public Integer getOutletId() {
        return outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public Double getFinanceAmount() {
        return financeAmount;
    }

    public Double getProcessingFeeAmount() {
        return processingFeeAmount;
    }

    public Double getCompulsorySaving() {
        return compulsorySaving;
    }

    public Double getSettlementAmount() {
        return settlementAmount;
    }

    public String getStaffName() {
        return staffName;
    }

    public Timestamp getSaleClaimFinishedDate() {
        return saleClaimFinishedDate;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public void setFinanceAmount(Double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public void setProcessingFeeAmount(Double processingFeeAmount) {
        this.processingFeeAmount = processingFeeAmount;
    }

    public void setCompulsorySaving(Double compulsorySaving) {
        this.compulsorySaving = compulsorySaving;
    }

    public void setSettlementAmount(Double settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setSaleClaimFinishedDate(Timestamp saleClaimFinishedDate) {
        this.saleClaimFinishedDate = saleClaimFinishedDate;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Integer getApprovedFinanceTerm() {
        return approvedFinanceTerm;
    }

    public Double getApprovedFinanceAmount() {
        return approvedFinanceAmount;
    }

    public void setApprovedFinanceTerm(Integer approvedFinanceTerm) {
        this.approvedFinanceTerm = approvedFinanceTerm;
    }

    public void setApprovedFinanceAmount(Double approvedFinanceAmount) {
        this.approvedFinanceAmount = approvedFinanceAmount;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

}
