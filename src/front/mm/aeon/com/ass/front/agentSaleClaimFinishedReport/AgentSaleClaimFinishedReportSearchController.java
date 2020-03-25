/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleClaimFinishedReport;

import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch.AgentSaleClaimFinishedReportCountSearchReqDto;
import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch.AgentSaleClaimFinishedReportSearchReqDto;
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

public class AgentSaleClaimFinishedReportSearchController extends AbstractController
        implements IControllerAccessor<AgentSaleClaimFinishedReportFormBean, AgentSaleClaimFinishedReportFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentSaleClaimFinishedReportFormBean process(AgentSaleClaimFinishedReportFormBean formBean) {
        // TODO Auto-generated method stub
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());
        applicationLogger.log("Agent Sale Claim Finished Report searching process started", LogLevel.INFO);
        MessageBean messageBean;

        AgentSaleClaimFinishedReportCountSearchReqDto reqcountDto = new AgentSaleClaimFinishedReportCountSearchReqDto();

        if (formBean.getHeaderBean().getAgentName() != null) {
            reqcountDto.setAgentName(formBean.getHeaderBean().getAgentName().trim().toLowerCase());
        }
        if (formBean.getHeaderBean().getLocation() != null) {
            reqcountDto.setLocation(formBean.getHeaderBean().getLocation().trim().toLowerCase());
        }
        reqcountDto.setUploadedDateFrom(formBean.getHeaderBean().getUploadedDateFrom());
        reqcountDto.setUploadedDateTo(formBean.getHeaderBean().getUploadedDateTo());

        AgentSaleClaimFinishedReportSearchReqDto reqDto = new AgentSaleClaimFinishedReportSearchReqDto();
        reqDto.setAgentName(reqcountDto.getAgentName());
        reqDto.setLocation(reqcountDto.getLocation());
        reqDto.setUploadedDateFrom(reqcountDto.getUploadedDateFrom());
        reqDto.setUploadedDateTo(reqcountDto.getUploadedDateTo());

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(reqcountDto);
            formBean.setTotalCount(totalCount);
            formBean.setAgentSaleClaimFinishedReportSearchReqDto(reqDto);
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
