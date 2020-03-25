/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.menuDataSelectList;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "MenuData")
public class MenuDataSelectListReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 2968977547295911978L;

    private Integer groupId;

    private Integer mainMenuId;

    private Integer subMenuId;

    private Integer menuLevel;

    private Boolean subMenuFlag;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getMainMenuId() {
        return mainMenuId;
    }

    public void setMainMenuId(Integer mainMenuId) {
        this.mainMenuId = mainMenuId;
    }

    public Integer getSubMenuId() {
        return subMenuId;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setSubMenuId(Integer subMenuId) {
        this.subMenuId = subMenuId;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Boolean getSubMenuFlag() {
        return subMenuFlag;
    }

    public void setSubMenuFlag(Boolean subMenuFlag) {
        this.subMenuFlag = subMenuFlag;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_LIST;
    }

}
