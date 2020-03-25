/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.leadTimeReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorStaffCommentSearchReqDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorStaffCommentSearchResDto;
import mm.aeon.com.ass.base.dto.leadTimeReportSearch.LeadTimeReportAttachmentEditedTimeSearchReqDto;
import mm.aeon.com.ass.base.dto.leadTimeReportSearch.LeadTimeReportAttachmentEditedTimeSearchResDto;
import mm.aeon.com.ass.base.dto.leadTimeReportSearch.LeadTimeReportSearchReqDto;
import mm.aeon.com.ass.base.dto.leadTimeReportSearch.LeadTimeReportSearchResDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class LeadTimeReportPaginationController extends LazyDataModel<LeadTimeReportLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 3571936885560427373L;
    private int rowCount;
    private LeadTimeReportSearchReqDto leadTimeReportSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public LeadTimeReportPaginationController(int rowCount, LeadTimeReportSearchReqDto leadTimeReportSearchReqDto) {
        this.rowCount = rowCount;
        this.leadTimeReportSearchReqDto = leadTimeReportSearchReqDto;

    }

    @Override
    public Object getRowKey(LeadTimeReportLineBean line) {
        // TODO Auto-generated method stub
        return line.getAgentName();

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<LeadTimeReportLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        applicationLogger.log("Agent Sale Claim Finished Report Search Pagination Process Started.", LogLevel.INFO);
        leadTimeReportSearchReqDto.setLimit(pageSize);
        leadTimeReportSearchReqDto.setOffset(first);
        leadTimeReportSearchReqDto.setSortField(sortField);
        leadTimeReportSearchReqDto.setSortOrder(sortOrder.toString());

        List<LeadTimeReportLineBean> resultList = new ArrayList<LeadTimeReportLineBean>();
        LeadTimeReportLineBean lineBean;

        try {
            List<LeadTimeReportSearchResDto> resDtoList = (List<LeadTimeReportSearchResDto>) CommonUtil
                    .getDaoServiceInvoker().execute(leadTimeReportSearchReqDto);
            for (LeadTimeReportSearchResDto resDto : resDtoList) {
                lineBean = new LeadTimeReportLineBean();

                lineBean.setApplicationNo(resDto.getApplicationNo());

                lineBean.setAgentName(resDto.getAgentName());

                lineBean.setCustomerName(resDto.getCustomerName());

                lineBean.setPhoneNo(resDto.getPhoneNo());

                lineBean.setNrcNo(resDto.getNrcNo());

                lineBean.setAgreementNo(resDto.getAgreementNo());

                lineBean.setInformationReceivedTime(resDto.getInformationReceivedTime());

                lineBean.setSaleEntryTime(resDto.getSaleEntryTime());

                LeadTimeReportAttachmentEditedTimeSearchReqDto saleClaimReqDto =
                        new LeadTimeReportAttachmentEditedTimeSearchReqDto();
                saleClaimReqDto.setApplicationNo(lineBean.getApplicationNo());
                saleClaimReqDto.setStatus(18);

                List<LeadTimeReportAttachmentEditedTimeSearchResDto> saleClaimResList =
                        (List<LeadTimeReportAttachmentEditedTimeSearchResDto>) CommonUtil.getDaoServiceInvoker()
                                .execute(saleClaimReqDto);
                List<LeadTimeReportTimeLineBean> saleClaimTimeList = new ArrayList<LeadTimeReportTimeLineBean>();
                for (LeadTimeReportAttachmentEditedTimeSearchResDto saleClaimRes : saleClaimResList) {
                    LeadTimeReportTimeLineBean saleClaimTime = new LeadTimeReportTimeLineBean();
                    saleClaimTime.setReportTime(saleClaimRes.getUpdatedTime());

                    saleClaimTimeList.add(saleClaimTime);
                }
                lineBean.setSaleClaimTimeList(saleClaimTimeList);

                AgentDocumentErrorStaffCommentSearchReqDto documentErrorReqDto =
                        new AgentDocumentErrorStaffCommentSearchReqDto();
                documentErrorReqDto.setApplicationNo(lineBean.getApplicationNo());
                documentErrorReqDto.setStatus(19);

                List<AgentDocumentErrorStaffCommentSearchResDto> documentErrorResList =
                        (List<AgentDocumentErrorStaffCommentSearchResDto>) CommonUtil.getDaoServiceInvoker()
                                .execute(documentErrorReqDto);
                List<LeadTimeReportTimeLineBean> documentErrorTimeList = new ArrayList<LeadTimeReportTimeLineBean>();
                for (AgentDocumentErrorStaffCommentSearchResDto staffRes : documentErrorResList) {
                    LeadTimeReportTimeLineBean documentErrorTime = new LeadTimeReportTimeLineBean();
                    documentErrorTime.setReportTime(staffRes.getUpdatedTime());

                    documentErrorTimeList.add(documentErrorTime);
                }
                lineBean.setDocumentErrorTimeList(documentErrorTimeList);
                lineBean.setSaleClaimTime(resDto.getSaleClaimTime());

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
