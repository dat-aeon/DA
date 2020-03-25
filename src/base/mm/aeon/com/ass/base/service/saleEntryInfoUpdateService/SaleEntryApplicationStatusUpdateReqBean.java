/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.saleEntryInfoUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class SaleEntryApplicationStatusUpdateReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = 7103688745860947296L;

    private Integer da_application_info_id;
    private Boolean saleEntryCheckFlag;
    private Timestamp updated_time;
    private String updated_by;

    public Integer getDa_application_info_id() {
        return da_application_info_id;
    }

    public void setDa_application_info_id(Integer da_application_info_id) {
        this.da_application_info_id = da_application_info_id;
    }

    public Boolean getSaleEntryCheckFlag() {
        return saleEntryCheckFlag;
    }

    public void setSaleEntryCheckFlag(Boolean saleEntryCheckFlag) {
        this.saleEntryCheckFlag = saleEntryCheckFlag;
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

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "SALEENTRYINFOSU";
    }
}
