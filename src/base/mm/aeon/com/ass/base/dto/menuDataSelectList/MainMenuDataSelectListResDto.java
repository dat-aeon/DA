/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.menuDataSelectList;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class MainMenuDataSelectListResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -727759117130998535L;

    private Integer mainMenuId;

    private String mainMenuName;

    private String mainMenuOrder;

    private Integer mainMenuFlag;

    private String menuAction;

    public Integer getMainMenuId() {
        return mainMenuId;
    }

    public void setMainMenuId(Integer mainMenuId) {
        this.mainMenuId = mainMenuId;
    }

    public String getMainMenuName() {
        return mainMenuName;
    }

    public void setMainMenuName(String mainMenuName) {
        this.mainMenuName = mainMenuName;
    }

    public String getMainMenuOrder() {
        return mainMenuOrder;
    }

    public void setMainMenuOrder(String mainMenuOrder) {
        this.mainMenuOrder = mainMenuOrder;
    }

    public Integer getMainMenuFlag() {
        return mainMenuFlag;
    }

    public void setMainMenuFlag(Integer mainMenuFlag) {
        this.mainMenuFlag = mainMenuFlag;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

}
