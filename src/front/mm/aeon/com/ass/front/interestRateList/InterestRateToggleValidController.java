/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.interestRateList;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.interestRateCount.InterestRateCountReqDto;
import mm.aeon.com.ass.base.service.interestRateUpdateService.InterestRateUpdateServiceReqBean;
import mm.aeon.com.ass.front.common.constants.DisplayItem;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.DisplayItemBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class InterestRateToggleValidController extends AbstractController
        implements IControllerAccessor<InterestRateListFormBean, InterestRateListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public InterestRateListFormBean process(InterestRateListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);

        /*
         * if (!VCSCommon.ONE.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
         * logger.log("Invalid URL Access.[Security List]", new InvalidScreenTransitionException(), LogLevel.ERROR);
         * throw new InvalidScreenTransitionException(); }
         */
        applicationLogger.log("Interest Rate valid status update started.", LogLevel.INFO);

        MessageBean msgBean;
        String serviceStatus;

        if (formBean.getLineBean().getDelFlag() == VCSCommon.VALUE_TRUE) {
            try {
                InterestRateCountReqDto interestRateCountReqDto = new InterestRateCountReqDto();
                int count = (int) CommonUtil.getDaoServiceInvoker().execute(interestRateCountReqDto);

                if (count > 0) {
                    msgBean = new MessageBean(MessageId.ME1043,
                            DisplayItemBean.getDisplayItemName(DisplayItem.INTEREST_RATE));
                    msgBean.setMessageType(MessageType.ERROR);
                    formBean.getMessageContainer().addMessage(msgBean);
                    return formBean;
                }
            } catch (PrestoDBException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        InterestRateUpdateServiceReqBean updateServiceReqBean = new InterestRateUpdateServiceReqBean();
        updateServiceReqBean
                .setDelFlag(formBean.getLineBean().getDelFlag() == VCSCommon.VALUE_FALSE ? VCSCommon.VALUE_TRUE
                        : VCSCommon.VALUE_FALSE);
        updateServiceReqBean.setInterestId(formBean.getLineBean().getInterestId());
        updateServiceReqBean.setUpdatedTime(formBean.getLineBean().getUpdatedTime());

        /*
         * int zeroOrOne = formBean.getLineBean().getDelFlag(); zeroOrOne ^= 1;
         * updateServiceReqBean.setDelFlag(zeroOrOne);
         * updateServiceReqBean.setUpdatedBy(CommonUtil.getLoginUserInfo().getUserId());
         * updateServiceReqBean.setDisabledBy(CommonUtil.getLoginUserInfo().getUserId());
         * updateServiceReqBean.setValidStatusToggle(true);
         */
        this.getServiceInvoker().addRequest(updateServiceReqBean);
        ResponseMessage responseMessage = this.getServiceInvoker().invoke();
        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.MI0002);
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Interest Rate valid status update finished.", LogLevel.INFO);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            // formBean.getUpdateParam().setUpdate(true);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            // formBean.getUpdateParam().setUpdate(true);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }
        return formBean;
    }

}
