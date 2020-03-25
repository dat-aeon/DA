/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.purchaseAttachmentPhotoCheck;

import java.io.Serializable;
import java.sql.Timestamp;

public class PurchaseAttachmentPhotoCheckLineBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5017192247163073041L;
    private Integer checkingAttachmentId;
    private Integer purchaseInfoId;
    private String agreementNo;
    private String oldFilePath;
    private String newFilePath;
    private Integer fileType;
    private Integer status;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    public Integer getCheckingAttachmentId() {
        return checkingAttachmentId;
    }
    public void setCheckingAttachmentId(Integer checkingAttachmentId) {
        this.checkingAttachmentId = checkingAttachmentId;
    }
    public Integer getPurchaseInfoId() {
        return purchaseInfoId;
    }
    public void setPurchaseInfoId(Integer purchaseInfoId) {
        this.purchaseInfoId = purchaseInfoId;
    }
    public String getAgreementNo() {
        return agreementNo;
    }
    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }
    public String getOldFilePath() {
        return oldFilePath;
    }
    public void setOldFilePath(String oldFilePath) {
        this.oldFilePath = oldFilePath;
    }
    public String getNewFilePath() {
        return newFilePath;
    }
    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }
    public Integer getFileType() {
        return fileType;
    }
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
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
