/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.operatorList;

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

import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
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

@Name("operatorListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class OperatorListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -532801949885579872L;

    private OperatorListHeaderBean searchHeaderBean;

    private OperatorListLineBean lineBean;

    @Out(required = false, value = "operatorUpdateParam")
    private OperatorManagementHeaderBean updateParam;

    @In(required = false, value = "operatorRegisterParam")
    @Out(required = false, value = "operatorRegisterParam")
    private OperatorManagementHeaderBean registerParam;

    private List<OperatorListLineBean> lineBeanList;

    private LazyDataModel<OperatorListLineBean> lazyModel;

    private boolean init = true;

    private int pageFirst;

    private ArrayList<SelectItem> departmentSelectItemList;

    private ArrayList<SelectItem> groupSelectItemList;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    @Begin(nested = true)
    @Action
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.OPERATOR_LIST);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new OperatorListHeaderBean();
        doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        doReload = new Boolean(false);
        updateParam = null;
        lazyModel = null;

        if (!getMessageContainer().checkMessage(MessageType.ERROR) && lineBeanList.size() != 0) {
            lazyModel = new OperatorListPaginationController(lineBeanList.size(), lineBeanList);
        }

        return LinkTarget.OK;
    }

    @Action
    public String toggleValidStatus(OperatorListLineBean lineBean) {
        return LinkTarget.OK;
    }

    public String prepareRegister() {
        updateParam = null;
        registerParam = new OperatorManagementHeaderBean();
        registerParam.setDepartmentSelectItemList(this.departmentSelectItemList);
        registerParam.setGroupSelectItemList(this.groupSelectItemList);
        return LinkTarget.REGISTER;
    }

    @Action
    public String delete() {
        doReload = false;
        lineBean = null;
        if (!getMessageContainer().checkMessage(MessageType.ERROR)) {
            doReload = true;
        }

        return LinkTarget.OK;
    }

    public String back() {
        return LinkTarget.BACK;
    }

    public String prepareUpdate(OperatorListLineBean lineBean) {
        updateParam = new OperatorManagementHeaderBean();

        updateParam.setUpdatedTime(lineBean.getUpdatedTime());
        updateParam.setUserId(lineBean.getUserId());
        updateParam.setUserLoginId(lineBean.getUserLoginId());
        updateParam.setUserName(lineBean.getUserName());
        if (lineBean.getDepartmentId() != null) {
            updateParam.setSelectedDepartment(lineBean.getDepartmentId());
        }
        if (lineBean.getGroupId() != null) {
            updateParam.setGroupId(lineBean.getGroupId());
        }
        updateParam.setDepartmentSelectItemList(this.departmentSelectItemList);
        updateParam.setGroupSelectItemList(this.groupSelectItemList);
        updateParam.setForUpdate(true);

        return LinkTarget.UPDATE_INIT;
    }

    @Action
    public String allowMessaging() {
        this.lineBean = null;
        if (!this.getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }
        return LinkTarget.OK;
    }

    @Action
    public String allowPersonalLoanMessaging() {
        this.lineBean = null;
        if (!this.getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }
        return LinkTarget.OK;
    }

    public String getDepartmentValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : departmentSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
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

    public void reset() {
        searchHeaderBean = new OperatorListHeaderBean();
    }

    public OperatorListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(OperatorListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public OperatorListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(OperatorListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public OperatorManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(OperatorManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<OperatorListLineBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<OperatorListLineBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public LazyDataModel<OperatorListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<OperatorListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
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

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public ArrayList<SelectItem> getDepartmentSelectItemList() {
        return departmentSelectItemList;
    }

    public void setDepartmentSelectItemList(ArrayList<SelectItem> departmentSelectItemList) {
        this.departmentSelectItemList = departmentSelectItemList;
    }

    public ArrayList<SelectItem> getGroupSelectItemList() {
        return groupSelectItemList;
    }

    public void setGroupSelectItemList(ArrayList<SelectItem> groupSelectItemList) {
        this.groupSelectItemList = groupSelectItemList;
    }

}
