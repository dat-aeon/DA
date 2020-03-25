/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.applicationIndexedStatusInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "UploadedApplicationInfo")
public class ApplicationIndexedStatusInfoReferReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = -8193215351274229102L;

    private String applicationNo;

    private Integer status;

    private Boolean delFlag;

    private Boolean canDuplicate;

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getCanDuplicate() {
        return canDuplicate;
    }

    public void setCanDuplicate(Boolean canDuplicate) {
        this.canDuplicate = canDuplicate;
    }

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.REFER;
    }

}
