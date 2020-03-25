/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.saleEntryInfoListSearch;

import java.util.Date;
import java.util.List;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class SaleEntryInfoApplicationPurchaseInfoSearchResDto implements IResServiceDto {

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

    private List<SaleEntryInfoApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList;

    private List<SaleEntryInfoApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList;

    private SaleEntryInfoApplicationPurchaseProductInfoSearchResDto purchaseProductDto;

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

    public List<SaleEntryInfoApplicationPurchaseAttachmentSearchResDto> getPurchaseAttachmentDtoList() {
        return purchaseAttachmentDtoList;
    }

    public void setPurchaseAttachmentDtoList(
            List<SaleEntryInfoApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList) {
        this.purchaseAttachmentDtoList = purchaseAttachmentDtoList;
    }

    public List<SaleEntryInfoApplicationPurchaseProductInfoSearchResDto> getPurchaseProductDtoList() {
        return purchaseProductDtoList;
    }

    public SaleEntryInfoApplicationPurchaseProductInfoSearchResDto getPurchaseProductDto() {
        return purchaseProductDto;
    }

    public void setPurchaseProductDtoList(
            List<SaleEntryInfoApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList) {
        this.purchaseProductDtoList = purchaseProductDtoList;
    }

    public void setPurchaseProductDto(SaleEntryInfoApplicationPurchaseProductInfoSearchResDto purchaseProductDto) {
        this.purchaseProductDto = purchaseProductDto;
    }

}
