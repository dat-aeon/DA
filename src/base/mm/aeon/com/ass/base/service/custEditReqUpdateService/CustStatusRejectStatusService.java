/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.custEditReqUpdateService;


import mm.aeon.com.ass.base.dto.custEditRequestUpdate.CustEditRequestStatusUpdateReqDto;
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

public class CustStatusRejectStatusService extends AbstractService
implements IService<CustStatusRejectServiceReqBean, CustStatusRejectServiceResBean> {

private ASSLogger logger = new ASSLogger();

@Override
public CustStatusRejectServiceResBean execute(CustStatusRejectServiceReqBean reqBean) {

CustStatusRejectServiceResBean resBean = new CustStatusRejectServiceResBean();

try {
        CustEditRequestStatusUpdateReqDto updateReqDto = new CustEditRequestStatusUpdateReqDto();
        updateReqDto.setCustEditReqId(reqBean.getCustEditReqId());
        updateReqDto.setRejectComment(reqBean.getRejectComment());
        updateReqDto.setStatus(reqBean.getStatus());
        this.getDaoServiceInvoker().execute(updateReqDto);
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
