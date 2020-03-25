/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentAndProductApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentAndProductApplicationSearchCheckResDto implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 6771508053017581415L;

    private Boolean lockFlag;

    private String lockedBy;

    public Boolean getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

}
