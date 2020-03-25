/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.purchaseAttachmentPhotoCheck;

import java.io.Serializable;

public class PurchaseAttachmentPhotoCheckHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5097482262352613318L;
    private Integer status;
    private Integer checkingAttachmentId;
    private String rejectComment;

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

    public String getRejectComment() {
        return rejectComment;
    }

    public void setRejectComment(String rejectComment) {
        this.rejectComment = rejectComment;
    }

}
