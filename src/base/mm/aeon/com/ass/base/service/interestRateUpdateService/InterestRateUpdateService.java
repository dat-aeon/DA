/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.interestRateUpdateService;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.interestRateSelectForUpdate.InterestRateSelectForUpdateReqDto;
import mm.aeon.com.ass.base.dto.interestRateSelectForUpdate.InterestRateSelectForUpdateResDto;
import mm.aeon.com.ass.base.dto.interestRateUpdate.InterestRateUpdateReqDto;
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

public class InterestRateUpdateService extends AbstractService
        implements IService<InterestRateUpdateServiceReqBean, InterestRateUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public InterestRateUpdateServiceResBean execute(InterestRateUpdateServiceReqBean reqBean) {

        InterestRateUpdateServiceResBean resBean = new InterestRateUpdateServiceResBean();
        InterestRateSelectForUpdateReqDto selectForUpdateReqDto = new InterestRateSelectForUpdateReqDto();
        selectForUpdateReqDto.setInterestId(reqBean.getInterestId());

        try {
            InterestRateSelectForUpdateResDto selectForUpdateResDto =
                    (InterestRateSelectForUpdateResDto) this.getDaoServiceInvoker().execute(selectForUpdateReqDto);

            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdatedTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                InterestRateUpdateReqDto updateReqDto = new InterestRateUpdateReqDto();
                updateReqDto.setInterestId(reqBean.getInterestId());
                updateReqDto.setInterestRate(reqBean.getInterestRate());
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
