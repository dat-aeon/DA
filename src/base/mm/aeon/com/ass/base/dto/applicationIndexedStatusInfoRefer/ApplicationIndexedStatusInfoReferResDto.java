/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.applicationIndexedStatusInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ApplicationIndexedStatusInfoReferResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 4511546202956199291L;

    private String indexedStaff;

    public String getIndexedStaff() {
        return indexedStaff;
    }

    public void setIndexedStaff(String indexedStaff) {
        this.indexedStaff = indexedStaff;
    }

}
