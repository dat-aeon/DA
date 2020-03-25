/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleClaimFinishedReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch.AgentSaleClaimFinishedAppInfo;
import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch.AgentSaleClaimFinishedReportSearchReqDto;
import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch.AgentSaleClaimFinishedReportSearchResDto;
import mm.aeon.com.ass.front.common.LoanCalculator;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentSaleClaimFinishedReportPaginationController
        extends LazyDataModel<AgentSaleClaimFinishedReportLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 3571936885560427373L;
    private int rowCount;
    private AgentSaleClaimFinishedReportSearchReqDto agentSaleClaimFinishedReportSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgentSaleClaimFinishedReportPaginationController(int rowCount,
            AgentSaleClaimFinishedReportSearchReqDto agentSaleClaimFinishedReportSearchReqDto) {
        this.rowCount = rowCount;
        this.agentSaleClaimFinishedReportSearchReqDto = agentSaleClaimFinishedReportSearchReqDto;

    }

    @Override
    public Object getRowKey(AgentSaleClaimFinishedReportLineBean line) {
        // TODO Auto-generated method stub
        return line.getAgentName();

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<AgentSaleClaimFinishedReportLineBean> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        applicationLogger.log("Agent Sale Claim Finished Report Search Pagination Process Started.", LogLevel.INFO);
        agentSaleClaimFinishedReportSearchReqDto.setLimit(pageSize);
        agentSaleClaimFinishedReportSearchReqDto.setOffset(first);
        agentSaleClaimFinishedReportSearchReqDto.setSortField(sortField);
        agentSaleClaimFinishedReportSearchReqDto.setSortOrder(sortOrder.toString());

        List<AgentSaleClaimFinishedReportLineBean> resultList = new ArrayList<AgentSaleClaimFinishedReportLineBean>();
        AgentSaleClaimFinishedReportLineBean lineBean;

        try {
            List<AgentSaleClaimFinishedReportSearchResDto> resDtoList =
                    (List<AgentSaleClaimFinishedReportSearchResDto>) CommonUtil.getDaoServiceInvoker()
                            .execute(agentSaleClaimFinishedReportSearchReqDto);
            for (AgentSaleClaimFinishedReportSearchResDto resDto : resDtoList) {
                lineBean = new AgentSaleClaimFinishedReportLineBean();

                lineBean.setAgentId(resDto.getAgentId());

                lineBean.setAgentName(resDto.getAgentName());

                lineBean.setPurchaseLocation(resDto.getPurchaseLocation());

                lineBean.setAgentSaleClaimFinishedAppInfoList(resDto.getAgentSaleClaimFinishedAppInfoList());

                lineBean.setApplicationCount(resDto.getAgentSaleClaimFinishedAppInfoList().size());

                Double financialAmount = 0.0, processingFees = 0.0, compulsoryAmount = 0.0, settlementAmount = 0.0;
                for (AgentSaleClaimFinishedAppInfo resBean : resDto.getAgentSaleClaimFinishedAppInfoList()) {

                    financialAmount += resBean.getApprovedFinanceAmount();
                    processingFees += LoanCalculator.calculateProcessingFees(false, resBean.getApprovedFinanceAmount());
                    compulsoryAmount += resBean.getCompulsoryAmount();
                    settlementAmount += resBean.getSettlementAmount();
                }

                lineBean.setTotalFinanceAmount(financialAmount);
                lineBean.setTotalProcessingFeeAmount(processingFees);
                lineBean.setTotalCompulsorySaving(compulsoryAmount);
                lineBean.setTotalSettlementAmount(settlementAmount);

                resultList.add(lineBean);

            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        applicationLogger.log("Agent Sale Claim Finished Report Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

}
