/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.productPurchaseListReport;

import mm.aeon.com.ass.base.dto.productPurchaseListReportSearch.ProductPurchaseListReportCountSearchReqDto;
import mm.aeon.com.ass.base.dto.productPurchaseListReportSearch.ProductPurchaseListReportSearchReqDto;
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

public class ProductPurchaseListReportSearchController extends AbstractController
        implements IControllerAccessor<ProductPurchaseListReportFormBean, ProductPurchaseListReportFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public ProductPurchaseListReportFormBean process(ProductPurchaseListReportFormBean formBean) {
        // TODO Auto-generated method stub
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());
        applicationLogger.log("Product Purchase List Report searching process started", LogLevel.INFO);
        MessageBean messageBean;

        ProductPurchaseListReportCountSearchReqDto reqcountDto = new ProductPurchaseListReportCountSearchReqDto();
        ProductPurchaseListReportHeaderBean headerBean = formBean.getHeaderBean();

        if (headerBean.getAgentName() != null)
            reqcountDto.setAgentName(headerBean.getAgentName().trim());

        if (headerBean.getOutletName() != null)
            reqcountDto.setOutletName(headerBean.getOutletName().trim().toLowerCase());

        reqcountDto.setSettlementDate(headerBean.getSettlementDate());

        ProductPurchaseListReportSearchReqDto reqDto = new ProductPurchaseListReportSearchReqDto();
        reqDto.setAgentName(reqcountDto.getAgentName());
        reqDto.setOutletName(reqcountDto.getOutletName());
        reqDto.setSettlementDate(reqcountDto.getSettlementDate());

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(reqcountDto);
            formBean.setTotalCount(totalCount);
            formBean.setProductPurchaseListReportSearchReqDto(reqDto);
            if (totalCount == 0)
                messageBean = new MessageBean(MessageId.MI0008);
            else
                messageBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Product Purchase List Report searching finished", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
        formBean.setProductSearchFlag(false);
        if (reqcountDto.getAgentName() != null && reqcountDto.getAgentName() != ""
                && headerBean.getSettlementDate() != null)
            formBean.setProductSearchFlag(true);
        return formBean;
    }

}
