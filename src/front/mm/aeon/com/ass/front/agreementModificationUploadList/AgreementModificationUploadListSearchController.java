/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationUploadList;

import mm.aeon.com.ass.base.dto.agreementModificationUploadListSearch.AgreementModificationUploadSearchReqDto;
import mm.aeon.com.ass.base.dto.agreementModificationUploadListSearch.AgreementModificationUploadSelectCountReqDto;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.util.CommonUtil;
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

public class AgreementModificationUploadListSearchController extends AbstractController implements
        IControllerAccessor<AgreementModificationUploadListFormBean, AgreementModificationUploadListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public AgreementModificationUploadListFormBean process(AgreementModificationUploadListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        /*
         * if (!VCSCommon.ONE.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
         * logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
         * LogLevel.ERROR); throw new InvalidScreenTransitionException(); }
         */

        applicationLogger.log("Interest Rate Searching Process started.", LogLevel.INFO);
        String serviceStatus;
        MessageBean msgBean;

        AgreementModificationUploadSelectCountReqDto countReqDto = new AgreementModificationUploadSelectCountReqDto();
        AgreementModificationUploadSearchReqDto reqDto = new AgreementModificationUploadSearchReqDto();
        AgreementModificationUploadListHeaderBean formBeanSearchHeaderBean = formBean.getSearchHeaderBean();
        if (formBeanSearchHeaderBean.getApplicationNo() != null) {
            countReqDto.setApplicationNo(formBeanSearchHeaderBean.getApplicationNo());
        }
        if (formBeanSearchHeaderBean.getApplicantName() != null) {
            countReqDto.setApplicantName(formBeanSearchHeaderBean.getApplicantName());
        }
        if (formBeanSearchHeaderBean.getApplicantPhoneNo() != null) {
            countReqDto.setApplicantPhoneNo(formBeanSearchHeaderBean.getApplicantPhoneNo());
        }
        if (formBeanSearchHeaderBean.getApplicantNrcNo() != null) {
            countReqDto.setApplicantNrcNo(formBeanSearchHeaderBean.getApplicantNrcNo());
        }
        if (formBeanSearchHeaderBean.getNewApplicationDateFrom() != null) {
            countReqDto.setNewApplicationDateFrom(formBeanSearchHeaderBean.getNewApplicationDateFrom());
        }
        if (formBeanSearchHeaderBean.getNewApplicationDateTo() != null) {
            countReqDto.setNewApplicationDateTo(formBeanSearchHeaderBean.getNewApplicationDateTo());
        }
        //
        if (formBeanSearchHeaderBean.getApplicationNo() != null) {
            reqDto.setApplicationNo(formBeanSearchHeaderBean.getApplicationNo());
        }
        if (formBeanSearchHeaderBean.getApplicantName() != null) {
            reqDto.setApplicantName(formBeanSearchHeaderBean.getApplicantName());
        }
        if (formBeanSearchHeaderBean.getApplicantPhoneNo() != null) {
            reqDto.setApplicantPhoneNo(formBeanSearchHeaderBean.getApplicantPhoneNo());
        }
        if (formBeanSearchHeaderBean.getApplicantNrcNo() != null) {
            reqDto.setApplicantNrcNo(formBeanSearchHeaderBean.getApplicantNrcNo());
        }
        if (formBeanSearchHeaderBean.getNewApplicationDateFrom() != null) {
            reqDto.setNewApplicationDateFrom(formBeanSearchHeaderBean.getNewApplicationDateFrom());
        }
        if (formBeanSearchHeaderBean.getNewApplicationDateTo() != null) {
            reqDto.setNewApplicationDateTo(formBeanSearchHeaderBean.getNewApplicationDateTo());
        }

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setTotalCount(totalCount);
            formBean.setAgreementModificationUploadSearchReqDto(reqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Interest Rate searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
