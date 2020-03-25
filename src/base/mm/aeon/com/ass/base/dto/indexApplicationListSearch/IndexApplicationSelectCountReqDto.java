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
public class IndexApplicationSelectCountReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5376898487674767104L;

    private String applicationNo;

    private String applicantName;

    private String applicantPhoneNo;

    private Date newApplicationDateFrom;

    private Date newApplicationDateTo;

    private String applicantNrcNo;

    private Date indexedDateFrom;

    private Date indexedDateTo;

    private String staffName;

    private List<String> staffNameList;

    private Boolean staffNameSearchFlag;

    @Override
    public DaoType getDaoType() {
        return DaoType.COUNT;
    }

    public Boolean getStaffNameSearchFlag() {
        return staffNameSearchFlag;
    }

    public void setStaffNameSearchFlag(Boolean staffNameSearchFlag) {
        this.staffNameSearchFlag = staffNameSearchFlag;
    }

    public List<String> getStaffNameList() {
        return staffNameList;
    }

    public void setStaffNameList(List<String> staffNameList) {
        this.staffNameList = staffNameList;
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

}
