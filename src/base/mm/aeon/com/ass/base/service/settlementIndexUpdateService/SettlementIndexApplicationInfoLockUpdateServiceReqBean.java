/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.settlementIndexUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;
 
public class  SettlementIndexApplicationInfoLockUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;


    private Integer applicationId;

    private Boolean lockFlag;

    private Timestamp lockTime;

    private String lockBy;

    @Override
    public String getServiceId() {
        return "SETTLEMENTINFOLU";
    }

   

    public Integer getApplicationId() {
        return applicationId;
    }



    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }



    public Boolean getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

    public Timestamp getLockTime() {
        return lockTime;
    }

    public void setLockTime(Timestamp lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockBy() {
        return lockBy;
    }

    public void setLockBy(String lockBy) {
        this.lockBy = lockBy;
    }

    
}
