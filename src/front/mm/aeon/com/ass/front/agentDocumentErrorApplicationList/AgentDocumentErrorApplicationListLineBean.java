/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentDocumentErrorApplicationList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationApplicantCompanySearchResDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationEmergencyContactSearchResDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationGuarantorSearchResDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationPurchaseAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationPurchaseInfoSearchResDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto;

public class AgentDocumentErrorApplicationListLineBean implements Serializable {

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

    private Integer highestEducationTypeId;

    private Double approvedAmount;

    private Double approvedFinanceAmount;

    private Integer approvedFinanceTerm;

    private Double modifyFinanceAmount;

    private Integer modifyFinanceTerm;

    private AgentDocumentErrorApplicationApplicantCompanySearchResDto applicantCompanyDto;

    private AgentDocumentErrorApplicationEmergencyContactSearchResDto emergencyContactDto;

    private AgentDocumentErrorApplicationGuarantorSearchResDto guarantorDto;

    private List<AgentDocumentErrorApplicationAttachmentSearchResDto> attachmentDtoList;

    private AgentDocumentErrorApplicationPurchaseInfoSearchResDto purchaseDto;

    private List<AgentDocumentErrorApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList;

    private AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto purchaseProductDto;

    private List<AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList;

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

    private String letterOfAgreementImageFilePath;

    private String cashReceiptImageFilePath;

    private List<AgentDocumentErrorOtherImageBean> othersImageFilePathList;

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

    private AgentDocumentErrorStaffBean agentDocumentErrorStaffBean;

    private List<AgentDocumentErrorStaffBean> agentDocumentErrorStaffBeanList;

    private AgentDocumentErrorEditImageBean agentDocumentErrorEditImageBean;

    private List<AgentDocumentErrorEditImageBean> agentDocumentErrorEditImageBeanList;

    private List<AgentDocumentErrorEditImageBean> agentDocumentErrorOriginalEditImageBeanList;

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

    public String getSettlement_comment() {
        return settlement_comment;
    }

    public void setSettlement_comment(String settlement_comment) {
        this.settlement_comment = settlement_comment;
    }

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
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
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

    public Double getFinanancialAmount() {
        return finanancialAmount;
    }

