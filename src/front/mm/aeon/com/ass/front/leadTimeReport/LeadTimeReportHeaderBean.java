/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.leadTimeReport;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class LeadTimeReportHeaderBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2275208006658591779L;

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
