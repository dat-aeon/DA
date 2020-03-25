/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi$
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.custEditRequestList;

import mm.aeon.com.ass.front.common.abstractController.AbstractDAController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class CustEditReqInitController extends AbstractDAController
        implements IControllerAccessor<CustEditReqListFormBean, CustEditReqListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public CustEditReqListFormBean process(CustEditReqListFormBean formBean) {
    formBean.getMessageContainer().clearAllMessages(true);    
     
        applicationLogger.log("Customer Init process stared.", LogLevel.INFO);
        formBean.setStatusSelectItemList(super.getCustomerEditStatusList());
        

        applicationLogger.log("Customer Init process ended.", LogLevel.INFO);
        return formBean;
    }

}
