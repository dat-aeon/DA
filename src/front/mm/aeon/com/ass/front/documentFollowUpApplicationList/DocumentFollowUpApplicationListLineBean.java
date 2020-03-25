/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.documentFollowUpApplicationList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.DocumentFollowUpApplicationApplicantCompanySearchResDto;
import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.DocumentFollowUpApplicationAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.DocumentFollowUpApplicationEmergencyContactSearchResDto;
import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.DocumentFollowUpApplicationGuarantorSearchResDto;

public class DocumentFollowUpApplicationListLineBean implements Serializable {

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

    private Integer status;

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

    private Integer highestEducationTypeId;

    private String createdBy;

    private String updatedBy;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    private Boolean delFlag;

    private DocumentFollowUpApplicationApplicantCompanySearchResDto applicantCompanyDto;

    private DocumentFollowUpApplicationEmergencyContactSearchResDto emergencyContactDto;

    private DocumentFollowUpApplicationGuarantorSearchResDto guarantorDto;

    private List<DocumentFollowUpApplicationAttachmentSearchResDto> attachmentDtoList;

    private String nrcFrontImageFilePath;

    private String nrcBackImageFilePath;

    private String residentProofImageFilePath;

    private String incomeProofImageFilePath;

    private String guarantorNrcFrontImageFilePath;

    private String guarantorNrcBackImageFilePath;

    private String householdCriminalImageFilePath;

    private String applicantPhotoImageFilePath;

    private String signatureImageFilePath;

    private String otherImageFilePath;

    private String guarantorSignatureImageFilePath;

    private Double processingFees;
    private Double financialAmount;
    private Double totalInterest;
    private Double totalRepayment;
    private Double monthlyInstallment;
    private Double firstPaymentForPSG;
    private Double firstPayment;
    private Double lastPayment;
    private Double modifyTotalRepayment;

    private String settlmentComment;

    private Boolean lockFlag;

    private Timestamp lockTime;

    private String lockBy;

    private String lastDocumentRequestedStaffName;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getApplicationTypeId() {
        return applicationTypeId;
    }

