/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationUploadList;

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

import mm.aeon.com.ass.base.dto.agreementModificationUploadListSearch.AgreementModificationUploadSearchReqDto;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.interestRateManagement.InterestRateManagementHeaderBean;
import mm.aeon.com.ass.front.sessions.LoginUserInfo;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("AgreementModificationUploadListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgreementModificationUploadListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private String loginUserName;
    private boolean checkNotYetFlag;

    private List<AgreementModificationUploadListLineBean> lineBeans;

    private LazyDataModel<AgreementModificationUploadListLineBean> lazyModel;

    private AgreementModificationUploadListLineBean lineBean;
    private Boolean notyetFlag;
    @Out(required = false, value = "newApplicationUpdateParam")
    private AgreementModificationUploadListHeaderBean newApplicationUpdateParam;

    private ArrayList<SelectItem> statusList;

    private AgreementModificationUploadListHeaderBean searchHeaderBean;

    private ArrayList<SelectItem> applicationStatusSelectItemList;

    private ArrayList<SelectItem> loanTypeSelectItemList;

    private ArrayList<SelectItem> genderSelectItemList;

    private ArrayList<SelectItem> applicationTypeSelectItemList;

    private ArrayList<SelectItem> nationalitySelectItemList;

    private ArrayList<SelectItem> maritalStatusSelectItemList;

    private ArrayList<SelectItem> typeOfResidenceSelectItemList;

    private ArrayList<SelectItem> livingWithSelectItemList;

    private ArrayList<SelectItem> productTypeSelectItemList;

    private ArrayList<SelectItem> channelSelectItemList;

    private ArrayList<SelectItem> relationshipSelectItemList;

    private ArrayList<SelectItem> companyStatusSelectItemList;

    private ArrayList<SelectItem> fileTypeSelectItemList;

    private ArrayList<SelectItem> townshipSelectItemList;

    private ArrayList<SelectItem> citySelectItemList;

    private ArrayList<SelectItem> educationSelectItemList;

    private int totalCount;

    private List<AgreementModificationUploadedTimeBean> timeBeanList;

    private AgreementModificationUploadSearchReqDto agreementModificationUploadSearchReqDto;

    private Integer newApplicationId;

    @Out(required = false, value = "securityUpdateParam")
    private InterestRateManagementHeaderBean updateParam;

    private boolean init = true;
    public Boolean newStatus = false;
    public Boolean cancelStatus = false;
    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    private Integer applicationId;

    private boolean modifydialogVisible;

    @Begin(nested = true)
    @Action
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.AGREEMENT_MODIFICATION_REPORT);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new AgreementModificationUploadListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgreementModificationUploadListPaginationController(totalCount,
                    agreementModificationUploadSearchReqDto);
        }
        loginUserName = CommonUtil.getLoginUserInfo().getId().toString();
        LoginUserInfo loginUserInfo = CommonUtil.getLoginUserInfo();
        System.out.println(loginUserInfo);
        return LinkTarget.OK;
    }

    public void reset() {
        this.searchHeaderBean = new AgreementModificationUploadListHeaderBean();
    }

    @Action
    public String back() {
        return LinkTarget.BACK;
    }

    public Integer getNewApplicationId() {
        return newApplicationId;
    }

    public void setNewApplicationId(Integer newApplicationId) {
        this.newApplicationId = newApplicationId;
    }

    public AgreementModificationUploadListHeaderBean getNewApplicationUpdateParam() {
        return newApplicationUpdateParam;
    }

    public void setNewApplicationUpdateParam(AgreementModificationUploadListHeaderBean newApplicationUpdateParam) {
        this.newApplicationUpdateParam = newApplicationUpdateParam;
    }

    public InterestRateManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(InterestRateManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<AgreementModificationUploadListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<AgreementModificationUploadListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<AgreementModificationUploadListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgreementModificationUploadListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AgreementModificationUploadListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgreementModificationUploadListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public String getApplicationStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : applicationStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
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

    public String getLoanTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : loanTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getApplicationTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : applicationTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getNationalityValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : nationalitySelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getMaritalStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : maritalStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getTypeOfResidenceValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : typeOfResidenceSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getLivingWithValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : livingWithSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getProductTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : productTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getChannelValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : channelSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getRelatinshipValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : relationshipSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getCompanyStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : companyStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getFiletTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : fileTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getTownshipValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : townshipSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getCityValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : citySelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getEducationValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : educationSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
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

    public AgreementModificationUploadListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(AgreementModificationUploadListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = newApplicationId;
    }

    public ArrayList<SelectItem> getApplicationStatusSelectItemList() {
        return applicationStatusSelectItemList;
    }

    public void setApplicationStatusSelectItemList(ArrayList<SelectItem> applicationStatusSelectItemList) {
        this.applicationStatusSelectItemList = applicationStatusSelectItemList;
    }

    public ArrayList<SelectItem> getLoanTypeSelectItemList() {
        return loanTypeSelectItemList;
    }

    public void setLoanTypeSelectItemList(ArrayList<SelectItem> loanTypeSelectItemList) {
        this.loanTypeSelectItemList = loanTypeSelectItemList;
    }

    public ArrayList<SelectItem> getGenderSelectItemList() {
        return genderSelectItemList;
    }

    public Boolean getNotyetFlag() {
        return notyetFlag;
    }

    public void setNotyetFlag(Boolean notyetFlag) {
        this.notyetFlag = notyetFlag;
    }

    public void setGenderSelectItemList(ArrayList<SelectItem> genderSelectItemList) {
        this.genderSelectItemList = genderSelectItemList;
    }

    public ArrayList<SelectItem> getApplicationTypeSelectItemList() {
        return applicationTypeSelectItemList;
    }

    public void setApplicationTypeSelectItemList(ArrayList<SelectItem> applicationTypeSelectItemList) {
        this.applicationTypeSelectItemList = applicationTypeSelectItemList;
    }

    public ArrayList<SelectItem> getNationalitySelectItemList() {
        return nationalitySelectItemList;
    }

    public void setNationalitySelectItemList(ArrayList<SelectItem> nationalitySelectItemList) {
        this.nationalitySelectItemList = nationalitySelectItemList;
    }

    public ArrayList<SelectItem> getMaritalStatusSelectItemList() {
        return maritalStatusSelectItemList;
    }

    public void setMaritalStatusSelectItemList(ArrayList<SelectItem> maritalStatusSelectItemList) {
        this.maritalStatusSelectItemList = maritalStatusSelectItemList;
    }

    public ArrayList<SelectItem> getTypeOfResidenceSelectItemList() {
        return typeOfResidenceSelectItemList;
    }

    public void setTypeOfResidenceSelectItemList(ArrayList<SelectItem> typeOfResidenceSelectItemList) {
        this.typeOfResidenceSelectItemList = typeOfResidenceSelectItemList;
    }

    public ArrayList<SelectItem> getLivingWithSelectItemList() {
        return livingWithSelectItemList;
    }

    public void setLivingWithSelectItemList(ArrayList<SelectItem> livingWithSelectItemList) {
        this.livingWithSelectItemList = livingWithSelectItemList;
    }

    public ArrayList<SelectItem> getProductTypeSelectItemList() {
        return productTypeSelectItemList;
    }

    public void setProductTypeSelectItemList(ArrayList<SelectItem> productTypeSelectItemList) {
        this.productTypeSelectItemList = productTypeSelectItemList;
    }

    public ArrayList<SelectItem> getChannelSelectItemList() {
        return channelSelectItemList;
    }

    public void setChannelSelectItemList(ArrayList<SelectItem> channelSelectItemList) {
        this.channelSelectItemList = channelSelectItemList;
    }

    public ArrayList<SelectItem> getRelationshipSelectItemList() {
        return relationshipSelectItemList;
    }

    public void setRelationshipSelectItemList(ArrayList<SelectItem> relationshipSelectItemList) {
        this.relationshipSelectItemList = relationshipSelectItemList;
    }

    public ArrayList<SelectItem> getCompanyStatusSelectItemList() {
        return companyStatusSelectItemList;
    }

    public void setCompanyStatusSelectItemList(ArrayList<SelectItem> companyStatusSelectItemList) {
        this.companyStatusSelectItemList = companyStatusSelectItemList;
    }

    public ArrayList<SelectItem> getFileTypeSelectItemList() {
        return fileTypeSelectItemList;
    }

    public void setFileTypeSelectItemList(ArrayList<SelectItem> fileTypeSelectItemList) {
        this.fileTypeSelectItemList = fileTypeSelectItemList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public AgreementModificationUploadSearchReqDto getAgreementModificationUploadSearchReqDto() {
        return agreementModificationUploadSearchReqDto;
    }

    public void setAgreementModificationUploadSearchReqDto(
            AgreementModificationUploadSearchReqDto agreementModificationUploadSearchReqDto) {
        this.agreementModificationUploadSearchReqDto = agreementModificationUploadSearchReqDto;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public boolean isCheckNotYetFlag() {
        return checkNotYetFlag;
    }

    public void setCheckNotYetFlag(boolean checkNotYetFlag) {
        this.checkNotYetFlag = checkNotYetFlag;
    }

    public ArrayList<SelectItem> getTownshipSelectItemList() {
        return townshipSelectItemList;
    }

    public ArrayList<SelectItem> getCitySelectItemList() {
        return citySelectItemList;
    }

    public void setTownshipSelectItemList(ArrayList<SelectItem> townshipSelectItemList) {
        this.townshipSelectItemList = townshipSelectItemList;
    }

    public void setCitySelectItemList(ArrayList<SelectItem> citySelectItemList) {
        this.citySelectItemList = citySelectItemList;
    }

    public ArrayList<SelectItem> getEducationSelectItemList() {
        return educationSelectItemList;
    }

    public void setEducationSelectItemList(ArrayList<SelectItem> educationSelectItemList) {
        this.educationSelectItemList = educationSelectItemList;
    }

    public List<AgreementModificationUploadedTimeBean> getTimeBeanList() {
        return timeBeanList;
    }

    public void setTimeBeanList(List<AgreementModificationUploadedTimeBean> timeBeanList) {
        this.timeBeanList = timeBeanList;
    }

    public boolean isModifydialogVisible() {
        return modifydialogVisible;
    }

    public void setModifydialogVisible(boolean modifydialogVisible) {
        this.modifydialogVisible = modifydialogVisible;
    }

}
