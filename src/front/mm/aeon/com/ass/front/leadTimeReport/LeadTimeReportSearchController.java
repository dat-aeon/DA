/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.leadTimeReport;

import mm.aeon.com.ass.base.dto.leadTimeReportSearch.LeadTimeReportCountSearchReqDto;
import mm.aeon.com.ass.base.dto.leadTimeReportSearch.LeadTimeReportSearchReqDto;
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

public class LeadTimeReportSearchController extends AbstractController
        implements IControllerAccessor<LeadTimeReportFormBean, LeadTimeReportFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public LeadTimeReportFormBean process(LeadTimeReportFormBean formBean) {
        // TODO Auto-generated method stub
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());
        applicationLogger.log("Agent Sale Claim Finished Report searching process started", LogLevel.INFO);
        MessageBean messageBean;

        LeadTimeReportCountSearchReqDto reqcountDto = new LeadTimeReportCountSearchReqDto();

        reqcountDto.setApplicationNo(formBean.getHeaderBean().getApplicationNo());
        reqcountDto.setCustomerName(formBean.getHeaderBean().getCustomerName());
        reqcountDto.setPhoneNo(formBean.getHeaderBean().getPhoneNo());
        reqcountDto.setNrcNo(formBean.getHeaderBean().getNrcNo());
        reqcountDto.setAgreementNo(formBean.getHeaderBean().getAgreementNo());
        reqcountDto.setAgentInformationReceivedDateFrom(formBean.getHeaderBean().getAgentInformationReceivedDateFrom());
        reqcountDto.setAgentInformationReceivedDateTo(formBean.getHeaderBean().getAgentInformationReceivedDateTo());

        LeadTimeReportSearchReqDto reqDto = new LeadTimeReportSearchReqDto();
        reqDto.setApplicationNo(reqcountDto.getApplicationNo());
        reqDto.setCustomerName(reqcountDto.getCustomerName());
        reqDto.setPhoneNo(reqcountDto.getPhoneNo());
        reqDto.setNrcNo(reqcountDto.getNrcNo());
        reqDto.setAgreementNo(reqcountDto.getAgreementNo());
        reqDto.setAgentInformationReceivedDateFrom(reqcountDto.getAgentInformationReceivedDateFrom());
        reqDto.setAgentInformationReceivedDateTo(reqcountDto.getAgentInformationReceivedDateTo());

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(reqcountDto);
            formBean.setTotalCount(totalCount);
            formBean.setLeadTimeReportSearchReqDto(reqDto);
            if (totalCount == 0)
                messageBean = new MessageBean(MessageId.MI0008);
            else
                messageBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Agent Sale Claim Finished Report searching finished", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
        return formBean;
    }

}
