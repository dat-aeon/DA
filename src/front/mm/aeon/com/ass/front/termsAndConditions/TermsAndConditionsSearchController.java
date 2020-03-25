/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.termsAndConditions;

import java.util.List;

import mm.aeon.com.ass.base.dto.termsAndConditionsInfoSearch.TermsAndConditionsInfoSearchReqDto;
import mm.aeon.com.ass.base.dto.termsAndConditionsInfoSearch.TermsAndConditionsInfoSearchResDto;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class TermsAndConditionsSearchController extends AbstractController
        implements IControllerAccessor<TermsAndConditionsManagementFormBean, TermsAndConditionsManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public TermsAndConditionsManagementFormBean process(TermsAndConditionsManagementFormBean formBean) {

        // formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        /*
         * if (!VCSCommon.ONE.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
         * logger.log("Invalid URL Access.[Company Information List]", new InvalidScreenTransitionException(),
         * LogLevel.ERROR); throw new InvalidScreenTransitionException(); }
         */

        applicationLogger.log("Terms And Conditions Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        TermsAndConditionsInfoSearchReqDto reqDto = new TermsAndConditionsInfoSearchReqDto();

        try {
            List<TermsAndConditionsInfoSearchResDto> resCompanyInfoList =
                    (List<TermsAndConditionsInfoSearchResDto>) this.getDaoServiceInvoker().execute(reqDto);

            if (resCompanyInfoList.size() > 0) {
                TermsAndConditionsInfoSearchResDto resdto = resCompanyInfoList.get(0);

                TermsAndConditionsBean lineBean = new TermsAndConditionsBean();

                lineBean.setTermsAndConditionsId(resdto.getTermsAndConditionsId());
                lineBean.setDescEng(resdto.getDescEng());
                lineBean.setDescMyan(resdto.getDescMyan());
                lineBean.setUpdatedBy(resdto.getUpdatedBy());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());

                formBean.setTermsAndConditionBean(lineBean);

            } else {
                msgBean = new MessageBean(MessageId.MI0008);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);
                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Terms And Conditions searching finished.", LogLevel.INFO);
            }

        } catch (

        PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
