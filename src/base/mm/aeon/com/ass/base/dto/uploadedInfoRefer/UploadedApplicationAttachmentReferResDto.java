/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.uploadedInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;
@MyBatisMapper(namespace = "AttachmentInfo")

public class UploadedApplicationAttachmentReferResDto implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = -4917479642672714623L;
     
    private Integer attachmentId;
    
    private Integer fileType;
    
    private Boolean flagType;
 
    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Boolean getFlagType() {
        return flagType;
    }

    public void setFlagType(Boolean flagType) {
        this.flagType = flagType;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

}
