/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2019 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.applicationTypeList;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ApplicationTypeSelectListResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7318982398749871155L;

    private Integer applicationTypeId;
    private String name;

    public Integer getApplicationTypeId() {
        return applicationTypeId;
    }

    public void setApplicationTypeId(Integer applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
