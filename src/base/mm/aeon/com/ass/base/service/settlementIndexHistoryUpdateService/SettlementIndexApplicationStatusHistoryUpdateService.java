/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.settlementIndexHistoryUpdateService;

import java.awt.Insets;
import java.sql.Date;
import java.sql.Timestamp;

import mm.aeon.com.ass.base.dto.SettlementIndexApplicationUpdate.SettlementIndexApplicationHistoryStatusUpdateReqDto;
import mm.aeon.com.ass.base.dto.SettlementIndexApplicationUpdate.SettlementIndexApplicationStatusUpdateReqDto;
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

public class SettlementIndexApplicationStatusHistoryUpdateService extends AbstractService
implements IService<SettlementIndexApplicationHistoryStatusUpdateReqBean, SettlementIndexApplicationStatusHistoryUpdateResBean> {

private ASSLogger logger = new ASSLogger();

@Override
public SettlementIndexApplicationStatusHistoryUpdateResBean execute(SettlementIndexApplicationHistoryStatusUpdateReqBean reqBean) {

SettlementIndexApplicationStatusHistoryUpdateResBean resBean = new SettlementIndexApplicationStatusHistoryUpdateResBean();
 
try {
    
    
        SettlementIndexApplicationHistoryStatusUpdateReqDto updateReqDto = new SettlementIndexApplicationHistoryStatusUpdateReqDto();

        updateReqDto.setApplicationHistoryId(reqBean.getApplicationHistoryId());
        updateReqDto.setStatus(reqBean.getStatus());
        updateReqDto.setPendingComment(reqBean.getPendingComment());
        updateReqDto.setUpdatedBy(reqBean.getUpdatedBy());
        updateReqDto.setUpdatedTime(reqBean.getUpdatedTime());

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
