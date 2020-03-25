/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.departmentInfoUpdateService;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.departmentInfoSelectForUpdate.DepartmentInfoSelectForUpdateReqDto;
import mm.aeon.com.ass.base.dto.departmentInfoSelectForUpdate.DepartmentInfoSelectForUpdateResDto;
import mm.aeon.com.ass.base.dto.departmentInfoUpdate.DepartmentInfoUpdateReqDto;
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

public class DepartmentInfoUpdateService extends AbstractService
        implements IService<DepartmentInfoUpdateServiceReqBean, DepartmentInfoUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public DepartmentInfoUpdateServiceResBean execute(DepartmentInfoUpdateServiceReqBean reqBean) {

        DepartmentInfoUpdateServiceResBean resBean = new DepartmentInfoUpdateServiceResBean();

        DepartmentInfoSelectForUpdateReqDto selectForUpdateReqDto = new DepartmentInfoSelectForUpdateReqDto();
        selectForUpdateReqDto.setDepartmentId(reqBean.getDepartmentId());

        try {
            DepartmentInfoSelectForUpdateResDto selectForUpdateResDto =
                    (DepartmentInfoSelectForUpdateResDto) this.getDaoServiceInvoker().execute(selectForUpdateReqDto);

            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdatedTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                DepartmentInfoUpdateReqDto updateReqDto = new DepartmentInfoUpdateReqDto();

                updateReqDto.setDepartmentId(reqBean.getDepartmentId());
                updateReqDto.setDelFlag(reqBean.getDelFlag());
                updateReqDto.setName(reqBean.getName());
                updateReqDto.setUpdatedBy(CommonUtil.getLoginUserInfo().getUserId());
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
