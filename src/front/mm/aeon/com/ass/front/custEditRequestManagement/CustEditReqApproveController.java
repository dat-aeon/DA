/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.custEditRequestManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.service.custEditReqUpdateService.CustEditReqUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.custEditReqUpdateService.CustStatusApproveServiceReqBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.custEditRequestList.CustEditReqListFormBean;
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

public class CustEditReqApproveController extends AbstractController
        implements IControllerAccessor<CustEditReqListFormBean, CustEditReqListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public CustEditReqListFormBean process(CustEditReqListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        String serviceStatus = null;
        String status = null;

        applicationLogger.log("Edit Request Info Update Process started.", LogLevel.INFO);
        // update customer info table
        CustEditReqUpdateServiceReqBean updateServiceReqBean = new CustEditReqUpdateServiceReqBean();
        updateServiceReqBean.setCustomerId(formBean.getLineBean().getCustomerId());
        updateServiceReqBean.setCustEditReqId(formBean.getLineBean().getCustEditReqId());
        updateServiceReqBean.setName(formBean.getLineBean().getName());
        updateServiceReqBean.setDob(formBean.getLineBean().getDob());
        updateServiceReqBean.setPhone_no(formBean.getLineBean().getPhone_no());
        updateServiceReqBean.setNrc_no(formBean.getLineBean().getNrc_no());
        updateServiceReqBean.setUpdatedBy(CommonUtil.getLoginUserName());
        updateServiceReqBean.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
        updateServiceReqBean.setStatus(2);
        this.getServiceInvoker().addRequest(updateServiceReqBean);
        msgBean = new MessageBean(MessageId.MI0002);
        msgBean.setMessageType(MessageType.INFO);
        formBean.getMessageContainer().addMessage(msgBean);

        ResponseMessage responseMessage = this.getServiceInvoker().invoke();
        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {

            msgBean = new MessageBean(MessageId.MI0002);
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Customer Info update process finished.", LogLevel.INFO);

            CustStatusApproveServiceReqBean reqBean = new CustStatusApproveServiceReqBean();
            reqBean.setCustEditReqId(formBean.getLineBean().getCustEditReqId());
            this.getServiceInvoker().addRequest(reqBean);
            ResponseMessage resMsg = this.getServiceInvoker().invoke();
            AbstractServiceResBean respBean = resMsg.getMessageBean(0);
            status = respBean.getServiceStatus();
            if (ServiceStatusCode.OK.equals(status)) {

                applicationLogger.log("CustEdit deleteing Process finished.", LogLevel.INFO);
            }
        } else {
            setErrorMessage(formBean, serviceStatus);
        }

        return formBean;
    }

    private CustEditReqListFormBean setErrorMessage(CustEditReqListFormBean formBean, String serviceStatus) {

        MessageBean msgBean;
        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Customer edit request approve data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Customer edit request approve data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Customer edit request approve data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Customer edit request approve data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

}
