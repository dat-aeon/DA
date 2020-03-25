/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.commonApplicationUpdateService;

import mm.aeon.com.ass.base.dto.commonApplicationUpdate.CommonApplicationStatusUpdateReqDto;
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

public class CommonApplicationStatusUpdateService extends AbstractService
        implements IService<CommonApplicationStatusUpdateReqBean, CommonApplicationStatusUpdateResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public CommonApplicationStatusUpdateResBean execute(CommonApplicationStatusUpdateReqBean reqBean) {

        CommonApplicationStatusUpdateResBean resBean = new CommonApplicationStatusUpdateResBean();

        try {

            CommonApplicationStatusUpdateReqDto updateReqDto = new CommonApplicationStatusUpdateReqDto();

            updateReqDto.setDaApplicationInfoId(reqBean.getDaApplicationInfoId());
            updateReqDto.setStatus(reqBean.getStatus());
            if (reqBean.getModifyComment() != null) {
                updateReqDto.setModifyComment(reqBean.getModifyComment());
            }
            if (reqBean.getPendingComment() != null) {
                updateReqDto.setPendingComment(reqBean.getPendingComment());
            }
            updateReqDto.setModifyStatus(reqBean.getModifyStatus());
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
