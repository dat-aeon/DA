/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.operatorManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSelectCountReqDto;
import mm.aeon.com.ass.base.dto.userGroupSelectedList.UserGroupSelectCountReqDto;
import mm.aeon.com.ass.base.service.userInfoRegisterService.UserInfoRegisterServiceReqBean;
import mm.aeon.com.ass.base.service.userInfoRegisterService.UserInfoRegisterServiceResBean;
import mm.aeon.com.ass.base.service.userInfoUpdateService.UserInfoUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.userInfoUpdateService.UserInfoUpdateServiceResBean;
import mm.aeon.com.ass.front.common.constants.DisplayItem;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.DisplayItemBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.authenticate.PasswordEncoder;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;
import mm.com.dat.presto.utils.common.InputChecker;

public class OperatorManagementController extends AbstractController
        implements IControllerAccessor<OperatorManagementFormBean, OperatorManagementFormBean> {

    private ASSLogger logger = new ASSLogger();

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public OperatorManagementFormBean process(OperatorManagementFormBean formBean) {

        MessageBean msgBean;
        formBean.getMessageContainer().clearAllMessages(true);

        String functionName = "";
        if (formBean.getManagementHeaderBean().getForUpdate()) {
            functionName = "Update";
        } else {
            functionName = "Register";
        }

        if (!isValidData(formBean)) {
            return formBean;
        }

        applicationLogger.log("Operator " + functionName + " process started.", LogLevel.INFO);

        String serviceStatus = null;

        DepartmentListSelectCountReqDto countReqDto = new DepartmentListSelectCountReqDto();
        countReqDto.setDepartmentId(formBean.getManagementHeaderBean().getSelectedDepartment());

        UserGroupSelectCountReqDto countGroupReqDto = new UserGroupSelectCountReqDto();
        countGroupReqDto.setGroupId(formBean.getManagementHeaderBean().getGroupId());

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            int totalGroupCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countGroupReqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.ME1058);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
                applicationLogger.log("Department is already deleted by other user", LogLevel.ERROR);
                return formBean;
            }

            if (totalGroupCount == 0) {
                msgBean = new MessageBean(MessageId.ME1059);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
                applicationLogger.log("Group is already deleted by other user", LogLevel.ERROR);
                return formBean;
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        if (!formBean.getManagementHeaderBean().getForUpdate()) {

            UserInfoRegisterServiceReqBean registerReqBean = new UserInfoRegisterServiceReqBean();
            UserInfoRegisterServiceResBean operatorResBean;

            registerReqBean.setCreatedBy(CommonUtil.getLoginUserName());
            registerReqBean.setCreatedTime(CommonUtil.getCurrentTimeStamp());
            registerReqBean.setUserTypeId(VCSCommon.USER_TYPE_DA_OPERATOR_ID);
            registerReqBean.setDepartmentId(formBean.getManagementHeaderBean().getSelectedDepartment());
            registerReqBean.setGroupId(formBean.getManagementHeaderBean().getGroupId());
            registerReqBean.setPassword(PasswordEncoder.encode(formBean.getManagementHeaderBean().getUserLoginId()));
            registerReqBean.setLoginId(formBean.getManagementHeaderBean().getUserLoginId());
            registerReqBean.setName(formBean.getManagementHeaderBean().getUserName());

            this.getServiceInvoker().addRequest(registerReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();
            operatorResBean = responseMessage.getMessageBean(0);
            serviceStatus = operatorResBean.getServiceStatus();

            if (ServiceStatusCode.OK.equals(serviceStatus)) {
                msgBean = new MessageBean(MessageId.MI0001);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Operator " + functionName + " process finished.", LogLevel.INFO);

            }

        } else {

            UserInfoUpdateServiceReqBean updateReqBean = new UserInfoUpdateServiceReqBean();
            UserInfoUpdateServiceResBean updateResBean;

            if (!InputChecker.isBlankOrNull(formBean.getManagementHeaderBean().getPassword())) {
                updateReqBean.setPassword(PasswordEncoder.encode(formBean.getManagementHeaderBean().getPassword()));
            }

            updateReqBean.setUserTypeId(VCSCommon.USER_TYPE_DA_OPERATOR_ID);
            updateReqBean.setUserId(formBean.getManagementHeaderBean().getUserId());
            updateReqBean.setUpdatedBy(CommonUtil.getLoginUserName());
            updateReqBean.setDepartmentId(formBean.getManagementHeaderBean().getSelectedDepartment());
            updateReqBean.setGroupId(formBean.getManagementHeaderBean().getGroupId());
            if (formBean.getRegisterHeaderBean().getResetPassword()) {
                updateReqBean.setPassword(PasswordEncoder.encode(formBean.getManagementHeaderBean().getUserLoginId()));
            }
            updateReqBean.setUpdatedTime(formBean.getManagementHeaderBean().getUpdatedTime());
            updateReqBean.setLoginId(formBean.getManagementHeaderBean().getUserLoginId());
            updateReqBean.setName(formBean.getManagementHeaderBean().getUserName());
            updateReqBean.setIsDelete(false);

            this.getServiceInvoker().addRequest(updateReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();
            updateResBean = responseMessage.getMessageBean(0);
            serviceStatus = updateResBean.getServiceStatus();

            if (ServiceStatusCode.OK.equals(serviceStatus)) {
                msgBean = new MessageBean(MessageId.MI0002);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Operator update process finished.", LogLevel.INFO);
            }
        }

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Operater " + functionName + " data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Operator " + functionName + " data is deleted.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Operator " + functionName + " data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

    private boolean isValidData(OperatorManagementFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;

        if (InputChecker.isBlankOrNull(formBean.getManagementHeaderBean().getUserLoginId())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.LOGIN_ID));
            msgBean.addColumnId(DisplayItem.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        if (InputChecker.isBlankOrNull(formBean.getManagementHeaderBean().getUserName())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.NAME));
            msgBean.addColumnId(DisplayItem.NAME);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        if (formBean.getManagementHeaderBean().getSelectedDepartment() == null) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.DEPARTMENT));
            msgBean.addColumnId(DisplayItem.DEPARTMENT);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        if (formBean.getManagementHeaderBean().getGroupId() == null) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.GROUP));
            msgBean.addColumnId(DisplayItem.GROUP);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        return isValid;
    }

}
