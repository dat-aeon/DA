/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.agentAndProductUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class AgentAndProductApplicationStatusUpdateReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = 7103688745860947296L;

    private Integer da_application_info_id;
    private Integer status;
    private String pendingComment;
    private Timestamp updated_time;
    private String updated_by;
    private Boolean followUpCompleteFlag;
    private Boolean notYetCompleteFlag;

    public Integer getDa_application_info_id() {
        return da_application_info_id;
    }

    public void setDa_application_info_id(Integer da_application_info_id) {
        this.da_application_info_id = da_application_info_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Timestamp updated_time) {
        this.updated_time = updated_time;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

    public Boolean getFollowUpCompleteFlag() {
        return followUpCompleteFlag;
    }

    public Boolean getNotYetCompleteFlag() {
        return notYetCompleteFlag;
    }

    public void setFollowUpCompleteFlag(Boolean followUpCompleteFlag) {
        this.followUpCompleteFlag = followUpCompleteFlag;
    }

    public void setNotYetCompleteFlag(Boolean notYetCompleteFlag) {
        this.notYetCompleteFlag = notYetCompleteFlag;
    }

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "APPINFOSU";
    }
}
