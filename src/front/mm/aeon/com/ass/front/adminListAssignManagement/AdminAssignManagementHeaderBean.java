/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.adminListAssignManagement;

import java.io.Serializable;

public class AdminAssignManagementHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public AdminAssignManagementHeaderBean copyAdminAssignManagementHeaderBean(
            AdminAssignManagementHeaderBean adminManagementHeaderBean) {
        this.userId = adminManagementHeaderBean.getUserId();

        return this;
    }

}
