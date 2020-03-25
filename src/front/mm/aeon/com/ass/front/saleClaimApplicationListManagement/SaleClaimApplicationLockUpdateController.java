/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.saleClaimApplicationListManagement;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.saleClaimApplicationListSearch.SaleClaimApplicationSearchCheckReqDto;
import mm.aeon.com.ass.base.dto.saleClaimApplicationListSearch.SaleClaimApplicationSearchCheckResDto;
import mm.aeon.com.ass.base.service.saleClaimUpdateService.SaleClaimApplicationInfoLockUpdateServiceReqBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.saleClaimApplicationList.SaleClaimApplicationListFormBean;
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

public class SaleClaimApplicationLockUpdateController extends AbstractController
        implements IControllerAccessor<SaleClaimApplicationListFormBean, SaleClaimApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public SaleClaimApplicationListFormBean process(SaleClaimApplicationListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;
        formBean.setCheckNotYetFlag(false);

        SaleClaimApplicationSearchCheckReqDto reqDto = new SaleClaimApplicationSearchCheckReqDto();
        reqDto.setApplicationID(formBean.getLineBean().getApplicationId());

        try {
            SaleClaimApplicationSearchCheckResDto resCheck =
                    (SaleClaimApplicationSearchCheckResDto) CommonUtil.getDaoServiceInvoker().execute(reqDto);
            if (!resCheck.getLockFlag()
                    || resCheck.getLockedBy().equals(CommonUtil.getLoginUserInfo().getId().toString())) {

                // Lock start
                SaleClaimApplicationInfoLockUpdateServiceReqBean lockServiceReqBean =
                        new SaleClaimApplicationInfoLockUpdateServiceReqBean();
                lockServiceReqBean.setApplicationId(formBean.getLineBean().getApplicationId());
                lockServiceReqBean.setLockFlag(true);
                lockServiceReqBean.setLockTime(CommonUtil.getCurrentTimeStamp());
                lockServiceReqBean.setLockBy(CommonUtil.getLoginUserInfo().getId().toString());
                this.getServiceInvoker().addRequest(lockServiceReqBean);
                ResponseMessage responseMessage = this.getServiceInvoker().invoke();
                AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
                String serviceStatus = resBean.getServiceStatus();
                if (ServiceStatusCode.OK.equals(serviceStatus)) {
                    msgBean = new MessageBean(MessageId.MI0002);
                    msgBean.setMessageType(MessageType.INFO);
                    formBean.getMessageContainer().addMessage(msgBean);
                    applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                    applicationLogger.log("Sale Claim application lock update process finished.", LogLevel.INFO);
                } else {
                    setErrorMessage(formBean, serviceStatus);
                }

            } else {
                msgBean = new MessageBean(MessageId.ME1048);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
                formBean.setCheckNotYetFlag(true);
            }
            return formBean;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return formBean;
    }

    private SaleClaimApplicationListFormBean setErrorMessage(SaleClaimApplicationListFormBean formBean,
            String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Sale Claim application lock update data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Sale Claim application lock update data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Sale Claim application lock update data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Sale Claim application lock update data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
