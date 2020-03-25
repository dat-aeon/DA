/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.agentSaleReportSearch.AgentSaleReportSearchReqDto;
import mm.aeon.com.ass.base.dto.agentSaleReportSearch.AgentSaleReportSearchResDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentSaleReportPaginationController extends LazyDataModel<AgentSaleReportLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 3571936885560427373L;
    private int rowCount;
    private AgentSaleReportSearchReqDto agentSaleReportSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgentSaleReportPaginationController(int rowCount, AgentSaleReportSearchReqDto agentSaleReportSearchReqDto) {
        this.rowCount = rowCount;
        this.agentSaleReportSearchReqDto = agentSaleReportSearchReqDto;
    }

    @Override
    public Object getRowKey(AgentSaleReportLineBean line) {
        // TODO Auto-generated method stub
        return line.getInvoiceNo();

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<AgentSaleReportLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        applicationLogger.log("Agent Sale Report Search Pagination Process Started.", LogLevel.INFO);
        agentSaleReportSearchReqDto.setLimit(pageSize);
        agentSaleReportSearchReqDto.setOffset(first);
        agentSaleReportSearchReqDto.setSortField(sortField);
        agentSaleReportSearchReqDto.setSortOrder(sortOrder.toString());

        List<AgentSaleReportLineBean> resultList = new ArrayList<AgentSaleReportLineBean>();
        AgentSaleReportLineBean lineBean;

        try {
            List<AgentSaleReportSearchResDto> resDtoList = (List<AgentSaleReportSearchResDto>) CommonUtil
                    .getDaoServiceInvoker().execute(agentSaleReportSearchReqDto);
            for (AgentSaleReportSearchResDto resDto : resDtoList) {
                lineBean = new AgentSaleReportLineBean();

                lineBean.setInvoiceNo(resDto.getInvoiceNo());

                lineBean.setCustomerName(resDto.getCustomerName());

                lineBean.setPurchaseDate(resDto.getPurchaseDate());

                lineBean.setAgentName(resDto.getAgentName());

                lineBean.setAgreementNo(resDto.getAgreementNo());

                if (resDto.getPrice() != null) {
                    lineBean.setPrice(resDto.getPrice());
                }

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

    public AgentSaleReportSearchReqDto getAgentSaleReportSearchReqDto() {
        return agentSaleReportSearchReqDto;
    }

    public void setAgentSaleReportSearchReqDto(AgentSaleReportSearchReqDto agentSaleReportSearchReqDto) {
        this.agentSaleReportSearchReqDto = agentSaleReportSearchReqDto;
    }

}
