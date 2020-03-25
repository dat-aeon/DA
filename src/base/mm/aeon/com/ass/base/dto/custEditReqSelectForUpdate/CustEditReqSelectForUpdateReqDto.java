/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************//*
package mm.aeon.com.ass.base.dto.custEditReqSelectForUpdate;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "CustEditRequest")
public class CustEditReqSelectForUpdateReqDto implements IReqServiceDto {

    *//**
     * 
     *//*
    private static final long serialVersionUID = 1992446030418803967L;

    *//**
     * 
     *//*

    private Integer custEditReqId;

    private Integer delFlag;

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_FOR_UPDATE;
    }

    public Integer getCustEditReqId() {
        return custEditReqId;
    }

    public void setCustEditReqId(Integer custEditReqId) {
        this.custEditReqId = custEditReqId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
*/