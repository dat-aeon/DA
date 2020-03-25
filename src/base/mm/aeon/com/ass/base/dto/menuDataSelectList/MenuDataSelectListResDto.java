/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.menuDataSelectList;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class MenuDataSelectListResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -727759117130998535L;

    private Integer subMenuId;

    private String subMenuName;

    private Integer subMenuLevel;

    private String menuAction;

    private Integer subMenuOrder;

    private Integer subMenuRef;

    public Integer getSubMenuId() {
        return subMenuId;
    }

    public void setSubMenuId(Integer subMenuId) {
        this.subMenuId = subMenuId;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    public Integer getSubMenuLevel() {
        return subMenuLevel;
    }

    public void setSubMenuLevel(Integer subMenuLevel) {
        this.subMenuLevel = subMenuLevel;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public Integer getSubMenuOrder() {
        return subMenuOrder;
    }

    public void setSubMenuOrder(Integer subMenuOrder) {
        this.subMenuOrder = subMenuOrder;
    }

    public Integer getSubMenuRef() {
        return subMenuRef;
    }

    public void setSubMenuRef(Integer subMenuRef) {
        this.subMenuRef = subMenuRef;
    }

}
