/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.agentDocumentErrorPurchaseAttachmentInsertService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class AgentDocumentErrorApplicationPurchaseAttachmentInsertServiceReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = 7103688745860947296L;

    private Integer purchaseId;

    private Integer attachmentId;

    private String filePath;

    private Integer fileType;

    private Boolean editFlag;

    private Timestamp updatedTime;

    private String updatedBy;

    private Boolean originalFlag;

    private Timestamp createdTime;

    private String createdBy;

    private Integer daApplicationInfoHistoryId;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public Boolean getEditFlag() {
        return editFlag;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Boolean getOriginalFlag() {
        return originalFlag;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public void setEditFlag(Boolean editFlag) {
        this.editFlag = editFlag;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setOriginalFlag(Boolean originalFlag) {
        this.originalFlag = originalFlag;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getDaApplicationInfoHistoryId() {
        return daApplicationInfoHistoryId;
    }

    public void setDaApplicationInfoHistoryId(Integer daApplicationInfoHistoryId) {
        this.daApplicationInfoHistoryId = daApplicationInfoHistoryId;
    }

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "AGENTDERRORATTACHIN";
    }
}
