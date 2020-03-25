/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.commonApplicationUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "CommonApplicationInsert")
public class CommonApplicationStatusUpdateReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = 8999850866293209560L;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.UPDATE;
    }

    private Integer daApplicationInfoId;
    private Integer status;
    private Timestamp updatedTime;
    private String updatedBy;
    private String modifyComment;
    private String pendingComment;
    private Integer modifyStatus;

    public Integer getDaApplicationInfoId() {
        return daApplicationInfoId;
    }

    public Integer getStatus() {
        return status;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getModifyComment() {
        return modifyComment;
    }

    public void setDaApplicationInfoId(Integer daApplicationInfoId) {
        this.daApplicationInfoId = daApplicationInfoId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setModifyComment(String modifyComment) {
        this.modifyComment = modifyComment;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

    public Integer getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(Integer modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

}
