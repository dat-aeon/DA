/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.SettlementIndexApplicationListManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.settlementIndexApplicationSelectForUpdate.SettlementIndexApplicationSelectForUpdateReqDto;
import mm.aeon.com.ass.base.dto.settlementIndexApplicationSelectForUpdate.SettlementIndexApplicationSelectForUpdateResDto;
import mm.aeon.com.ass.base.service.commonApplicationInsertService.CommonApplicationHistoryInsertServiceReqBean;
import mm.aeon.com.ass.base.service.commonApplicationUpdateService.CommonApplicationInfoLockUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.commonApplicationUpdateService.CommonApplicationStatusUpdateReqBean;
import mm.aeon.com.ass.base.service.settlementIndexHistoryUpdateService.SettlementIndexApplicationHistoryStatusUpdateReqBean;
import mm.aeon.com.ass.front.common.constants.DisplayItem;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.DisplayItemBean;
import mm.aeon.com.ass.front.sessions.LoginUserInfo;
import mm.aeon.com.ass.front.settlementIndexApplicationList.SettlementIndexApplicationListFormBean;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;
import mm.com.dat.presto.utils.common.InputChecker;

public class SettlementIndexApplicationPendingStatusUpdateController extends AbstractController
        implements IControllerAccessor<SettlementIndexApplicationListFormBean, SettlementIndexApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public SettlementIndexApplicationListFormBean process(SettlementIndexApplicationListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);

        if (!isValidData(formBean)) {
            return formBean;
        }

        MessageBean msgBean;
        ResponseMessage responseMessage;
        AbstractServiceResBean resBean;
        String serviceStatus = null;

        try {
            LoginUserInfo loginUser = new LoginUserInfo();
            loginUser = CommonUtil.getLoginUserInfo();
            String userId = String.valueOf(loginUser.getId());
            String userTypeId = loginUser.getUserTypeId();

            applicationLogger.log("Settlement application pending status update process started.", LogLevel.INFO);
            CommonApplicationStatusUpdateReqBean updateServiceReqBean = new CommonApplicationStatusUpdateReqBean();
            updateServiceReqBean.setDaApplicationInfoId(formBean.getLineBean().getApplicationId());
            updateServiceReqBean.setStatus(17);
            updateServiceReqBean.setPendingComment(formBean.getPendingHeaderBean().getPendingComment());
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
                    SettlementIndexApplicationSelectForUpdateReqDto selectForUpdateReqDto =
                            new SettlementIndexApplicationSelectForUpdateReqDto();
                    selectForUpdateReqDto.setApplicationNo(formBean.getLineBean().getApplicationNo());
                    selectForUpdateReqDto.setStatus(17);

                    SettlementIndexApplicationSelectForUpdateResDto selectForUpdateResDto =
                            (SettlementIndexApplicationSelectForUpdateResDto) this.getDaoServiceInvoker()
                                    .execute(selectForUpdateReqDto);

                    if (selectForUpdateResDto != null) {
                        SettlementIndexApplicationHistoryStatusUpdateReqBean historyUpdateServiceReqBean =
                                new SettlementIndexApplicationHistoryStatusUpdateReqBean();
                        historyUpdateServiceReqBean
                                .setApplicationHistoryId(selectForUpdateResDto.getApplicationHistoryId());
                        historyUpdateServiceReqBean.setStatus(17);
                        historyUpdateServiceReqBean
                                .setPendingComment(formBean.getPendingHeaderBean().getPendingComment());
                        historyUpdateServiceReqBean.setUpdatedBy(String.valueOf(userId) + "," + userTypeId);
                        historyUpdateServiceReqBean.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
                        this.getServiceInvoker().addRequest(historyUpdateServiceReqBean);
                        responseMessage = this.getServiceInvoker().invoke();
                        resBean = responseMessage.getMessageBean(0);
                        serviceStatus = resBean.getServiceStatus();

                        if (ServiceStatusCode.OK.equals(serviceStatus)) {
                            msgBean = new MessageBean(MessageId.MI0002);
                            msgBean.setMessageType(MessageType.INFO);
                            formBean.getMessageContainer().addMessage(msgBean);
                            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                            applicationLogger.log("Settlement application pending status update process finished.",
                                    LogLevel.INFO);
                            return formBean;
                        } else {
                            setErrorMessage(formBean, serviceStatus);
                        }
                    } else {
                        // start record to history table
                        applicationLogger.log("SettlementIndex Application insert Process started.", LogLevel.INFO);
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
                        serviceReqBean
                                .setCurrentAddressBuildingNo(formBean.getLineBean().getCurrentAddressBuildingNo());
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
                        serviceReqBean
                                .setPermanentAddressTownship(formBean.getLineBean().getPermanentAddressTownship());
                        serviceReqBean.setPermanentAddressCity(formBean.getLineBean().getPermanentAddressCity());
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
                        serviceReqBean.setStatus(17);
                        serviceReqBean.setDaInterestInfoId(formBean.getLineBean().getInterestId());
                        serviceReqBean.setDaCompulsoryInfoId(formBean.getLineBean().getCompulsoryId());
                        serviceReqBean.setDaLoanTypeId(formBean.getLineBean().getLoanTypeId());
                        serviceReqBean.setFinanceAmount(formBean.getLineBean().getFinanceAmount());
                        serviceReqBean.setFinanceTerm(formBean.getLineBean().getFinanceTerm());
                        serviceReqBean.setDaProductTypeId(formBean.getLineBean().getProductId());
                        serviceReqBean.setProductDescription(formBean.getLineBean().getProductDescription());
                        serviceReqBean.setChannelType(formBean.getLineBean().getChannelType());
                        serviceReqBean.setDelFlag(formBean.getLineBean().getDelFlag());
                        serviceReqBean.setSettlementPendingComment(formBean.getPendingHeaderBean().getPendingComment());

                        serviceReqBean.setCreatedBy(String.valueOf(userId) + "," + userTypeId);
                        serviceReqBean.setCreatedTime(CommonUtil.getCurrentTimeStamp());
                        serviceReqBean.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
                        serviceReqBean.setUpdatedBy(String.valueOf(userId) + "," + userTypeId);
                        this.getServiceInvoker().addRequest(serviceReqBean);
                        responseMessage = this.getServiceInvoker().invoke();
                        resBean = responseMessage.getMessageBean(0);
                        serviceStatus = resBean.getServiceStatus();

                        if (ServiceStatusCode.OK.equals(serviceStatus)) {
                            msgBean = new MessageBean(MessageId.MI0002);
                            msgBean.setMessageType(MessageType.INFO);
                            formBean.getMessageContainer().addMessage(msgBean);
                            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                            applicationLogger.log("Settlement application pending status update process finished.",
                                    LogLevel.INFO);
                            return formBean;
                        } else {
                            setErrorMessage(formBean, serviceStatus);
                        }
                        // end record history table
                    }

                } else {
                    setErrorMessage(formBean, serviceStatus);
                }

            } else {
                setErrorMessage(formBean, serviceStatus);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
        return formBean;
    }

    private boolean isValidData(SettlementIndexApplicationListFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;

        if (InputChecker.isBlankOrNull(formBean.getPendingHeaderBean().getPendingComment())) {
            msgBean =
                    new MessageBean(MessageId.ME1057, DisplayItemBean.getDisplayItemName(DisplayItem.PENDING_COMMENT));
            msgBean.addColumnId(DisplayItem.PENDING_COMMENT);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        return isValid;
    }

    private SettlementIndexApplicationListFormBean setErrorMessage(SettlementIndexApplicationListFormBean formBean,
            String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Settlement application pending status update data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Settlement application pending status update data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Settlement application pending status update data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Settlement application pending status update data already deleted by other.",
                    LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
