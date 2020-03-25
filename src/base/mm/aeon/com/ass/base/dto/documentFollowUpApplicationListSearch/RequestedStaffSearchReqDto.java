/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "RequestedStaffInfo")
public class RequestedStaffSearchReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1300986247960971419L;

    private Integer status;

    private Integer daApplicationInfoId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDaApplicationInfoId() {
        return daApplicationInfoId;
    }

    public void setDaApplicationInfoId(Integer daApplicationInfoId) {
        this.daApplicationInfoId = daApplicationInfoId;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_LIST;
    }
}
