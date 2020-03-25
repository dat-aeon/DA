/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.custEditRequestUpdate;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace="EditRequestStatus")
public class CustStatusApproveReqDto implements IReqDto {
 
    /**
     * 
     */
    private static final long serialVersionUID = -3262626402068217153L;
   
    private Integer custEditReqId;
    
    @Override
    public DaoType getDaoType() {
        return DaoType.DELETE;
    }

    public Integer getCustEditReqId() {
        return custEditReqId;
    }

    public void setCustEditReqId(Integer custEditReqId) {
        this.custEditReqId = custEditReqId;
    }
   }
