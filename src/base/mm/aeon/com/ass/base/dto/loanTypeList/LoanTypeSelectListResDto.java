/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2019 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.loanTypeList;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class LoanTypeSelectListResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7318982398749871155L;

    private Integer loanTypeId;
    private Boolean delFlag;
    private String name;

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
