/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.menuAssignGroupService;

import mm.aeon.com.ass.base.dto.menuAssignGroupRegister.MenuAssignGroupRegisterReqDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class MenuAssignGroupService extends AbstractService
        implements IService<MenuAssignGroupServiceReqBean, MenuAssignGroupServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public MenuAssignGroupServiceResBean execute(MenuAssignGroupServiceReqBean reqBean) {

        MenuAssignGroupRegisterReqDto reqDto = new MenuAssignGroupRegisterReqDto();

        MenuAssignGroupServiceResBean resBean = new MenuAssignGroupServiceResBean();

        reqDto.setGroupId(reqBean.getUserGroupId());
        reqDto.setMenuId(reqBean.getMenuId());
        reqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
        reqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
        try {
            this.getDaoServiceInvoker().execute(reqDto);
            if (reqDto.getMenuId() != null) {
                resBean.setServiceStatus(ServiceStatusCode.OK);
            }
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
