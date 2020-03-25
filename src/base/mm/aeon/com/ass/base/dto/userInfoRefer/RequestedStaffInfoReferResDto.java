/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.userInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class RequestedStaffInfoReferResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 722121406568061860L;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
