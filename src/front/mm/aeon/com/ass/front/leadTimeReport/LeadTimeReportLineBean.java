/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.leadTimeReport;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class LeadTimeReportLineBean implements Serializable {

    private static final long serialVersionUID = -5054329851170226442L;
    /**
     * 
     */

    private String applicationNo;
    private String customerName;
    private String nrcNo;
    private String phoneNo;
    private String agreementNo;
    private String agentName;
    private Timestamp informationReceivedTime;
    private Timestamp saleClaimTime;
    private List<LeadTimeReportTimeLineBean> saleClaimTimeList;
    private List<LeadTimeReportTimeLineBean> documentErrorTimeList;
    private Timestamp saleEntryTime;

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

    public List<LeadTimeReportTimeLineBean> getSaleClaimTimeList() {
        return saleClaimTimeList;
    }

    public List<LeadTimeReportTimeLineBean> getDocumentErrorTimeList() {
        return documentErrorTimeList;
    }

    public void setSaleClaimTimeList(List<LeadTimeReportTimeLineBean> saleClaimTimeList) {
        this.saleClaimTimeList = saleClaimTimeList;
    }

    public void setDocumentErrorTimeList(List<LeadTimeReportTimeLineBean> documentErrorTimeList) {
        this.documentErrorTimeList = documentErrorTimeList;
    }

    public void setSaleEntryTime(Timestamp saleEntryTime) {
        this.saleEntryTime = saleEntryTime;
    }

    public Timestamp getSaleClaimTime() {
        return saleClaimTime;
    }

    public void setSaleClaimTime(Timestamp saleClaimTime) {
        this.saleClaimTime = saleClaimTime;
    }

}
