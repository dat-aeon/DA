/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentDocumentErrorApplicationList;

import java.io.Serializable;

public class AgentDocumentErrorOtherImageBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1373078522115084916L;

    private Integer attachmentId;

    private String imagePath;

    private Integer fileType;

    private Boolean editFlag;

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public Boolean getEditFlag() {
        return editFlag;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public void setEditFlag(Boolean editFlag) {
        this.editFlag = editFlag;
    }

}
