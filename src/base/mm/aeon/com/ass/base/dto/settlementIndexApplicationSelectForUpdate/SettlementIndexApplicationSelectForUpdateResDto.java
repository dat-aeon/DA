/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.settlementIndexApplicationSelectForUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class SettlementIndexApplicationSelectForUpdateResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2984883292668226332L;

    private Integer applicationHistoryId;

    public Integer getApplicationHistoryId() {
        return applicationHistoryId;
    }

    public void setApplicationHistoryId(Integer applicationHistoryId) {
        this.applicationHistoryId = applicationHistoryId;
    }

}
