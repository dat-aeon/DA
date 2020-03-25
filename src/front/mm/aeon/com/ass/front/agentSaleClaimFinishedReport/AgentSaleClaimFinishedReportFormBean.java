/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleClaimFinishedReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.springframework.util.FileCopyUtils;

import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch.AgentSaleClaimFinishedReportSearchReqDto;
import mm.aeon.com.ass.front.agentAndProductInfoUploadedReport.AgentAndProductInfoUploadedReportFormBean;
import mm.aeon.com.ass.front.agentSaleClaimFinishedDetailReport.AgentSaleClaimFinishedDetailReportHeaderBean;
import mm.aeon.com.ass.front.common.ProjectPath;
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

/**
 * AgentSaleReportFormBean Class.
 * <p>
 * 
 * <pre>
 * 
 * </pre>
 * 
 * </p>
 */
@Name("agentSaleClaimFinishedReport")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentSaleClaimFinishedReportFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7268045135060096439L;

    @Out(required = false, value = "reportDetailParam")
    private AgentSaleClaimFinishedDetailReportHeaderBean reportDetailParam;

    public Boolean agentNameSearchFlag;

    private LazyDataModel<AgentSaleClaimFinishedReportLineBean> lazyModel;

    private AgentSaleClaimFinishedReportSearchReqDto agentSaleClaimFinishedReportSearchReqDto;

    private AgentSaleClaimFinishedReportHeaderBean headerBean;

    private AgentSaleClaimFinishedReportLineBean lineBean;

    private List<AgentSaleClaimFinishedReportLineBean> agentSaleReportLineBeanList;

    private int pageFirst;

    private int totalCount;

    private String reportName;

    private String pdfPath;

    private String filePath;

    private String fileName;

    private StreamedContent file;

    private boolean init = true;

    DecimalFormat decimalformat = new DecimalFormat("###,##0.00");

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    @Begin(nested = true)
    public void init() {
        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.AGENT_SALE_CLAIM_FINISHED_REPORT);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        headerBean = new AgentSaleClaimFinishedReportHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {

        doReload = new Boolean(false);
        lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgentSaleClaimFinishedReportPaginationController(totalCount,
                    agentSaleClaimFinishedReportSearchReqDto);

        }

        return LinkTarget.OK;

    }

    public void downloadSaleClaimFinishedReport() {
        String downloadFileName = "Agent_Sale_Claim_Finished_Report.xlsx";
        File downloadFile = generateSaleClaimFinishedReport(lazyModel, downloadFileName);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.responseReset();
        externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        externalContext.setResponseContentLength((int) downloadFile.length());
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
        if (userAgent.contains("Firefox")) {
            externalContext.setResponseHeader("Content-Disposition",
                    "attachment; filename*=\"UTF-8''" + CommonUtil.getURLEncodeFileName(downloadFileName) + "\"");
        } else {
            externalContext.setResponseHeader("Content-Disposition",
                    "attachment; filename=\"" + CommonUtil.getURLEncodeFileName(downloadFileName) + "\"");
        }
        try {
            InputStream stream = new FileInputStream(downloadFile);
            FileCopyUtils.copy(stream, externalContext.getResponseOutputStream());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

    public File generateSaleClaimFinishedReport(
            LazyDataModel<AgentSaleClaimFinishedReportLineBean> agentSaleClaimFinihsedList, String reportName) {
        // Create blank workbook
        XSSFWorkbook workBook;
        ClassLoader classLoader = new AgentAndProductInfoUploadedReportFormBean().getClass().getClassLoader();
        File exFile =
                new File(classLoader.getResource("report-template/Agent_Sale_Claim_Finished_Report.xlsx").getFile());

        String filePath = ProjectPath.createTempDirectory(reportName);
        File file = new File(filePath + reportName);

        try {
            InputStream inputStream = new FileInputStream(exFile);
            workBook = new XSSFWorkbook(inputStream);
            int i = 3;
            int index = 0;
            XSSFSheet sheet = workBook.getSheet("SaleClaimFinishedListReport");

            for (AgentSaleClaimFinishedReportLineBean agentAndProductBean : agentSaleClaimFinihsedList) {
                i = i + 1;
                index = index + 1;
                Row row = sheet.createRow(i);
                row.setHeightInPoints(2 * 16);

                Cell noCell = row.createCell(0);
                noCell.setCellValue(index);
                noCell.setCellStyle(getDefaultCell(workBook));
                noCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell agentNameCell = row.createCell(1);
                agentNameCell.setCellValue(agentAndProductBean.getAgentName());
                agentNameCell.setCellStyle(getDefaultCell(workBook));
                agentNameCell.getCellStyle().setWrapText(true);
                agentNameCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell locationCell = row.createCell(2);
                locationCell.setCellValue(agentAndProductBean.getPurchaseLocation());
                locationCell.setCellStyle(getDefaultCell(workBook));
                locationCell.getCellStyle().setWrapText(true);
                locationCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell accountNoCell = row.createCell(3);
                accountNoCell.setCellValue(agentAndProductBean.getApplicationCount());
                accountNoCell.setCellStyle(getDefaultCell(workBook));
                accountNoCell.getCellStyle().setWrapText(true);
                accountNoCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell financeAmountCell = row.createCell(4);
                financeAmountCell.setCellValue(decimalformat.format(agentAndProductBean.getTotalFinanceAmount()));
                financeAmountCell.setCellStyle(getDefaultCell(workBook));
                financeAmountCell.getCellStyle().setWrapText(true);
                financeAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

                Cell totalProcessingFeeCell = row.createCell(5);
                totalProcessingFeeCell
                        .setCellValue(decimalformat.format(agentAndProductBean.getTotalProcessingFeeAmount()));
                totalProcessingFeeCell.setCellStyle(getDefaultCell(workBook));
                totalProcessingFeeCell.getCellStyle().setWrapText(true);
                totalProcessingFeeCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

                Cell totalCompulsorySavingCell = row.createCell(6);
                totalCompulsorySavingCell
                        .setCellValue(decimalformat.format(agentAndProductBean.getTotalCompulsorySaving()));
                totalCompulsorySavingCell.setCellStyle(getDefaultCell(workBook));
                totalCompulsorySavingCell.getCellStyle().setWrapText(true);
                totalCompulsorySavingCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

                Cell totalSettlementAmountCell = row.createCell(7);
                totalSettlementAmountCell
                        .setCellValue(decimalformat.format(agentAndProductBean.getTotalSettlementAmount()));
                totalSettlementAmountCell.setCellStyle(getDefaultCell(workBook));
                totalSettlementAmountCell.getCellStyle().setWrapText(true);
                totalSettlementAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);
            }

            i = i + 1;
            XSSFRow row1 = sheet.createRow(i);
            row1.setHeightInPoints(2 * 16);

            for (int columnNo = 0; columnNo < 4; columnNo++) {
                row1.createCell(columnNo);
                row1.getCell(columnNo).setCellStyle(getDefaultCell(workBook));
                row1.getCell(columnNo).setCellValue("Total");
                row1.getCell(columnNo).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                row1.getCell(columnNo).getCellStyle().setWrapText(true);
            }
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));

            Cell totalNetFinanceAmountCell = row1.createCell(4);
            totalNetFinanceAmountCell
                    .setCellValue(decimalformat.format(calculateTotalFinanceAmount(agentSaleClaimFinihsedList)));
            totalNetFinanceAmountCell.setCellStyle(getDefaultCell(workBook));
            totalNetFinanceAmountCell.getCellStyle().setWrapText(true);
            totalNetFinanceAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            Cell totalNetProcessingFeeCell = row1.createCell(5);
            totalNetProcessingFeeCell
                    .setCellValue(decimalformat.format(calculateTotalProcessingFeeAmount(agentSaleClaimFinihsedList)));
            totalNetProcessingFeeCell.setCellStyle(getDefaultCell(workBook));
            totalNetProcessingFeeCell.getCellStyle().setWrapText(true);
            totalNetProcessingFeeCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            Cell totalNetCompulsorySavingCell = row1.createCell(6);
            totalNetCompulsorySavingCell
                    .setCellValue(decimalformat.format(calculateTotalCompulsorySaving(agentSaleClaimFinihsedList)));
            totalNetCompulsorySavingCell.setCellStyle(getDefaultCell(workBook));
            totalNetCompulsorySavingCell.getCellStyle().setWrapText(true);
            totalNetCompulsorySavingCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            Cell totalNetSettlementAmountCell = row1.createCell(7);
            totalNetSettlementAmountCell
                    .setCellValue(decimalformat.format(calculateTotalSettlementAmount(agentSaleClaimFinihsedList)));
            totalNetSettlementAmountCell.setCellStyle(getDefaultCell(workBook));
            totalNetSettlementAmountCell.getCellStyle().setWrapText(true);
            totalNetSettlementAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            FileOutputStream out = new FileOutputStream(file);
            workBook.setPrintArea(0, "$A$1:$H$" + (i + 1));
            workBook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        return file;
    }

    private XSSFCellStyle getDefaultCell(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private Double calculateTotalFinanceAmount(
            LazyDataModel<AgentSaleClaimFinishedReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getTotalFinanceAmount();
        }
        return total;
    }

    private Double calculateTotalProcessingFeeAmount(
            LazyDataModel<AgentSaleClaimFinishedReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getTotalProcessingFeeAmount();
        }
        return total;
    }

    private Double calculateTotalCompulsorySaving(
            LazyDataModel<AgentSaleClaimFinishedReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getTotalCompulsorySaving();
        }
        return total;
    }

    private Double calculateTotalSettlementAmount(
            LazyDataModel<AgentSaleClaimFinishedReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getTotalSettlementAmount();
        }
        return total;
    }

    protected String getSystemPath() {
        Object context = FacesContext.getCurrentInstance().getExternalContext().getContext();
        String systemPath = ((ServletContext) context).getRealPath("/");
        return systemPath;
    }

    public String getReportStream() {
        return pdfPath + fileName;
    }

    public String prepareDetailReport(AgentSaleClaimFinishedReportLineBean lineBean) {

        this.reportDetailParam = new AgentSaleClaimFinishedDetailReportHeaderBean();
        this.reportDetailParam.setAgentId(lineBean.getAgentId());
        this.reportDetailParam.setAgentName(lineBean.getAgentName());

        return LinkTarget.DETAIL;
    }

    public void reset() {

        this.headerBean = new AgentSaleClaimFinishedReportHeaderBean();
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

    public LazyDataModel<AgentSaleClaimFinishedReportLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgentSaleClaimFinishedReportLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<AgentSaleClaimFinishedReportLineBean> getAgentSaleReportLineBeanList() {
        return agentSaleReportLineBeanList;
    }

    public void setAgentSaleReportLineBeanList(List<AgentSaleClaimFinishedReportLineBean> agentSaleReportLineBeanList) {
        this.agentSaleReportLineBeanList = agentSaleReportLineBeanList;
    }

    public AgentSaleClaimFinishedReportHeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(AgentSaleClaimFinishedReportHeaderBean headerBean) {
        this.headerBean = headerBean;
    }

    public AgentSaleClaimFinishedReportLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgentSaleClaimFinishedReportLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
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

    public Boolean getAgentNameSearchFlag() {
        return agentNameSearchFlag;
    }

    public void setAgentNameSearchFlag(Boolean agentNameSearchFlag) {
        this.agentNameSearchFlag = agentNameSearchFlag;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public AgentSaleClaimFinishedReportSearchReqDto getAgentSaleClaimFinishedReportSearchReqDto() {
        return agentSaleClaimFinishedReportSearchReqDto;
    }

    public void setAgentSaleClaimFinishedReportSearchReqDto(
            AgentSaleClaimFinishedReportSearchReqDto agentSaleClaimFinishedReportSearchReqDto) {
        this.agentSaleClaimFinishedReportSearchReqDto = agentSaleClaimFinishedReportSearchReqDto;
    }

    public AgentSaleClaimFinishedDetailReportHeaderBean getReportDetailParam() {
        return reportDetailParam;
    }

    public void setReportDetailParam(AgentSaleClaimFinishedDetailReportHeaderBean reportDetailParam) {
        this.reportDetailParam = reportDetailParam;
    }

}
