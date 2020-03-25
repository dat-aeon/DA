/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.termsAndConditions;

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
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("termsAndConditionsManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class TermsAndConditionsManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 2561404663068363440L;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;
    private boolean isUpdate = true;
    private boolean isError = false;
    private TermsAndConditionsBean termsAndConditionBean;

    private TermsAndConditionsBean backUpHeaderBean;

    @Begin(join = true)
    @Action
    public String init() {
        
        ASSLogger logger = new ASSLogger();
        
        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.TERMS_AND_CONDITIONS_SETUP);
        if(result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
            LogLevel.ERROR); throw new InvalidScreenTransitionException();
        }
        
        init = false;
        this.getMessageContainer().clearAllMessages((null == this.doReload || !this.doReload));
        doReload = false;

        this.backUpHeaderBean = new TermsAndConditionsBean().copyTermsAndConditionsBean(this.termsAndConditionBean);

        return LinkTarget.OK;
    }

    /*
     * @Begin(join = true) public void updateInit() { this.getMessageContainer().clearAllMessages(true); init = false;
     * 
     * this.backUpHeaderBean = new termsAndConditionBean().copytermsAndConditionBean(this.termsAndConditionBean);
     * 
     * isUpdate = false; }
     */

    @Action
    public String update() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }
        this.init = true;
        this.isUpdate = true;
        doReload = new Boolean(true);
        termsAndConditionBean = new TermsAndConditionsBean();
        return LinkTarget.OK;
    }

    public void reset() {
        this.termsAndConditionBean = new TermsAndConditionsBean().copyTermsAndConditionsBean(backUpHeaderBean);
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public TermsAndConditionsBean getTermsAndConditionBean() {
        return termsAndConditionBean;
    }

    public void setTermsAndConditionBean(TermsAndConditionsBean termsAndConditionBean) {
        this.termsAndConditionBean = termsAndConditionBean;
    }

    public TermsAndConditionsBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(TermsAndConditionsBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

}
