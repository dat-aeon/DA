/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.termsAndConditions;

import java.io.Serializable;
import java.sql.Timestamp;

public class TermsAndConditionsBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -573584367570393877L;

    private Integer termsAndConditionsId;

    private String descEng;

    private String descMyan;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    private String updatedBy;

    private boolean isUpdate;

    public Integer getTermsAndConditionsId() {
        return termsAndConditionsId;
    }

    public void setTermsAndConditionsId(Integer termsAndConditionsId) {
        this.termsAndConditionsId = termsAndConditionsId;
    }

    public String getDescEng() {
        return descEng;
    }

    public void setDescEng(String descEng) {
        this.descEng = descEng;
    }

    public String getDescMyan() {
        return descMyan;
    }

    public void setDescMyan(String descMyan) {
        this.descMyan = descMyan;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public TermsAndConditionsBean copyTermsAndConditionsBean(TermsAndConditionsBean termsAndConditionsBean) {
        this.termsAndConditionsId = termsAndConditionsBean.getTermsAndConditionsId();
        this.descMyan = termsAndConditionsBean.getDescMyan();
        this.descEng = termsAndConditionsBean.getDescEng();
        this.updatedBy = termsAndConditionsBean.getUpdatedBy();
        this.updatedTime = termsAndConditionsBean.getUpdatedTime();

        return this;
    }

}
