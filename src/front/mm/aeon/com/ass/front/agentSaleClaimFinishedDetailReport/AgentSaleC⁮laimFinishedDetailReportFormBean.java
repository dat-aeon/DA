/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleClaimFinishedDetailReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedDetailReportSearch.AgentSaleClaimFinishedDetailReportSearchReqDto;
import mm.aeon.com.ass.front.agentAndProductInfoUploadedReport.AgentAndProductInfoUploadedReportFormBean;
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
@Name("agentSaleClaimFinishedDetailReport")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentSaleC⁮laimFinishedDetailReportFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7268045135060096439L;

    @In(required = false, value = "reportDetailParam")
    @Out(required = false, value = "reportDetailParam")
    private AgentSaleClaimFinishedDetailReportHeaderBean reportDetailParam;

    public Boolean agentNameSearchFlag;

    private LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> lazyModel;

    private AgentSaleClaimFinishedDetailReportSearchReqDto agentSaleClaimFinishedDetailReportSearchReqDto;

    private AgentSaleClaimFinishedDetailReportHeaderBean headerBean;

    private AgentSaleClaimFinishedDetailReportHeaderBean defaultSearchHeaderBean;

    private AgentSaleClaimFinishedDetailReportLineBean lineBean;

    private List<AgentSaleClaimFinishedDetailReportLineBean> agentSaleReportLineBeanList;

    private int pageFirst;

    private int totalCount;

    DecimalFormat decimalformat = new DecimalFormat("###,##0.00");

    private String reportName;

    private String pdfPath;

    private String filePath;

    private String fileName;

    private StreamedContent file;

    private boolean init = true;

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
        headerBean = new AgentSaleClaimFinishedDetailReportHeaderBean();
        defaultSearchHeaderBean = new AgentSaleClaimFinishedDetailReportHeaderBean();
        this.defaultSearchHeaderBean.setAgentId(reportDetailParam.getAgentId());
        this.defaultSearchHeaderBean.setAgentName(reportDetailParam.getAgentName());
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {

        doReload = new Boolean(false);
        lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgentSaleClaimFinishedDetailReportPaginationController(totalCount,
                    agentSaleClaimFinishedDetailReportSearchReqDto);

        }

        return LinkTarget.OK;

    }

    public void downloadSaleClaimFinishedDetailReport() {
        String downloadFileName = "Agent_Sale_Claim_Finished_Detail_Report.xlsx";
        File downloadFile = generateSaleClaimFinishedDetailReport(lazyModel, downloadFileName);
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

    public File generateSaleClaimFinishedDetailReport(
            LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> agentSaleClaimFinihsedList, String reportName) {
        // Create blank workbook
        XSSFWorkbook workBook;
        ClassLoader classLoader = new AgentAndProductInfoUploadedReportFormBean().getClass().getClassLoader();
        File exFile = new File(
                classLoader.getResource("report-template/Agent_Sale_Claim_Finished_Detail_Report.xlsx").getFile());

        String filePath = ProjectPath.createTempDirectory(reportName);
        File file = new File(filePath + reportName);

        try {
            InputStream inputStream = new FileInputStream(exFile);
            workBook = new XSSFWorkbook(inputStream);
            int i = 4;
            int index = 0;
            XSSFSheet sheet = workBook.getSheet("SaleClaimFinishedListReport");
            /* sheet.setDisplayGridlines(false); */

            Row rowt = sheet.createRow(i - 3);
            rowt.setHeightInPoints(2 * 16);

            Cell dateCell = rowt.createCell(0);
            dateCell.setCellValue("Date:");
            dateCell.setCellStyle(getDefaultCell(workBook));
            dateCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);
            dateCell.getCellStyle().setBorderBottom(CellStyle.BORDER_NONE);
            dateCell.getCellStyle().setBorderTop(CellStyle.BORDER_NONE);
            dateCell.getCellStyle().setBorderLeft(CellStyle.BORDER_NONE);
            dateCell.getCellStyle().setBorderRight(CellStyle.BORDER_NONE);

            LocalDate date = LocalDate.now();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
            String dateNow = date.format(formatters);
            Cell dateValueCell = rowt.createCell(1);
            dateValueCell.setCellValue(dateNow);
            dateValueCell.setCellStyle(getDefaultCell(workBook));
            dateValueCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);
            dateValueCell.getCellStyle().setBorderBottom(CellStyle.BORDER_NONE);
            dateValueCell.getCellStyle().setBorderTop(CellStyle.BORDER_NONE);
            dateValueCell.getCellStyle().setBorderLeft(CellStyle.BORDER_NONE);
            dateValueCell.getCellStyle().setBorderRight(CellStyle.BORDER_NONE);

            for (int columnNo = 8; columnNo < 11; columnNo++) {
                rowt.createCell(columnNo);
                rowt.getCell(columnNo).setCellStyle(getDefaultCell(workBook));
                rowt.getCell(columnNo).setCellValue("Agent Name : " + this.defaultSearchHeaderBean.getAgentName());
                rowt.getCell(columnNo).getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);
                rowt.getCell(columnNo).getCellStyle().setBorderBottom(CellStyle.BORDER_NONE);
                rowt.getCell(columnNo).getCellStyle().setBorderTop(CellStyle.BORDER_NONE);
                rowt.getCell(columnNo).getCellStyle().setBorderLeft(CellStyle.BORDER_NONE);
                rowt.getCell(columnNo).getCellStyle().setBorderRight(CellStyle.BORDER_NONE);
                rowt.getCell(columnNo).getCellStyle().setWrapText(true);
            }

            for (AgentSaleClaimFinishedDetailReportLineBean agentAndProductBean : agentSaleClaimFinihsedList) {
                i = i + 1;
                index = index + 1;
                Row row = sheet.createRow(i);
                row.setHeightInPoints(2 * 16);

                Cell noCell = row.createCell(0);
                noCell.setCellValue(index);
                noCell.setCellStyle(getDefaultCell(workBook));
                noCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell agentNameCell = row.createCell(1);
                DateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date saleCalimDate = new Date();
                saleCalimDate.setTime(agentAndProductBean.getSaleClaimFinishedDate().getTime());
                agentNameCell.setCellValue(f.format(saleCalimDate));
                agentNameCell.setCellStyle(getDefaultCell(workBook));
                agentNameCell.getCellStyle().setWrapText(true);
                agentNameCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell outletNameCell = row.createCell(2);
                outletNameCell.setCellValue(agentAndProductBean.getStaffName());
                outletNameCell.setCellStyle(getDefaultCell(workBook));
                outletNameCell.getCellStyle().setWrapText(true);
                outletNameCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell locationCell = row.createCell(3);
                locationCell.setCellValue(agentAndProductBean.getCustomerName());
                locationCell.setCellStyle(getDefaultCell(workBook));
                locationCell.getCellStyle().setWrapText(true);
                locationCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell nrcNoCell = row.createCell(4);
                nrcNoCell.setCellValue(agentAndProductBean.getNrcNo());
                nrcNoCell.setCellStyle(getDefaultCell(workBook));
                nrcNoCell.getCellStyle().setWrapText(true);
                nrcNoCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell accountNoCell = row.createCell(5);
                accountNoCell.setCellValue(agentAndProductBean.getInvoiceNo());
                accountNoCell.setCellStyle(getDefaultCell(workBook));
                accountNoCell.getCellStyle().setWrapText(true);
                accountNoCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell financeAmountCell = row.createCell(6);
                financeAmountCell.setCellValue(agentAndProductBean.getAgreementNo());
                financeAmountCell.setCellStyle(getDefaultCell(workBook));
                financeAmountCell.getCellStyle().setWrapText(true);
                financeAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                Cell totalProcessingFeeCell = row.createCell(7);
                totalProcessingFeeCell.setCellValue(decimalformat.format(agentAndProductBean.getProcessingFeeAmount()));
                totalProcessingFeeCell.setCellStyle(getDefaultCell(workBook));
                totalProcessingFeeCell.getCellStyle().setWrapText(true);
                totalProcessingFeeCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

                Cell totalCompulsorySavingCell = row.createCell(8);
                totalCompulsorySavingCell.setCellValue(decimalformat.format(agentAndProductBean.getCompulsorySaving()));
                totalCompulsorySavingCell.setCellStyle(getDefaultCell(workBook));
                totalCompulsorySavingCell.getCellStyle().setWrapText(true);
                totalCompulsorySavingCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

                Cell settlementAmountCell = row.createCell(9);
                settlementAmountCell.setCellValue(decimalformat.format(agentAndProductBean.getSettlementAmount()));
                settlementAmountCell.setCellStyle(getDefaultCell(workBook));
                settlementAmountCell.getCellStyle().setWrapText(true);
                settlementAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

                Cell approvedAmountCell = row.createCell(10);
                approvedAmountCell.setCellValue(decimalformat.format(agentAndProductBean.getApprovedFinanceAmount()));
                approvedAmountCell.setCellStyle(getDefaultCell(workBook));
                approvedAmountCell.getCellStyle().setWrapText(true);
                approvedAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);
            }

            i = i + 1;
            XSSFRow row1 = sheet.createRow(i);
            row1.setHeightInPoints(2 * 16);

            for (int columnNo = 0; columnNo < 7; columnNo++) {
                row1.createCell(columnNo);
                row1.getCell(columnNo).setCellStyle(getDefaultCell(workBook));
                row1.getCell(columnNo).setCellValue("Total");
                row1.getCell(columnNo).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                ;
                row1.getCell(columnNo).getCellStyle().setWrapText(true);
            }
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 5));

            Cell totalNetProcessingFeeCell = row1.createCell(7);
            totalNetProcessingFeeCell
                    .setCellValue(decimalformat.format(calculateTotalProcessingFeeAmount(agentSaleClaimFinihsedList)));
            totalNetProcessingFeeCell.setCellStyle(getDefaultCell(workBook));
            totalNetProcessingFeeCell.getCellStyle().setWrapText(true);
            totalNetProcessingFeeCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            Cell totalNetCompulsorySavingCell = row1.createCell(8);
            totalNetCompulsorySavingCell
                    .setCellValue(decimalformat.format(calculateTotalCompulsorySaving(agentSaleClaimFinihsedList)));
            totalNetCompulsorySavingCell.setCellStyle(getDefaultCell(workBook));
            totalNetCompulsorySavingCell.getCellStyle().setWrapText(true);
            totalNetCompulsorySavingCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            Cell totalNetSettlementAmountCell = row1.createCell(9);
            totalNetSettlementAmountCell
                    .setCellValue(decimalformat.format(calculateTotalSettlementAmount(agentSaleClaimFinihsedList)));
            totalNetSettlementAmountCell.setCellStyle(getDefaultCell(workBook));
            totalNetSettlementAmountCell.getCellStyle().setWrapText(true);
            totalNetSettlementAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            Cell totalNetApprovedAmountCell = row1.createCell(10);
            totalNetApprovedAmountCell
                    .setCellValue(decimalformat.format(calculateTotalApprovedAmount(agentSaleClaimFinihsedList)));
            totalNetApprovedAmountCell.setCellStyle(getDefaultCell(workBook));
            totalNetApprovedAmountCell.getCellStyle().setWrapText(true);
            totalNetApprovedAmountCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);

            FileOutputStream out = new FileOutputStream(file);
            workBook.setPrintArea(0, "$A$1:$K$" + (i + 1));
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

    private Double calculateTotalProcessingFeeAmount(
            LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedDetailReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getProcessingFeeAmount();
        }
        return total;
    }

    private Double calculateTotalCompulsorySaving(
            LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedDetailReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getCompulsorySaving();
        }
        return total;
    }

    private Double calculateTotalSettlementAmount(
            LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedDetailReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getSettlementAmount();
        }
        return total;
    }

    private Double calculateTotalApprovedAmount(
            LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> saleClaimFinishedList) {
        Double total = 0.0;
        for (AgentSaleClaimFinishedDetailReportLineBean agentSaleClaimDto : saleClaimFinishedList) {
            total += agentSaleClaimDto.getApprovedFinanceAmount();
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

    public void reset() {

        this.headerBean = new AgentSaleClaimFinishedDetailReportHeaderBean();
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.reportDetailParam = null;
        this.headerBean = null;
        this.defaultSearchHeaderBean = null;

        return LinkTarget.BACK;
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

    public LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgentSaleClaimFinishedDetailReportLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<AgentSaleClaimFinishedDetailReportLineBean> getAgentSaleReportLineBeanList() {
        return agentSaleReportLineBeanList;
    }

    public void setAgentSaleReportLineBeanList(
            List<AgentSaleClaimFinishedDetailReportLineBean> agentSaleReportLineBeanList) {
        this.agentSaleReportLineBeanList = agentSaleReportLineBeanList;
    }

    public AgentSaleClaimFinishedDetailReportHeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(AgentSaleClaimFinishedDetailReportHeaderBean headerBean) {
        this.headerBean = headerBean;
    }

    public AgentSaleClaimFinishedDetailReportLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgentSaleClaimFinishedDetailReportLineBean lineBean) {
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

    public AgentSaleClaimFinishedDetailReportSearchReqDto getAgentSaleClaimFinishedDetailReportSearchReqDto() {
        return agentSaleClaimFinishedDetailReportSearchReqDto;
    }

    public void setAgentSaleClaimFinishedDetailReportSearchReqDto(
            AgentSaleClaimFinishedDetailReportSearchReqDto agentSaleClaimFinishedDetailReportSearchReqDto) {
        this.agentSaleClaimFinishedDetailReportSearchReqDto = agentSaleClaimFinishedDetailReportSearchReqDto;
    }

    public AgentSaleClaimFinishedDetailReportHeaderBean getReportDetailParam() {
        return reportDetailParam;
    }

    public AgentSaleClaimFinishedDetailReportHeaderBean getDefaultSearchHeaderBean() {
        return defaultSearchHeaderBean;
    }

    public void setReportDetailParam(AgentSaleClaimFinishedDetailReportHeaderBean reportDetailParam) {
        this.reportDetailParam = reportDetailParam;
    }

    public void setDefaultSearchHeaderBean(AgentSaleClaimFinishedDetailReportHeaderBean defaultSearchHeaderBean) {
        this.defaultSearchHeaderBean = defaultSearchHeaderBean;
    }

}
