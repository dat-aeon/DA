/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.welcome;

import mm.aeon.com.ass.base.dto.agentAndProductApplicationListSearch.AgentAndProductApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.agreementModificationRequestListSearch.AgreementModificationRequestSelectCountReqDto;
import mm.aeon.com.ass.base.dto.applicationListSearch.ApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.cancelledApplicationListSearch.CancelledApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.DocumentFollowUpApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.newApplicationListSearch.NewApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoCheck.PurchaseAttachmentPhotoCountReqDto;
import mm.aeon.com.ass.base.dto.saleClaimApplicationListSearch.SaleClaimApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.saleEntryInfoListSearch.SaleEntryInfoApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.uploadedApplicationListSearch.UploadedApplicationSelectCountReqDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.LogLevel;

public class WelcomeCountController extends AbstractController
        implements IControllerAccessor<WelcomeCountFormBean, WelcomeCountFormBean> {
    private ASSLogger logger = new ASSLogger();

    @Override
    public WelcomeCountFormBean process(WelcomeCountFormBean formBean) {

        ApplicationSelectCountReqDto applicationSelectCountReqDto = new ApplicationSelectCountReqDto();
        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(applicationSelectCountReqDto);
            formBean.setAllApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        NewApplicationSelectCountReqDto newApplicationSelectCountReqDto = new NewApplicationSelectCountReqDto();
        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(newApplicationSelectCountReqDto);
            formBean.setNewApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        UploadedApplicationSelectCountReqDto countReqDto = new UploadedApplicationSelectCountReqDto();
        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setUploadedApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        CancelledApplicationSelectCountReqDto cancelledApplicationSelectCountReqDto =
                new CancelledApplicationSelectCountReqDto();
        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(cancelledApplicationSelectCountReqDto);
            formBean.setCancelledApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        DocumentFollowUpApplicationSelectCountReqDto documentFollowUpApplicationSelectCountReqDto =
                new DocumentFollowUpApplicationSelectCountReqDto();
        try {
            int totalCount =
                    (Integer) CommonUtil.getDaoServiceInvoker().execute(documentFollowUpApplicationSelectCountReqDto);
            formBean.setDocumentFollowUpApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        AgentAndProductApplicationSelectCountReqDto agentAndProductApplicationSelectCountReqDto =
                new AgentAndProductApplicationSelectCountReqDto();
        try {
            int totalCount =
                    (Integer) CommonUtil.getDaoServiceInvoker().execute(agentAndProductApplicationSelectCountReqDto);
            formBean.setAgentAndProductApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        SaleEntryInfoApplicationSelectCountReqDto saleEntryInfoApplicationSelectCountReqDto =
                new SaleEntryInfoApplicationSelectCountReqDto();
        try {
            int totalCount =
                    (Integer) CommonUtil.getDaoServiceInvoker().execute(saleEntryInfoApplicationSelectCountReqDto);
            formBean.setSaleEntryInfoApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        SaleClaimApplicationSelectCountReqDto saleClaimApplicationSelectCountReqDto =
                new SaleClaimApplicationSelectCountReqDto();
        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(saleClaimApplicationSelectCountReqDto);
            formBean.setSaleClaimInfoApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        AgentDocumentErrorApplicationSelectCountReqDto agentDocumentErrorApplicationSelectCountReqDto =
                new AgentDocumentErrorApplicationSelectCountReqDto();
        try {
            int totalCount =
                    (Integer) CommonUtil.getDaoServiceInvoker().execute(agentDocumentErrorApplicationSelectCountReqDto);
            formBean.setAgentDocumentErrorInfoApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        AgreementModificationRequestSelectCountReqDto agreementModificationRequestSelectCountReqDto =
                new AgreementModificationRequestSelectCountReqDto();
        try {
            int totalCount =
                    (Integer) CommonUtil.getDaoServiceInvoker().execute(agreementModificationRequestSelectCountReqDto);
            formBean.setAgreementModificationRequestInfoApplicationCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        PurchaseAttachmentPhotoCountReqDto inspectPhotoCountReqDto = new PurchaseAttachmentPhotoCountReqDto();
        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(inspectPhotoCountReqDto);
            formBean.setInspectPhotoCount(totalCount);
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
