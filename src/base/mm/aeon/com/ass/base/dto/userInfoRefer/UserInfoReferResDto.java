/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.userInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class UserInfoReferResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 722121406568061860L;
    private String user_name;

    private Integer departmentId;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

}
