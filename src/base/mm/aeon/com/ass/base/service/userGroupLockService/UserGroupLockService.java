/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.userGroupLockService;

import mm.aeon.com.ass.base.dto.commonApplicationUpdate.CommonApplicationLockUpdateReqDto;
import mm.aeon.com.ass.base.dto.custEditRequestUpdate.CustEditRequestLockUpdateReqDto;
import mm.aeon.com.ass.base.dto.userGroupLock.UserGroupLockReqDto;
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

public class UserGroupLockService extends AbstractService
implements IService<UserGroupLockServiceReqBean, UserGroupLockServiceResBean> {

private ASSLogger logger = new ASSLogger();

@Override
public UserGroupLockServiceResBean execute(UserGroupLockServiceReqBean reqBean) {

UserGroupLockServiceResBean resBean = new UserGroupLockServiceResBean();
 
try {
    
     
        UserGroupLockReqDto updateReqDto = new UserGroupLockReqDto();

        updateReqDto.setGroupId(reqBean.getGroupId());
        updateReqDto.setLockFlag(reqBean.getLockFlag());
        updateReqDto.setLockedTime(reqBean.getLockedTime());
        updateReqDto.setLockedBy(reqBean.getLockedBy());

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
