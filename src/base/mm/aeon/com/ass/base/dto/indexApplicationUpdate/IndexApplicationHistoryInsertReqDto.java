/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.indexApplicationUpdate;

import java.sql.Timestamp;
import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "IndexApplicationInsert")

public class IndexApplicationHistoryInsertReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = 8999850866293209560L;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.INSERT;
    }

    private Integer da_application_info_id;

    private String application_no;

    private Integer da_application_type_id;

    private Date applied_date;

    private String name;

    private Date dob;

    private String nrc_no;

    private String father_name;

    private Integer nationality;

    private String nationality_other;

    private Integer gender;

    private Integer marital_status;

    private String current_address;

    private String permanent_address;

    private Integer type_of_residence;

    private String type_of_residence_other;

    private Integer living_with;

    private String living_with_other;

    private Integer year_of_stay_year;

    private Integer year_of_stay_month;

    private String mobile_no;

    private String resident_tel_no;

    private String other_phone_no;

    private String email;

    private Integer customer_id;

    private Integer status;

    private Integer da_interest_info_id;

    private Integer da_compulsory_info_id;

    private Integer da_loan_type_id;

    private Double finance_amount;

    private Integer finance_term;

    private Integer da_product_type_id;

    private String product_description;

    private Integer channel_type;

    private Timestamp created_time;

    private String created_by;

    private Timestamp updated_time;

    private String updated_by;

    private Boolean del_flag;

    private String settlement_pending_comment;

    public Integer getDa_application_info_id() {
        return da_application_info_id;
    }

    public void setDa_application_info_id(Integer da_application_info_id) {
        this.da_application_info_id = da_application_info_id;
    }

    public String getApplication_no() {
        return application_no;
    }

    public void setApplication_no(String application_no) {
        this.application_no = application_no;
    }

    public Integer getDa_application_type_id() {
        return da_application_type_id;
    }

    public void setDa_application_type_id(Integer da_application_type_id) {
        this.da_application_type_id = da_application_type_id;
    }

    public Date getApplied_date() {
        return applied_date;
    }

    public void setApplied_date(Date applied_date) {
        this.applied_date = applied_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNrc_no() {
        return nrc_no;
    }

    public void setNrc_no(String nrc_no) {
        this.nrc_no = nrc_no;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public String getNationality_other() {
        return nationality_other;
    }

    public void setNationality_other(String nationality_other) {
        this.nationality_other = nationality_other;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(Integer marital_status) {
        this.marital_status = marital_status;
    }

    public String getCurrent_address() {
        return current_address;
    }

    public void setCurrent_address(String current_address) {
        this.current_address = current_address;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public Integer getType_of_residence() {
        return type_of_residence;
    }

    public void setType_of_residence(Integer type_of_residence) {
        this.type_of_residence = type_of_residence;
    }

    public String getType_of_residence_other() {
        return type_of_residence_other;
    }

    public void setType_of_residence_other(String type_of_residence_other) {
        this.type_of_residence_other = type_of_residence_other;
    }

    public Integer getLiving_with() {
        return living_with;
    }

    public void setLiving_with(Integer living_with) {
        this.living_with = living_with;
    }

    public String getLiving_with_other() {
        return living_with_other;
    }

    public void setLiving_with_other(String living_with_other) {
        this.living_with_other = living_with_other;
    }

    public Integer getYear_of_stay_year() {
        return year_of_stay_year;
    }

    public void setYear_of_stay_year(Integer year_of_stay_year) {
        this.year_of_stay_year = year_of_stay_year;
    }

    public Integer getYear_of_stay_month() {
        return year_of_stay_month;
    }

    public void setYear_of_stay_month(Integer year_of_stay_month) {
        this.year_of_stay_month = year_of_stay_month;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getResident_tel_no() {
        return resident_tel_no;
    }

    public void setResident_tel_no(String resident_tel_no) {
        this.resident_tel_no = resident_tel_no;
    }

    public String getOther_phone_no() {
        return other_phone_no;
    }

    public void setOther_phone_no(String other_phone_no) {
        this.other_phone_no = other_phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDa_interest_info_id() {
        return da_interest_info_id;
    }

    public void setDa_interest_info_id(Integer da_interest_info_id) {
        this.da_interest_info_id = da_interest_info_id;
    }

    public Integer getDa_compulsory_info_id() {
        return da_compulsory_info_id;
    }

    public void setDa_compulsory_info_id(Integer da_compulsory_info_id) {
        this.da_compulsory_info_id = da_compulsory_info_id;
    }

    public Integer getDa_loan_type_id() {
        return da_loan_type_id;
    }

    public void setDa_loan_type_id(Integer da_loan_type_id) {
        this.da_loan_type_id = da_loan_type_id;
    }

    public Double getFinance_amount() {
        return finance_amount;
    }

    public void setFinance_amount(Double finance_amount) {
        this.finance_amount = finance_amount;
    }

    public Integer getFinance_term() {
        return finance_term;
    }

    public void setFinance_term(Integer finance_term) {
        this.finance_term = finance_term;
    }

    public Integer getDa_product_type_id() {
        return da_product_type_id;
    }

    public void setDa_product_type_id(Integer da_product_type_id) {
        this.da_product_type_id = da_product_type_id;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public Integer getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(Integer channel_type) {
        this.channel_type = channel_type;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

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

    public Boolean getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Boolean del_flag) {
        this.del_flag = del_flag;
    }

    public String getSettlement_pending_comment() {
        return settlement_pending_comment;
    }

    public void setSettlement_pending_comment(String settlement_pending_comment) {
        this.settlement_pending_comment = settlement_pending_comment;
    }

}
