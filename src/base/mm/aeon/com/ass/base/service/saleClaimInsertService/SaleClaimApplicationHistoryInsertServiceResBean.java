/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.saleClaimInsertService;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;

public class SaleClaimApplicationHistoryInsertServiceResBean extends AbstractServiceResBean {

    /**
     * 
     */
    private static final long serialVersionUID = 9180802110754680381L;

    private Integer daApplicationHistoryId;

    public Integer getDaApplicationHistoryId() {
        return daApplicationHistoryId;
    }

    public void setDaApplicationHistoryId(Integer daApplicationHistoryId) {
        this.daApplicationHistoryId = daApplicationHistoryId;
    }

}
