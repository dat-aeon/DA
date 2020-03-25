/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.settlementIndexHistoryUpdateService;

import java.sql.Date;
import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class SettlementIndexApplicationHistoryStatusUpdateReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = 7103688745860947296L;

    private Integer applicationHistoryId;
    private Integer status;
    private String pendingComment;
    private Timestamp updatedTime;
    private String updatedBy;

    public Integer getApplicationHistoryId() {
        return applicationHistoryId;
    }

    public void setApplicationHistoryId(Integer applicationHistoryId) {
        this.applicationHistoryId = applicationHistoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "SETTLEMENTINFOHISSU";
    }
}
