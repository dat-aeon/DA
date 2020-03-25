/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch;

import java.util.Date;
import java.util.List;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentDocumentErrorApplicationPurchaseInfoSearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7591551575539498312L;

    private Integer purchaseId;

    private String loanTypeId;

    private String agreementNo;

    private Date purchaseDate;

    private Integer outletId;

    private String outletName;

    private Integer agentId;

    private String agentName;

    private Integer purchaseStatus;

    private Double settlementAmount;

    private String invoiceNo;

    private List<AgentDocumentErrorApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList;

    private List<AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList;

    private AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto purchaseProductDto;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(Integer purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public Double getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Double settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public List<AgentDocumentErrorApplicationPurchaseAttachmentSearchResDto> getPurchaseAttachmentDtoList() {
        return purchaseAttachmentDtoList;
    }

    public void setPurchaseAttachmentDtoList(
            List<AgentDocumentErrorApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList) {
        this.purchaseAttachmentDtoList = purchaseAttachmentDtoList;
    }

    public List<AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto> getPurchaseProductDtoList() {
        return purchaseProductDtoList;
    }

    public AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto getPurchaseProductDto() {
        return purchaseProductDto;
    }

    public void setPurchaseProductDtoList(
            List<AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList) {
        this.purchaseProductDtoList = purchaseProductDtoList;
    }

    public void setPurchaseProductDto(AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto purchaseProductDto) {
        this.purchaseProductDto = purchaseProductDto;
    }

}
