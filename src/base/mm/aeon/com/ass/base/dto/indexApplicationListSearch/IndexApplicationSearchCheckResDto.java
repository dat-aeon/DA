/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.indexApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class IndexApplicationSearchCheckResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -928552617456248226L;

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
