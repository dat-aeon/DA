/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agreementModificationCancelReportSearch;

import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgreementModificationCancelReport")
public class AgreementModificationCancelReportSearchReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = -5578964690693393044L;
    private String applicationNo;
    private String applicantName;
    private String phoneNo;
    private Date modifyTimeFrom;
    private Date modifyTimeTo;
    private String nrcNo;
    private Integer limit;
    private Integer offset;
    private String sortField;
    private String sortOrder;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.SELECT_LIST;
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

    public String getApplicationNo() {
        return applicationNo;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Date getModifyTimeFrom() {
        return modifyTimeFrom;
    }

    public Date getModifyTimeTo() {
        return modifyTimeTo;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setModifyTimeFrom(Date modifyTimeFrom) {
        this.modifyTimeFrom = modifyTimeFrom;
    }

    public void setModifyTimeTo(Date modifyTimeTo) {
        this.modifyTimeTo = modifyTimeTo;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

}
