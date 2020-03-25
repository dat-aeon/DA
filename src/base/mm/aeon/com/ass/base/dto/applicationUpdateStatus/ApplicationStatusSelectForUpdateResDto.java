/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.applicationUpdateStatus;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ApplicationStatusSelectForUpdateResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2984883292668226332L;

    private Integer daApplicationId;

    private Timestamp createdTime;

    public Integer getDaApplicationId() {
        return daApplicationId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setDaApplicationId(Integer daApplicationId) {
        this.daApplicationId = daApplicationId;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

}
