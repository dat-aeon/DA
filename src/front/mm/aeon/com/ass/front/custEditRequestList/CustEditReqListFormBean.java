/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.custEditRequestList;

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
import mm.aeon.com.ass.front.custEditRequestManagement.CustEditReqManagementHeaderBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("custEditReqListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class CustEditReqListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -532801949885579872L;

    private boolean checkNotYetFlag;

    private boolean viewRequestFLag;

    private String loginUserName;

    private CustEditReqListHeaderBean searchHeaderBean;

    private CustEditReqManagementHeaderBean custEditHeaderBean;

    private CustEditReqListLineBean lineBean;

    private ArrayList<SelectItem> statusSelectItemList;

    private List<CustEditReqListLineBean> lineBeanList;

    private LazyDataModel<CustEditReqListLineBean> lazyModel;

    private boolean init = true;

    private int pageFirst;

    private Integer itemId;

    public int approveStatus;

    private List<CustEditReqListLineBean> itemStatusList;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    @Action
    @Begin(nested = true)
    public void init() {
        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.CUSTOMER_EDIT_REQUEST_INFO);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }
        getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new CustEditReqListHeaderBean();
        custEditHeaderBean = new CustEditReqManagementHeaderBean();

        doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String detail(CustEditReqListLineBean lineBean) {
        if (checkNotYetFlag) {
            return LinkTarget.OK;
        }
        getMessageContainer().clearAllMessages(true);
        viewRequestFLag = true;
        this.lineBean = lineBean;
        this.custEditHeaderBean = new CustEditReqManagementHeaderBean();
        return LinkTarget.DETAIL;
    }

    @Action
    public String approve(CustEditReqListLineBean lineBean) {
        this.lineBean = lineBean;
        doReload = true;
        return LinkTarget.OK;
    }

    @Action
    public String reject(CustEditReqListLineBean lineBean) {
        
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }
        this.lineBean = lineBean;
        doReload = true;
        return LinkTarget.OK;
    }

    @Action
    public String search() {
        doReload = new Boolean(false);
        lazyModel = null;

        if (!getMessageContainer().checkMessage(MessageType.ERROR) && lineBeanList.size() != 0) {
            lazyModel = new CustEditReqListPaginationController(lineBeanList.size(), lineBeanList);
        }
        loginUserName = CommonUtil.getLoginUserInfo().getUserName();
        return LinkTarget.OK;
    }

    @Action
    public String toggleValidStatus(CustEditReqListLineBean lineBean) {
        return LinkTarget.OK;
    }

    @Action
    public String back() {
        return LinkTarget.BACK;
    }

    // for string item list
    public String getStatusValue() {
        for (SelectItem selectItem : statusSelectItemList) {
            if (lineBean.getStatus() != null) {
                if (lineBean.getStatus().equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getApplicationStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : statusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String statusName;

    public String getStatusName() {
        if (lineBean.getStatus() != null) {
            if (lineBean.getStatus() == 1) {
                statusName = "Requested";
            } else if (lineBean.getStatus() == 3) {
                statusName = "Rejected";
            } else {
                statusName = "All";
            }

            return statusName;
        }
        return "";
    }

    public CustEditReqListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(CustEditReqListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public CustEditReqListLineBean getLineBean() {
        return lineBean;
    }

    public ArrayList<SelectItem> getStatusSelectItemList() {
        return statusSelectItemList;
    }

    public void setStatusSelectItemList(ArrayList<SelectItem> statusSelectItemList) {
        this.statusSelectItemList = statusSelectItemList;
    }

    public void setLineBean(CustEditReqListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public List<CustEditReqListLineBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<CustEditReqListLineBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public LazyDataModel<CustEditReqListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<CustEditReqListLineBean> lazyModel) {
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

    public List<CustEditReqListLineBean> getItemStatusList() {
        return itemStatusList;
    }

    public void setItemStatusList(List<CustEditReqListLineBean> itemStatusList) {
        this.itemStatusList = itemStatusList;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public CustEditReqManagementHeaderBean getCustEditHeaderBean() {
        return custEditHeaderBean;
    }

    public void setCustEditHeaderBean(CustEditReqManagementHeaderBean custEditHeaderBean) {
        this.custEditHeaderBean = custEditHeaderBean;
    }

    public boolean isCheckNotYetFlag() {
        return checkNotYetFlag;
    }

    public void setCheckNotYetFlag(boolean checkNotYetFlag) {
        this.checkNotYetFlag = checkNotYetFlag;
    }

    public boolean getViewRequestFLag() {
        return viewRequestFLag;
    }

    public void setViewRequestFLag(boolean viewRequestFLag) {
        this.viewRequestFLag = viewRequestFLag;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

}