    public void setApplicationTypeId(Integer applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public String getNationalityOther() {
        return nationalityOther;
    }

    public void setNationalityOther(String nationalityOther) {
        this.nationalityOther = nationalityOther;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getSettlmentComment() {
        return settlmentComment;
    }

    public void setSettlmentComment(String settlmentComment) {
        this.settlmentComment = settlmentComment;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Integer getTypeOfResident() {
        return typeOfResident;
    }

    public void setTypeOfResident(Integer typeOfResident) {
        this.typeOfResident = typeOfResident;
    }

    public String getTypeOfResidentOther() {
        return typeOfResidentOther;
    }

    public void setTypeOfResidentOther(String typeOfResidentOther) {
        this.typeOfResidentOther = typeOfResidentOther;
    }

    public Integer getLivingWith() {
        return livingWith;
    }

    public void setLivingWith(Integer livingWith) {
        this.livingWith = livingWith;
    }

    public String getLivingWithOther() {
        return livingWithOther;
    }

    public void setLivingWithOther(String livingWithOther) {
        this.livingWithOther = livingWithOther;
    }

    public Integer getStayYear() {
        return stayYear;
    }

    public void setStayYear(Integer stayYear) {
        this.stayYear = stayYear;
    }

    public Integer getStayMonth() {
        return stayMonth;
    }

    public void setStayMonth(Integer stayMonth) {
        this.stayMonth = stayMonth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getResidentTelNo() {
        return residentTelNo;
    }

    public void setResidentTelNo(String residentTelNo) {
        this.residentTelNo = residentTelNo;
    }

    public String getOtherPhoneNo() {
        return otherPhoneNo;
    }

    public void setOtherPhoneNo(String otherPhoneNo) {
        this.otherPhoneNo = otherPhoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getApplicantCompanyId() {
        return ApplicantCompanyId;
    }

    public void setApplicantCompanyId(Integer applicantCompanyId) {
        ApplicantCompanyId = applicantCompanyId;
    }

    public Integer getEmergencyContactId() {
        return emergencyContactId;
    }

    public void setEmergencyContactId(Integer emergencyContactId) {
        this.emergencyContactId = emergencyContactId;
    }

    public Integer getGuarantorId() {
        return guarantorId;
    }

    public void setGuarantorId(Integer guarantorId) {
        this.guarantorId = guarantorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getCompulsoryId() {
        return compulsoryId;
    }

    public void setCompulsoryId(Integer compulsoryId) {
        this.compulsoryId = compulsoryId;
    }

    public Double getCompulsoryAmount() {
        return compulsoryAmount;
    }

    public void setCompulsoryAmount(Double compulsoryAmount) {
        this.compulsoryAmount = compulsoryAmount;
    }

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public Double getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(Double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public Integer getFinanceTerm() {
        return financeTerm;
    }

    public void setFinanceTerm(Integer financeTerm) {
        this.financeTerm = financeTerm;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public DocumentFollowUpApplicationApplicantCompanySearchResDto getApplicantCompanyDto() {
        return applicantCompanyDto;
    }

    public void setApplicantCompanyDto(DocumentFollowUpApplicationApplicantCompanySearchResDto applicantCompanyDto) {
        this.applicantCompanyDto = applicantCompanyDto;
    }

    public DocumentFollowUpApplicationEmergencyContactSearchResDto getEmergencyContactDto() {
        return emergencyContactDto;
    }

    public void setEmergencyContactDto(DocumentFollowUpApplicationEmergencyContactSearchResDto emergencyContactDto) {
        this.emergencyContactDto = emergencyContactDto;
    }

    public DocumentFollowUpApplicationGuarantorSearchResDto getGuarantorDto() {
        return guarantorDto;
    }

    public void setGuarantorDto(DocumentFollowUpApplicationGuarantorSearchResDto guarantorDto) {
        this.guarantorDto = guarantorDto;
    }

    public List<DocumentFollowUpApplicationAttachmentSearchResDto> getAttachmentDtoList() {
        return attachmentDtoList;
    }

    public void setAttachmentDtoList(List<DocumentFollowUpApplicationAttachmentSearchResDto> attachmentDtoList) {
        this.attachmentDtoList = attachmentDtoList;
    }

    public String getNrcFrontImageFilePath() {
        return nrcFrontImageFilePath;
    }

    public void setNrcFrontImageFilePath(String nrcFrontImageFilePath) {
        this.nrcFrontImageFilePath = nrcFrontImageFilePath;
    }

    public String getNrcBackImageFilePath() {
        return nrcBackImageFilePath;
    }

    public void setNrcBackImageFilePath(String nrcBackImageFilePath) {
        this.nrcBackImageFilePath = nrcBackImageFilePath;
    }

    public String getResidentProofImageFilePath() {
        return residentProofImageFilePath;
    }

    public void setResidentProofImageFilePath(String residentProofImageFilePath) {
        this.residentProofImageFilePath = residentProofImageFilePath;
    }

    public String getIncomeProofImageFilePath() {
        return incomeProofImageFilePath;
    }

    public void setIncomeProofImageFilePath(String incomeProofImageFilePath) {
        this.incomeProofImageFilePath = incomeProofImageFilePath;
    }

    public String getGuarantorNrcFrontImageFilePath() {
        return guarantorNrcFrontImageFilePath;
    }

    public void setGuarantorNrcFrontImageFilePath(String guarantorNrcFrontImageFilePath) {
        this.guarantorNrcFrontImageFilePath = guarantorNrcFrontImageFilePath;
    }

    public String getGuarantorNrcBackImageFilePath() {
        return guarantorNrcBackImageFilePath;
    }

    public void setGuarantorNrcBackImageFilePath(String guarantorNrcBackImageFilePath) {
        this.guarantorNrcBackImageFilePath = guarantorNrcBackImageFilePath;
    }

    public String getHouseholdCriminalImageFilePath() {
        return householdCriminalImageFilePath;
    }

    public void setHouseholdCriminalImageFilePath(String householdCriminalImageFilePath) {
        this.householdCriminalImageFilePath = householdCriminalImageFilePath;
    }

    public String getApplicantPhotoImageFilePath() {
        return applicantPhotoImageFilePath;
    }

    public void setApplicantPhotoImageFilePath(String applicantPhotoImageFilePath) {
        this.applicantPhotoImageFilePath = applicantPhotoImageFilePath;
    }

    public String getSignatureImageFilePath() {
        return signatureImageFilePath;
    }

    public void setSignatureImageFilePath(String signatureImageFilePath) {
        this.signatureImageFilePath = signatureImageFilePath;
    }

    public Double getProcessingFees() {
        return processingFees;
    }

    public void setProcessingFees(Double processingFees) {
        this.processingFees = processingFees;
    }

    public Double getFinancialAmount() {
        return financialAmount;
    }

    public void setFinancialAmount(Double financialAmount) {
        this.financialAmount = financialAmount;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Double getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(Double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public Double getMonthlyInstallment() {
        return monthlyInstallment;
    }

    public void setMonthlyInstallment(Double monthlyInstallment) {
        this.monthlyInstallment = monthlyInstallment;
    }

    public Double getFirstPaymentForPSG() {
        return firstPaymentForPSG;
    }

    public void setFirstPaymentForPSG(Double firstPaymentForPSG) {
        this.firstPaymentForPSG = firstPaymentForPSG;
    }

    public Double getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(Double firstPayment) {
        this.firstPayment = firstPayment;
    }

    public Double getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(Double lastPayment) {
        this.lastPayment = lastPayment;
    }

    public Double getModifyTotalRepayment() {
        return modifyTotalRepayment;
    }

    public void setModifyTotalRepayment(Double modifyTotalRepayment) {
        this.modifyTotalRepayment = modifyTotalRepayment;
    }

    public String getLastDocumentRequestedStaffName() {
        return lastDocumentRequestedStaffName;
    }

    public void setLastDocumentRequestedStaffName(String lastDocumentRequestedStaffName) {
        this.lastDocumentRequestedStaffName = lastDocumentRequestedStaffName;
    }

    public String getCurrentAddressBuildingNo() {
        return currentAddressBuildingNo;
    }

    public void setCurrentAddressBuildingNo(String currentAddressBuildingNo) {
        this.currentAddressBuildingNo = currentAddressBuildingNo;
    }

    public String getCurrentAddressRoomNo() {
        return currentAddressRoomNo;
    }

    public void setCurrentAddressRoomNo(String currentAddressRoomNo) {
        this.currentAddressRoomNo = currentAddressRoomNo;
    }

    public String getCurrentAddressFloor() {
        return currentAddressFloor;
    }

    public void setCurrentAddressFloor(String currentAddressFloor) {
        this.currentAddressFloor = currentAddressFloor;
    }

    public String getCurrentAddressStreet() {
        return currentAddressStreet;
    }

    public void setCurrentAddressStreet(String currentAddressStreet) {
        this.currentAddressStreet = currentAddressStreet;
    }

    public String getCurrentAddressQtr() {
        return currentAddressQtr;
    }

    public void setCurrentAddressQtr(String currentAddressQtr) {
        this.currentAddressQtr = currentAddressQtr;
    }

    public String getPermanentAddressBuildingNo() {
        return permanentAddressBuildingNo;
    }

    public void setPermanentAddressBuildingNo(String permanentAddressBuildingNo) {
        this.permanentAddressBuildingNo = permanentAddressBuildingNo;
    }

    public String getPermanentAddressRoomNo() {
        return permanentAddressRoomNo;
    }

    public void setPermanentAddressRoomNo(String permanentAddressRoomNo) {
        this.permanentAddressRoomNo = permanentAddressRoomNo;
    }

    public String getPermanentAddressFloor() {
        return permanentAddressFloor;
    }

    public void setPermanentAddressFloor(String permanentAddressFloor) {
        this.permanentAddressFloor = permanentAddressFloor;
    }

    public String getPermanentAddressStreet() {
        return permanentAddressStreet;
    }

    public void setPermanentAddressStreet(String permanentAddressStreet) {
        this.permanentAddressStreet = permanentAddressStreet;
    }

    public String getPermanentAddressQtr() {
        return permanentAddressQtr;
    }

    public void setPermanentAddressQtr(String permanentAddressQtr) {
        this.permanentAddressQtr = permanentAddressQtr;
    }

    public Integer getCurrentAddressTownship() {
        return currentAddressTownship;
    }

    public void setCurrentAddressTownship(Integer currentAddressTownship) {
        this.currentAddressTownship = currentAddressTownship;
    }

    public Integer getCurrentAddressCity() {
        return currentAddressCity;
    }

    public void setCurrentAddressCity(Integer currentAddressCity) {
        this.currentAddressCity = currentAddressCity;
    }

    public Integer getPermanentAddressTownship() {
        return permanentAddressTownship;
    }

    public void setPermanentAddressTownship(Integer permanentAddressTownship) {
        this.permanentAddressTownship = permanentAddressTownship;
    }

    public Integer getPermanentAddressCity() {
        return permanentAddressCity;
    }

    public void setPermanentAddressCity(Integer permanentAddressCity) {
        this.permanentAddressCity = permanentAddressCity;
    }

    public Boolean getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

    public Timestamp getLockTime() {
        return lockTime;
    }

    public void setLockTime(Timestamp lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockBy() {
        return lockBy;
    }

    public void setLockBy(String lockBy) {
        this.lockBy = lockBy;
    }

    public Integer getHighestEducationTypeId() {
        return highestEducationTypeId;
    }

    public void setHighestEducationTypeId(Integer highestEducationTypeId) {
        this.highestEducationTypeId = highestEducationTypeId;
    }

    public String getOtherImageFilePath() {
        return otherImageFilePath;
    }

    public String getGuarantorSignatureImageFilePath() {
        return guarantorSignatureImageFilePath;
    }

    public void setOtherImageFilePath(String otherImageFilePath) {
        this.otherImageFilePath = otherImageFilePath;
    }

    public void setGuarantorSignatureImageFilePath(String guarantorSignatureImageFilePath) {
        this.guarantorSignatureImageFilePath = guarantorSignatureImageFilePath;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
