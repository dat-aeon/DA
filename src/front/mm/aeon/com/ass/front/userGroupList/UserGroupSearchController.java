/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.userGroupList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.userGroupInfoSearch.UserGroupSearchReqDto;
import mm.aeon.com.ass.base.dto.userGroupInfoSearch.UserGroupSearchResDto;
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
import mm.com.dat.presto.utils.common.InputChecker;

public class UserGroupSearchController extends AbstractController
        implements IControllerAccessor<UserGroupListFormBean, UserGroupListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public UserGroupListFormBean process(UserGroupListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("User Group Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        UserGroupSearchReqDto reqDto = new UserGroupSearchReqDto();
        
        if(formBean.getLockErrorParam() != null) {
            if(formBean.getLockErrorParam()) {
                msgBean = new MessageBean(MessageId.ME1054);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            }
        }

        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getGroupName())) {
            reqDto.setGroupName(formBean.getSearchHeaderBean().getGroupName().toLowerCase());
        }
        try {
            List<UserGroupSearchResDto> resUserGroupList =
                    (List<UserGroupSearchResDto>) this.getDaoServiceInvoker().execute(reqDto);
            List<UserGroupListLineBean> lineBeanList = new ArrayList<UserGroupListLineBean>();

            for (UserGroupSearchResDto resdto : resUserGroupList) {
                UserGroupListLineBean lineBean = new UserGroupListLineBean();
                lineBean.setGroupId(resdto.getGroupId());
                lineBean.setGroupName(resdto.getGroupName());
                lineBean.setDelFlag(resdto.getDelFlag());
                lineBean.setCreatedBy(resdto.getCreatedBy());
                lineBean.setCreatedTime(resdto.getCreatedTime());
                lineBean.setUpdatedBy(resdto.getUpdatedBy());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
                lineBean.setLockFlag(resdto.getLockFlag());
                lineBean.setLockedBy(resdto.getLockedBy());
                lineBean.setLockedTime(resdto.getLockedTime());
                lineBeanList.add(lineBean);
            }

            formBean.setLineBeans(lineBeanList);

            if (lineBeanList.size() == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(lineBeanList.size()));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("User Group searching finished.", LogLevel.INFO);
            
            

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
