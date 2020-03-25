/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.custEditRequestUpdate;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;
@MyBatisMapper(namespace="EditRequestStatus")

public class CustStatusRejectReqDto implements IReqDto {
 
    /**
     * 
     */
    private static final long serialVersionUID = -3262626402068217153L;
   
    private Integer status;
    private Integer CustEditReqId;
    
    @Override
    public DaoType getDaoType() {
        return DaoType.UPDATE;
    }
 
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCustEditReqId() {
        return CustEditReqId;
    }

    public void setCustEditReqId(Integer custEditReqId) {
        CustEditReqId = custEditReqId;
    }
   }
