/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.itemLabelList;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;

import mm.aeon.com.ass.base.dto.indexApplicationListSearch.IndexApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemLabelSelectListReqDto;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.itemLabelManagement.ItemLabelManagementHeaderBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("itemLabelListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class ItemLabelListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -532801949885579872L;

    private ItemLabelListHeaderBean searchHeaderBean;

    private ItemLabelListLineBean lineBean;

    private int totalCount;

    private ItemLabelSelectListReqDto itemLabelSearchReqDto;

    @Out(required = false, value = "itemUpdateParam")
    private ItemLabelManagementHeaderBean updateParam;

    private List<ItemLabelListLineBean> lineBeanList;

    private LazyDataModel<ItemLabelListLineBean> lazyModel;

    private boolean init = true;

    private int pageFirst;

    private Integer itemId;

    private List<ItemLabelListLineBean> itemCategoryList;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    @Begin(nested = true)
    public void init() {
        ASSLogger logger = new ASSLogger();
        
        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.ITEM_INFO_SETUP);
        if(result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
            LogLevel.ERROR); throw new InvalidScreenTransitionException();
        }
        getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new ItemLabelListHeaderBean();
        doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        doReload = new Boolean(false);
        updateParam = null;
        lazyModel = null;

        if (!getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new ItemLabelPaginationController(totalCount, itemLabelSearchReqDto);
        }

        return LinkTarget.OK;
    }

    public String detail(ItemLabelListLineBean lineBean) {
        getMessageContainer().clearAllMessages(true);
        this.lineBean = lineBean;
        return LinkTarget.DETAIL;
    }

    @Action
    public String toggleValidStatus(ItemLabelListLineBean lineBean) {
        return LinkTarget.OK;
    }

    public String prepareRegister() {
        updateParam = null;
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

    public String prepareUpdate(ItemLabelListLineBean lineBean) {
        updateParam = new ItemLabelManagementHeaderBean();

        updateParam.setUpdatedTime(lineBean.getUpdatedTime());
        updateParam.setItemLabelId(lineBean.getItemLabelId());
        updateParam.setItemLabelCode(lineBean.getItemLabelCode());
        updateParam.setItemLabelEng(lineBean.getItemLabelEng());
        updateParam.setItemLabelMym(lineBean.getItemLabelMym());
        updateParam.setDescription(lineBean.getDescription());
        updateParam.setCategory(lineBean.getCategory());

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

    public void reset() {
        searchHeaderBean = new ItemLabelListHeaderBean();
    }

    public ItemLabelListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(ItemLabelListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public ItemLabelListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(ItemLabelListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public ItemLabelManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(ItemLabelManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<ItemLabelListLineBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<ItemLabelListLineBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public LazyDataModel<ItemLabelListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<ItemLabelListLineBean> lazyModel) {
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

    public List<ItemLabelListLineBean> getItemCategoryList() {
        return itemCategoryList;
    }

    public void setItemCategoryList(List<ItemLabelListLineBean> itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ItemLabelSelectListReqDto getItemLabelSearchReqDto() {
        return itemLabelSearchReqDto;
    }

    public void setItemLabelSearchReqDto(ItemLabelSelectListReqDto itemLabelSearchReqDto) {
        this.itemLabelSearchReqDto = itemLabelSearchReqDto;
    }

}
