/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.customerList;

import java.io.File;
import java.io.IOException;
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
import org.springframework.util.StringUtils;

import mm.aeon.com.ass.base.dto.customerSearch.CustomerSearchReqDto;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.FileHandler;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("customerListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class CustomerListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -532801949885579872L;

    private CustomerListHeaderBean searchHeaderBean;

    private CustomerSearchReqDto customerSearchReqDto;

    private CustomerListLineBean lineBean;

    private List<CustomerListLineBean> lineBeanList;

    private LazyDataModel<CustomerListLineBean> lazyModel;

    private ArrayList<SelectItem> customerTypeSelectItemList;

    private ArrayList<SelectItem> genderSelectItemList;

    private List<CustomerSecurityQuestionListLineBean> customerSecurityQuestionListLineBean;

    private boolean init = true;

    private int pageFirst;

    private int totalCount;

    private String tempUploadImageFilePath;

    @In(required = false, value = "clear")
    @Out(required = false, value = "clear")
    private Boolean clear;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private Integer customerId;

    private String generatedPassword;

    private boolean dialogVisible;

    @Begin(nested = true)
    @Action
    public void init() {
        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.CUSTOMER_INFO);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new CustomerListHeaderBean();
        doReload = new Boolean(true);
        init = false;
        dialogVisible = false;
    }

    @Action
    public String search() {
        doReload = new Boolean(false);
        lazyModel = null;
        dialogVisible = false;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new CustomerListPaginationController(totalCount, customerSearchReqDto);
        }

        return LinkTarget.OK;
    }

    @Action
    public String detail(CustomerListLineBean lineBean) {
        getMessageContainer().clearAllMessages(true);
        if (!StringUtils.isEmpty(lineBean.getPhotoPath())) {
            String uploadPath = CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getProfileUploadImageFolder();
            File sourceFile = new File(uploadPath + lineBean.getPhotoPath());
            File destinationFile = new File(FileHandler.getSystemPath() + "/temp"
                    + CommonUtil.getProfileUploadImageFolder() + lineBean.getPhotoPath());

            try {
                System.gc();// Added this part
                Thread.sleep(1000);// This part gives the Bufferedreaders and the InputStreams time to close
                                   // Completely
                FileHandler.copyFile(sourceFile, destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempUploadImageFilePath = "/temp" + CommonUtil.getProfileUploadImageFolder() + lineBean.getPhotoPath();
        }

        this.lineBean = lineBean;
        dialogVisible = false;

        this.lineBean = lineBean;
        return LinkTarget.DETAIL;
    }

    public String back() {
        getMessageContainer().clearAllMessages(true);
        tempUploadImageFilePath = null;
        dialogVisible = false;
        return LinkTarget.BACK;
    }

    public void reset() {
        dialogVisible = false;
        this.searchHeaderBean = new CustomerListHeaderBean();
    }

    public String importedMemberList() {
        getMessageContainer().clearAllMessages(true);
        clear = true;
        doReload = true;
        return LinkTarget.IMPORT;
    }

    @Action
    public String lockAccount() {
        this.lineBean = null;
        if (!this.getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }
        return LinkTarget.OK;
    }

    @Action
    public String resetPassword() {

        doReload = false;
        dialogVisible = true;
        return LinkTarget.OK;

    }

    public String getGenderValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : genderSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getCustomerTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : customerTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public CustomerListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(CustomerListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public CustomerListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(CustomerListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public List<CustomerListLineBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<CustomerListLineBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public LazyDataModel<CustomerListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<CustomerListLineBean> lazyModel) {
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

    public ArrayList<SelectItem> getCustomerTypeSelectItemList() {
        return customerTypeSelectItemList;
    }

    public void setCustomerTypeSelectItemList(ArrayList<SelectItem> customerTypeSelectItemList) {
        this.customerTypeSelectItemList = customerTypeSelectItemList;
    }

    public ArrayList<SelectItem> getGenderSelectItemList() {
        return genderSelectItemList;
    }

    public void setGenderSelectItemList(ArrayList<SelectItem> genderSelectItemList) {
        this.genderSelectItemList = genderSelectItemList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public CustomerSearchReqDto getCustomerSearchReqDto() {
        return customerSearchReqDto;
    }

    public void setCustomerSearchReqDto(CustomerSearchReqDto customerSearchReqDto) {
        this.customerSearchReqDto = customerSearchReqDto;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }

    public void setGeneratedPassword(String generatedPassword) {
        this.generatedPassword = generatedPassword;
    }

    public boolean isDialogVisible() {
        return dialogVisible;
    }

    public void setDialogVisible(boolean dialogVisible) {
        this.dialogVisible = dialogVisible;
    }

    public Boolean getClear() {
        return clear;
    }

    public void setClear(Boolean clear) {
        this.clear = clear;
    }

    public String getTempUploadImageFilePath() {
        return tempUploadImageFilePath;
    }

    public void setTempUploadImageFilePath(String tempUploadImageFilePath) {
        this.tempUploadImageFilePath = tempUploadImageFilePath;
    }

    public List<CustomerSecurityQuestionListLineBean> getCustomerSecurityQuestionListLineBean() {
        return customerSecurityQuestionListLineBean;
    }

    public void setCustomerSecurityQuestionListLineBean(
            List<CustomerSecurityQuestionListLineBean> customerSecurityQuestionListLineBean) {
        this.customerSecurityQuestionListLineBean = customerSecurityQuestionListLineBean;
    }

}
