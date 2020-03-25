/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleReport;

import mm.aeon.com.ass.base.dto.agentSaleReportSearch.AgentSaleReportCountSearchReqDto;
import mm.aeon.com.ass.base.dto.agentSaleReportSearch.AgentSaleReportSearchReqDto;
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

public class AgentSaleReportSearchController extends AbstractController
        implements IControllerAccessor<AgentSaleReportFormBean, AgentSaleReportFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentSaleReportFormBean process(AgentSaleReportFormBean formBean) {
        // TODO Auto-generated method stub
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());
        applicationLogger.log("Agent Sale Report searching process started", LogLevel.INFO);
        MessageBean messageBean;

        AgentSaleReportCountSearchReqDto reqcountDto = new AgentSaleReportCountSearchReqDto();
        AgentSaleReportHeaderBean headerBean = formBean.getHeaderbean();

        if (headerBean.getCustomerName() != null) {
            reqcountDto.setCustomerName(headerBean.getCustomerName().trim().toLowerCase());
        }
        if (headerBean.getAgentName() != null) {
            reqcountDto.setAgentName(headerBean.getAgentName().trim().toLowerCase());
        }
        reqcountDto.setInvoiceDateFrom(headerBean.getInvoiceDateFrom());
        reqcountDto.setInvoiceDateTo(headerBean.getInvoiceDateTo());

        AgentSaleReportSearchReqDto reqDto = new AgentSaleReportSearchReqDto();
        reqDto.setCustomerName(reqcountDto.getCustomerName());
        reqDto.setInvoiceDateFrom(reqcountDto.getInvoiceDateFrom());
        reqDto.setInvoiceDateTo(reqcountDto.getInvoiceDateTo());
        reqDto.setAgentName(reqcountDto.getAgentName());

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(reqcountDto);
            formBean.setTotalCount(totalCount);
            formBean.setAgentSaleReportSearchReqDto(reqDto);
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
        formBean.setAgentNameSearchFlag(false);
        if (reqcountDto.getAgentName() != null && reqcountDto.getAgentName() != "")
            formBean.setAgentNameSearchFlag(true);
        return formBean;
    }

}
