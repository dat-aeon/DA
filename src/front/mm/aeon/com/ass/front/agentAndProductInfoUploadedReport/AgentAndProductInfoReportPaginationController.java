/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentAndProductInfoUploadedReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.agentAndProductInfoUploadedReportSearch.AgentAndProductAppInfo;
import mm.aeon.com.ass.base.dto.agentAndProductInfoUploadedReportSearch.AgentAndProductInfoUploadedReportSearchReqDto;
import mm.aeon.com.ass.base.dto.agentAndProductInfoUploadedReportSearch.AgentAndProductInfoUploadedReportSearchResDto;
import mm.aeon.com.ass.front.common.LoanCalculator;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentAndProductInfoReportPaginationController
        extends LazyDataModel<AgentAndProductInfoUploadedReportLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 3571936885560427373L;
    private int rowCount;
    private AgentAndProductInfoUploadedReportSearchReqDto agentAndProductInfoUploadedReportSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgentAndProductInfoReportPaginationController(int rowCount,
            AgentAndProductInfoUploadedReportSearchReqDto agentAndProductInfoUploadedReportSearchReqDto) {
        this.rowCount = rowCount;
        this.agentAndProductInfoUploadedReportSearchReqDto = agentAndProductInfoUploadedReportSearchReqDto;

    }

    @Override
    public Object getRowKey(AgentAndProductInfoUploadedReportLineBean line) {
        // TODO Auto-generated method stub
        return line.getAgentName();

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<AgentAndProductInfoUploadedReportLineBean> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        applicationLogger.log("Agent Sale Report Search Pagination Process Started.", LogLevel.INFO);
        agentAndProductInfoUploadedReportSearchReqDto.setLimit(pageSize);
        agentAndProductInfoUploadedReportSearchReqDto.setOffset(first);
        agentAndProductInfoUploadedReportSearchReqDto.setSortField(sortField);
        agentAndProductInfoUploadedReportSearchReqDto.setSortOrder(sortOrder.toString());

        List<AgentAndProductInfoUploadedReportLineBean> resultList =
                new ArrayList<AgentAndProductInfoUploadedReportLineBean>();
        AgentAndProductInfoUploadedReportLineBean lineBean;

        try {
            List<AgentAndProductInfoUploadedReportSearchResDto> resDtoList =
                    (List<AgentAndProductInfoUploadedReportSearchResDto>) CommonUtil.getDaoServiceInvoker()
                            .execute(agentAndProductInfoUploadedReportSearchReqDto);
            for (AgentAndProductInfoUploadedReportSearchResDto resDto : resDtoList) {
                lineBean = new AgentAndProductInfoUploadedReportLineBean();

                lineBean.setAgentId(resDto.getAgentId());

                lineBean.setAgentName(resDto.getAgentName());

                lineBean.setPurchaseLocation(resDto.getPurchaseLocation());

                lineBean.setAgentAndProductAppInfoList(resDto.getAgentAndProductAppInfoList());

                lineBean.setApplicationCount(resDto.getAgentAndProductAppInfoList().size());

                Double financialAmount = 0.0, processingFees = 0.0, compulsoryAmount = 0.0;
                for (AgentAndProductAppInfo resBean : resDto.getAgentAndProductAppInfoList()) {

                    financialAmount += resBean.getApprovedFinanceAmount();
                    processingFees += LoanCalculator.calculateProcessingFees(false, resBean.getApprovedFinanceAmount());
                    compulsoryAmount += resBean.getCompulsoryAmount();
                }

                lineBean.setTotalFinanceAmount(financialAmount);
                lineBean.setTotalProcessingFeeAmount(processingFees);
                lineBean.setTotalCompulsorySaving(compulsoryAmount);

                resultList.add(lineBean);

            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        applicationLogger.log("Agent Sale Report Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

}
