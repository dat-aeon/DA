/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentDocumentErrorApplicationListManagement;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class AgentDocumentErrorApplicationHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;

    private String pendingComment;

    private UploadedFile editImage;

    private String uploadedImageFilePath;

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

    public UploadedFile getEditImage() {
        return editImage;
    }

    public void setEditImage(UploadedFile editImage) {
        this.editImage = editImage;
    }

    public String getUploadedImageFilePath() {
        return uploadedImageFilePath;
    }

    public void setUploadedImageFilePath(String uploadedImageFilePath) {
        this.uploadedImageFilePath = uploadedImageFilePath;
    }

}
