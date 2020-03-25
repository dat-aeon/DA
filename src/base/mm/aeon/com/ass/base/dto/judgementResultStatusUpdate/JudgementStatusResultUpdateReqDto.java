/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Swe Hsu Mon $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.judgementResultStatusUpdate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import mm.aeon.com.ass.front.judgementStatusUpload.CustAgreementList;
import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "ApplicationInfoStatus")
public class JudgementStatusResultUpdateReqDto implements IReqServiceDto {

    private static final long serialVersionUID = 2672207836115099916L;

    private String agreementNo;
    private Integer qrShow;
    private Integer financialTerm;
    private Double financialAmt;
    private Integer financialStatus;
    private Integer custAgreementId;
    private Integer importCustomerId;
    private Date updatedTime;
    private Date createdTime;
    private String applicationNo;
    private Integer judgementResult;
    private Date judgementDate;

    public String getAgreementNo() {
        return agreementNo;
    }

    public Integer getQrShow() {
        return qrShow;
    }

    public Integer getFinancialTerm() {
        return financialTerm;
    }

    public Double getFinancialAmt() {
        return financialAmt;
    }

    public Integer getFinancialStatus() {
        return financialStatus;
    }

    public Integer getCustAgreementId() {
        return custAgreementId;
    }

    public Integer getImportCustomerId() {
        return importCustomerId;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public Integer getJudgementResult() {
        return judgementResult;
    }

    public Date getJudgementDate() {
        return judgementDate;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public void setQrShow(Integer qrShow) {
        this.qrShow = qrShow;
    }

    public void setFinancialTerm(Integer financialTerm) {
        this.financialTerm = financialTerm;
    }

    public void setFinancialAmt(Double financialAmt) {
        this.financialAmt = financialAmt;
    }

    public void setFinancialStatus(Integer financialStatus) {
        this.financialStatus = financialStatus;
    }

    public void setCustAgreementId(Integer custAgreementId) {
        this.custAgreementId = custAgreementId;
    }

    public void setImportCustomerId(Integer importCustomerId) {
        this.importCustomerId = importCustomerId;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setJudgementResult(Integer judgementResult) {
        this.judgementResult = judgementResult;
    }

    public void setJudgementDate(Date judgementDate) {
        this.judgementDate = judgementDate;
    }


    @Override
    public DaoType getDaoType() {
        return DaoType.INSERT;
    }

}
