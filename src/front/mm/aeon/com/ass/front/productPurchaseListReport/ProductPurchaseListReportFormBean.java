/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.productPurchaseListReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;

import mm.aeon.com.ass.base.dto.productPurchaseListReportSearch.ProductPurchaseListReportSearchReqDto;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Name("productPurchaseListReport")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class ProductPurchaseListReportFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -962053775336916099L;

    private Boolean productSearchFlag;

    private ProductPurchaseListReportHeaderBean headerBean;

    private List<ProductPurchaseListReportLineBean> productPurchaseListReportLineBeanList;

    private LazyDataModel<ProductPurchaseListReportLineBean> lazyModel;

    private ProductPurchaseListReportSearchReqDto productPurchaseListReportSearchReqDto;

    private ProductPurchaseListReportLineBean lineBean;

    private int pageFirst;

    private int totalCount;

    private boolean init = true;

    private String reportName;

    private StreamedContent file;

    private String pdfPath;

    private String filePath;

    private String fileName;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    @Begin(nested = true)
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.PRODUCT_PURCHASE_LIST_REPORT);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        headerBean = new ProductPurchaseListReportHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        doReload = new Boolean(false);
        lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new ProductPurchaseListReportPaginationController(totalCount,
                    productPurchaseListReportSearchReqDto);
        }

        return LinkTarget.OK;

    }

    public void reset() {

        this.headerBean = new ProductPurchaseListReportHeaderBean();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected String getSystemPath() {
        Object context = FacesContext.getCurrentInstance().getExternalContext().getContext();
        String systemPath = ((ServletContext) context).getRealPath("/");
        return systemPath;
    }

    public StreamedContent reportProductPurchaseListReportResign() {

        reportName = "DOWNLOAD_ProductPurchaseListReport";
        pdfPath = "/temp/ProductPurchaseListReport/" + System.currentTimeMillis() + "/";
        filePath = getSystemPath() + pdfPath;
        fileName = reportName + ".xlsx";
        return generateProductPurchaseListReportResign(filePath, fileName);

    }

    public StreamedContent generateProductPurchaseListReportResign(String dirPath, String fileName) {
        try {
            productPurchaseListReportLineBeanList = new ArrayList<ProductPurchaseListReportLineBean>();

            for (ProductPurchaseListReportLineBean data : lazyModel)
                productPurchaseListReportLineBeanList.add(data);

            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("agentName", productPurchaseListReportLineBeanList.get(0).getAgentName());
            parameterMap.put("settlementDate", productPurchaseListReportLineBeanList.get(0).getSettlementDate());
            parameterMap.put("ProductPurchaseList", productPurchaseListReportLineBeanList);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ClassLoader classLoader = new ProductPurchaseListReportFormBean().getClass().getClassLoader();
            File exfile =
                    new File(classLoader.getResource("report-template/ProductPurchaseListReport.jrxml").getFile());
            InputStream inputStream = new FileInputStream(exfile);
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, new JREmptyDataSource());
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            FileUtils.forceMkdir(new File(dirPath));
            OutputStream outputStream = new FileOutputStream(dirPath + fileName);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();

            facesContext.responseComplete();
            facesContext.renderResponse();
            InputStream stream = new FileInputStream(dirPath + fileName);

            file = new DefaultStreamedContent(stream,
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    "ProductPurchaseListReport.xlsx");
            outputStream.close();

        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public String getReportStream() {
        return pdfPath + fileName;
    }

    public ProductPurchaseListReportHeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(ProductPurchaseListReportHeaderBean headerBean) {
        this.headerBean = headerBean;
    }

    public LazyDataModel<ProductPurchaseListReportLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<ProductPurchaseListReportLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public ProductPurchaseListReportSearchReqDto getProductPurchaseListReportSearchReqDto() {
        return productPurchaseListReportSearchReqDto;
    }

    public void setProductPurchaseListReportSearchReqDto(
            ProductPurchaseListReportSearchReqDto productPurchaseListReportSearchReqDto) {
        this.productPurchaseListReportSearchReqDto = productPurchaseListReportSearchReqDto;
    }

    public ProductPurchaseListReportLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(ProductPurchaseListReportLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getProductSearchFlag() {
        return productSearchFlag;
    }

    public void setProductSearchFlag(Boolean productSearchFlag) {
        this.productSearchFlag = productSearchFlag;
    }

    public List<ProductPurchaseListReportLineBean> getProductPurchaseListReportLineBeanList() {
        return productPurchaseListReportLineBeanList;
    }

    public void setProductPurchaseListReportLineBeanList(
            List<ProductPurchaseListReportLineBean> productPurchaseListReportLineBeanList) {
        this.productPurchaseListReportLineBeanList = productPurchaseListReportLineBeanList;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

}
