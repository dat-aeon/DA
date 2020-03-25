/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.commonApplicationUpdateService;

import mm.aeon.com.ass.base.dto.commonApplicationUpdate.CommonApplicationLockUpdateReqDto;
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

public class CommonApplicationInfoLockUpdateService extends AbstractService
implements IService<CommonApplicationInfoLockUpdateServiceReqBean, CommonApplicationInfoLockUpdateServiceResBean> {

private ASSLogger logger = new ASSLogger();

@Override
public CommonApplicationInfoLockUpdateServiceResBean execute(CommonApplicationInfoLockUpdateServiceReqBean reqBean) {

CommonApplicationInfoLockUpdateServiceResBean resBean = new CommonApplicationInfoLockUpdateServiceResBean();
 
try {
    
     
        CommonApplicationLockUpdateReqDto updateReqDto = new CommonApplicationLockUpdateReqDto();

        updateReqDto.setApplicationId(reqBean.getApplicationId());
        updateReqDto.setLockFlag(reqBean.getLockFlag());
        updateReqDto.setLockTime(reqBean.getLockTime());
        updateReqDto.setLockBy(reqBean.getLockBy());

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
