/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.saleClaimApplicationUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "SaleClaimApplicationHistoryInfo")
public class SaleClaimApplicationHistoryStatusUpdateReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = 8999850866293209560L;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.UPDATE;
    }

    private Integer applicationHistoryId;
    private Integer status;
    private Timestamp updatedTime;
    private String updatedBy;
    private String pendingComment;

    public Integer getApplicationHistoryId() {
        return applicationHistoryId;
    }

    public void setApplicationHistoryId(Integer applicationHistoryId) {
        this.applicationHistoryId = applicationHistoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

}
