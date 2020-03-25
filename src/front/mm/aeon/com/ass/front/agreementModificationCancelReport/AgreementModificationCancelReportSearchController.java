/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationCancelReport;

import mm.aeon.com.ass.base.dto.agreementModificationCancelReportSearch.AgreementModificationCancelReportCountSearchReqDto;
import mm.aeon.com.ass.base.dto.agreementModificationCancelReportSearch.AgreementModificationCancelReportSearchReqDto;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgreementModificationCancelReportSearchController extends AbstractController implements
        IControllerAccessor<AgreementModificationCancelReportFormBean, AgreementModificationCancelReportFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public AgreementModificationCancelReportFormBean process(AgreementModificationCancelReportFormBean formBean) {
        // TODO Auto-generated method stub
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());
        applicationLogger.log("Agent Sale Report searching process started", LogLevel.INFO);
        MessageBean messageBean;

        AgreementModificationCancelReportCountSearchReqDto countReqDto =
                new AgreementModificationCancelReportCountSearchReqDto();
        AgreementModificationCancelReportSearchReqDto reqDto = new AgreementModificationCancelReportSearchReqDto();

        if (formBean.getHeaderBean().getApplicationNo() != null) {
            countReqDto.setApplicationNo(formBean.getHeaderBean().getApplicationNo());
        }
        if (formBean.getHeaderBean().getApplicantName() != null) {
            countReqDto.setApplicantName(formBean.getHeaderBean().getApplicantName());
        }
        if (formBean.getHeaderBean().getPhoneNo() != null) {
            countReqDto.setPhoneNo(formBean.getHeaderBean().getPhoneNo());
        }
        if (formBean.getHeaderBean().getNrcNo() != null) {
            countReqDto.setNrcNo(formBean.getHeaderBean().getNrcNo());
        }
        if (formBean.getHeaderBean().getModifyTimeFrom() != null) {
            countReqDto.setModifyTimeFrom(formBean.getHeaderBean().getModifyTimeFrom());
        }
        if (formBean.getHeaderBean().getModifyTimeTo() != null) {
            countReqDto.setModifyTimeTo(formBean.getHeaderBean().getModifyTimeTo());
        }
        //
        if (formBean.getHeaderBean().getApplicationNo() != null) {
            reqDto.setApplicationNo(formBean.getHeaderBean().getApplicationNo());
        }
        if (formBean.getHeaderBean().getApplicantName() != null) {
            reqDto.setApplicantName(formBean.getHeaderBean().getApplicantName());
        }
        if (formBean.getHeaderBean().getPhoneNo() != null) {
            reqDto.setPhoneNo(formBean.getHeaderBean().getPhoneNo());
        }
        if (formBean.getHeaderBean().getNrcNo() != null) {
            reqDto.setNrcNo(formBean.getHeaderBean().getNrcNo());
        }
        if (formBean.getHeaderBean().getModifyTimeFrom() != null) {
            reqDto.setModifyTimeFrom(formBean.getHeaderBean().getModifyTimeFrom());
        }
        if (formBean.getHeaderBean().getModifyTimeTo() != null) {
            reqDto.setModifyTimeTo(formBean.getHeaderBean().getModifyTimeTo());
        }

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setTotalCount(totalCount);
            formBean.setAgreementModificationCancelReportSearchReqDto(reqDto);
            if (totalCount == 0)
                messageBean = new MessageBean(MessageId.MI0008);
            else
                messageBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Agent Sale Report searching finished", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
        return formBean;
    }

}
