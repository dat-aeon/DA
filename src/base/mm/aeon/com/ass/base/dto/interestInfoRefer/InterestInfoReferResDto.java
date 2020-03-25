/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.interestInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class InterestInfoReferResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 4511546202956199291L;

    private Integer interestId;

    private Double interestRate;

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

}
