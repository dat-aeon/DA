/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.saleEntryInfoListManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.service.commonApplicationUpdateService.CommonApplicationInfoLockUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.saleEntryInfoUpdateService.SaleEntryApplicationStatusUpdateReqBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.saleEntryInfoList.SaleEntryInfoListFormBean;
import mm.aeon.com.ass.front.sessions.LoginUserInfo;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class SaleEntryInfoApplicationCheckStatusUpdateController extends AbstractController
        implements IControllerAccessor<SaleEntryInfoListFormBean, SaleEntryInfoListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public SaleEntryInfoListFormBean process(SaleEntryInfoListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        ResponseMessage responseMessage;
        AbstractServiceResBean resBean;
        String serviceStatus = null;
        LoginUserInfo loginUser = new LoginUserInfo();
        loginUser = CommonUtil.getLoginUserInfo();
        String userId = String.valueOf(loginUser.getId());
        String userTypeId = loginUser.getUserTypeId();

        // index finished action
        applicationLogger.log("Agent And Product application upload status update process started.", LogLevel.INFO);
        SaleEntryApplicationStatusUpdateReqBean updateServiceReqBean = new SaleEntryApplicationStatusUpdateReqBean();
        updateServiceReqBean.setDa_application_info_id(formBean.getLineBean().getApplicationId());
        updateServiceReqBean.setSaleEntryCheckFlag(true);
        updateServiceReqBean.setUpdated_by(String.valueOf(userId) + "," + userTypeId);
        updateServiceReqBean.setUpdated_time(CommonUtil.getCurrentTimeStamp());

        this.getServiceInvoker().addRequest(updateServiceReqBean);
        responseMessage = this.getServiceInvoker().invoke();
        resBean = responseMessage.getMessageBean(0);
        serviceStatus = resBean.getServiceStatus();
        // index status update finished

        if (ServiceStatusCode.OK.equals(serviceStatus)) {
            // unlock
            CommonApplicationInfoLockUpdateServiceReqBean unLockServiceReqBean =
                    new CommonApplicationInfoLockUpdateServiceReqBean();
            unLockServiceReqBean.setApplicationId(formBean.getLineBean().getApplicationId());
            unLockServiceReqBean.setLockFlag(false);
            unLockServiceReqBean.setLockTime(CommonUtil.getCurrentTimeStamp());
            unLockServiceReqBean.setLockBy(CommonUtil.getLoginUserInfo().getUserName());
            this.getServiceInvoker().addRequest(unLockServiceReqBean);
            responseMessage = this.getServiceInvoker().invoke();
            resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();

        } else {
            setErrorMessage(formBean, serviceStatus);
        }

        return formBean;
    }

    private SaleEntryInfoListFormBean setErrorMessage(SaleEntryInfoListFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent And Product application upload status update data already exist.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent And Product application upload status update data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent And Product application upload status update data already updated.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent And Product application upload status update data already deleted by other.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
