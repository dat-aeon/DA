/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.menuListAssignManagement;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.DualListModel;

import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.userGroupList.UserGroupListLineBean;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("menuAssignManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class MenuAssignManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 2561404663068363440L;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private MenuAssignManagementHeaderBean registerHeaderBean;

    private DualListModel<MenuListAssignLineBean> dualLineBeanList;

    @In(required = false, value = "selectedGroupId")
    @Out(required = false, value = "selectedGroupId")
    private UserGroupListLineBean userLineBean;

    @Out(required = false, value = "lockErrorParam")
    private Boolean lockErrorParam;

    private List<MenuListAssignLineBean> selectedLineBeanList;

    private List<MenuListAssignLineBean> lineBeanList;

    private MenuAssignManagementHeaderBean backUpHeaderBean;

    private boolean isValidStatusUpdate;

    private boolean isError = false;

    private boolean cancel = false;

    private boolean checkLockFlag;

    @Begin(join = true)
    public void init() {
        init = false;
        cancel = true;
        this.getMessageContainer().clearAllMessages(true);
        registerHeaderBean = new MenuAssignManagementHeaderBean();
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        if (checkLockFlag) {
            this.doReload = new Boolean(true);
            this.getMessageContainer().clearAllMessages(true);
            lockErrorParam = new Boolean(true);
            return LinkTarget.SEARCH;
        }
        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && lineBeanList.size() != 0) {
            dualLineBeanList = new DualListModel<MenuListAssignLineBean>(lineBeanList, selectedLineBeanList);
        } else {
            lineBeanList = new ArrayList<MenuListAssignLineBean>();
            dualLineBeanList = new DualListModel<MenuListAssignLineBean>(lineBeanList, selectedLineBeanList);
        }
        return LinkTarget.ASSIGN_MENU;
    }

    @Action
    public String register() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }
        doReload = new Boolean(true);
        registerHeaderBean = new MenuAssignManagementHeaderBean();
        return LinkTarget.REGISTER;
    }

    @Action
    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.registerHeaderBean = null;
        lockErrorParam = new Boolean(false);
        return LinkTarget.SEARCH;
    }

    public void clear() {
        this.registerHeaderBean = new MenuAssignManagementHeaderBean();
    }

    public void reset() {
        this.registerHeaderBean = registerHeaderBean.copyMenuAssignManagementHeaderBean(backUpHeaderBean);
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public MenuAssignManagementHeaderBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(MenuAssignManagementHeaderBean registerHeaderBean) {
        this.registerHeaderBean = registerHeaderBean;
    }

    public boolean isValidStatusUpdate() {
        return isValidStatusUpdate;
    }

    public void setValidStatusUpdate(boolean isValidStatusUpdate) {
        this.isValidStatusUpdate = isValidStatusUpdate;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public DualListModel<MenuListAssignLineBean> getDualLineBeanList() {
        return dualLineBeanList;
    }

    public void setDualLineBeanList(DualListModel<MenuListAssignLineBean> dualLineBeanList) {
        this.dualLineBeanList = dualLineBeanList;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public MenuAssignManagementHeaderBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(MenuAssignManagementHeaderBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

    public UserGroupListLineBean getUserLineBean() {
        return userLineBean;
    }

    public void setUserLineBean(UserGroupListLineBean userLineBean) {
        this.userLineBean = userLineBean;
    }

    public List<MenuListAssignLineBean> getSelectedLineBeanList() {
        return selectedLineBeanList;
    }

    public void setSelectedLineBeanList(List<MenuListAssignLineBean> selectedLineBeanList) {
        this.selectedLineBeanList = selectedLineBeanList;
    }

    public List<MenuListAssignLineBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<MenuListAssignLineBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public boolean getCheckLockFlag() {
        return checkLockFlag;
    }

    public void setCheckLockFlag(boolean checkLockFlag) {
        this.checkLockFlag = checkLockFlag;
    }

    public Boolean getLockErrorParam() {
        return lockErrorParam;
    }

    public void setLockErrorParam(Boolean lockErrorParam) {
        this.lockErrorParam = lockErrorParam;
    }

}
