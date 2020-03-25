/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.leadTimeReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import mm.aeon.com.ass.base.dto.leadTimeReportSearch.LeadTimeReportSearchReqDto;
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
@Name("LeadTimeReportFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class LeadTimeReportFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7268045135060096439L;

    public Boolean agentNameSearchFlag;

    private LazyDataModel<LeadTimeReportLineBean> lazyModel;

    private LeadTimeReportSearchReqDto leadTimeReportSearchReqDto;

    private LeadTimeReportHeaderBean headerBean;

    private LeadTimeReportLineBean lineBean;

    private List<LeadTimeReportLineBean> leadTimeReportLineBeanList;

    private List<LeadTimeReportTimeLineBean> timeBeanList;

    private int pageFirst;

    private int totalCount;

    private boolean saleClaimdialogVisible;

    private boolean documentErrordialogVisible;

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

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.LEAD_TIME_REPORT);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        headerBean = new LeadTimeReportHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {

        doReload = new Boolean(false);
        lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new LeadTimeReportPaginationController(totalCount, leadTimeReportSearchReqDto);

        }

        return LinkTarget.OK;

    }

    public void downloadLeadTimeReport() {
        String downloadFileName = "Lead_Time_Report.xlsx";
        File downloadFile = generateLeadTimeReport(lazyModel, downloadFileName);
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

    public File generateLeadTimeReport(LazyDataModel<LeadTimeReportLineBean> leadTimeReportList, String reportName) {
        // Create blank workbook
        XSSFWorkbook workBook;
        ClassLoader classLoader = new AgentAndProductInfoUploadedReportFormBean().getClass().getClassLoader();
        File exFile = new File(classLoader.getResource("report-template/Lead_Time_Report.xlsx").getFile());

        String filePath = ProjectPath.createTempDirectory(reportName);
        File file = new File(filePath + reportName);

        try {
            InputStream inputStream = new FileInputStream(exFile);
            workBook = new XSSFWorkbook(inputStream);
            int i = 3;
            int index = 0;
            int startRow = 0;
            XSSFSheet sheet = workBook.getSheet("leadTimeReport");
            DateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            for (LeadTimeReportLineBean leadTimeReportBean : leadTimeReportList) {
                i = i + 1;
                startRow = i;
                index = index + 1;
                int myIndex = 0;
                int noRow = leadTimeReportBean.getSaleClaimTimeList().size();
                int defaultRow = i + (noRow > 1 ? noRow - 1 : 0);

                for (int iRow = i; iRow <= defaultRow; iRow++) {

                    i = iRow;
                    Row row = sheet.createRow(i);
                    row.setHeightInPoints(2 * 16);

                    Cell noCell = row.createCell(0);
                    noCell.setCellValue(index);
                    noCell.setCellStyle(getDefaultCell(workBook));
                    noCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell applicationNoCell = row.createCell(1);
                    applicationNoCell.setCellValue(leadTimeReportBean.getApplicationNo());
                    applicationNoCell.setCellStyle(getDefaultCell(workBook));
                    applicationNoCell.getCellStyle().setWrapText(true);
                    applicationNoCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell customerNameCell = row.createCell(2);
                    customerNameCell.setCellValue(leadTimeReportBean.getCustomerName());
                    customerNameCell.setCellStyle(getDefaultCell(workBook));
                    customerNameCell.getCellStyle().setWrapText(true);
                    customerNameCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell nrcNoCell = row.createCell(3);
                    nrcNoCell.setCellValue(leadTimeReportBean.getNrcNo());
                    nrcNoCell.setCellStyle(getDefaultCell(workBook));
                    nrcNoCell.getCellStyle().setWrapText(true);
                    nrcNoCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell phoneNoCell = row.createCell(4);
                    phoneNoCell.setCellValue(leadTimeReportBean.getPhoneNo());
                    phoneNoCell.setCellStyle(getDefaultCell(workBook));
                    phoneNoCell.getCellStyle().setWrapText(true);
                    phoneNoCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell agreementNoCell = row.createCell(5);
                    agreementNoCell.setCellValue(leadTimeReportBean.getAgreementNo());
                    agreementNoCell.setCellStyle(getDefaultCell(workBook));
                    agreementNoCell.getCellStyle().setWrapText(true);
                    agreementNoCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell infoReceivedTimeCell = row.createCell(6);
                    Date infoReceivedDate = new Date();
                    infoReceivedDate.setTime(leadTimeReportBean.getInformationReceivedTime().getTime());
                    infoReceivedTimeCell.setCellValue(f.format(infoReceivedDate));
                    infoReceivedTimeCell.setCellStyle(getDefaultCell(workBook));
                    infoReceivedTimeCell.getCellStyle().setWrapText(true);
                    infoReceivedTimeCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell saleClaimTimeCell = row.createCell(7);
                    Date saleClaimDate = new Date();
                    saleClaimDate.setTime(leadTimeReportBean.getSaleClaimTime().getTime());
                    saleClaimTimeCell.setCellValue(f.format(saleClaimDate));
                    saleClaimTimeCell.setCellStyle(getDefaultCell(workBook));
                    saleClaimTimeCell.getCellStyle().setWrapText(true);
                    saleClaimTimeCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell documentErrorEditedTimeCell = row.createCell(8);
                    if (leadTimeReportBean.getDocumentErrorTimeList().size() != 0) {
                        Date documentErrorEditedDate = new Date();
                        documentErrorEditedDate.setTime(
                                leadTimeReportBean.getDocumentErrorTimeList().get(myIndex).getReportTime().getTime());
                        documentErrorEditedTimeCell.setCellValue(f.format(documentErrorEditedDate));
                    } else {
                        documentErrorEditedTimeCell.setCellValue("");
                    }
                    documentErrorEditedTimeCell.setCellStyle(getDefaultCell(workBook));
                    documentErrorEditedTimeCell.getCellStyle().setWrapText(true);
                    documentErrorEditedTimeCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell saleClaimErrorEditedTimeCell = row.createCell(9);
                    if (leadTimeReportBean.getSaleClaimTimeList().size() != 0) {
                        Date saleClaimErrorEditedDate = new Date();
                        saleClaimErrorEditedDate.setTime(
                                leadTimeReportBean.getSaleClaimTimeList().get(myIndex).getReportTime().getTime());
                        saleClaimErrorEditedTimeCell.setCellValue(f.format(saleClaimErrorEditedDate));
                    } else {
                        saleClaimErrorEditedTimeCell.setCellValue("");
                    }
                    saleClaimErrorEditedTimeCell.setCellStyle(getDefaultCell(workBook));
                    saleClaimErrorEditedTimeCell.getCellStyle().setWrapText(true);
                    saleClaimErrorEditedTimeCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    Cell saleEntryTimeCell = row.createCell(10);
                    Date saleEntryDate = new Date();
                    saleEntryDate.setTime(leadTimeReportBean.getSaleEntryTime().getTime());
                    saleEntryTimeCell.setCellValue(f.format(saleEntryDate));
                    saleEntryTimeCell.setCellStyle(getDefaultCell(workBook));
                    saleEntryTimeCell.getCellStyle().setWrapText(true);
                    saleEntryTimeCell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);

                    myIndex++;

                }

                if (leadTimeReportBean.getSaleClaimTimeList().size() > 1) {
                    // No
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 0, 0));
                    // Application No
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 1, 1));
                    // Customer Name
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 2, 2));
                    // Nrc No
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 3, 3));
                    // Mobile No
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 4, 4));
                    // Agreement No
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 5, 5));
                    // Information Received Time
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 6, 6));
                    // Sale Claim First Reached Time
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 7, 7));
                    // Sale Claim Time
                    sheet.addMergedRegion(new CellRangeAddress(startRow, i, 10, 10));
                }
            }

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

    protected String getSystemPath() {
        Object context = FacesContext.getCurrentInstance().getExternalContext().getContext();
        String systemPath = ((ServletContext) context).getRealPath("/");
        return systemPath;
    }

    public String getReportStream() {
        return pdfPath + fileName;
    }

    public void reset() {

        this.headerBean = new LeadTimeReportHeaderBean();
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.headerBean = null;

        return LinkTarget.BACK;
    }

    public String viewSaleClaimTime(LeadTimeReportLineBean lineBean) {
        if (lineBean.getSaleClaimTimeList().size() > 0) {
            List<LeadTimeReportTimeLineBean> saleClaimTimeBeaList = new ArrayList<LeadTimeReportTimeLineBean>();
            for (LeadTimeReportTimeLineBean timeBean : lineBean.getSaleClaimTimeList()) {
                LeadTimeReportTimeLineBean timeReportBean = new LeadTimeReportTimeLineBean();
                timeReportBean.setReportTime(timeBean.getReportTime());
                saleClaimTimeBeaList.add(timeReportBean);
            }
            Collections.sort(saleClaimTimeBeaList, Comparator.comparing(LeadTimeReportTimeLineBean::getReportTime));
            this.timeBeanList = saleClaimTimeBeaList;
        }
        doReload = false;
        saleClaimdialogVisible = true;
        return LinkTarget.OK;
    }

    public void closeSaleClaimDialog() {
        this.timeBeanList = null;
        saleClaimdialogVisible = false;
    }

    public String viewDocumentErrorTime(LeadTimeReportLineBean lineBean) {
        if (lineBean.getDocumentErrorTimeList().size() > 0) {
            List<LeadTimeReportTimeLineBean> documentErrorTimeBeaList = new ArrayList<LeadTimeReportTimeLineBean>();

            for (LeadTimeReportTimeLineBean timeBean : lineBean.getDocumentErrorTimeList()) {
                LeadTimeReportTimeLineBean timeReportBean = new LeadTimeReportTimeLineBean();
                timeReportBean.setReportTime(timeBean.getReportTime());
                documentErrorTimeBeaList.add(timeReportBean);
            }
            Collections.sort(documentErrorTimeBeaList, Comparator.comparing(LeadTimeReportTimeLineBean::getReportTime));
            this.timeBeanList = documentErrorTimeBeaList;
        }

        doReload = false;
        documentErrordialogVisible = true;
        return LinkTarget.OK;
    }

    public void closeDocumentErrorDialog() {
        this.timeBeanList = null;
        documentErrordialogVisible = false;
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

    public LazyDataModel<LeadTimeReportLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<LeadTimeReportLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<LeadTimeReportLineBean> getLeadTimeReportLineBeanList() {
        return leadTimeReportLineBeanList;
    }

    public void setLeadTimeReportLineBeanList(List<LeadTimeReportLineBean> leadTimeReportLineBeanList) {
        this.leadTimeReportLineBeanList = leadTimeReportLineBeanList;
    }

    public LeadTimeReportHeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(LeadTimeReportHeaderBean headerBean) {
        this.headerBean = headerBean;
    }

    public LeadTimeReportLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(LeadTimeReportLineBean lineBean) {
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

    public LeadTimeReportSearchReqDto getLeadTimeReportSearchReqDto() {
        return leadTimeReportSearchReqDto;
    }

    public void setLeadTimeReportSearchReqDto(LeadTimeReportSearchReqDto leadTimeReportSearchReqDto) {
        this.leadTimeReportSearchReqDto = leadTimeReportSearchReqDto;
    }

    public boolean isSaleClaimdialogVisible() {
        return saleClaimdialogVisible;
    }

    public boolean isDocumentErrordialogVisible() {
        return documentErrordialogVisible;
    }

    public void setSaleClaimdialogVisible(boolean saleClaimdialogVisible) {
        this.saleClaimdialogVisible = saleClaimdialogVisible;
    }

    public void setDocumentErrordialogVisible(boolean documentErrordialogVisible) {
        this.documentErrordialogVisible = documentErrordialogVisible;
    }

    public List<LeadTimeReportTimeLineBean> getTimeBeanList() {
        return timeBeanList;
    }

    public void setTimeBeanList(List<LeadTimeReportTimeLineBean> timeBeanList) {
        this.timeBeanList = timeBeanList;
    }

}
