/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.userGroupManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.userGroupLockCheck.UserGroupLockCheckReqDto;
import mm.aeon.com.ass.base.dto.userGroupLockCheck.UserGroupLockCheckResDto;
import mm.aeon.com.ass.base.service.userGroupLockService.UserGroupLockServiceReqBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.userGroupList.UserGroupListFormBean;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class UserGroupLockController extends AbstractController
        implements IControllerAccessor<UserGroupListFormBean, UserGroupListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public UserGroupListFormBean process(UserGroupListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;
        formBean.setCheckLockFlag(false);

        UserGroupLockCheckReqDto reqDto = new UserGroupLockCheckReqDto();
        reqDto.setGroupId(formBean.getLineBean().getGroupId());

        try {
            UserGroupLockCheckResDto resCheck =
                    (UserGroupLockCheckResDto) CommonUtil.getDaoServiceInvoker().execute(reqDto);
            if (resCheck == null) {
                msgBean = new MessageBean(MessageId.ME1009);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
                formBean.setCheckLockFlag(true);
            } else {
                if (!resCheck.getLockFlag()
                        || resCheck.getLockedBy().equals(CommonUtil.getLoginUserInfo().getUserName())) {
                    // Lock start
                    UserGroupLockServiceReqBean lockServiceReqBean = new UserGroupLockServiceReqBean();
                    lockServiceReqBean.setGroupId(formBean.getLineBean().getGroupId());
                    lockServiceReqBean.setLockFlag(true);
                    lockServiceReqBean.setLockedTime(CommonUtil.getCurrentTimeStamp());
                    lockServiceReqBean.setLockedBy(CommonUtil.getLoginUserInfo().getUserName());
                    this.getServiceInvoker().addRequest(lockServiceReqBean);
                    ResponseMessage responseMessage = this.getServiceInvoker().invoke();
                    AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
                    String serviceStatus = resBean.getServiceStatus();

                    if (ServiceStatusCode.OK.equals(serviceStatus)) {
                        msgBean = new MessageBean(MessageId.MI0002);
                        msgBean.setMessageType(MessageType.INFO);
                        formBean.getMessageContainer().addMessage(msgBean);
                        applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                        applicationLogger.log("User group lock process finished.", LogLevel.INFO);
                    } else {
                        setErrorMessage(formBean, serviceStatus);
                    }
                } else {
                    msgBean = new MessageBean(MessageId.ME1054);
                    msgBean.setMessageType(MessageType.ERROR);
                    formBean.getMessageContainer().addMessage(msgBean);
                    applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
                    formBean.setCheckLockFlag(true);
                }
            }
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
        return formBean;
    }

    private UserGroupListFormBean setErrorMessage(UserGroupListFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("User group lock data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("User group lock data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("User group lock data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("User group lock data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
