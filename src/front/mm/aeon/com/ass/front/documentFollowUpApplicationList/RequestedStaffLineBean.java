/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.documentFollowUpApplicationList;

import java.io.Serializable;
import java.sql.Timestamp;

public class RequestedStaffLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8598824585608509268L;

    private String name;

    private Timestamp requestedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Timestamp requestedDate) {
        this.requestedDate = requestedDate;
    }

}
