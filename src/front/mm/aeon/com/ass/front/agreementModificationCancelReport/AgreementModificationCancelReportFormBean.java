/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationCancelReport;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;

import mm.aeon.com.ass.base.dto.agreementModificationCancelReportSearch.AgreementModificationCancelReportSearchReqDto;
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
@Name("agreementModificationCancelReportFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgreementModificationCancelReportFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7268045135060096439L;

    public Boolean agentNameSearchFlag;

    private LazyDataModel<AgreementModificationCancelReportLineBean> lazyModel;

    private AgreementModificationCancelReportSearchReqDto agreementModificationCancelReportSearchReqDto;

    private AgreementModificationCancelReportHeaderBean headerBean;

    private AgreementModificationCancelReportLineBean lineBean;

    private List<AgreementModificationCancelReportLineBean> agentAndProductUploadedReportList;

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

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.AGREEMENT_MODIFICATION_REPORT);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        headerBean = new AgreementModificationCancelReportHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {

        doReload = new Boolean(false);
        lazyModel = null;
        agentAndProductUploadedReportList = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgreementModificationCancelReportPaginationController(totalCount,
                    agreementModificationCancelReportSearchReqDto);
        }

        return LinkTarget.OK;

    }

    public void reset() {

        this.headerBean = new AgreementModificationCancelReportHeaderBean();
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

    public LazyDataModel<AgreementModificationCancelReportLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgreementModificationCancelReportLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public AgreementModificationCancelReportSearchReqDto getAgreementModificationCancelReportSearchReqDto() {
        return agreementModificationCancelReportSearchReqDto;
    }

    public void setAgreementModificationCancelReportSearchReqDto(
            AgreementModificationCancelReportSearchReqDto agreementModificationCancelReportSearchReqDto) {
        this.agreementModificationCancelReportSearchReqDto = agreementModificationCancelReportSearchReqDto;
    }

    public AgreementModificationCancelReportHeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(AgreementModificationCancelReportHeaderBean headerBean) {
        this.headerBean = headerBean;
    }

    public AgreementModificationCancelReportLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgreementModificationCancelReportLineBean lineBean) {
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

    public List<AgreementModificationCancelReportLineBean> getAgentAndProductUploadedReportList() {
        return agentAndProductUploadedReportList;
    }

    public void setAgentAndProductUploadedReportList(
            List<AgreementModificationCancelReportLineBean> agentAndProductUploadedReportList) {
        this.agentAndProductUploadedReportList = agentAndProductUploadedReportList;
    }
}
