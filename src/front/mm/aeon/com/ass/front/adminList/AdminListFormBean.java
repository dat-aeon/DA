/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.adminList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;

import mm.aeon.com.ass.front.adminManagement.AdminManagementHeaderBean;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.operatorManagement.OperatorManagementHeaderBean;
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
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

@Name("adminListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AdminListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private AdminListHeaderBean searchHeaderBean;

    private List<AdminListLineBean> lineBeans;

    private LazyDataModel<AdminListLineBean> lazyModel;

    private AdminListLineBean lineBean;
    
    private ArrayList<SelectItem> groupSelectItemList;

    private ArrayList<SelectItem> statusList;

    @Out(required = false, value = "adminUpdateParam")
    private AdminManagementHeaderBean updateParam;
    
    @In(required = false, value = "adminRegisterParam")
    @Out(required = false, value = "adminRegisterParam")
    private AdminManagementHeaderBean registerParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    private String reportName;

    private String pdfPath;

    private String filePath;

    private String fileName;

    @Begin(nested = true)
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.ADMIN_LIST);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new AdminListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && lineBeans.size() != 0) {
            lazyModel = new AdminListPaginationController(lineBeans.size(), lineBeans);
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

    public void reportEmployeeResign() {
        reportName = "DOWNLOAD_EMPLOYEE_RESIGN_LABEL";
        pdfPath = "/temp/EmployeeResign/" + System.currentTimeMillis() + "/";
        filePath = getSystemPath() + pdfPath;
        fileName = reportName + ".pdf";
        generateEmployeeResign(filePath, fileName);
        // RequestContext context = RequestContext.getCurrentInstance();
        // context.execute("PF('employeePrintDialog').show();");
        // context.update("adminSearchform");
    }

    public void generateEmployeeResign(String dirPath, String fileName) {
        try {
            Map<String, Object> parameterMap = new HashMap<>();
            DecimalFormat decimalformat = new DecimalFormat("###,###,###,##0.00");
            parameterMap.put("employeeNo", "aa");
            parameterMap.put("employeeName", "aa");
            parameterMap.put("employeeNrc", "aa");
            parameterMap.put("employeeAddress", "aa");
            parameterMap.put("employeeNrc", "aa");
            parameterMap.put("jobTitle", "aa");
            parameterMap.put("lastMonthSalary", "aa");
            parameterMap.put("dateOfEmployment", "aa");
            parameterMap.put("dateOfResignation", "aa");
            parameterMap.put("resignationReason", "aa");
            parameterMap.put("additionalInformation", "aa");

            InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext()
                    .getResourceAsStream("/report-template/employeeResignPrint.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, new JREmptyDataSource());
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
            FileUtils.forceMkdir(new File(dirPath));
            OutputStream outputStream = new FileOutputStream(dirPath + fileName);
            exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
            outputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getReportStream() {
        return pdfPath + fileName;
    }

    public String prepareUpdate(AdminListLineBean lineBean) {

        this.updateParam = new AdminManagementHeaderBean();
        this.updateParam.setUserId(lineBean.getAdminId());
        this.updateParam.setLoginId(lineBean.getAdminLoginId());
        this.updateParam.setName(lineBean.getAdminName());
        this.updateParam.setUpdatedTime(lineBean.getUpdatedTime());
        if(lineBean.getGroupId() != null) {
            updateParam.setGroupId(lineBean.getGroupId());
        }
        this.updateParam.setGroupSelectItemList(this.groupSelectItemList);

        return LinkTarget.UPDATE_INIT;
    }
    
    public String getGroupValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : groupSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    @Action
    public String toggleValidStatus(AdminListLineBean lineBean) {
        this.lineBean = null;
        if (!this.getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }
        return LinkTarget.OK;
    }

    @Action
    public String delete() {
        this.doReload = false;
        this.lineBean = null;
        if (!getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }

        return LinkTarget.OK;
    }

    public void reset() {
        this.searchHeaderBean = new AdminListHeaderBean();
    }

    public String prepareRegister() {
        this.updateParam = null;
        registerParam = new AdminManagementHeaderBean();
        registerParam.setGroupSelectItemList(this.groupSelectItemList);
        return LinkTarget.REGISTER;
    }

    public AdminManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(AdminManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public AdminListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(AdminListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public List<AdminListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<AdminListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<AdminListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AdminListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AdminListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AdminListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public ArrayList<SelectItem> getStatusList() {

        statusList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        item.setLabel(VCSCommon.SPACE);
        item.setValue(VCSCommon.TWO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.DISABLED);
        item.setValue(VCSCommon.ZERO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.ENABLED);
        item.setValue(VCSCommon.ONE);
        statusList.add(item);

        return statusList;
    }

    public void setStatusList(ArrayList<SelectItem> statusList) {
        this.statusList = statusList;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public ArrayList<SelectItem> getGroupSelectItemList() {
        return groupSelectItemList;
    }

    public void setGroupSelectItemList(ArrayList<SelectItem> groupSelectItemList) {
        this.groupSelectItemList = groupSelectItemList;
    }

    public AdminManagementHeaderBean getRegisterParam() {
        return registerParam;
    }

    public void setRegisterParam(AdminManagementHeaderBean registerParam) {
        this.registerParam = registerParam;
    }

}
