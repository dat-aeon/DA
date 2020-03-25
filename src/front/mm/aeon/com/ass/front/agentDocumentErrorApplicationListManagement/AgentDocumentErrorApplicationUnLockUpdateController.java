/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentDocumentErrorApplicationListManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.service.agentDocumentErrorUpdateService.AgentDocumentErrorApplicationInfoLockUpdateServiceReqBean;
import mm.aeon.com.ass.front.agentDocumentErrorApplicationList.AgentDocumentErrorApplicationListFormBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
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

public class AgentDocumentErrorApplicationUnLockUpdateController extends AbstractController implements
        IControllerAccessor<AgentDocumentErrorApplicationListFormBean, AgentDocumentErrorApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public AgentDocumentErrorApplicationListFormBean process(AgentDocumentErrorApplicationListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        if (!formBean.getNotyetFlag())
            return formBean;

        AgentDocumentErrorApplicationInfoLockUpdateServiceReqBean unlockServiceReqBean =
                new AgentDocumentErrorApplicationInfoLockUpdateServiceReqBean();
        unlockServiceReqBean.setApplicationId(formBean.getLineBean().getApplicationId());
        unlockServiceReqBean.setLockFlag(false);
        this.getServiceInvoker().addRequest(unlockServiceReqBean);
        ResponseMessage responseMessage = this.getServiceInvoker().invoke();
        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        String serviceStatus = resBean.getServiceStatus();
        if (ServiceStatusCode.OK.equals(serviceStatus)) {
            applicationLogger.log("Agent Document Error application unlock update process finished.", LogLevel.INFO);
        } else {
            setErrorMessage(formBean, serviceStatus);
        }

        return formBean;
    }

    private AgentDocumentErrorApplicationListFormBean setErrorMessage(
            AgentDocumentErrorApplicationListFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent Document Error application unlock update data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent Document Error application unlock update data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent Document Error application unlock update data already updated.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent Document Error application unlock update data already deleted by other.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
