/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.adminListAssignManagement;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.userAssignGroupSelectList.UserAssignGroupSelectListReqDto;
import mm.aeon.com.ass.base.dto.userAssignGroupSelectList.UserAssignGroupSelectListResDto;
import mm.aeon.com.ass.base.dto.userGroupLockCheck.UserGroupLockCheckReqDto;
import mm.aeon.com.ass.base.dto.userGroupLockCheck.UserGroupLockCheckResDto;
import mm.aeon.com.ass.base.dto.userGroupSelectedList.UserGroupSelectedListReqDto;
import mm.aeon.com.ass.base.dto.userGroupSelectedList.UserGroupSelectedListResDto;
import mm.aeon.com.ass.base.service.userGroupLockService.UserGroupLockServiceReqBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AdminListAssignController extends AbstractController
        implements IControllerAccessor<AdminAssignManagementFormBean, AdminAssignManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public AdminAssignManagementFormBean process(AdminAssignManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Admin Searching Process started.", LogLevel.INFO);
        MessageBean msgBean;
        String serviceStatus = null;
        UserAssignGroupSelectListReqDto reqDto = new UserAssignGroupSelectListReqDto();
        UserGroupSelectedListReqDto selectedReqDto = new UserGroupSelectedListReqDto();
        selectedReqDto.setGroupId(formBean.getUserLineBean().getGroupId());

        UserGroupLockCheckReqDto GroupReqDto = new UserGroupLockCheckReqDto();
        GroupReqDto.setGroupId(formBean.getUserLineBean().getGroupId());

        try {
            UserGroupLockCheckResDto resCheck =
                    (UserGroupLockCheckResDto) CommonUtil.getDaoServiceInvoker().execute(GroupReqDto);
            if (resCheck == null) {
                msgBean = new MessageBean(MessageId.ME1009);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
                formBean.setCheckLockFlag(true);
            } else {
                if (!resCheck.getLockFlag()
                        || resCheck.getLockedBy().equals(CommonUtil.getLoginUserInfo().getUserName())) {
                    // Lock start
                    formBean.setCheckLockFlag(false);
                    UserGroupLockServiceReqBean lockServiceReqBean = new UserGroupLockServiceReqBean();
                    lockServiceReqBean.setGroupId(formBean.getUserLineBean().getGroupId());
                    lockServiceReqBean.setLockFlag(true);
                    lockServiceReqBean.setLockedTime(CommonUtil.getCurrentTimeStamp());
                    lockServiceReqBean.setLockedBy(CommonUtil.getLoginUserInfo().getUserName());
                    this.getServiceInvoker().addRequest(lockServiceReqBean);
                    ResponseMessage responseMessage = this.getServiceInvoker().invoke();

                    AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
                    serviceStatus = resBean.getServiceStatus();

                } else {
                    msgBean = new MessageBean(MessageId.ME1054);
                    msgBean.setMessageType(MessageType.ERROR);
                    formBean.getMessageContainer().addMessage(msgBean);
                    applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
                    formBean.setCheckLockFlag(true);
                }
            }

            // start search Menu List
            List<UserAssignGroupSelectListResDto> resDtoList =
                    (List<UserAssignGroupSelectListResDto>) this.getDaoServiceInvoker().execute(reqDto);

            List<UserGroupSelectedListResDto> selectedResDtoList =
                    (List<UserGroupSelectedListResDto>) this.getDaoServiceInvoker().execute(selectedReqDto);

            List<AdminListAssignLineBean> lineBeanList = new ArrayList<AdminListAssignLineBean>();
            List<AdminListAssignLineBean> selectedLineBeanList = new ArrayList<AdminListAssignLineBean>();

            for (UserAssignGroupSelectListResDto resdto : resDtoList) {
                AdminListAssignLineBean lineBean = new AdminListAssignLineBean();

                lineBean.setAdminId(resdto.getUserId());
                lineBean.setAdminName(resdto.getName());
                if (resdto.getUserTypeId() == 4) {
                    lineBean.setUserTypeName("Admin");
                } else {
                    lineBean.setUserTypeName("Operator");
                }
                lineBeanList.add(lineBean);
            }
            formBean.setLineBeanList(lineBeanList);
            for (UserGroupSelectedListResDto selectedResdto : selectedResDtoList) {
                AdminListAssignLineBean lineBean = new AdminListAssignLineBean();

                lineBean.setAdminId(selectedResdto.getUserId());
                lineBean.setAdminName(selectedResdto.getName());
                if (selectedResdto.getUserTypeId() == 4) {
                    lineBean.setUserTypeName("Admin");
                } else {
                    lineBean.setUserTypeName("Operator");
                }
                selectedLineBeanList.add(lineBean);
            }
            formBean.setSelectedLineBeanList(selectedLineBeanList);
            if (lineBeanList.size() == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(lineBeanList.size()));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Admin searching finished.", LogLevel.INFO);
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
