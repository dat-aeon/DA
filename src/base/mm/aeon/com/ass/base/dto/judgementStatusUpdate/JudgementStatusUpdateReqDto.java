/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Swe Hsu Mon $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.judgementStatusUpdate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import mm.aeon.com.ass.front.judgementStatusUpload.CustAgreementList;
import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "JudgementStatus")
public class JudgementStatusUpdateReqDto implements IReqServiceDto {

    private static final long serialVersionUID = 2672207836115099916L;

    private Integer age;
    private String companyName;
    private Date createdTime;
    private Integer customerId;
    private String customerNo;
    private Integer delFlag;
    private Date dob;
    private Integer gender;
    private String memberCardId;
    private Integer memberCardStatus;
    private Integer memberFlag;
    private String name;
    private String nrcNo;
    private String phoneNo;
    private Double salary;
    private Integer status;
    private String township;
    private Date updatedTime;

    private List<CustAgreementList> custAgreementListList;

    public Integer getAge() {
        return age;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public Date getDob() {
        return dob;
    }

    public Integer getGender() {
        return gender;
    }

    public String getMemberCardId() {
        return memberCardId;
    }

    public Integer getMemberCardStatus() {
        return memberCardStatus;
    }

    public Integer getMemberFlag() {
        return memberFlag;
    }

    public String getName() {
        return name;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer getStatus() {
        return status;
    }

    public String getTownship() {
        return township;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public List<CustAgreementList> getCustAgreementListList() {
        return custAgreementListList;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setMemberCardId(String memberCardId) {
        this.memberCardId = memberCardId;
    }

    public void setMemberCardStatus(Integer memberCardStatus) {
        this.memberCardStatus = memberCardStatus;
    }

    public void setMemberFlag(Integer memberFlag) {
        this.memberFlag = memberFlag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setCustAgreementListList(List<CustAgreementList> custAgreementListList) {
        this.custAgreementListList = custAgreementListList;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.INSERT;
    }

}
