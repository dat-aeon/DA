/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleClaimFinishedDetailReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedDetailReportSearch.AgentSaleClaimFinishedDetailReportSearchReqDto;
import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedDetailReportSearch.AgentSaleClaimFinishedDetailReportSearchResDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.UserInfoReferReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.UserInfoReferResDto;
import mm.aeon.com.ass.front.common.LoanCalculator;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentSaleClaimFinishedDetailReportPaginationController
        extends LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 3571936885560427373L;
    private int rowCount;
    private AgentSaleClaimFinishedDetailReportSearchReqDto agentSaleClaimFinishedDetailReportSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgentSaleClaimFinishedDetailReportPaginationController(int rowCount,
            AgentSaleClaimFinishedDetailReportSearchReqDto agentSaleClaimFinishedDetailReportSearchReqDto) {
        this.rowCount = rowCount;
        this.agentSaleClaimFinishedDetailReportSearchReqDto = agentSaleClaimFinishedDetailReportSearchReqDto;

    }

    @Override
    public Object getRowKey(AgentSaleClaimFinishedDetailReportLineBean line) {
        // TODO Auto-generated method stub
        return line.getAgentName();

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<AgentSaleClaimFinishedDetailReportLineBean> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        applicationLogger.log("Agent Sale Claim Finished Report Search Pagination Process Started.", LogLevel.INFO);
        agentSaleClaimFinishedDetailReportSearchReqDto.setLimit(pageSize);
        agentSaleClaimFinishedDetailReportSearchReqDto.setOffset(first);
        agentSaleClaimFinishedDetailReportSearchReqDto.setSortField(sortField);
        agentSaleClaimFinishedDetailReportSearchReqDto.setSortOrder(sortOrder.toString());

        List<AgentSaleClaimFinishedDetailReportLineBean> resultList =
                new ArrayList<AgentSaleClaimFinishedDetailReportLineBean>();
        AgentSaleClaimFinishedDetailReportLineBean lineBean;

        try {
            List<AgentSaleClaimFinishedDetailReportSearchResDto> resDtoList =
                    (List<AgentSaleClaimFinishedDetailReportSearchResDto>) CommonUtil.getDaoServiceInvoker()
                            .execute(agentSaleClaimFinishedDetailReportSearchReqDto);
            for (AgentSaleClaimFinishedDetailReportSearchResDto resDto : resDtoList) {
                lineBean = new AgentSaleClaimFinishedDetailReportLineBean();

                lineBean.setAgentId(resDto.getAgentId());

                lineBean.setAgentName(resDto.getAgentName());

                lineBean.setOutletId(resDto.getOutletId());

                lineBean.setOutletName(resDto.getOutletName());

                lineBean.setCustomerName(resDto.getCustomerName());

                lineBean.setNrcNo(resDto.getNrcNo());

                lineBean.setAgreementNo(resDto.getAgreementNo());

                lineBean.setSettlementAmount(resDto.getSettlementAmount());

                lineBean.setInvoiceNo(resDto.getInvoiceNo());

                lineBean.setApprovedFinanceAmount(resDto.getApprovedFinanceAmount());

                lineBean.setProcessingFeeAmount(
                        LoanCalculator.calculateProcessingFees(false, resDto.getApprovedFinanceAmount()));
                lineBean.setCompulsorySaving(resDto.getCompulsorySaving());
                lineBean.setStaffName(resDto.getStaffName());
                if (resDto.getStaffName() != null) {
                    if (lineBean.getStaffName().contains(",")) {
                        UserInfoReferReqDto userInfoReferReqDto = new UserInfoReferReqDto();
                        UserInfoReferResDto userInfoReferResDto = new UserInfoReferResDto();
                        String[] str = new String[2];
                        str = lineBean.getStaffName().split(VCSCommon.REXCOMMA);
                        userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                        userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                        if (!userInfoReferReqDto.getUser_type_id().equals(3)) {
                            userInfoReferResDto = (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                    .execute(userInfoReferReqDto);
                            lineBean.setStaffName(userInfoReferResDto.getUser_name());
                        }
                    }
                }

                lineBean.setSaleClaimFinishedDate(resDto.getSaleClaimFinishedDate());

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
