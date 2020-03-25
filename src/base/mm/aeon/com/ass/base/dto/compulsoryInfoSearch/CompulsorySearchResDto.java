/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.compulsoryInfoSearch;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class CompulsorySearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7591551575539498312L;

    private Integer compulsoryId;

    private Double compulsoryAmount;

    private Boolean delFlag;

    private String createdBy;

    private String updatedBy;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    public Integer getCompulsoryId() {
        return compulsoryId;
    }

    public void setCompulsoryId(Integer compulsoryId) {
        this.compulsoryId = compulsoryId;
    }

    public Double getCompulsoryAmount() {
        return compulsoryAmount;
    }

    public void setCompulsoryAmount(Double compulsoryAmount) {
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
