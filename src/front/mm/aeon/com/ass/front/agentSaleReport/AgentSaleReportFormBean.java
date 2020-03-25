/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
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

import mm.aeon.com.ass.base.dto.agentSaleReportSearch.AgentSaleReportSearchReqDto;
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
@SuppressWarnings("deprecation")
@Name("agentSaleReport")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentSaleReportFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7268045135060096439L;

    public Boolean agentNameSearchFlag;

    private LazyDataModel<AgentSaleReportLineBean> lazyModel;

    private AgentSaleReportSearchReqDto agentSaleReportSearchReqDto;

    private AgentSaleReportHeaderBean headerbean;

    private AgentSaleReportLineBean lineBean;

    private List<AgentSaleReportLineBean> agentSaleReportLineBeanList;

    private int pageFirst;

    private int totalCount;

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

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.AGENT_SALE_REPORT);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        headerbean = new AgentSaleReportHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {

        doReload = new Boolean(false);
        lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgentSaleReportPaginationController(totalCount, agentSaleReportSearchReqDto);

        }

        return LinkTarget.OK;

    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected String getSystemPath() {
        Object context = FacesContext.getCurrentInstance().getExternalContext().getContext();
        String systemPath = ((ServletContext) context).getRealPath("/");
        return systemPath;
    }

    public StreamedContent reportAgentSaleReportResign() {

        reportName = "DOWNLOAD_AgentSaleReport";
        pdfPath = "/temp/AgentSaleReport/" + System.currentTimeMillis() + "/";

        filePath = getSystemPath() + pdfPath;
        fileName = reportName + ".xlsx";

        // RequestContext context = RequestContext.getCurrentInstance();
        // context.execute("PF('employeePrintDialog').show();");
        // context.update("adminSearchform");
        return generateAgentSaleReportResign(filePath, fileName);
    }

    public StreamedContent generateAgentSaleReportResign(String dirPath, String fileName) {
        try {
            agentSaleReportLineBeanList = new ArrayList<AgentSaleReportLineBean>();

            for (AgentSaleReportLineBean data : lazyModel)
                agentSaleReportLineBeanList.add(data);

            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("date", LocalDate.now());
            parameterMap.put("agentName", agentSaleReportLineBeanList.get(0).getAgentName());
            parameterMap.put("name", CommonUtil.getLoginUserInfo().getUserName());
            parameterMap.put("AgentSaleReport", agentSaleReportLineBeanList);
            FacesContext facesContext = FacesContext.getCurrentInstance();

            ClassLoader classLoader = new AgentSaleReportFormBean().getClass().getClassLoader();
            File exfile = new File(classLoader.getResource("report-template/AgentSaleReport.jrxml").getFile());
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
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "AgentSaleReport.xlsx");

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

    public void reset() {

        this.headerbean = new AgentSaleReportHeaderBean();
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

    public LazyDataModel<AgentSaleReportLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgentSaleReportLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public AgentSaleReportSearchReqDto getAgentSaleReportSearchReqDto() {
        return agentSaleReportSearchReqDto;
    }

    public void setAgentSaleReportSearchReqDto(AgentSaleReportSearchReqDto agentSaleReportSearchReqDto) {
        this.agentSaleReportSearchReqDto = agentSaleReportSearchReqDto;
    }

    public AgentSaleReportHeaderBean getHeaderbean() {
        return headerbean;
    }

    public void setHeaderbean(AgentSaleReportHeaderBean headerbean) {
        this.headerbean = headerbean;
    }

    public AgentSaleReportLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgentSaleReportLineBean lineBean) {
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

}
