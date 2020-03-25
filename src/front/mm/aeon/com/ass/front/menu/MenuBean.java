/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.menu;

import java.io.Serializable;

/**
 * MenuBean Class.
 * <p>
 * 
 * <pre>
 * 
 * </pre>
 * 
 * </p>
 */
public class MenuBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7107265908132766090L;

    private String menuId;

    private String menuDisplayOrder;

    private String menuDisplayName;

    private String hierarchyLevel;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuDisplayOrder() {
        return menuDisplayOrder;
    }

    public void setMenuDisplayOrder(String menuDisplayOrder) {
        this.menuDisplayOrder = menuDisplayOrder;
    }

    public String getMenuDisplayName() {
        return menuDisplayName;
    }

    public void setMenuDisplayName(String menuDisplayName) {
        this.menuDisplayName = menuDisplayName;
    }

    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

}
