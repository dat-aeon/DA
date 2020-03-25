/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.operatorList;

import mm.aeon.com.ass.front.common.abstractController.AbstractDAController;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class OperatorListInitController extends AbstractDAController
        implements IControllerAccessor<OperatorListFormBean, OperatorListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public OperatorListFormBean process(OperatorListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);

        applicationLogger.log("Operator Init process stared.", LogLevel.INFO);
        MessageBean messageBean;

        formBean.setDepartmentSelectItemList(super.getDepartmentSelectList());
        if (formBean.getDepartmentSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "DEPARTMENT");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setGroupSelectItemList(super.getGroupSelectList());
        if (formBean.getGroupSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "GROUP");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        applicationLogger.log("Operator Init process ended.", LogLevel.INFO);
        return formBean;
    }

}
