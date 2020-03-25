/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgentDocumentErrorApplicationInsert")
public class AgentDocumentErrorApplicationStatusUpdateReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = 8999850866293209560L;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.UPDATE;
    }

    private Integer da_application_info_id;
    private Integer status;
    private Timestamp updated_time;
    private String updated_by;
    private String pendingComment;

    public Timestamp getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Timestamp updated_time) {
        this.updated_time = updated_time;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Integer getDa_application_info_id() {
        return da_application_info_id;
    }

    public void setDa_application_info_id(Integer da_application_info_id) {
        this.da_application_info_id = da_application_info_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPendingComment() {
        return pendingComment;
    }

    public void setPendingComment(String pendingComment) {
        this.pendingComment = pendingComment;
    }

}
