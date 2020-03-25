/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.leadTimeReport;

import java.io.Serializable;
import java.sql.Timestamp;

public class LeadTimeReportTimeLineBean implements Serializable {

    private static final long serialVersionUID = -5054329851170226442L;
    /**
     * 
     */

    private Timestamp reportTime;

    public Timestamp getReportTime() {
        return reportTime;
    }

    public void setReportTime(Timestamp reportTime) {
        this.reportTime = reportTime;
    }

}
