/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.termsAndConditionsInfoUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class TermsAndConditionsInfoUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;

    private Integer termsAndConditionsId;

    private String descEng;

    private String descMyan;

    private String updatedBy;

    private Timestamp updatedTime;

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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String getServiceId() {
        return "TNCINFOSU";
    }
}
