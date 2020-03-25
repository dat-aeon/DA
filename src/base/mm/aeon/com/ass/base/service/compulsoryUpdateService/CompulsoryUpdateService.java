/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.compulsoryUpdateService;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.compulsoryInfoSelectForUpdate.CompulsorySelectForUpdateReqDto;
import mm.aeon.com.ass.base.dto.compulsoryInfoSelectForUpdate.CompulsorySelectForUpdateResDto;
import mm.aeon.com.ass.base.dto.compulsoryUpdate.CompulsoryUpdateReqDto;
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

public class CompulsoryUpdateService extends AbstractService
        implements IService<CompulsoryUpdateServiceReqBean, CompulsoryUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public CompulsoryUpdateServiceResBean execute(CompulsoryUpdateServiceReqBean reqBean) {

        CompulsoryUpdateServiceResBean resBean = new CompulsoryUpdateServiceResBean();
        CompulsorySelectForUpdateReqDto selectForUpdateReqDto = new CompulsorySelectForUpdateReqDto();
        selectForUpdateReqDto.setCompulsoryId(reqBean.getCompulsoryId());

        try {
            CompulsorySelectForUpdateResDto selectForUpdateResDto =
                    (CompulsorySelectForUpdateResDto) this.getDaoServiceInvoker().execute(selectForUpdateReqDto);

            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdatedTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                CompulsoryUpdateReqDto updateReqDto = new CompulsoryUpdateReqDto();
                updateReqDto.setCompulsoryId(reqBean.getCompulsoryId());
                updateReqDto.setCompulsoryAmount(reqBean.getCompulsoryAmount());
                updateReqDto.setDelFlag(reqBean.getDelFlag());
                updateReqDto.setUpdatedBy(reqBean.getUpdatedBy());
                updateReqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());

                this.getDaoServiceInvoker().execute(updateReqDto);
                resBean.setServiceStatus(ServiceStatusCode.OK);
            }

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
