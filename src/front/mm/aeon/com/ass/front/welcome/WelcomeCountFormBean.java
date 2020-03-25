/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.welcome;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;

@Name("welcomeCountFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class WelcomeCountFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -2509155655083982728L;

    private String allStatusApplicationCss;
    private String newStatusApplication;
    private String indexStatusApplication;
    private String uploadedStatusApplication;
    private String cancelStatusApplication;
    private String followupStatusApplications;
    private String agentAndProductStatusApplication;
    private String saleEntryInfoStatusApplication;
    private String saleClaimInfoStatusApplication;
    private String agentDocumentErrorInfoStatusApplication;
    private String agreementModificationRequestInfoStatusApplication;
    private String inspectPhoto;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    @Begin(nested = true)
    public void init() {
        this.getMessageContainer().clearAllMessages(true);
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        if (!CommonUtil.validateUrlAccess(CommonMenu.ALL_APPLICATIONS)) {
            allStatusApplicationCss = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.NEW_APPLICATIONS)) {
            newStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.UPLOADED_APPLICATIONS)) {
            uploadedStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.CANCELLED_APPLICATIONS)) {
            cancelStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.FOLLOW_UP_APPLICATIONS)) {
            followupStatusApplications = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.AGENT_AND_PRODUCT_APPLICATIONS)) {
            agentAndProductStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.SALE_ENTRY_APPLICATIONS)) {
            saleEntryInfoStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.SALE_CLAIM_APPLICATIONS)) {
            saleClaimInfoStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.AGENT_DOCUMENT_ERROR_APPLICATIONS)) {
            agentDocumentErrorInfoStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.AGREEMENT_MODIFICATION_REQUEST)) {
            agreementModificationRequestInfoStatusApplication = "true";
        }
        if (!CommonUtil.validateUrlAccess(CommonMenu.INSPECT_PHOTO)) {
            inspectPhoto = "true";
        }

        return LinkTarget.OK;

    }

    private Integer allApplicationCount;

    private Integer newApplicationCount;

    private Integer uploadedApplicationCount;

    private Integer cancelledApplicationCount;

    private Integer documentFollowUpApplicationCount;

    private Integer agentAndProductApplicationCount;

    private Integer saleEntryInfoApplicationCount;

    private Integer saleClaimInfoApplicationCount;

    private Integer agentDocumentErrorInfoApplicationCount;

    private Integer agreementModificationRequestInfoApplicationCount;

    private Integer inspectPhotoCount;

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

    public String getAllStatusApplicationCss() {
        return allStatusApplicationCss;
    }

    public void setAllStatusApplicationCss(String allStatusApplicationCss) {
        this.allStatusApplicationCss = allStatusApplicationCss;
    }

    public String getNewStatusApplication() {
        return newStatusApplication;
    }

    public void setNewStatusApplication(String newStatusApplication) {
        this.newStatusApplication = newStatusApplication;
    }

    public String getIndexStatusApplication() {
        return indexStatusApplication;
    }

    public void setIndexStatusApplication(String indexStatusApplication) {
        this.indexStatusApplication = indexStatusApplication;
    }

    public String getUploadedStatusApplication() {
        return uploadedStatusApplication;
    }

    public void setUploadedStatusApplication(String uploadedStatusApplication) {
        this.uploadedStatusApplication = uploadedStatusApplication;
    }

    public String getCancelStatusApplication() {
        return cancelStatusApplication;
    }

    public void setCancelStatusApplication(String cancelStatusApplication) {
        this.cancelStatusApplication = cancelStatusApplication;
    }

    public String getFollowupStatusApplications() {
        return followupStatusApplications;
    }

    public void setFollowupStatusApplications(String followupStatusApplications) {
        this.followupStatusApplications = followupStatusApplications;
    }

    public String getAgentAndProductStatusApplication() {
        return agentAndProductStatusApplication;
    }

    public void setAgentAndProductStatusApplication(String agentAndProductStatusApplication) {
        this.agentAndProductStatusApplication = agentAndProductStatusApplication;
    }

    public String getSaleEntryInfoStatusApplication() {
        return saleEntryInfoStatusApplication;
    }

    public Integer getSaleEntryInfoApplicationCount() {
        return saleEntryInfoApplicationCount;
    }

    public void setSaleEntryInfoStatusApplication(String saleEntryInfoStatusApplication) {
        this.saleEntryInfoStatusApplication = saleEntryInfoStatusApplication;
    }

    public void setSaleEntryInfoApplicationCount(Integer saleEntryInfoApplicationCount) {
        this.saleEntryInfoApplicationCount = saleEntryInfoApplicationCount;
    }

    public Integer getAllApplicationCount() {
        return allApplicationCount;
    }

    public void setAllApplicationCount(Integer allApplicationCount) {
        this.allApplicationCount = allApplicationCount;
    }

    public Integer getNewApplicationCount() {
        return newApplicationCount;
    }

    public void setNewApplicationCount(Integer newApplicationCount) {
        this.newApplicationCount = newApplicationCount;
    }

    public Integer getUploadedApplicationCount() {
        return uploadedApplicationCount;
    }

    public void setUploadedApplicationCount(Integer uploadedApplicationCount) {
        this.uploadedApplicationCount = uploadedApplicationCount;
    }

    public Integer getCancelledApplicationCount() {
        return cancelledApplicationCount;
    }

    public void setCancelledApplicationCount(Integer cancelledApplicationCount) {
        this.cancelledApplicationCount = cancelledApplicationCount;
    }

    public Integer getDocumentFollowUpApplicationCount() {
        return documentFollowUpApplicationCount;
    }

    public void setDocumentFollowUpApplicationCount(Integer documentFollowUpApplicationCount) {
        this.documentFollowUpApplicationCount = documentFollowUpApplicationCount;
    }

    public Integer getAgentAndProductApplicationCount() {
        return agentAndProductApplicationCount;
    }

    public void setAgentAndProductApplicationCount(Integer agentAndProductApplicationCount) {
        this.agentAndProductApplicationCount = agentAndProductApplicationCount;
    }

    public String getSaleClaimInfoStatusApplication() {
        return saleClaimInfoStatusApplication;
    }

    public Integer getSaleClaimInfoApplicationCount() {
        return saleClaimInfoApplicationCount;
    }

    public void setSaleClaimInfoStatusApplication(String saleClaimInfoStatusApplication) {
        this.saleClaimInfoStatusApplication = saleClaimInfoStatusApplication;
    }

    public void setSaleClaimInfoApplicationCount(Integer saleClaimInfoApplicationCount) {
        this.saleClaimInfoApplicationCount = saleClaimInfoApplicationCount;
    }

    public String getAgentDocumentErrorInfoStatusApplication() {
        return agentDocumentErrorInfoStatusApplication;
    }

    public Integer getAgentDocumentErrorInfoApplicationCount() {
        return agentDocumentErrorInfoApplicationCount;
    }

    public void setAgentDocumentErrorInfoStatusApplication(String agentDocumentErrorInfoStatusApplication) {
        this.agentDocumentErrorInfoStatusApplication = agentDocumentErrorInfoStatusApplication;
    }

    public void setAgentDocumentErrorInfoApplicationCount(Integer agentDocumentErrorInfoApplicationCount) {
        this.agentDocumentErrorInfoApplicationCount = agentDocumentErrorInfoApplicationCount;
    }

    public String getAgreementModificationRequestInfoStatusApplication() {
        return agreementModificationRequestInfoStatusApplication;
    }

    public Integer getAgreementModificationRequestInfoApplicationCount() {
        return agreementModificationRequestInfoApplicationCount;
    }

    public void setAgreementModificationRequestInfoStatusApplication(
            String agreementModificationRequestInfoStatusApplication) {
        this.agreementModificationRequestInfoStatusApplication = agreementModificationRequestInfoStatusApplication;
    }

    public void setAgreementModificationRequestInfoApplicationCount(
            Integer agreementModificationRequestInfoApplicationCount) {
        this.agreementModificationRequestInfoApplicationCount = agreementModificationRequestInfoApplicationCount;
    }

    public String getInspectPhoto() {
        return inspectPhoto;
    }

    public Integer getInspectPhotoCount() {
        return inspectPhotoCount;
    }

    public void setInspectPhoto(String inspectPhoto) {
        this.inspectPhoto = inspectPhoto;
    }

    public void setInspectPhotoCount(Integer inspectPhotoCount) {
        this.inspectPhotoCount = inspectPhotoCount;
    }

}
