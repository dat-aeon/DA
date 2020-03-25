/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agreementModificationCancelReportSearch;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgreementModificationCancelReportSearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5578964690693393044L;

    private String applicationNo;
    private Timestamp modifyTime;
    private String applicantName;
    private String nrcNo;
    private String phoneNo;
    private String agreementNo;
    private Double currentFinanceAmount;
    private Integer currentFinanceTerm;
    private Double modifyFinanceAmount;
    private Integer modifyFinanceTerm;
    private String modifyComment;

    public String getApplicationNo() {
        return applicationNo;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public Double getCurrentFinanceAmount() {
        return currentFinanceAmount;
    }

    public Integer getCurrentFinanceTerm() {
        return currentFinanceTerm;
    }

    public Double getModifyFinanceAmount() {
        return modifyFinanceAmount;
    }

    public Integer getModifyFinanceTerm() {
        return modifyFinanceTerm;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public void setCurrentFinanceAmount(Double currentFinanceAmount) {
        this.currentFinanceAmount = currentFinanceAmount;
    }

    public void setCurrentFinanceTerm(Integer currentFinanceTerm) {
        this.currentFinanceTerm = currentFinanceTerm;
    }

    public void setModifyFinanceAmount(Double modifyFinanceAmount) {
        this.modifyFinanceAmount = modifyFinanceAmount;
    }

    public void setModifyFinanceTerm(Integer modifyFinanceTerm) {
        this.modifyFinanceTerm = modifyFinanceTerm;
    }

    public String getModifyComment() {
        return modifyComment;
    }

    public void setModifyComment(String modifyComment) {
        this.modifyComment = modifyComment;
    }

}
