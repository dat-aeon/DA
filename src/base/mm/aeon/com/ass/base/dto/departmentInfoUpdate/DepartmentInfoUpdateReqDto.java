/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.departmentInfoUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "DepartmentInfo")
public class DepartmentInfoUpdateReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = -748406496640180341L;

    private Integer departmentId;

    private String name;

    private Boolean delFlag;

    private String createdBy;

    private String updatedBy;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    @Override
    public DaoType getDaoType() {
        return DaoType.UPDATE;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
