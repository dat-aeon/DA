/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.indexApplicationListSearch;

import java.util.Date;
import java.util.List;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "IndexApplicationInfo")
public class IndexApplicationSearchReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 622234158884618063L;

    private String applicationNo;

    private String applicantName;

    private String applicantPhoneNo;

    private Date newApplicationDateFrom;

    private Date newApplicationDateTo;

    private String applicantNrcNo;

    private Date indexedDateFrom;

    private Date indexedDateTo;

    private String staffName;

    private Integer limit;

    private Integer offset;

    private String sortField;

    private String sortOrder;

    private List<String> staffNameList;

    private Boolean staffNameSearchFlag;

    public List<String> getStaffNameList() {
        return staffNameList;
    }

    public void setStaffNameList(List<String> staffNameList) {
        this.staffNameList = staffNameList;
    }

    public Boolean getStaffNameSearchFlag() {
        return staffNameSearchFlag;
    }

    public void setStaffNameSearchFlag(Boolean staffNameSearchFlag) {
        this.staffNameSearchFlag = staffNameSearchFlag;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantPhoneNo() {
        return applicantPhoneNo;
    }

    public void setApplicantPhoneNo(String applicantPhoneNo) {
        this.applicantPhoneNo = applicantPhoneNo;
    }

    public Date getNewApplicationDateFrom() {
        return newApplicationDateFrom;
    }

    public void setNewApplicationDateFrom(Date newApplicationDateFrom) {
        this.newApplicationDateFrom = newApplicationDateFrom;
    }

    public Date getNewApplicationDateTo() {
        return newApplicationDateTo;
    }

    public void setNewApplicationDateTo(Date newApplicationDateTo) {
        this.newApplicationDateTo = newApplicationDateTo;
    }

    public String getApplicantNrcNo() {
        return applicantNrcNo;
    }

    public void setApplicantNrcNo(String applicantNrcNo) {
        this.applicantNrcNo = applicantNrcNo;
    }

    public Date getIndexedDateFrom() {
        return indexedDateFrom;
    }

    public void setIndexedDateFrom(Date indexedDateFrom) {
        this.indexedDateFrom = indexedDateFrom;
    }

    public Date getIndexedDateTo() {
        return indexedDateTo;
    }

    public void setIndexedDateTo(Date indexedDateTo) {
        this.indexedDateTo = indexedDateTo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_LIST;
    }
}
