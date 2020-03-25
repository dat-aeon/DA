/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.uploadedAttachmentFlagUpdateService;

import java.util.ArrayList;

import mm.aeon.com.ass.base.dto.uploadedApplicationListSearch.UploadedApplicationAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.uploadedApplicationUpdate.UploadedApplicationAttachmentFlagUpdateReqDto;
import mm.aeon.com.ass.base.dto.uploadedApplicationUpdate.UploadedApplicationAttachmentFlagUpdateReqDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PhysicalRecordLockedException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class UploadedAttachmentFlagUpdateService extends AbstractService
implements IService<UploadedAttachmentUpdateServiceReqBean, UploadedAttachmentUpdateServiceResBean> {

private ASSLogger logger = new ASSLogger();

@Override
public UploadedAttachmentUpdateServiceResBean execute(UploadedAttachmentUpdateServiceReqBean reqBean) {
    UploadedAttachmentUpdateServiceResBean resBean = new UploadedAttachmentUpdateServiceResBean();
    
    try {     
           UploadedApplicationAttachmentFlagUpdateReqDto reqDto=new UploadedApplicationAttachmentFlagUpdateReqDto();
           reqDto.setAttachmentId(reqBean.getAttachmentId());
           reqDto.setEditFlag(reqBean.getFlagType());
           this.getDaoServiceInvoker().execute(reqDto);
           resBean.setServiceStatus(ServiceStatusCode.OK);
         } catch (PrestoDBException e) {
    if (e instanceof RecordDuplicatedException) {
        resBean.setServiceStatus(ServiceStatusCode.RECORD_DUPLICATED_ERROR);
    } else if (e instanceof PhysicalRecordLockedException) {
        resBean.setServiceStatus(ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR);
    } else if (e instanceof DaoSqlException) {
        logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
        resBean.setServiceStatus(ServiceStatusCode.SQL_ERROR);
    } else {
        logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
        throw new BaseException(e.getCause());
    }
}

return resBean;
}

}
