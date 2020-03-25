/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleReport;

import java.io.Serializable;
import java.util.Date;

/**
 * AgentSaleReportJasperBean Class.
 * <p>
 * 
 * <pre>
 * 
 * </pre>
 * 
 * </p>
 */
public class AgentSaleReportJasperBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8816640205974335228L;
    private Date purchaseDate;
    private String customerName;
    private String invoiceNo;
    private String agreementNo;
    private Double price;
    private String agentName;

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

}
