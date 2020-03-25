/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.uploadedApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class UploadedApplicationApplicantCompanySearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7591551575539498312L;

    private Integer daApplicantCompanyId;

    private String companyName;

    private String companyAddress;

    private String companyAddressBuildingNo;

    private String companyAddressRoomNo;

    private String companyAddressFloor;

    private String companyAddressStreet;

    private String companyAddressQtr;

    private Integer companyAddressTownship;

    private Integer companyAddressCity;

    private String companyTelNo;

    private String contactTimeFrom;

    private String contactTimeTo;

    private String department;

    private String position;

    private Integer serviceYear;

    private Integer serviceMonth;

    private Integer companyStatus;

    private String companyStatusOther;

    private Double monthlyBasicIncome;

    private Double otherIncome;

    private Double totalIncome;

    private Integer salaryDay;

    public Integer getDaApplicantCompanyId() {
        return daApplicantCompanyId;
    }

    public void setDaApplicantCompanyId(Integer daApplicantCompanyId) {
        this.daApplicantCompanyId = daApplicantCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyTelNo() {
        return companyTelNo;
    }

    public void setCompanyTelNo(String companyTelNo) {
        this.companyTelNo = companyTelNo;
    }

    public String getContactTimeFrom() {
        return contactTimeFrom;
    }

    public void setContactTimeFrom(String contactTimeFrom) {
        this.contactTimeFrom = contactTimeFrom;
    }

    public String getContactTimeTo() {
        return contactTimeTo;
    }

    public void setContactTimeTo(String contactTimeTo) {
        this.contactTimeTo = contactTimeTo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getServiceYear() {
        return serviceYear;
    }

    public void setServiceYear(Integer serviceYear) {
        this.serviceYear = serviceYear;
    }

    public Integer getServiceMonth() {
        return serviceMonth;
    }

    public void setServiceMonth(Integer serviceMonth) {
        this.serviceMonth = serviceMonth;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyStatusOther() {
        return companyStatusOther;
    }

    public void setCompanyStatusOther(String companyStatusOther) {
        this.companyStatusOther = companyStatusOther;
    }

    public Double getMonthlyBasicIncome() {
        return monthlyBasicIncome;
    }

    public void setMonthlyBasicIncome(Double monthlyBasicIncome) {
        this.monthlyBasicIncome = monthlyBasicIncome;
    }

    public Double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getSalaryDay() {
        return salaryDay;
    }

    public void setSalaryDay(Integer salaryDay) {
        this.salaryDay = salaryDay;
    }

    public String getCompanyAddressBuildingNo() {
        return companyAddressBuildingNo;
    }

    public void setCompanyAddressBuildingNo(String companyAddressBuildingNo) {
        this.companyAddressBuildingNo = companyAddressBuildingNo;
    }

    public String getCompanyAddressRoomNo() {
        return companyAddressRoomNo;
    }

    public void setCompanyAddressRoomNo(String companyAddressRoomNo) {
        this.companyAddressRoomNo = companyAddressRoomNo;
    }

    public String getCompanyAddressFloor() {
        return companyAddressFloor;
    }

    public void setCompanyAddressFloor(String companyAddressFloor) {
        this.companyAddressFloor = companyAddressFloor;
    }

    public String getCompanyAddressStreet() {
        return companyAddressStreet;
    }

    public void setCompanyAddressStreet(String companyAddressStreet) {
        this.companyAddressStreet = companyAddressStreet;
    }

    public String getCompanyAddressQtr() {
        return companyAddressQtr;
    }

    public void setCompanyAddressQtr(String companyAddressQtr) {
        this.companyAddressQtr = companyAddressQtr;
    }

    public Integer getCompanyAddressTownship() {
        return companyAddressTownship;
    }

    public void setCompanyAddressTownship(Integer companyAddressTownship) {
        this.companyAddressTownship = companyAddressTownship;
    }

    public Integer getCompanyAddressCity() {
        return companyAddressCity;
    }

    public void setCompanyAddressCity(Integer companyAddressCity) {
        this.companyAddressCity = companyAddressCity;
    }

}
