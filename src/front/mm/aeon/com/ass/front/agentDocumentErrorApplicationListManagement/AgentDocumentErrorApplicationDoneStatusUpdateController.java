/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentDocumentErrorApplicationListManagement;

import java.sql.Timestamp;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.service.agentDocumentErrorPurchaseAttachmentInsertService.AgentDocumentErrorApplicationPurchaseAttachmentInsertServiceReqBean;
import mm.aeon.com.ass.base.service.commonApplicationInsertService.CommonApplicationHistoryInsertServiceReqBean;
import mm.aeon.com.ass.base.service.saleClaimAttachmentFlagUpdateService.SaleClaimAttachmentUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.saleClaimUpdateService.SaleClaimApplicationInfoLockUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.saleClaimUpdateService.SaleClaimApplicationStatusUpdateReqBean;
import mm.aeon.com.ass.front.agentDocumentErrorApplicationList.AgentDocumentErrorApplicationListFormBean;
import mm.aeon.com.ass.front.agentDocumentErrorApplicationList.AgentDocumentErrorEditImageBean;
import mm.aeon.com.ass.front.common.constants.DisplayItem;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.DisplayItemBean;
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
import mm.com.dat.presto.utils.common.InputChecker;

public class AgentDocumentErrorApplicationDoneStatusUpdateController extends AbstractController implements
        IControllerAccessor<AgentDocumentErrorApplicationListFormBean, AgentDocumentErrorApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public AgentDocumentErrorApplicationListFormBean process(AgentDocumentErrorApplicationListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        Boolean allImageEdit = false;
        for (AgentDocumentErrorEditImageBean imageBean : formBean.getLineBean()
                .getAgentDocumentErrorEditImageBeanList()) {
            if (imageBean.getFileName() == null) {
                allImageEdit = true;
            }
        }

        if (allImageEdit) {
            msgBean = new MessageBean(MessageId.ME1063);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Sale Claim application edit flag is false.", LogLevel.ERROR);
        } else if (InputChecker.isBlankOrNull(formBean.getErrorApplicationHeaderBean().getPendingComment())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.COMMENT));
            msgBean.addColumnId(DisplayItem.COMMENT);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
        } else {

            ResponseMessage responseMessage;
            AbstractServiceResBean resBean;
            String serviceStatus = null;
            LoginUserInfo loginUser = new LoginUserInfo();
            loginUser = CommonUtil.getLoginUserInfo();
            String userId = String.valueOf(loginUser.getId());
            String userTypeId = loginUser.getUserTypeId();
            Timestamp updatedTime = CommonUtil.getCurrentTimeStamp();
            Integer historyId = null;

            // index finished action
            applicationLogger.log("Agent Document Error application upload status update process started.",
                    LogLevel.INFO);
            SaleClaimApplicationStatusUpdateReqBean updateServiceReqBean =
                    new SaleClaimApplicationStatusUpdateReqBean();
            updateServiceReqBean.setDa_application_info_id(formBean.getLineBean().getApplicationId());
            updateServiceReqBean.setStatus(18);
            updateServiceReqBean.setPendingComment(formBean.getErrorApplicationHeaderBean().getPendingComment());
            updateServiceReqBean.setUpdated_by(String.valueOf(userId) + "," + userTypeId);
            updateServiceReqBean.setUpdated_time(updatedTime);

            this.getServiceInvoker().addRequest(updateServiceReqBean);
            responseMessage = this.getServiceInvoker().invoke();
            resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();
            // index status update finished

            if (ServiceStatusCode.OK.equals(serviceStatus)) {
                // unlock
                SaleClaimApplicationInfoLockUpdateServiceReqBean unLockServiceReqBean =
                        new SaleClaimApplicationInfoLockUpdateServiceReqBean();
                unLockServiceReqBean.setApplicationId(formBean.getLineBean().getApplicationId());
                unLockServiceReqBean.setLockFlag(false);
                unLockServiceReqBean.setLockTime(CommonUtil.getCurrentTimeStamp());
                unLockServiceReqBean.setLockBy(CommonUtil.getLoginUserInfo().getUserName());
                this.getServiceInvoker().addRequest(unLockServiceReqBean);
                responseMessage = this.getServiceInvoker().invoke();
                resBean = responseMessage.getMessageBean(0);
                serviceStatus = resBean.getServiceStatus();

                if (ServiceStatusCode.OK.equals(serviceStatus)) {
                    // start record to history table
                    applicationLogger.log(
                            "Sale Claim application check status update insert into history Process started.",
                            LogLevel.INFO);

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
                    serviceReqBean
                            .setPermanentAddressBuildingNo(formBean.getLineBean().getPermanentAddressBuildingNo());
                    serviceReqBean.setPermanentAddressRoomNo(formBean.getLineBean().getPermanentAddressRoomNo());
                    serviceReqBean.setPermanentAddressFloor(formBean.getLineBean().getPermanentAddressFloor());
                    serviceReqBean.setPermanentAddressStreet(formBean.getLineBean().getPermanentAddressStreet());
                    serviceReqBean.setPermanentAddressQtr(formBean.getLineBean().getPermanentAddressQtr());
                    serviceReqBean.setPermanentAddressTownship(formBean.getLineBean().getPermanentAddressTownship());
                    serviceReqBean.setPermanentAddressCity(formBean.getLineBean().getPermanentAddressCity());
                    serviceReqBean.setHighestEducationTypeId(formBean.getLineBean().getHighestEducationTypeId());
                    serviceReqBean.setApprovedFinanceAmount(formBean.getLineBean().getApprovedFinanceAmount());
                    serviceReqBean.setApprovedFinanceTerm(formBean.getLineBean().getApprovedFinanceTerm());
                    serviceReqBean.setModifyFinanceAmount(formBean.getLineBean().getModifyFinanceAmount());
                    serviceReqBean.setModifyFinanceTerm(formBean.getLineBean().getModifyFinanceTerm());
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
                    serviceReqBean
                            .setSettlementPendingComment(formBean.getErrorApplicationHeaderBean().getPendingComment());
                    serviceReqBean.setStatus(18);

                    serviceReqBean.setDaInterestInfoId(formBean.getLineBean().getInterestId());
                    serviceReqBean.setDaCompulsoryInfoId(formBean.getLineBean().getCompulsoryId());
                    serviceReqBean.setDaLoanTypeId(formBean.getLineBean().getLoanTypeId());
                    serviceReqBean.setFinanceAmount(formBean.getLineBean().getFinanceAmount());
                    serviceReqBean.setFinanceTerm(formBean.getLineBean().getFinanceTerm());
                    serviceReqBean.setDaProductTypeId(formBean.getLineBean().getProductId());
                    serviceReqBean.setProductDescription(formBean.getLineBean().getProductDescription());
                    serviceReqBean.setChannelType(formBean.getLineBean().getChannelType());
                    serviceReqBean.setHighestEducationTypeId(formBean.getLineBean().getHighestEducationTypeId());

                    serviceReqBean.setDelFlag(formBean.getLineBean().getDelFlag());
                    serviceReqBean.setCreatedBy(String.valueOf(userId) + "," + userTypeId);
                    serviceReqBean.setCreatedTime(CommonUtil.getCurrentTimeStamp());
                    serviceReqBean.setUpdatedTime(updatedTime);
                    serviceReqBean.setUpdatedBy(String.valueOf(userId) + "," + userTypeId);

                    this.getServiceInvoker().addRequest(serviceReqBean);
                    responseMessage = this.getServiceInvoker().invoke();
                    resBean = responseMessage.getMessageBean(0);

                    if (serviceReqBean.getDaApplicationInfoHistoryId() != null) {
                        historyId = serviceReqBean.getDaApplicationInfoHistoryId();
                    }

                    serviceStatus = resBean.getServiceStatus();
                    // end record history table
                    if (ServiceStatusCode.OK.equals(serviceStatus)) {

                        AgentDocumentErrorApplicationPurchaseAttachmentInsertServiceReqBean reqBean =
                                new AgentDocumentErrorApplicationPurchaseAttachmentInsertServiceReqBean();
                        for (AgentDocumentErrorEditImageBean imageDto : formBean.getLineBean()
                                .getAgentDocumentErrorEditImageBeanList()) {
                            reqBean.setPurchaseId(imageDto.getPurchaseId());
                            reqBean.setFilePath(imageDto.getFilePath());
                            reqBean.setFileType(imageDto.getFileType());
                            reqBean.setEditFlag(false);
                            reqBean.setOriginalFlag(false);
                            reqBean.setCreatedTime(updatedTime);
                            reqBean.setCreatedBy(String.valueOf(userId) + "," + userTypeId);
                            reqBean.setDaApplicationInfoHistoryId(historyId);

                            if (reqBean.getFilePath() != null) {
                                this.getServiceInvoker().addRequest(reqBean);
                                responseMessage = this.getServiceInvoker().invoke();
                                resBean = responseMessage.getMessageBean(0);
                                serviceStatus = resBean.getServiceStatus();
                                if (!ServiceStatusCode.OK.equals(serviceStatus)) {
                                    setErrorMessage(formBean, serviceStatus);
                                }
                            }
                        }

                        SaleClaimAttachmentUpdateServiceReqBean imageEditReqBean =
                                new SaleClaimAttachmentUpdateServiceReqBean();
                        for (AgentDocumentErrorEditImageBean originalImageDto : formBean.getLineBean()
                                .getAgentDocumentErrorOriginalEditImageBeanList()) {
                            imageEditReqBean.setAttachmentId(originalImageDto.getAttachmentId());
                            imageEditReqBean.setEditFlag(false);
                            imageEditReqBean.setUpdatedBy(String.valueOf(userId) + "," + userTypeId);
                            imageEditReqBean.setUpdatedTime(updatedTime);

                            if (imageEditReqBean.getAttachmentId() != null) {
                                this.getServiceInvoker().addRequest(imageEditReqBean);
                                responseMessage = this.getServiceInvoker().invoke();
                                resBean = responseMessage.getMessageBean(0);
                                serviceStatus = resBean.getServiceStatus();
                                if (!ServiceStatusCode.OK.equals(serviceStatus)) {
                                    setErrorMessage(formBean, serviceStatus);
                                }
                            }
                        }

                        msgBean = new MessageBean(MessageId.MI0002);
                        msgBean.setMessageType(MessageType.INFO);
                        formBean.getMessageContainer().addMessage(msgBean);
                        applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                        applicationLogger.log("Sale Claim application upload status update process finished.",
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
        }

        return formBean;
    }

    private AgentDocumentErrorApplicationListFormBean setErrorMessage(
            AgentDocumentErrorApplicationListFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent Document Error application upload status update data already exist.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent Document Error application upload status update data is locked.",
                    LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Agent Document Error application upload status update data already updated.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log(
                    "Agent Document Error application upload status update data already deleted by other.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
