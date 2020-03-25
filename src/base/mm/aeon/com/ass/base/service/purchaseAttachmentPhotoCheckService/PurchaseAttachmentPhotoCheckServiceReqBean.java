/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.purchaseAttachmentPhotoCheckService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class PurchaseAttachmentPhotoCheckServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -6478231136281789463L;
    private Integer status;
    private Integer checkingAttachmentId;
    private Timestamp updatedTime;
    private String rejectComment;

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "STATUSARU";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckingAttachmentId() {
        return checkingAttachmentId;
    }

    public void setCheckingAttachmentId(Integer checkingAttachmentId) {
        this.checkingAttachmentId = checkingAttachmentId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getRejectComment() {
        return rejectComment;
    }

    public void setRejectComment(String rejectComment) {
        this.rejectComment = rejectComment;
    }

}
