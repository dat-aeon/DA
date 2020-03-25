/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.custEditRequestSearch;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "EditRequestLockInfo")
public class CustEditSearchCheckReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -3676061240439241306L;
    private Integer editRequestId;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.REFER;
    }

    public Integer getEditRequestId() {
        return editRequestId;
    }

    public void setEditRequestId(Integer editRequestId) {
        this.editRequestId = editRequestId;
    }

}
