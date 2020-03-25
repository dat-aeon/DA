/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.documentFollowUpAttachmentEditStatusUpdateService;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class DocumentFollowUpAttachmentEditStatusUpdateReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = 844175608751726456L;
    private Integer applicationInfoID;

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "EDSTUP";
    }

    public Integer getApplicationInfoID() {
        return applicationInfoID;
    }

    public void setApplicationInfoID(Integer applicationInfoID) {
        this.applicationInfoID = applicationInfoID;
    }

}
