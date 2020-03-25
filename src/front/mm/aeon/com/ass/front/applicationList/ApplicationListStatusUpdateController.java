/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.applicationList;

import java.util.List;

import mm.aeon.com.ass.base.dto.applicationUpdateStatus.ApplicationUpdateStatusSearchReqBean;
import mm.aeon.com.ass.base.dto.applicationUpdateStatus.ApplicationUpdateStatusSearchResBean;
import mm.aeon.com.ass.base.service.applicationInfoStatus.ApplicationInfoStatusUpdateServiceReqBean;
import mm.aeon.com.ass.front.common.abstractController.AbstractDAController;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class ApplicationListStatusUpdateController extends AbstractDAController
        implements IControllerAccessor<ApplicationListFormBean, ApplicationListFormBean> {
    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public ApplicationListFormBean process(ApplicationListFormBean formBean) {
        // TODO Auto-generated method stub

        formBean.getMessageContainer().clearAllMessages(true);

        applicationLogger.log("Application Update Status started.", LogLevel.INFO);
        MessageBean messageBean;
        ApplicationUpdateStatusSearchReqBean reqSearchBean = new ApplicationUpdateStatusSearchReqBean();

        try {
            List<ApplicationUpdateStatusSearchResBean> resDtoList =
                    (List<ApplicationUpdateStatusSearchResBean>) CommonUtil.getDaoServiceInvoker()
                            .execute(reqSearchBean);

            applicationLogger.log("Application update Status finished.", LogLevel.INFO);
            for (ApplicationUpdateStatusSearchResBean resDto : resDtoList) {

                applicationLogger.log("Application Status Update Process started.", LogLevel.INFO);

                ApplicationInfoStatusUpdateServiceReqBean updateServiceReqBean =
                        new ApplicationInfoStatusUpdateServiceReqBean();

                updateServiceReqBean.setApplicationNo(resDto.getApplicationNo());
                updateServiceReqBean.setFinancialStatus(resDto.getFinancialStatus());
                updateServiceReqBean.setJudgementDate(resDto.getJudgementDate());
                updateServiceReqBean.setJudgementStatus(resDto.getJudgementStatus());
                updateServiceReqBean.setApplicationStatus(resDto.getApplicationStatus());
                updateServiceReqBean.setFinancialAmount(resDto.getFinancialAmount());
                updateServiceReqBean.setFinancialTerm(resDto.getFinancialTerm());
                if (updateServiceReqBean.getApplicationStatus() < 8
                        || updateServiceReqBean.getApplicationStatus() == 12) {
                    if (updateServiceReqBean.getApplicationStatus() == 12) {
                        if (resDto.getUpdatedTime().before(updateServiceReqBean.getJudgementDate())) {
                            this.getServiceInvoker().addRequest(updateServiceReqBean);
                            ResponseMessage responseMessage = this.getServiceInvoker().invoke();
                            AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
                        }

                    } else {
                        this.getServiceInvoker().addRequest(updateServiceReqBean);
                        ResponseMessage responseMessage = this.getServiceInvoker().invoke();
                        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
                    }
                }
                applicationLogger.log("Applicaton status update process finished.", LogLevel.INFO);

            }
            messageBean = new MessageBean(MessageId.MI0023);
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
            messageBean = new MessageBean(MessageId.ME1049);
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
        }

        return formBean;
    }
}
