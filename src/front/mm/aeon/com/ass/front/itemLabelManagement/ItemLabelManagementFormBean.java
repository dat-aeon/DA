/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.itemLabelManagement;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("itemLabelManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class ItemLabelManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 2561404663068363440L;

    @In(required = false, value = "itemUpdateParam")
    @Out(required = false, value = "itemUpdateParam")
    private ItemLabelManagementHeaderBean updateParam;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private ItemLabelManagementHeaderBean registerHeaderBean;

    private ItemLabelManagementHeaderBean backUpHeaderBean;

    private boolean isUpdate;

    private boolean isValidStatusUpdate;

    private boolean isError = false;

    private boolean cancel = false;

    @Begin(join = true)
    public void updateInit() {

        this.getMessageContainer().clearAllMessages(true);

        init = false;
        isUpdate = true;
        cancel = false;
        registerHeaderBean = new ItemLabelManagementHeaderBean();
        this.registerHeaderBean.setItemLabelId(updateParam.getItemLabelId());
        this.registerHeaderBean.setItemLabelCode(updateParam.getItemLabelCode());
        this.registerHeaderBean.setItemLabelEng(updateParam.getItemLabelEng());
        this.registerHeaderBean.setItemLabelMym(updateParam.getItemLabelMym());
        this.registerHeaderBean.setDescription(updateParam.getDescription());
        this.registerHeaderBean.setCategory(updateParam.getCategory());
        this.registerHeaderBean.setUpdatedTime(updateParam.getUpdatedTime());

        this.backUpHeaderBean =
                new ItemLabelManagementHeaderBean().copyItemLabelManagementHeaderBean(this.registerHeaderBean);
    }

    @Action
    public String register() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }

        doReload = new Boolean(true);
        registerHeaderBean = new ItemLabelManagementHeaderBean();
        init = true;
        cancel = true;
        return LinkTarget.SEARCH;
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.updateParam = null;
        this.registerHeaderBean = null;

        return LinkTarget.SEARCH;
    }

    public void clear() {
        this.registerHeaderBean = new ItemLabelManagementHeaderBean();
    }

    public void reset() {
        this.registerHeaderBean = registerHeaderBean.copyItemLabelManagementHeaderBean(backUpHeaderBean);
    }

    public ItemLabelManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(ItemLabelManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public ItemLabelManagementHeaderBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(ItemLabelManagementHeaderBean registerHeaderBean) {
        this.registerHeaderBean = registerHeaderBean;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
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

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public ItemLabelManagementHeaderBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(ItemLabelManagementHeaderBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

}
