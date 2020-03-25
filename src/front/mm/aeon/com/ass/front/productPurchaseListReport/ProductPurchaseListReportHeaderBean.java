/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.productPurchaseListReport;

import java.io.Serializable;
import java.util.Date;

public class ProductPurchaseListReportHeaderBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8315363061071102573L;
    private String agentName;
    private String outletName;
    private Date settlementDate;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

}
