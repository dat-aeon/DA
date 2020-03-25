/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.saleEntryInfoList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationApplicantCompanySearchResDto;
import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationEmergencyContactSearchResDto;
import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationGuarantorSearchResDto;
import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationPurchaseAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationPurchaseInfoSearchResDto;
import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationPurchaseProductInfoSearchResDto;

public class SaleEntryInfoListLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1373078522115084916L;

    private Integer applicationId;

    private String applicationNo;

    private Integer applicationTypeId;

    private Date appliedDate;

    private String applicantName;

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

    private Integer typeOfResident;

    private String typeOfResidentOther;

    private Integer livingWith;

    private String livingWithOther;

    private Integer stayYear;

    private Integer stayMonth;

    private String mobileNo;

    private String residentTelNo;

    private String otherPhoneNo;

    private String email;

    private Integer customerId;

    private Integer ApplicantCompanyId;

    private Integer emergencyContactId;

    private Integer guarantorId;

    private Integer Status;

    private Integer interestId;

    private Double interestRate;

    private Integer compulsoryId;

    private Double compulsoryAmount;

    private Integer loanTypeId;

    private Double financeAmount;

    private Integer financeTerm;

    private Integer productId;

    private String productDescription;

    private Integer channelType;

    private String createdBy;

    private String updatedBy;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    private Boolean delFlag;

    private Boolean saleEntryCheckFlag;

    private Integer highestEducationTypeId;

    private Double approvedAmount;

    private Double approvedFinanceAmount;

    private Integer approvedFinanceTerm;

    private Double modifyFinanceAmount;

    private Integer modifyFinanceTerm;

    private SaleEntryInfoApplicationApplicantCompanySearchResDto applicantCompanyDto;

    private SaleEntryInfoApplicationEmergencyContactSearchResDto emergencyContactDto;

    private SaleEntryInfoApplicationGuarantorSearchResDto guarantorDto;

    private List<SaleEntryInfoApplicationAttachmentSearchResDto> attachmentDtoList;

    private SaleEntryInfoApplicationPurchaseInfoSearchResDto purchaseDto;

    private List<SaleEntryInfoApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList;

    private SaleEntryInfoApplicationPurchaseProductInfoSearchResDto purchaseProductDto;

    private List<SaleEntryInfoApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList;

    private String pendingComment;

    private String nrcFrontImageFilePath;

    private String nrcBackImageFilePath;

    private String residentProofImageFilePath;

    private String incomeProofImageFilePath;

    private String guarantorNrcFrontImageFilePath;

    private String guarantorNrcBackImageFilePath;

    private String householdCriminalImageFilePath;

    private String applicantPhotoImageFilePath;

    private String signatureImageFilePath;

    private String memberCardImageFilePath;

    private String invoiceImageFilePath;

    private String uloanImageFilePath;

    private List<String> othersImageFilePath;

    private String letterOfAgreementImageFilePath;

    private String cashReceiptImageFilePath;

    private Double processingFees;
    private Double finanancialAmount;
    private Double totalInterest;
    private Double totalRepayment;
    private Double monthlyInstallment;
    private Double firstPaymentForPSG;
    private Double firstPayment;
    private Double lastPayment;
    private Double modifyTotalRepayment;

    private String settlement_comment;

    private Boolean lockFlag;

    private Timestamp lockTime;

    private String lockBy;

    private String lastRequestedStaffName;

    public Integer getApplicationId() {
        return applicationId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public Integer getApplicationTypeId() {
        return applicationTypeId;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public String getApplicantName() {
        return applicantName;
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

    public Integer getTypeOfResident() {
        return typeOfResident;
    }

    public String getTypeOfResidentOther() {
        return typeOfResidentOther;
    }

    public Integer getLivingWith() {
        return livingWith;
    }

    public String getLivingWithOther() {
        return livingWithOther;
    }

    public Integer getStayYear() {
        return stayYear;
    }

    public Integer getStayMonth() {
        return stayMonth;
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

    public Integer getApplicantCompanyId() {
        return ApplicantCompanyId;
    }

    public Integer getEmergencyContactId() {
        return emergencyContactId;
    }

    public Integer getGuarantorId() {
        return guarantorId;
    }

    public Integer getStatus() {
        return Status;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getCompulsoryId() {
        return compulsoryId;
    }

    public Double getCompulsoryAmount() {
        return compulsoryAmount;
    }

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public Double getFinanceAmount() {
        return financeAmount;
    }

    public Integer getFinanceTerm() {
        return financeTerm;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public SaleEntryInfoApplicationApplicantCompanySearchResDto getApplicantCompanyDto() {
        return applicantCompanyDto;
    }

    public SaleEntryInfoApplicationEmergencyContactSearchResDto getEmergencyContactDto() {
        return emergencyContactDto;
    }

    public SaleEntryInfoApplicationGuarantorSearchResDto getGuarantorDto() {
        return guarantorDto;
    }

    public List<SaleEntryInfoApplicationAttachmentSearchResDto> getAttachmentDtoList() {
        return attachmentDtoList;
    }

    public SaleEntryInfoApplicationPurchaseInfoSearchResDto getPurchaseDto() {
        return purchaseDto;
    }

    public List<SaleEntryInfoApplicationPurchaseAttachmentSearchResDto> getPurchaseAttachmentDtoList() {
        return purchaseAttachmentDtoList;
    }

    public SaleEntryInfoApplicationPurchaseProductInfoSearchResDto getPurchaseProductDto() {
        return purchaseProductDto;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public String getNrcFrontImageFilePath() {
        return nrcFrontImageFilePath;
    }

    public String getNrcBackImageFilePath() {
        return nrcBackImageFilePath;
    }

    public String getResidentProofImageFilePath() {
        return residentProofImageFilePath;
    }

    public String getIncomeProofImageFilePath() {
        return incomeProofImageFilePath;
    }

    public String getGuarantorNrcFrontImageFilePath() {
        return guarantorNrcFrontImageFilePath;
    }

    public String getGuarantorNrcBackImageFilePath() {
        return guarantorNrcBackImageFilePath;
    }

    public String getHouseholdCriminalImageFilePath() {
        return householdCriminalImageFilePath;
    }

    public String getApplicantPhotoImageFilePath() {
        return applicantPhotoImageFilePath;
    }

    public String getSignatureImageFilePath() {
        return signatureImageFilePath;
    }

    public String getMemberCardImageFilePath() {
        return memberCardImageFilePath;
    }

    public String getInvoiceImageFilePath() {
        return invoiceImageFilePath;
    }

    public String getUloanImageFilePath() {
        return uloanImageFilePath;
    }

    public List<String> getOthersImageFilePath() {
        return othersImageFilePath;
    }

    public Double getProcessingFees() {
        return processingFees;
    }

    public Double getFinanancialAmount() {
        return finanancialAmount;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }

    public Double getTotalRepayment() {
        return totalRepayment;
    }

    public Double getMonthlyInstallment() {
        return monthlyInstallment;
    }

    public Double getFirstPaymentForPSG() {
        return firstPaymentForPSG;
    }

    public Double getFirstPayment() {
        return firstPayment;
    }

    public Double getLastPayment() {
        return lastPayment;
    }

    public Double getModifyTotalRepayment() {
        return modifyTotalRepayment;
    }

    public String getSettlement_comment() {
        return settlement_comment;
    }

    public Boolean getLockFlag() {
        return lockFlag;
    }

    public Timestamp getLockTime() {
        return lockTime;
    }

    public String getLockBy() {
        return lockBy;
    }

    public String getLastRequestedStaffName() {
        return lastRequestedStaffName;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setApplicationTypeId(Integer applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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

    public void setTypeOfResident(Integer typeOfResident) {
        this.typeOfResident = typeOfResident;
    }

    public void setTypeOfResidentOther(String typeOfResidentOther) {
        this.typeOfResidentOther = typeOfResidentOther;
    }

    public void setLivingWith(Integer livingWith) {
        this.livingWith = livingWith;
    }

    public void setLivingWithOther(String livingWithOther) {
        this.livingWithOther = livingWithOther;
    }

    public void setStayYear(Integer stayYear) {
        this.stayYear = stayYear;
    }

    public void setStayMonth(Integer stayMonth) {
        this.stayMonth = stayMonth;
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

    public void setApplicantCompanyId(Integer applicantCompanyId) {
        ApplicantCompanyId = applicantCompanyId;
    }

    public void setEmergencyContactId(Integer emergencyContactId) {
        this.emergencyContactId = emergencyContactId;
    }

    public void setGuarantorId(Integer guarantorId) {
        this.guarantorId = guarantorId;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void setCompulsoryId(Integer compulsoryId) {
        this.compulsoryId = compulsoryId;
    }

    public void setCompulsoryAmount(Double compulsoryAmount) {
        this.compulsoryAmount = compulsoryAmount;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public void setFinanceAmount(Double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public void setFinanceTerm(Integer financeTerm) {
        this.financeTerm = financeTerm;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void setApplicantCompanyDto(SaleEntryInfoApplicationApplicantCompanySearchResDto applicantCompanyDto) {
        this.applicantCompanyDto = applicantCompanyDto;
    }

    public void setEmergencyContactDto(SaleEntryInfoApplicationEmergencyContactSearchResDto emergencyContactDto) {
        this.emergencyContactDto = emergencyContactDto;
    }

    public void setGuarantorDto(SaleEntryInfoApplicationGuarantorSearchResDto guarantorDto) {
        this.guarantorDto = guarantorDto;
    }

    public void setAttachmentDtoList(List<SaleEntryInfoApplicationAttachmentSearchResDto> attachmentDtoList) {
        this.attachmentDtoList = attachmentDtoList;
    }

    public void setPurchaseDto(SaleEntryInfoApplicationPurchaseInfoSearchResDto purchaseDto) {
        this.purchaseDto = purchaseDto;
    }

    public void setPurchaseAttachmentDtoList(
            List<SaleEntryInfoApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList) {
        this.purchaseAttachmentDtoList = purchaseAttachmentDtoList;
    }

    public void setPurchaseProductDto(SaleEntryInfoApplicationPurchaseProductInfoSearchResDto purchaseProductDto) {
        this.purchaseProductDto = purchaseProductDto;
    }

    public List<SaleEntryInfoApplicationPurchaseProductInfoSearchResDto> getPurchaseProductDtoList() {
        return purchaseProductDtoList;
    }

    public void setPurchaseProductDtoList(
            List<SaleEntryInfoApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList) {
        this.purchaseProductDtoList = purchaseProductDtoList;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

    public void setNrcFrontImageFilePath(String nrcFrontImageFilePath) {
        this.nrcFrontImageFilePath = nrcFrontImageFilePath;
    }

    public void setNrcBackImageFilePath(String nrcBackImageFilePath) {
        this.nrcBackImageFilePath = nrcBackImageFilePath;
    }

    public void setResidentProofImageFilePath(String residentProofImageFilePath) {
        this.residentProofImageFilePath = residentProofImageFilePath;
    }

    public void setIncomeProofImageFilePath(String incomeProofImageFilePath) {
        this.incomeProofImageFilePath = incomeProofImageFilePath;
    }

    public void setGuarantorNrcFrontImageFilePath(String guarantorNrcFrontImageFilePath) {
        this.guarantorNrcFrontImageFilePath = guarantorNrcFrontImageFilePath;
    }

    public void setGuarantorNrcBackImageFilePath(String guarantorNrcBackImageFilePath) {
        this.guarantorNrcBackImageFilePath = guarantorNrcBackImageFilePath;
    }

    public void setHouseholdCriminalImageFilePath(String householdCriminalImageFilePath) {
        this.householdCriminalImageFilePath = householdCriminalImageFilePath;
    }

    public void setApplicantPhotoImageFilePath(String applicantPhotoImageFilePath) {
        this.applicantPhotoImageFilePath = applicantPhotoImageFilePath;
    }

    public void setSignatureImageFilePath(String signatureImageFilePath) {
        this.signatureImageFilePath = signatureImageFilePath;
    }

    public void setMemberCardImageFilePath(String memberCardImageFilePath) {
        this.memberCardImageFilePath = memberCardImageFilePath;
    }

    public void setInvoiceImageFilePath(String invoiceImageFilePath) {
        this.invoiceImageFilePath = invoiceImageFilePath;
    }

    public void setUloanImageFilePath(String uloanImageFilePath) {
        this.uloanImageFilePath = uloanImageFilePath;
    }

    public void setOthersImageFilePath(List<String> othersImageFilePath) {
        this.othersImageFilePath = othersImageFilePath;
    }

    public void setProcessingFees(Double processingFees) {
        this.processingFees = processingFees;
    }

    public void setFinanancialAmount(Double finanancialAmount) {
        this.finanancialAmount = finanancialAmount;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public void setTotalRepayment(Double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public void setMonthlyInstallment(Double monthlyInstallment) {
        this.monthlyInstallment = monthlyInstallment;
    }

    public void setFirstPaymentForPSG(Double firstPaymentForPSG) {
        this.firstPaymentForPSG = firstPaymentForPSG;
    }

    public void setFirstPayment(Double firstPayment) {
        this.firstPayment = firstPayment;
    }

    public void setLastPayment(Double lastPayment) {
        this.lastPayment = lastPayment;
    }

    public void setModifyTotalRepayment(Double modifyTotalRepayment) {
        this.modifyTotalRepayment = modifyTotalRepayment;
    }

    public void setSettlement_comment(String settlement_comment) {
        this.settlement_comment = settlement_comment;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

    public void setLockTime(Timestamp lockTime) {
        this.lockTime = lockTime;
    }

    public void setLockBy(String lockBy) {
        this.lockBy = lockBy;
    }

    public void setLastRequestedStaffName(String lastRequestedStaffName) {
        this.lastRequestedStaffName = lastRequestedStaffName;
    }

    public Boolean getSaleEntryCheckFlag() {
        return saleEntryCheckFlag;
    }

    public void setSaleEntryCheckFlag(Boolean saleEntryCheckFlag) {
        this.saleEntryCheckFlag = saleEntryCheckFlag;
    }

    public Integer getHighestEducationTypeId() {
        return highestEducationTypeId;
    }

    public void setHighestEducationTypeId(Integer highestEducationTypeId) {
        this.highestEducationTypeId = highestEducationTypeId;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getLetterOfAgreementImageFilePath() {
        return letterOfAgreementImageFilePath;
    }

    public String getCashReceiptImageFilePath() {
        return cashReceiptImageFilePath;
    }

    public void setLetterOfAgreementImageFilePath(String letterOfAgreementImageFilePath) {
        this.letterOfAgreementImageFilePath = letterOfAgreementImageFilePath;
    }

    public void setCashReceiptImageFilePath(String cashReceiptImageFilePath) {
        this.cashReceiptImageFilePath = cashReceiptImageFilePath;
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
}
