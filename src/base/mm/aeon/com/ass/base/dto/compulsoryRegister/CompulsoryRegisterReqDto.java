/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.compulsoryRegister;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "CompulsoryInfo")
public class CompulsoryRegisterReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = 6801423902491492578L;

    private Integer compulsoryId;

    private double compulsoryAmount;

    private Boolean delFlag;

    private String createdBy;

    private Timestamp createdTime;

    public Integer getCompulsoryId() {
        return compulsoryId;
    }

    public void setCompulsoryId(Integer compulsoryId) {
        this.compulsoryId = compulsoryId;
    }

    public double getCompulsoryAmount() {
        return compulsoryAmount;
    }

    public void setCompulsoryAmount(double compulsoryAmount) {
        this.compulsoryAmount = compulsoryAmount;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.INSERT;
    }

}
