/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.compulsoryUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class CompulsoryUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;

    private Integer compulsoryId;

    private Double compulsoryAmount;

    private Boolean delFlag;

    private String updatedBy;

    private Timestamp updatedTime;

    private boolean isValidStatusToggle;

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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public boolean isValidStatusToggle() {
        return isValidStatusToggle;
    }

    public void setValidStatusToggle(boolean isValidStatusToggle) {
        this.isValidStatusToggle = isValidStatusToggle;
    }

    @Override
    public String getServiceId() {
        return "COMPULSORYSU";
    }
}
