/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationUploadList;

import java.io.Serializable;
import java.sql.Timestamp;

public class AgreementModificationUploadedTimeBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1373078522115084916L;

    private String pendingComment;

    private Timestamp createdTime;

    private String createdBy;

    private String staffName;

    private Integer departmentId;

    public String getStaffName() {
        return staffName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

}
