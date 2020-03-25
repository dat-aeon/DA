/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.commonApplicationInsertService;

import java.sql.Timestamp;
import java.util.Date;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class CommonApplicationHistoryInsertServiceReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = 7103688745860947296L;

    private Integer daApplicationInfoId;

    private String applicationNo;

    private Integer daApplicationTypeId;

    private Date appliedDate;

    private String name;

    private Date dob;

    private String nrcNo;

    private String fatherName;

    private Integer nationality;

    private String nationalityOther;

    private Integer gender;

    private Integer maritalStatus;

    private String currentAddress;

    private String currentAddressBuildingNo;

    private String currentAddressRoomNo;

    private String currentAddressFloor;

    private String currentAddressStreet;

    private String currentAddressQtr;

    private Integer currentAddressTownship;

    private Integer currentAddressCity;

    private String permanentAddress;

    private String permanentAddressBuildingNo;

    private String permanentAddressRoomNo;

    private String permanentAddressFloor;

    private String permanentAddressStreet;

    private String permanentAddressQtr;

    private Integer permanentAddressTownship;

    private Integer permanentAddressCity;

    private Integer typeOfResidence;

    private String typeOfResidenceOther;

    private Integer livingWith;

    private String livingWithOther;

    private Integer yearOfStayYear;

    private Integer yearOfStayMonth;

    private String mobileNo;

    private String residentTelNo;

    private String otherPhoneNo;

    private String email;

    private Integer customerId;

    private Integer status;

    private Integer daInterestInfoId;

    private Integer daCompulsoryInfoId;

    private Integer daLoanTypeId;

    private Double financeAmount;

    private Integer financeTerm;

    private Integer daProductTypeId;

    private String productDescription;

    private Integer channelType;

    private Timestamp createdTime;

    private String createdBy;

    private Timestamp updatedTime;

    private String updatedBy;

    private Boolean delFlag;

    private String settlementPendingComment;

    private Integer highestEducationTypeId;

    private Double approvedFinanceAmount;

    private Integer approvedFinanceTerm;

    private Double modifyFinanceAmount;

    private Integer modifyFinanceTerm;

    private Integer daApplicationInfoHistoryId;

    private String modifyComment;

    private Integer modifyStatus;

    public Integer getDaApplicationInfoId() {
        return daApplicationInfoId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public Integer getDaApplicationTypeId() {
        return daApplicationTypeId;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Integer getNationality() {
        return nationality;
    }

    public String getNationalityOther() {
        return nationalityOther;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getCurrentAddressBuildingNo() {
        return currentAddressBuildingNo;
    }

    public String getCurrentAddressRoomNo() {
        return currentAddressRoomNo;
    }

    public String getCurrentAddressFloor() {
        return currentAddressFloor;
    }

    public String getCurrentAddressStreet() {
        return currentAddressStreet;
    }

    public String getCurrentAddressQtr() {
        return currentAddressQtr;
    }

    public Integer getCurrentAddressTownship() {
        return currentAddressTownship;
    }

    public Integer getCurrentAddressCity() {
        return currentAddressCity;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getPermanentAddressBuildingNo() {
        return permanentAddressBuildingNo;
    }

    public String getPermanentAddressRoomNo() {
        return permanentAddressRoomNo;
    }

    public String getPermanentAddressFloor() {
        return permanentAddressFloor;
    }

    public String getPermanentAddressStreet() {
        return permanentAddressStreet;
    }

    public String getPermanentAddressQtr() {
        return permanentAddressQtr;
    }

    public Integer getPermanentAddressTownship() {
        return permanentAddressTownship;
    }

    public Integer getPermanentAddressCity() {
        return permanentAddressCity;
    }

    public Integer getTypeOfResidence() {
        return typeOfResidence;
    }

    public String getTypeOfResidenceOther() {
        return typeOfResidenceOther;
    }

    public Integer getLivingWith() {
        return livingWith;
    }

    public String getLivingWithOther() {
        return livingWithOther;
    }

    public Integer getYearOfStayYear() {
        return yearOfStayYear;
    }

    public Integer getYearOfStayMonth() {
        return yearOfStayMonth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getResidentTelNo() {
        return residentTelNo;
    }

    public String getOtherPhoneNo() {
        return otherPhoneNo;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getDaInterestInfoId() {
        return daInterestInfoId;
    }

    public Integer getDaCompulsoryInfoId() {
        return daCompulsoryInfoId;
    }

    public Integer getDaLoanTypeId() {
        return daLoanTypeId;
    }

    public Double getFinanceAmount() {
        return financeAmount;
    }

    public Integer getFinanceTerm() {
        return financeTerm;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public String getSettlementPendingComment() {
        return settlementPendingComment;
    }

    public Integer getHighestEducationTypeId() {
        return highestEducationTypeId;
    }

    public Double getApprovedFinanceAmount() {
        return approvedFinanceAmount;
    }

    public Integer getApprovedFinanceTerm() {
        return approvedFinanceTerm;
    }

    public Double getModifyFinanceAmount() {
        return modifyFinanceAmount;
    }

    public Integer getModifyFinanceTerm() {
        return modifyFinanceTerm;
    }

    public Integer getDaApplicationInfoHistoryId() {
        return daApplicationInfoHistoryId;
    }

    public String getModifyComment() {
        return modifyComment;
    }

    public void setDaApplicationInfoId(Integer daApplicationInfoId) {
        this.daApplicationInfoId = daApplicationInfoId;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setDaApplicationTypeId(Integer daApplicationTypeId) {
        this.daApplicationTypeId = daApplicationTypeId;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public void setNationalityOther(String nationalityOther) {
        this.nationalityOther = nationalityOther;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public void setCurrentAddressBuildingNo(String currentAddressBuildingNo) {
        this.currentAddressBuildingNo = currentAddressBuildingNo;
    }

    public void setCurrentAddressRoomNo(String currentAddressRoomNo) {
        this.currentAddressRoomNo = currentAddressRoomNo;
    }

    public void setCurrentAddressFloor(String currentAddressFloor) {
        this.currentAddressFloor = currentAddressFloor;
    }

    public void setCurrentAddressStreet(String currentAddressStreet) {
        this.currentAddressStreet = currentAddressStreet;
    }

    public void setCurrentAddressQtr(String currentAddressQtr) {
        this.currentAddressQtr = currentAddressQtr;
    }

    public void setCurrentAddressTownship(Integer currentAddressTownship) {
        this.currentAddressTownship = currentAddressTownship;
    }

    public void setCurrentAddressCity(Integer currentAddressCity) {
        this.currentAddressCity = currentAddressCity;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public void setPermanentAddressBuildingNo(String permanentAddressBuildingNo) {
        this.permanentAddressBuildingNo = permanentAddressBuildingNo;
    }

    public void setPermanentAddressRoomNo(String permanentAddressRoomNo) {
        this.permanentAddressRoomNo = permanentAddressRoomNo;
    }

    public void setPermanentAddressFloor(String permanentAddressFloor) {
        this.permanentAddressFloor = permanentAddressFloor;
    }

    public void setPermanentAddressStreet(String permanentAddressStreet) {
        this.permanentAddressStreet = permanentAddressStreet;
    }

    public void setPermanentAddressQtr(String permanentAddressQtr) {
        this.permanentAddressQtr = permanentAddressQtr;
    }

    public void setPermanentAddressTownship(Integer permanentAddressTownship) {
        this.permanentAddressTownship = permanentAddressTownship;
    }

    public void setPermanentAddressCity(Integer permanentAddressCity) {
        this.permanentAddressCity = permanentAddressCity;
    }

    public void setTypeOfResidence(Integer typeOfResidence) {
        this.typeOfResidence = typeOfResidence;
    }

    public void setTypeOfResidenceOther(String typeOfResidenceOther) {
        this.typeOfResidenceOther = typeOfResidenceOther;
    }

    public void setLivingWith(Integer livingWith) {
        this.livingWith = livingWith;
    }

    public void setLivingWithOther(String livingWithOther) {
        this.livingWithOther = livingWithOther;
    }

    public void setYearOfStayYear(Integer yearOfStayYear) {
        this.yearOfStayYear = yearOfStayYear;
    }

    public void setYearOfStayMonth(Integer yearOfStayMonth) {
        this.yearOfStayMonth = yearOfStayMonth;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setResidentTelNo(String residentTelNo) {
        this.residentTelNo = residentTelNo;
    }

    public void setOtherPhoneNo(String otherPhoneNo) {
        this.otherPhoneNo = otherPhoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDaInterestInfoId(Integer daInterestInfoId) {
        this.daInterestInfoId = daInterestInfoId;
    }

    public void setDaCompulsoryInfoId(Integer daCompulsoryInfoId) {
        this.daCompulsoryInfoId = daCompulsoryInfoId;
    }

    public void setDaLoanTypeId(Integer daLoanTypeId) {
        this.daLoanTypeId = daLoanTypeId;
    }

    public void setFinanceAmount(Double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public void setFinanceTerm(Integer financeTerm) {
        this.financeTerm = financeTerm;
    }

    public Integer getDaProductTypeId() {
        return daProductTypeId;
    }

    public void setDaProductTypeId(Integer daProductTypeId) {
        this.daProductTypeId = daProductTypeId;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void setSettlementPendingComment(String settlementPendingComment) {
        this.settlementPendingComment = settlementPendingComment;
    }

    public void setHighestEducationTypeId(Integer highestEducationTypeId) {
        this.highestEducationTypeId = highestEducationTypeId;
    }

    public void setApprovedFinanceAmount(Double approvedFinanceAmount) {
        this.approvedFinanceAmount = approvedFinanceAmount;
    }

    public void setApprovedFinanceTerm(Integer approvedFinanceTerm) {
        this.approvedFinanceTerm = approvedFinanceTerm;
    }

    public void setModifyFinanceAmount(Double modifyFinanceAmount) {
        this.modifyFinanceAmount = modifyFinanceAmount;
    }

    public void setModifyFinanceTerm(Integer modifyFinanceTerm) {
        this.modifyFinanceTerm = modifyFinanceTerm;
    }

    public void setDaApplicationInfoHistoryId(Integer daApplicationInfoHistoryId) {
        this.daApplicationInfoHistoryId = daApplicationInfoHistoryId;
    }

    public void setModifyComment(String modifyComment) {
        this.modifyComment = modifyComment;
    }

    public Integer getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(Integer modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "HISTORYI";
    }
}
