/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.userGroupList;

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
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.userGroupManagement.UserGroupManagementHeaderBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("userGroupListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class UserGroupListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private List<UserGroupListLineBean> lineBeans;

    private UserGroupListHeaderBean searchHeaderBean;

    private LazyDataModel<UserGroupListLineBean> lazyModel;

    private UserGroupListLineBean lineBean;

    private ArrayList<SelectItem> statusList;

    private Integer groupId;

    private boolean checkLockFlag;

    @Out(required = false, value = "lockParam")
    private boolean lockFLag;

    @In(required = false, value = "lockErrorParam")
    @Out(required = false, value = "lockErrorParam")
    private Boolean lockErrorParam;

    private String loginUserName;

    @Out(required = false, value = "securityUpdateParam")
    private UserGroupManagementHeaderBean updateParam;

    @Out(required = false, value = "selectedGroupId")
    private UserGroupListLineBean userLineBean;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    @Begin(nested = true)
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.USER_GROUP);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new UserGroupListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (lineBeans.size() != 0) {
            lazyModel = new UserGroupListPaginationController(lineBeans.size(), lineBeans);
        }
        this.lockErrorParam = new Boolean(false);
        loginUserName = CommonUtil.getLoginUserInfo().getUserName();
        return LinkTarget.OK;
    }

    public String prepareRegister() {
        this.updateParam = null;
        return LinkTarget.REGISTER;
    }

    public String back() {
        return LinkTarget.BACK;
    }

    public void reset() {
        this.searchHeaderBean = new UserGroupListHeaderBean();
    }

    @Action
    public String prepareUpdate(UserGroupListLineBean lineBean) {
        if (checkLockFlag) {
            return LinkTarget.OK;
        }
        lockFLag = true;
        this.updateParam = new UserGroupManagementHeaderBean();
        this.updateParam.setGroupId(lineBean.getGroupId());
        this.updateParam.setGroupName(lineBean.getGroupName());
        this.updateParam.setUpdatedTime(lineBean.getUpdatedTime());

        return LinkTarget.UPDATE_INIT;
    }

    @Action
    public String toggleValidStatus(UserGroupListLineBean lineBean) {
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
        if (checkLockFlag) {
            return LinkTarget.OK;
        }

        return LinkTarget.OK;
    }

    public String prepareAssignMember(UserGroupListLineBean lineBean) {
        userLineBean = new UserGroupListLineBean();
        userLineBean.setGroupId(lineBean.getGroupId());
        userLineBean.setGroupName(lineBean.getGroupName());
        userLineBean.setUpdatedTime(lineBean.getUpdatedTime());

        return LinkTarget.ASSIGN;
    }

    public String prepareAssignMenu(UserGroupListLineBean lineBean) {
        userLineBean = new UserGroupListLineBean();
        userLineBean.setGroupId(lineBean.getGroupId());
        userLineBean.setGroupName(lineBean.getGroupName());
        userLineBean.setUpdatedTime(lineBean.getUpdatedTime());

        return LinkTarget.ASSIGN_MENU;
    }

    public UserGroupManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(UserGroupManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<UserGroupListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<UserGroupListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<UserGroupListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<UserGroupListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public UserGroupListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(UserGroupListLineBean lineBean) {
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

    public UserGroupListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(UserGroupListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public boolean isCheckLockFlag() {
        return checkLockFlag;
    }

    public void setCheckLockFlag(boolean checkLockFlag) {
        this.checkLockFlag = checkLockFlag;
    }

    public boolean getLockFLag() {
        return lockFLag;
    }

    public void setLockFLag(boolean lockFLag) {
        this.lockFLag = lockFLag;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public Boolean getLockErrorParam() {
        return lockErrorParam;
    }

    public void setLockErrorParam(Boolean lockErrorParam) {
        this.lockErrorParam = lockErrorParam;
    }

    public UserGroupListLineBean getUserLineBean() {
        return userLineBean;
    }

    public void setUserLineBean(UserGroupListLineBean userLineBean) {
        this.userLineBean = userLineBean;
    }

}
