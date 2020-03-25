/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.documentFollowUpUpdate;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "ApplicationAttachmentEditStatusUpdate")
public class DocumentFollowUpApplicationAttachmentEditStatusReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = -4141337131490062768L;
    private Integer applicationInfoID;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.UPDATE;
    }

    public Integer getApplicationInfoID() {
        return applicationInfoID;
    }

    public void setApplicationInfoID(Integer applicationInfoID) {
        this.applicationInfoID = applicationInfoID;
    }

}
