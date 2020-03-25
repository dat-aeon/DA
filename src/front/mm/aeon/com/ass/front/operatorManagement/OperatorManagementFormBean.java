/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.operatorManagement;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.authenticate.LoginInfo;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("operatorManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class OperatorManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 1583457870206052674L;

    @In(create = true)
    private LoginInfo loginInfo;

    private boolean init = true;

    private ArrayList<SelectItem> teamSelectItemList;

    @In(required = false, value = "operatorUpdateParam")
    @Out(required = false, value = "operatorUpdateParam")
    private OperatorManagementHeaderBean managementHeaderBean;

    @In(required = false, value = "operatorRegisterParam")
    @Out(required = false, value = "operatorRegisterParam")
    private OperatorManagementHeaderBean registerManagementHeaderBean;

    private OperatorManagementHeaderBean backUpHeaderBean;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean cancel = false;

    private OperatorManagementHeaderBean registerHeaderBean;

    private ArrayList<SelectItem> operatorRoleSelectItemList;

    @Begin(join = true)
    public String init() {
        ASSLogger logger = new ASSLogger();
        getMessageContainer().clearAllMessages(true);
        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.OPERATOR_LIST);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.BACK;
        }

        cancel = true;
        init = false;
        getMessageContainer().clearAllMessages(true);
        managementHeaderBean = new OperatorManagementHeaderBean();
        registerHeaderBean = new OperatorManagementHeaderBean();
        registerHeaderBean.setDepartmentSelectItemList(registerManagementHeaderBean.getDepartmentSelectItemList());
        registerHeaderBean.setGroupSelectItemList(registerManagementHeaderBean.getGroupSelectItemList());

        return LinkTarget.INIT;
    }

    @Begin(join = true)
    public void updateInit() {
        getMessageContainer().clearAllMessages(true);
        init = false;
        cancel = false;
        registerHeaderBean = new OperatorManagementHeaderBean();
        backUpHeaderBean = new OperatorManagementHeaderBean().copyOperatorManagementHeaderBean(managementHeaderBean);
        registerHeaderBean.setDepartmentSelectItemList(managementHeaderBean.getDepartmentSelectItemList());
        registerHeaderBean.setGroupSelectItemList(managementHeaderBean.getGroupSelectItemList());
    }

    @Action
    public String register() {
        if (this.getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }
        managementHeaderBean = new OperatorManagementHeaderBean();

        doReload = new Boolean(true);
        return LinkTarget.OK;
    }

    @Action
    public String update() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }
        init = true;
        managementHeaderBean = new OperatorManagementHeaderBean();
        doReload = new Boolean(true);

        cancel = true;
        return LinkTarget.SEARCH;

    }

    public String back() {
        getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.managementHeaderBean = null;
        this.doReload = new Boolean(true);
        return LinkTarget.BACK;
    }

    public String getOperatorRoleValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : operatorRoleSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public void clear() {
        managementHeaderBean = new OperatorManagementHeaderBean();
    }

    public void reset() {
        managementHeaderBean = managementHeaderBean.copyOperatorManagementHeaderBean(backUpHeaderBean);
        this.registerHeaderBean.setResetPassword(false);
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public ArrayList<SelectItem> getTeamSelectItemList() {
        return teamSelectItemList;
    }

    public void setTeamSelectItemList(ArrayList<SelectItem> teamSelectItemList) {
        this.teamSelectItemList = teamSelectItemList;
    }

    public OperatorManagementHeaderBean getManagementHeaderBean() {
        return managementHeaderBean;
    }

    public void setManagementHeaderBean(OperatorManagementHeaderBean managementHeaderBean) {
        this.managementHeaderBean = managementHeaderBean;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public OperatorManagementHeaderBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(OperatorManagementHeaderBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

    public ArrayList<SelectItem> getOperatorRoleSelectItemList() {
        return operatorRoleSelectItemList;
    }

    public void setOperatorRoleSelectItemList(ArrayList<SelectItem> operatorRoleSelectItemList) {
        this.operatorRoleSelectItemList = operatorRoleSelectItemList;
    }

    public OperatorManagementHeaderBean getRegisterManagementHeaderBean() {
        return registerManagementHeaderBean;
    }

    public void setRegisterManagementHeaderBean(OperatorManagementHeaderBean registerManagementHeaderBean) {
        this.registerManagementHeaderBean = registerManagementHeaderBean;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public OperatorManagementHeaderBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(OperatorManagementHeaderBean registerHeaderBean) {
        this.registerHeaderBean = registerHeaderBean;
    }

}
