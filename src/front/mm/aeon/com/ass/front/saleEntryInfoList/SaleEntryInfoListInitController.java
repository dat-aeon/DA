/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.saleEntryInfoList;

import mm.aeon.com.ass.front.common.abstractController.AbstractDAController;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class SaleEntryInfoListInitController extends AbstractDAController
        implements IControllerAccessor<SaleEntryInfoListFormBean, SaleEntryInfoListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public SaleEntryInfoListFormBean process(SaleEntryInfoListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);

        /*
         * if (!VCSCommon.ONE.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
         * logger.log("Invalid URL Access.[Customer List Init]", new InvalidScreenTransitionException(),
         * LogLevel.ERROR); throw new InvalidScreenTransitionException(); }
         */

        applicationLogger.log("Customer Init process stared.", LogLevel.INFO);
        MessageBean messageBean;

        formBean.setApplicationStatusSelectItemList(super.getApplicationStatuselectList());
        if (formBean.getApplicationStatusSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "APPLICATION_STATUS");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setLoanTypeSelectItemList(super.getLoanTypeSelectList());
        if (formBean.getLoanTypeSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "LOAN_TYPE");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setGenderSelectItemList(super.getGenderSelectList());
        if (formBean.getGenderSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "GENDER");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setApplicationTypeSelectItemList(super.getApplicationTypeSelectList());
        if (formBean.getApplicationTypeSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "APPLICATION_TYPE");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setNationalitySelectItemList(super.getNationalitySelectList());
        if (formBean.getNationalitySelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "NATIONALILTY");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setMaritalStatusSelectItemList(super.getMaritalStatusSelectList());
        if (formBean.getMaritalStatusSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "MARITAL_STATUS");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setTypeOfResidenceSelectItemList(super.getTypeOfResidenceSelectList());
        if (formBean.getTypeOfResidenceSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "TYPE_OF_RESIDENCE");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setLivingWithSelectItemList(super.getLivingWithSelectList());
        if (formBean.getLivingWithSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "LIVING_WITH");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setProductTypeSelectItemList(super.getProductTypeSelectList());
        if (formBean.getProductTypeSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "PRODUCT_TYPE");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setChannelSelectItemList(super.getChannelSelectList());
        if (formBean.getChannelSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "CHANNEL");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setRelationshipSelectItemList(super.getRelationshipSelectList());
        if (formBean.getRelationshipSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "Relationship");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setCompanyStatusSelectItemList(super.getCompanyStatusSelectList());
        if (formBean.getCompanyStatusSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "Company Status");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setSettlementTypeSelectItemList(super.getSettlementStatusSelectList());
        if (formBean.getSettlementTypeSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "Settlement Status");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setUserTypeSelectItemList(super.getUserTypeSelectList());
        if (formBean.getUserTypeSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "User Type Status");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setTownshipSelectItemList(super.getTownshipSelectList());
        if (formBean.getTownshipSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "Township");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setCitySelectItemList(super.getCitySelectList());
        if (formBean.getCitySelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "City");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setEducationSelectItemList(super.getHighestEducationTypeSelectList());
        if (formBean.getEducationSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "Education");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setSaleEntryStatusSelectItemList(super.getSaleEntryTypeSelectList());
        if (formBean.getSaleEntryStatusSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "Sale Entry Status");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        applicationLogger.log("Customer Init process ended.", LogLevel.INFO);
        return formBean;
    }

}
