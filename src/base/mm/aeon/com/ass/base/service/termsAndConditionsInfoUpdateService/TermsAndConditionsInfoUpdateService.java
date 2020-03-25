/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.termsAndConditionsInfoUpdateService;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.termsAndConditionsInfoSelectForUpdate.TermsAndConditionsInfoSelectForUpdateReqDto;
import mm.aeon.com.ass.base.dto.termsAndConditionsInfoSelectForUpdate.TermsAndConditionsInfoSelectForUpdateResDto;
import mm.aeon.com.ass.base.dto.termsAndConditionsInfoUpdate.TermsAndConditionsInfoUpdateReqDto;
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

public class TermsAndConditionsInfoUpdateService extends AbstractService
        implements IService<TermsAndConditionsInfoUpdateServiceReqBean, TermsAndConditionsInfoUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public TermsAndConditionsInfoUpdateServiceResBean execute(TermsAndConditionsInfoUpdateServiceReqBean reqBean) {

        TermsAndConditionsInfoUpdateServiceResBean resBean = new TermsAndConditionsInfoUpdateServiceResBean();
        TermsAndConditionsInfoSelectForUpdateReqDto selectForUpdateReqDto =
                new TermsAndConditionsInfoSelectForUpdateReqDto();
        selectForUpdateReqDto.setTermsAndConditionsId(reqBean.getTermsAndConditionsId());

        try {
            TermsAndConditionsInfoSelectForUpdateResDto selectForUpdateResDto =
                    (TermsAndConditionsInfoSelectForUpdateResDto) this.getDaoServiceInvoker()
                            .execute(selectForUpdateReqDto);

            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdatedTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                TermsAndConditionsInfoUpdateReqDto updateReqDto = new TermsAndConditionsInfoUpdateReqDto();
                updateReqDto.setTermsAndConditionsId(reqBean.getTermsAndConditionsId());
                updateReqDto.setDescEng(reqBean.getDescEng());
                updateReqDto.setDescMyan(reqBean.getDescMyan());
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
