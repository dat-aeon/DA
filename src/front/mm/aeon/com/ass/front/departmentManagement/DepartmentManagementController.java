/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.departmentManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.service.departmentInfoRegisterService.DepartmentInfoRegisterServiceReqBean;
import mm.aeon.com.ass.base.service.departmentInfoUpdateService.DepartmentInfoUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.userInfoRegisterService.UserInfoRegisterServiceReqBean;
import mm.aeon.com.ass.base.service.userInfoUpdateService.UserInfoUpdateServiceReqBean;
import mm.aeon.com.ass.front.common.constants.DisplayItem;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.DisplayItemBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.authenticate.PasswordEncoder;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;
import mm.com.dat.presto.utils.common.InputChecker;

public class DepartmentManagementController extends AbstractController
        implements IControllerAccessor<DepartmentManagementFormBean, DepartmentManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public DepartmentManagementFormBean process(DepartmentManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        if (!isValidData(formBean)) {
            return formBean;
        }

        String serviceStatus = null;

        if (!formBean.getIsUpdate()) {

            applicationLogger.log("Department Register Process started.", LogLevel.INFO);

            DepartmentInfoRegisterServiceReqBean serviceReqBean = new DepartmentInfoRegisterServiceReqBean();

            serviceReqBean.setName(formBean.getRegisterHeaderBean().getName());

            this.getServiceInvoker().addRequest(serviceReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();

            AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();

            if (ServiceStatusCode.OK.equals(serviceStatus)) {

                msgBean = new MessageBean(MessageId.MI0001);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Department registration finished.", LogLevel.INFO);

            } else {
                setErrorMessage(formBean, serviceStatus);
            }

        } else {
            applicationLogger.log("Department Update Process started.", LogLevel.INFO);

            DepartmentInfoUpdateServiceReqBean updateServiceReqBean = new DepartmentInfoUpdateServiceReqBean();

            updateServiceReqBean.setDepartmentId(formBean.getRegisterHeaderBean().getDepartmentId());
            updateServiceReqBean.setName(formBean.getRegisterHeaderBean().getName());
            updateServiceReqBean.setUpdatedTime(formBean.getRegisterHeaderBean().getUpdatedTime());

            this.getServiceInvoker().addRequest(updateServiceReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();
            AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();

            if (ServiceStatusCode.OK.equals(serviceStatus)) {

                formBean.getUpdateParam().setUpdate(true);
                msgBean = new MessageBean(MessageId.MI0002);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Department update process finished.", LogLevel.INFO);
            } else {
                setErrorMessage(formBean, serviceStatus);
            }
        }

        return formBean;
    }

    private DepartmentManagementFormBean setErrorMessage(DepartmentManagementFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.DEPARTMENT_NAME);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Registerd admin data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Update admin data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating admin data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating admin data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

    private boolean isValidData(DepartmentManagementFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;


        if (InputChecker.isBlankOrNull(formBean.getRegisterHeaderBean().getName())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.NAME));
            msgBean.addColumnId(DisplayItem.NAME);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        return isValid;
    }

}
