/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.leadTimeReportSearch;

import java.sql.Timestamp;
import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "leadTimeReport")
public class LeadTimeReportCountSearchReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = -3712645002309316118L;
    private String applicationNo;
    private String customerName;
    private String nrcNo;
    private String phoneNo;
    private String agreementNo;
    private String agentName;
    private Timestamp informationReceivedTime;
    private Timestamp saleClaimFinishedTime;
    private Timestamp documentErrorTime;
    private Timestamp saleEntryTime;

    private Date agentInformationReceivedDateFrom;
    private Date agentInformationReceivedDateTo;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.COUNT;
    }

    public Date getAgentInformationReceivedDateFrom() {
        return agentInformationReceivedDateFrom;
    }

    public void setAgentInformationReceivedDateFrom(Date agentInformationReceivedDateFrom) {
        this.agentInformationReceivedDateFrom = agentInformationReceivedDateFrom;
    }

    public Date getAgentInformationReceivedDateTo() {
        return agentInformationReceivedDateTo;
    }

    public void setAgentInformationReceivedDateTo(Date agentInformationReceivedDateTo) {
        this.agentInformationReceivedDateTo = agentInformationReceivedDateTo;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public String getCustomerName() {
        return customerName;
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

    public String getAgentName() {
        return agentName;
    }

    public Timestamp getInformationReceivedTime() {
        return informationReceivedTime;
    }

    public Timestamp getSaleClaimFinishedTime() {
        return saleClaimFinishedTime;
    }

    public Timestamp getDocumentErrorTime() {
        return documentErrorTime;
    }

    public Timestamp getSaleEntryTime() {
        return saleEntryTime;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setInformationReceivedTime(Timestamp informationReceivedTime) {
        this.informationReceivedTime = informationReceivedTime;
    }

    public void setSaleClaimFinishedTime(Timestamp saleClaimFinishedTime) {
        this.saleClaimFinishedTime = saleClaimFinishedTime;
    }

    public void setDocumentErrorTime(Timestamp documentErrorTime) {
        this.documentErrorTime = documentErrorTime;
    }

    public void setSaleEntryTime(Timestamp saleEntryTime) {
        this.saleEntryTime = saleEntryTime;
    }

}
