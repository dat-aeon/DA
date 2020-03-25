/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.uploadedAttachmentFlagUpdateService;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class UploadedAttachmentUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;

    private Integer applicationId;
 
    private Integer attachmentId;
    
    private Boolean flagType;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }
 

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

    @Override
    public String getServiceId() {
        
        return "ATTACHMENTU";
    }
    
}
