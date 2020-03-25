/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.departmentInfoRegisterService;

import mm.aeon.com.ass.base.dto.departmentInfoRegister.DepartmentInfoRegisterReqDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class DepartmentInfoRegisterService extends AbstractService
        implements IService<DepartmentInfoRegisterServiceReqBean, DepartmentInfoRegisterServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public DepartmentInfoRegisterServiceResBean execute(DepartmentInfoRegisterServiceReqBean reqBean) {

        DepartmentInfoRegisterReqDto reqDto = new DepartmentInfoRegisterReqDto();

        DepartmentInfoRegisterServiceResBean resBean = new DepartmentInfoRegisterServiceResBean();

        reqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
        reqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
        reqDto.setName(reqBean.getName());

        try {
            this.getDaoServiceInvoker().execute(reqDto);
            resBean.setServiceStatus(ServiceStatusCode.OK);
        } catch (Exception e) {
            if (e instanceof RecordDuplicatedException) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_DUPLICATED_ERROR);

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
