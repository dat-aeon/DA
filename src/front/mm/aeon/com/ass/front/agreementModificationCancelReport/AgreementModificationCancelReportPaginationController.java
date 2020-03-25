/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationCancelReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.agreementModificationCancelReportSearch.AgreementModificationCancelReportSearchReqDto;
import mm.aeon.com.ass.base.dto.agreementModificationCancelReportSearch.AgreementModificationCancelReportSearchResDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgreementModificationCancelReportPaginationController
        extends LazyDataModel<AgreementModificationCancelReportLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 3571936885560427373L;
    private int rowCount;
    private AgreementModificationCancelReportSearchReqDto agreementModificationCancelReportSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgreementModificationCancelReportPaginationController(int rowCount,
            AgreementModificationCancelReportSearchReqDto agreementModificationCancelReportSearchReqDto) {
        this.rowCount = rowCount;
        this.agreementModificationCancelReportSearchReqDto = agreementModificationCancelReportSearchReqDto;

    }

    @Override
    public Object getRowKey(AgreementModificationCancelReportLineBean line) {
        // TODO Auto-generated method stub
        return line.getApplicationNo();

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<AgreementModificationCancelReportLineBean> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        applicationLogger.log("Agent Sale Report Search Pagination Process Started.", LogLevel.INFO);
        agreementModificationCancelReportSearchReqDto.setLimit(pageSize);
        agreementModificationCancelReportSearchReqDto.setOffset(first);
        agreementModificationCancelReportSearchReqDto.setSortField(sortField);
        agreementModificationCancelReportSearchReqDto.setSortOrder(sortOrder.toString());

        List<AgreementModificationCancelReportLineBean> resultList =
                new ArrayList<AgreementModificationCancelReportLineBean>();
        AgreementModificationCancelReportLineBean lineBean;

        try {
            List<AgreementModificationCancelReportSearchResDto> resDtoList =
                    (List<AgreementModificationCancelReportSearchResDto>) CommonUtil.getDaoServiceInvoker()
                            .execute(agreementModificationCancelReportSearchReqDto);
            for (AgreementModificationCancelReportSearchResDto resDto : resDtoList) {
                lineBean = new AgreementModificationCancelReportLineBean();

                lineBean.setApplicationNo(resDto.getApplicationNo());

                lineBean.setApplicantName(resDto.getApplicantName());

                lineBean.setNrcNo(resDto.getNrcNo());

                lineBean.setPhoneNo(resDto.getPhoneNo());

                lineBean.setModifyTime(resDto.getModifyTime());

                lineBean.setCurrentFinanceAmount(resDto.getCurrentFinanceAmount());

                lineBean.setCurrentFinanceTerm(resDto.getCurrentFinanceTerm());

                lineBean.setModifyFinanceAmount(resDto.getModifyFinanceAmount());

                lineBean.setModifyFinanceTerm(resDto.getModifyFinanceTerm());

                lineBean.setAgreementNo(resDto.getAgreementNo());

                lineBean.setModifyComment(resDto.getModifyComment());

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
