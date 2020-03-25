/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.custEditReqUpdateService;
 
import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class CustStatusApproveServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = 9127700691958278402L;

    /**
     * 
     */

    private Integer custEditReqId;
    
    @Override
    public String getServiceId() {
        return "STATUSD";
    }


    public Integer getCustEditReqId() {
        return custEditReqId;
    }


    public void setCustEditReqId(Integer custEditReqId) {
        this.custEditReqId = custEditReqId;
    }
    
}