    public void setFinanancialAmount(Double finanancialAmount) {
        this.finanancialAmount = finanancialAmount;
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

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

    public String getMemberCardImageFilePath() {
        return memberCardImageFilePath;
    }

    public void setMemberCardImageFilePath(String memberCardImageFilePath) {
        this.memberCardImageFilePath = memberCardImageFilePath;
    }

    public String getInvoiceImageFilePath() {
        return invoiceImageFilePath;
    }

    public void setInvoiceImageFilePath(String invoiceImageFilePath) {
        this.invoiceImageFilePath = invoiceImageFilePath;
    }

    public String getUloanImageFilePath() {
        return uloanImageFilePath;
    }

    public void setUloanImageFilePath(String uloanImageFilePath) {
        this.uloanImageFilePath = uloanImageFilePath;
    }

    public List<AgentDocumentErrorOtherImageBean> getOthersImageFilePathList() {
        return othersImageFilePathList;
    }

    public void setOthersImageFilePathList(List<AgentDocumentErrorOtherImageBean> othersImageFilePathList) {
        this.othersImageFilePathList = othersImageFilePathList;
    }

    public String getLastRequestedStaffName() {
        return lastRequestedStaffName;
    }

    public void setLastRequestedStaffName(String lastRequestedStaffName) {
        this.lastRequestedStaffName = lastRequestedStaffName;
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

    public Integer getHighestEducationTypeId() {
        return highestEducationTypeId;
    }

    public void setHighestEducationTypeId(Integer highestEducationTypeId) {
        this.highestEducationTypeId = highestEducationTypeId;
    }

    public AgentDocumentErrorApplicationApplicantCompanySearchResDto getApplicantCompanyDto() {
        return applicantCompanyDto;
    }

    public AgentDocumentErrorApplicationEmergencyContactSearchResDto getEmergencyContactDto() {
        return emergencyContactDto;
    }

    public AgentDocumentErrorApplicationGuarantorSearchResDto getGuarantorDto() {
        return guarantorDto;
    }

    public List<AgentDocumentErrorApplicationAttachmentSearchResDto> getAttachmentDtoList() {
        return attachmentDtoList;
    }

    public AgentDocumentErrorApplicationPurchaseInfoSearchResDto getPurchaseDto() {
        return purchaseDto;
    }

    public List<AgentDocumentErrorApplicationPurchaseAttachmentSearchResDto> getPurchaseAttachmentDtoList() {
        return purchaseAttachmentDtoList;
    }

    public AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto getPurchaseProductDto() {
        return purchaseProductDto;
    }

    public List<AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto> getPurchaseProductDtoList() {
        return purchaseProductDtoList;
    }

    public void setApplicantCompanyDto(AgentDocumentErrorApplicationApplicantCompanySearchResDto applicantCompanyDto) {
        this.applicantCompanyDto = applicantCompanyDto;
    }

    public void setEmergencyContactDto(AgentDocumentErrorApplicationEmergencyContactSearchResDto emergencyContactDto) {
        this.emergencyContactDto = emergencyContactDto;
    }

    public void setGuarantorDto(AgentDocumentErrorApplicationGuarantorSearchResDto guarantorDto) {
        this.guarantorDto = guarantorDto;
    }

    public void setAttachmentDtoList(List<AgentDocumentErrorApplicationAttachmentSearchResDto> attachmentDtoList) {
        this.attachmentDtoList = attachmentDtoList;
    }

    public void setPurchaseDto(AgentDocumentErrorApplicationPurchaseInfoSearchResDto purchaseDto) {
        this.purchaseDto = purchaseDto;
    }

    public void setPurchaseAttachmentDtoList(
            List<AgentDocumentErrorApplicationPurchaseAttachmentSearchResDto> purchaseAttachmentDtoList) {
        this.purchaseAttachmentDtoList = purchaseAttachmentDtoList;
    }

    public void setPurchaseProductDto(AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto purchaseProductDto) {
        this.purchaseProductDto = purchaseProductDto;
    }

    public void setPurchaseProductDtoList(
            List<AgentDocumentErrorApplicationPurchaseProductInfoSearchResDto> purchaseProductDtoList) {
        this.purchaseProductDtoList = purchaseProductDtoList;
    }

    public AgentDocumentErrorStaffBean getAgentDocumentErrorStaffBean() {
        return agentDocumentErrorStaffBean;
    }

    public List<AgentDocumentErrorStaffBean> getAgentDocumentErrorStaffBeanList() {
        return agentDocumentErrorStaffBeanList;
    }

    public void setAgentDocumentErrorStaffBean(AgentDocumentErrorStaffBean agentDocumentErrorStaffBean) {
        this.agentDocumentErrorStaffBean = agentDocumentErrorStaffBean;
    }

    public void setAgentDocumentErrorStaffBeanList(List<AgentDocumentErrorStaffBean> agentDocumentErrorStaffBeanList) {
        this.agentDocumentErrorStaffBeanList = agentDocumentErrorStaffBeanList;
    }

    public AgentDocumentErrorEditImageBean getAgentDocumentErrorEditImageBean() {
        return agentDocumentErrorEditImageBean;
    }

    public List<AgentDocumentErrorEditImageBean> getAgentDocumentErrorEditImageBeanList() {
        return agentDocumentErrorEditImageBeanList;
    }

    public void setAgentDocumentErrorEditImageBean(AgentDocumentErrorEditImageBean agentDocumentErrorEditImageBean) {
        this.agentDocumentErrorEditImageBean = agentDocumentErrorEditImageBean;
    }

    public void setAgentDocumentErrorEditImageBeanList(
            List<AgentDocumentErrorEditImageBean> agentDocumentErrorEditImageBeanList) {
        this.agentDocumentErrorEditImageBeanList = agentDocumentErrorEditImageBeanList;
    }

    public List<AgentDocumentErrorEditImageBean> getAgentDocumentErrorOriginalEditImageBeanList() {
        return agentDocumentErrorOriginalEditImageBeanList;
    }

    public void setAgentDocumentErrorOriginalEditImageBeanList(
            List<AgentDocumentErrorEditImageBean> agentDocumentErrorOriginalEditImageBeanList) {
        this.agentDocumentErrorOriginalEditImageBeanList = agentDocumentErrorOriginalEditImageBeanList;
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

    public void setLetterOfAgreementImageFilePath(String letterOfAgreementImageFilePath) {
        this.letterOfAgreementImageFilePath = letterOfAgreementImageFilePath;
    }

    public String getCashReceiptImageFilePath() {
        return cashReceiptImageFilePath;
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
