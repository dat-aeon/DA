/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.menuListAssignManagement;

import java.io.Serializable;

public class MenuAssignManagementHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;

    private Integer menuId;

    private String menuName;

   
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MenuAssignManagementHeaderBean copyMenuAssignManagementHeaderBean(
            MenuAssignManagementHeaderBean adminManagementHeaderBean) {

        this.menuId = adminManagementHeaderBean.getMenuId();
        this.menuName = adminManagementHeaderBean.getMenuName();
        return this;
    }

}
