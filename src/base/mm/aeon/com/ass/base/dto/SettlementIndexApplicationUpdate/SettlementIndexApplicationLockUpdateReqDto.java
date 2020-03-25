/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.SettlementIndexApplicationUpdate;

 
import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;
 
@MyBatisMapper(namespace = "SettlementIndexApplicationInfo")
public class SettlementIndexApplicationLockUpdateReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = -748406496640180341L;

    private Integer applicationId;

    private Boolean lockFlag;

    private Timestamp lockTime;

    private String lockBy;

 
    @Override
    public DaoType getDaoType() {
        return DaoType.UPDATE;
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
