/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.compulsoryInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class CompulsoryInfoReferResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 4511546202956199291L;

    private Integer compulsoryId;

    private Double compulsoryAmount;

    public Integer getCompulsoryId() {
        return compulsoryId;
    }

    public void setCompulsoryId(Integer compulsoryId) {
        this.compulsoryId = compulsoryId;
    }

    public Double getCompulsoryAmount() {
        return compulsoryAmount;
    }

    public void setCompulsoryAmount(Double compulsoryAmount) {
        this.compulsoryAmount = compulsoryAmount;
    }

}
