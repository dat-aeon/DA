/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.applicationUpdateStatus;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "ApplicationInfoStatus")
public class ApplicationUpdateStatusUpdateReqBean implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -1258829070582799592L;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.UPDATE;
    }

    private String applicationNo;
    private Boolean fullyPaidFlag;
    private Integer status;
    private Integer applicationStatus;
    private Double financialAmount;
    private Integer financialTerm;
    private Boolean statusChangedReadFlag;

    public Boolean getStatusChangedReadFlag() {
        return statusChangedReadFlag;
    }

    public void setStatusChangedReadFlag(Boolean statusChangedReadFlag) {
        this.statusChangedReadFlag = statusChangedReadFlag;
    }

    public Integer getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Boolean getFullyPaidFlag() {
        return fullyPaidFlag;
    }

    public void setFullyPaidFlag(Boolean fullyPaidFlag) {
        this.fullyPaidFlag = fullyPaidFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getFinancialAmount() {
        return financialAmount;
    }

    public Integer getFinancialTerm() {
        return financialTerm;
    }

    public void setFinancialAmount(Double financialAmount) {
        this.financialAmount = financialAmount;
    }

    public void setFinancialTerm(Integer financialTerm) {
        this.financialTerm = financialTerm;
    }

}
