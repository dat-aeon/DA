/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.indexApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "IndexApplicationInfo")
public class IndexApplicationSearchCheckReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 8128284704745114333L;
    private Integer applicationID;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.REFER;
    }

    public Integer getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Integer applicationID) {
        this.applicationID = applicationID;
    }

}
