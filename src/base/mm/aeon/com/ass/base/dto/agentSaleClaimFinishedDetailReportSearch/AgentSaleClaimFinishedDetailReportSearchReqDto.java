/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentSaleClaimFinishedDetailReportSearch;

import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgentSaleClaimFinishedDetailReport")
public class AgentSaleClaimFinishedDetailReportSearchReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = -5578964690693393044L;
    private String location;
    private Date uploadedDateFrom;
    private Date uploadedDateTo;
    private Integer agentId;
    private Integer limit;
    private Integer offset;
    private String sortField;
    private String sortOrder;
    private String agentName;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.SELECT_LIST;
    }

    public String getLocation() {
        return location;
    }

    public Date getUploadedDateFrom() {
        return uploadedDateFrom;
    }

    public Date getUploadedDateTo() {
        return uploadedDateTo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUploadedDateFrom(Date uploadedDateFrom) {
        this.uploadedDateFrom = uploadedDateFrom;
    }

    public void setUploadedDateTo(Date uploadedDateTo) {
        this.uploadedDateTo = uploadedDateTo;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

}
