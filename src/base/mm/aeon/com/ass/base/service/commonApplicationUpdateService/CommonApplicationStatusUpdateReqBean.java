/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.commonApplicationUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class CommonApplicationStatusUpdateReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = 7103688745860947296L;

    private Integer daApplicationInfoId;
    private Integer status;
    private String modifyComment;
    private Timestamp updatedTime;
    private String updatedBy;
    private String pendingComment;
    private Integer modifyStatus;

    public Integer getDaApplicationInfoId() {
        return daApplicationInfoId;
    }

    public Integer getStatus() {
        return status;
    }

    public String getModifyComment() {
        return modifyComment;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setDaApplicationInfoId(Integer daApplicationInfoId) {
        this.daApplicationInfoId = daApplicationInfoId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setModifyComment(String modifyComment) {
        this.modifyComment = modifyComment;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

    public Integer getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(Integer modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "COMMONINFOSU";
    }
}
