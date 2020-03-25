/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.productPurchaseListReport;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.productPurchaseListReportSearch.ProductPurchaseListReportSearchReqDto;
import mm.aeon.com.ass.base.dto.productPurchaseListReportSearch.ProductPurchaseListReportSearchResDto;
import mm.aeon.com.ass.front.common.LoanCalculator;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class ProductPurchaseListReportPaginationController extends LazyDataModel<ProductPurchaseListReportLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 6846837416967107385L;

    private int rowCount;
    private ProductPurchaseListReportSearchReqDto productPurchaseListReportSearchReqDto;
    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public ProductPurchaseListReportPaginationController(int rowCount,
            ProductPurchaseListReportSearchReqDto productPurchaseListReportSearchReqDto) {
        this.rowCount = rowCount;
        this.productPurchaseListReportSearchReqDto = productPurchaseListReportSearchReqDto;
    }

    @Override
    public Object getRowKey(ProductPurchaseListReportLineBean line) {
        // TODO Auto-generated method stub
        return line.getAgreementCode();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<ProductPurchaseListReportLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        applicationLogger.log("Product Purchase List Report Search Pagination Process Started.", LogLevel.INFO);
        productPurchaseListReportSearchReqDto.setLimit(pageSize);
        productPurchaseListReportSearchReqDto.setOffset(first);
        productPurchaseListReportSearchReqDto.setSortField(sortField);
        productPurchaseListReportSearchReqDto.setSortOrder(sortOrder.toString());

        List<ProductPurchaseListReportLineBean> resultList = new ArrayList<ProductPurchaseListReportLineBean>();
        ProductPurchaseListReportLineBean lineBean;
        DecimalFormat df = new DecimalFormat(".##");
        Double processingFees = (double) 0, compulsorySaving = (double) 0;

        try {
            List<ProductPurchaseListReportSearchResDto> resDtoList =
                    (List<ProductPurchaseListReportSearchResDto>) CommonUtil.getDaoServiceInvoker()
                            .execute(productPurchaseListReportSearchReqDto);
            for (ProductPurchaseListReportSearchResDto resDto : resDtoList) {
                lineBean = new ProductPurchaseListReportLineBean();
                lineBean.setOutletName(resDto.getOutletName());
                lineBean.setSettlementDate(resDto.getCreatedDate());
                lineBean.setAgentName(resDto.getAgentName());
                lineBean.setClaimDate(resDto.getCreatedDate());
                lineBean.setAgreementCode(resDto.getAgreementNo());
                if (resDto.getFinanceAmount() != null) {
                    lineBean.setAmount(resDto.getFinanceAmount());
                    processingFees = LoanCalculator.calculateProcessingFees(false, resDto.getFinanceAmount());
                    lineBean.setProcessingFees(processingFees);
                }

                if (resDto.getCompulsoryAmount() != null) {
                    compulsorySaving = LoanCalculator.calculateTotalConSaving(resDto.getCompulsoryAmount(),
                            resDto.getFinanceTerm());
                    lineBean.setCompulsorySaving(compulsorySaving);
                }
                lineBean.setDate(resDto.getPurchaseDate());
                if (resDto.getFinanceAmount() != null)
                    lineBean.setSettlementAmount(resDto.getFinanceAmount() - (processingFees + compulsorySaving));
                resultList.add(lineBean);

            }
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        applicationLogger.log("Product Purchase List Report Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

}
