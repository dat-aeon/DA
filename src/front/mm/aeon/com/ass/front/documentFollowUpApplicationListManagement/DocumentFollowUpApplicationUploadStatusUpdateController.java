/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.documentFollowUpApplicationListManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.service.commonApplicationInsertService.CommonApplicationHistoryInsertServiceReqBean;
import mm.aeon.com.ass.base.service.commonApplicationUpdateService.CommonApplicationInfoLockUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.commonApplicationUpdateService.CommonApplicationStatusUpdateReqBean;
import mm.aeon.com.ass.base.service.documentFollowUpAttachmentEditStatusUpdateService.DocumentFollowUpAttachmentEditStatusUpdateReqBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.documentFollowUpApplicationList.DocumentFollowUpApplicationListFormBean;
import mm.aeon.com.ass.front.sessions.LoginUserInfo;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class DocumentFollowUpApplicationUploadStatusUpdateController extends AbstractController implements
        IControllerAccessor<DocumentFollowUpApplicationListFormBean, DocumentFollowUpApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public DocumentFollowUpApplicationListFormBean process(DocumentFollowUpApplicationListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        LoginUserInfo loginUser = new LoginUserInfo();
        loginUser = CommonUtil.getLoginUserInfo();
        String userId = String.valueOf(loginUser.getId());
        String userTypeId = loginUser.getUserTypeId();
        ResponseMessage responseMessage;
        String serviceStatus = null;
        AbstractServiceResBean resBean;

        applicationLogger.log("Document follow up application upload status update process started.", LogLevel.INFO);

        CommonApplicationStatusUpdateReqBean updateServiceReqBean = new CommonApplicationStatusUpdateReqBean();
        updateServiceReqBean.setDaApplicationInfoId(formBean.getLineBean().getApplicationId());
        updateServiceReqBean.setStatus(4);
        updateServiceReqBean.setUpdatedBy(String.valueOf(userId) + "," + userTypeId);
        updateServiceReqBean.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
        this.getServiceInvoker().addRequest(updateServiceReqBean);
        responseMessage = this.getServiceInvoker().invoke();
        resBean = responseMessage.getMessageBean(0);
        serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {
            // unlock start
            CommonApplicationInfoLockUpdateServiceReqBean unLockServiceReqBean =
                    new CommonApplicationInfoLockUpdateServiceReqBean();
            unLockServiceReqBean.setApplicationId(formBean.getLineBean().getApplicationId());
            unLockServiceReqBean.setLockFlag(false);
            unLockServiceReqBean.setLockTime(CommonUtil.getCurrentTimeStamp());
            unLockServiceReqBean.setLockBy(CommonUtil.getLoginUserInfo().getUserName());
            this.getServiceInvoker().addRequest(unLockServiceReqBean);
            responseMessage = this.getServiceInvoker().invoke();
            resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();
            // unlock finished

            if (ServiceStatusCode.OK.equals(serviceStatus)) {
                // start record to history table
                applicationLogger.log(
                        "Document follow up application upload status insert into history Process started.",
                        LogLevel.INFO);
                DocumentFollowUpAttachmentEditStatusUpdateReqBean editStatusReqBean =
                        new DocumentFollowUpAttachmentEditStatusUpdateReqBean();
                editStatusReqBean.setApplicationInfoID(formBean.getLineBean().getApplicationId());
                this.getServiceInvoker().addRequest(editStatusReqBean);
                responseMessage = this.getServiceInvoker().invoke();

                CommonApplicationHistoryInsertServiceReqBean serviceReqBean =
                        new CommonApplicationHistoryInsertServiceReqBean();

                serviceReqBean.setDaApplicationInfoId(formBean.getLineBean().getApplicationId());
                serviceReqBean.setApplicationNo(formBean.getLineBean().getApplicationNo());
                serviceReqBean.setDaApplicationTypeId(formBean.getLineBean().getApplicationTypeId());
                serviceReqBean.setAppliedDate(formBean.getLineBean().getAppliedDate());
                serviceReqBean.setName(formBean.getLineBean().getApplicantName());
                serviceReqBean.setDob(formBean.getLineBean().getDob());
                serviceReqBean.setNrcNo(formBean.getLineBean().getNrcNo());
                serviceReqBean.setFatherName(formBean.getLineBean().getFatherName());
                serviceReqBean.setNationality(formBean.getLineBean().getNationality());
                serviceReqBean.setNationalityOther(formBean.getLineBean().getNationalityOther());
                serviceReqBean.setGender(formBean.getLineBean().getGender());
                serviceReqBean.setMaritalStatus(formBean.getLineBean().getMaritalStatus());
                serviceReqBean.setCurrentAddress(formBean.getLineBean().getCurrentAddress());
                serviceReqBean.setCurrentAddressBuildingNo(formBean.getLineBean().getCurrentAddressBuildingNo());
                serviceReqBean.setCurrentAddressRoomNo(formBean.getLineBean().getCurrentAddressRoomNo());
                serviceReqBean.setCurrentAddressFloor(formBean.getLineBean().getCurrentAddressFloor());
                serviceReqBean.setCurrentAddressStreet(formBean.getLineBean().getCurrentAddressStreet());
                serviceReqBean.setCurrentAddressQtr(formBean.getLineBean().getCurrentAddressQtr());
                serviceReqBean.setCurrentAddressTownship(formBean.getLineBean().getCurrentAddressTownship());
                serviceReqBean.setCurrentAddressCity(formBean.getLineBean().getCurrentAddressCity());
                serviceReqBean.setPermanentAddress(formBean.getLineBean().getPermanentAddress());
                serviceReqBean.setPermanentAddressBuildingNo(formBean.getLineBean().getPermanentAddressBuildingNo());
                serviceReqBean.setPermanentAddressRoomNo(formBean.getLineBean().getPermanentAddressRoomNo());
                serviceReqBean.setPermanentAddressFloor(formBean.getLineBean().getPermanentAddressFloor());
                serviceReqBean.setPermanentAddressStreet(formBean.getLineBean().getPermanentAddressStreet());
                serviceReqBean.setPermanentAddressQtr(formBean.getLineBean().getPermanentAddressQtr());
                serviceReqBean.setPermanentAddressTownship(formBean.getLineBean().getPermanentAddressTownship());
                serviceReqBean.setPermanentAddressCity(formBean.getLineBean().getPermanentAddressCity());
                serviceReqBean.setHighestEducationTypeId(formBean.getLineBean().getHighestEducationTypeId());
                serviceReqBean.setTypeOfResidence(formBean.getLineBean().getTypeOfResident());
                serviceReqBean.setTypeOfResidenceOther(formBean.getLineBean().getTypeOfResidentOther());
                serviceReqBean.setLivingWith(formBean.getLineBean().getLivingWith());
                serviceReqBean.setLivingWithOther(formBean.getLineBean().getLivingWithOther());
                serviceReqBean.setYearOfStayYear(formBean.getLineBean().getStayYear());
                serviceReqBean.setYearOfStayMonth(formBean.getLineBean().getStayMonth());
                serviceReqBean.setMobileNo(formBean.getLineBean().getMobileNo());
                serviceReqBean.setResidentTelNo(formBean.getLineBean().getResidentTelNo());
                serviceReqBean.setOtherPhoneNo(formBean.getLineBean().getOtherPhoneNo());
                serviceReqBean.setEmail(formBean.getLineBean().getEmail());
                serviceReqBean.setCustomerId(formBean.getLineBean().getCustomerId());
                serviceReqBean.setStatus(4);
                serviceReqBean.setDaInterestInfoId(formBean.getLineBean().getInterestId());
                serviceReqBean.setDaCompulsoryInfoId(formBean.getLineBean().getCompulsoryId());
                serviceReqBean.setDaLoanTypeId(formBean.getLineBean().getLoanTypeId());
                serviceReqBean.setFinanceAmount(formBean.getLineBean().getFinanceAmount());
                serviceReqBean.setFinanceTerm(formBean.getLineBean().getFinanceTerm());
                serviceReqBean.setDaProductTypeId(formBean.getLineBean().getProductId());
                serviceReqBean.setProductDescription(formBean.getLineBean().getProductDescription());
                serviceReqBean.setChannelType(formBean.getLineBean().getChannelType());
                serviceReqBean.setDelFlag(formBean.getLineBean().getDelFlag());
                serviceReqBean.setSettlementPendingComment(formBean.getLineBean().getSettlmentComment());
                serviceReqBean.setCreatedBy(String.valueOf(userId) + "," + userTypeId);
                serviceReqBean.setCreatedTime(CommonUtil.getCurrentTimeStamp());
                serviceReqBean.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
                serviceReqBean.setUpdatedBy(String.valueOf(userId) + "," + userTypeId);
                this.getServiceInvoker().addRequest(serviceReqBean);
                responseMessage = this.getServiceInvoker().invoke();
                resBean = responseMessage.getMessageBean(0);
                serviceStatus = resBean.getServiceStatus();
                // end record history table

                if (ServiceStatusCode.OK.equals(serviceStatus)) {
                    msgBean = new MessageBean(MessageId.MI0002);
                    msgBean.setMessageType(MessageType.INFO);
                    formBean.getMessageContainer().addMessage(msgBean);

                    applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                    applicationLogger.log("Document follow up application upload status update process finished.",
                            LogLevel.INFO);
                } else {
                    setErrorMessage(formBean, serviceStatus);
                }
            } else {
                setErrorMessage(formBean, serviceStatus);
            }

        } else {
            setErrorMessage(formBean, serviceStatus);
        }

        return formBean;
    }

    private DocumentFollowUpApplicationListFormBean setErrorMessage(DocumentFollowUpApplicationListFormBean formBean,
            String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Document follow up application upload status update data already exist.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Document follow up application upload status update data is locked.",
                    LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Document follow up application upload status update data already updated.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Document follow up application upload status update data already deleted by other.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
