/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.departmentList;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;

import mm.aeon.com.ass.base.dto.applicationListSearch.ApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSearchReqDto;
import mm.aeon.com.ass.front.adminList.AdminListLineBean;
import mm.aeon.com.ass.front.adminManagement.AdminManagementHeaderBean;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.departmentManagement.DepartmentManagementHeaderBean;
import mm.aeon.com.ass.front.interestRateList.InterestRateListLineBean;
import mm.aeon.com.ass.front.interestRateManagement.InterestRateManagementHeaderBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("departmentListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class DepartmentListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private List<DepartmentListLineBean> lineBeans;

    private LazyDataModel<DepartmentListLineBean> lazyModel;

    private DepartmentListLineBean lineBean;

    private ArrayList<SelectItem> statusList;
    
    private int totalCount;

    private DepartmentListSearchReqDto applicationSearchReqDto;

    @Out(required = false, value = "departmentUpdateParam")
    private DepartmentManagementHeaderBean updateParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    @Begin(nested = true)
    public void init() {
        ASSLogger logger = new ASSLogger();
        
        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.DEPARTMENT_LIST);
        if(result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
            LogLevel.ERROR); throw new InvalidScreenTransitionException();
        }
        
        this.getMessageContainer().clearAllMessages(true);
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new DepartmentListPaginationController(totalCount, applicationSearchReqDto);
        }

        return LinkTarget.OK;
    }

    public String detail(DepartmentListLineBean lineBean) {
        this.getMessageContainer().clearAllMessages(true);
        this.lineBean = lineBean;
        return LinkTarget.DETAIL;
    }

    public String prepareRegister() {
        this.updateParam = null;
        return LinkTarget.REGISTER;
    }
    
    public String prepareUpdate(DepartmentListLineBean lineBean) {

        this.updateParam = new DepartmentManagementHeaderBean();
        this.updateParam.setDepartmentId(lineBean.getDepartmentId());
        this.updateParam.setName(lineBean.getName());
        this.updateParam.setUpdatedTime(lineBean.getUpdatedTime());

        return LinkTarget.UPDATE_INIT;
    }

    @Action
    public String delete(DepartmentListLineBean lineBean) {
        this.doReload = false;
        this.lineBean = null;
        if (!getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }

        return LinkTarget.OK;
    }

    public DepartmentManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(DepartmentManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<DepartmentListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<DepartmentListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<DepartmentListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<DepartmentListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public DepartmentListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(DepartmentListLineBean lineBean) {
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

    public int getTotalCount() {
        return totalCount;
    }

    public DepartmentListSearchReqDto getApplicationSearchReqDto() {
        return applicationSearchReqDto;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setApplicationSearchReqDto(DepartmentListSearchReqDto applicationSearchReqDto) {
        this.applicationSearchReqDto = applicationSearchReqDto;
    }

}
