/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch;

import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgentSaleClaimFinishedReport")
public class AgentSaleClaimFinishedReportCountSearchReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = -3712645002309316118L;
    private String agentName;
    private String location;
    private Date uploadedDateFrom;
    private Date uploadedDateTo;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.COUNT;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

}
