/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.purchaseAttachmentPhotoCheck;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.service.purchaseAttachmentPhotoCheckService.PurchaseAttachmentPhotoCheckServiceReqBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
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

public class PurchaseAttachmentPhotoCheckApproveRejectController extends AbstractController
        implements IControllerAccessor<PurchaseAttachmentPhotoCheckFormBean, PurchaseAttachmentPhotoCheckFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public PurchaseAttachmentPhotoCheckFormBean process(PurchaseAttachmentPhotoCheckFormBean formBean) {

        String serviceStatus = null;

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        PurchaseAttachmentPhotoCheckLineBean lineBean = formBean.getLineBean();
        if (formBean.getLineBean() == null) {
            msgBean = new MessageBean(MessageId.ME1050);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Checking attachment data already checked by other.", LogLevel.ERROR);

            return formBean;
        }

        PurchaseAttachmentPhotoCheckServiceReqBean reqBean = new PurchaseAttachmentPhotoCheckServiceReqBean();
        reqBean.setStatus(formBean.isRejectFlag() ? 3 : 2);
        if (reqBean.getStatus() == 3 && (formBean.getHeaderBean().getRejectComment().trim() == null
                || formBean.getHeaderBean().getRejectComment().trim().equals(""))) {
            msgBean = new MessageBean(MessageId.ME1062);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Need Reject Comment.", LogLevel.ERROR);

            return formBean;

        } else if (reqBean.getStatus() == 3) {
            reqBean.setRejectComment(formBean.getHeaderBean().getRejectComment());
        }
        reqBean.setCheckingAttachmentId(lineBean.getCheckingAttachmentId());
        reqBean.setUpdatedTime(lineBean.getUpdatedTime());
        this.getServiceInvoker().addRequest(reqBean);
        ResponseMessage responseMessage = this.getServiceInvoker().invoke();

        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {

            applicationLogger.log("Attachment photo checked.", LogLevel.INFO);
        } else {
            setErrorMessage(formBean, serviceStatus);
        }
        return formBean;
    }

    private PurchaseAttachmentPhotoCheckFormBean setErrorMessage(PurchaseAttachmentPhotoCheckFormBean formBean,
            String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1051);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Checking attachment data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1052);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Checking attachment data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1050);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Checking attachment data already checked by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
