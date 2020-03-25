/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.menuListAssignManagement;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.menuAssignGroupSelectList.MenuAssignGroupSelectListReqDto;
import mm.aeon.com.ass.base.dto.menuAssignGroupSelectList.MenuAssignGroupSelectListResDto;
import mm.aeon.com.ass.base.dto.menuGroupSelectedList.MenuGroupSelectedListReqDto;
import mm.aeon.com.ass.base.dto.menuGroupSelectedList.MenuGroupSelectedListResDto;
import mm.aeon.com.ass.base.dto.userGroupLockCheck.UserGroupLockCheckReqDto;
import mm.aeon.com.ass.base.dto.userGroupLockCheck.UserGroupLockCheckResDto;
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

public class MenuListAssignController extends AbstractController
        implements IControllerAccessor<MenuAssignManagementFormBean, MenuAssignManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public MenuAssignManagementFormBean process(MenuAssignManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Menu Searching Process started.", LogLevel.INFO);
        MessageBean msgBean;
        String serviceStatus = null;
        MenuAssignGroupSelectListReqDto reqDto = new MenuAssignGroupSelectListReqDto();
        MenuGroupSelectedListReqDto selectedReqDto = new MenuGroupSelectedListReqDto();
        reqDto.setGroupId(formBean.getUserLineBean().getGroupId());
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

            List<MenuAssignGroupSelectListResDto> resDtoList =
                    (List<MenuAssignGroupSelectListResDto>) this.getDaoServiceInvoker().execute(reqDto);
            List<MenuGroupSelectedListResDto> selectedResDtoList =
                    (List<MenuGroupSelectedListResDto>) this.getDaoServiceInvoker().execute(selectedReqDto);

            List<MenuListAssignLineBean> lineBeanList = new ArrayList<MenuListAssignLineBean>();
            List<MenuListAssignLineBean> selectedLineBeanList = new ArrayList<MenuListAssignLineBean>();

            for (MenuAssignGroupSelectListResDto resdto : resDtoList) {
                MenuListAssignLineBean lineBean = new MenuListAssignLineBean();

                lineBean.setMenuId(resdto.getMenuId());
                lineBean.setMenuName(resdto.getMenuName());
                lineBeanList.add(lineBean);
            }

            formBean.setLineBeanList(lineBeanList);
            for (MenuGroupSelectedListResDto selectedResdto : selectedResDtoList) {
                MenuListAssignLineBean lineBean = new MenuListAssignLineBean();

                lineBean.setMenuId(selectedResdto.getMenuId());
                lineBean.setMenuName(selectedResdto.getMenuName());
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
            applicationLogger.log("Menu searching finished.", LogLevel.INFO);
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
