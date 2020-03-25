/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleClaimFinishedReport;

import java.io.Serializable;
import java.util.Date;

public class AgentSaleClaimFinishedReportHeaderBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2275208006658591779L;
    private String agentName;
    private String location;
    private Date uploadedDateFrom;
    private Date uploadedDateTo;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getLocation() {
        return location;
    }

    public Date getUploadedDateFrom() {
        return uploadedDateFrom;
    }

    public Date getUploadedDateTo() {
        return uploadedDateTo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUploadedDateFrom(Date uploadedDateFrom) {
        this.uploadedDateFrom = uploadedDateFrom;
    }

    public void setUploadedDateTo(Date uploadedDateTo) {
        this.uploadedDateTo = uploadedDateTo;
    }

}
