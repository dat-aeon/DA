/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.saleClaimApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class SaleClaimApplicationPurchaseProductInfoSearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7591551575539498312L;

    private Integer purchaseProductId;

    private Integer purchaseInfoId;

    private Integer daLoanTypeId;

    private String loanType;

    private Integer productTypeId;

    private String productType;

    private String productDescription;

    private String model;

    private String brand;

    private Double price;

    private Double cashDownAmount;

    public Integer getPurchaseProductId() {
        return purchaseProductId;
    }

    public Integer getPurchaseInfoId() {
        return purchaseInfoId;
    }

    public Integer getDaLoanTypeId() {
        return daLoanTypeId;
    }

    public String getLoanType() {
        return loanType;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPrice() {
        return price;
    }

    public Double getCashDownAmount() {
        return cashDownAmount;
    }

    public void setPurchaseProductId(Integer purchaseProductId) {
        this.purchaseProductId = purchaseProductId;
    }

    public void setPurchaseInfoId(Integer purchaseInfoId) {
        this.purchaseInfoId = purchaseInfoId;
    }

    public void setDaLoanTypeId(Integer daLoanTypeId) {
        this.daLoanTypeId = daLoanTypeId;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCashDownAmount(Double cashDownAmount) {
        this.cashDownAmount = cashDownAmount;
    }

}
