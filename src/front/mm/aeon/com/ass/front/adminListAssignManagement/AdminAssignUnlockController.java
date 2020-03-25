/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.adminListAssignManagement;

import mm.aeon.com.ass.base.service.userGroupLockService.UserGroupLockServiceReqBean;
import mm.aeon.com.ass.front.userGroupList.UserGroupListFormBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AdminAssignUnlockController extends AbstractController
        implements IControllerAccessor<AdminAssignManagementFormBean, AdminAssignManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public AdminAssignManagementFormBean process(AdminAssignManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        
        try {
            UserGroupLockServiceReqBean unlockServiceReqBean =
                    new UserGroupLockServiceReqBean();
            unlockServiceReqBean.setGroupId(formBean.getUserLineBean().getGroupId());
            unlockServiceReqBean.setLockFlag(false);
            this.getServiceInvoker().addRequest(unlockServiceReqBean);
            this.getServiceInvoker().invoke();
            applicationLogger.log("User Group Unlock process finished.", LogLevel.INFO);
            return formBean;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return formBean;
    }
}
