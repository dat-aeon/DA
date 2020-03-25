/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentAndProductInfoUploadedReportSearch;

import java.util.List;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentAndProductInfoUploadedReportSearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5578964690693393044L;

    private Integer agentId;
    private String agentName;
    private String purchaseLocation;
    private Integer outletId;
    private String outletName;
    private Integer applicationCount;
    private List<AgentAndProductAppInfo> agentAndProductAppInfoList;
    private Double totalFinanceAmount;
    private Double totalProcessingFeeAmount;
    private Double totalCompulsorySaving;

    public Integer getAgentId() {
        return agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getPurchaseLocation() {
        return purchaseLocation;
    }

    public Integer getOutletId() {
        return outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public Integer getApplicationCount() {
        return applicationCount;
    }

    public List<AgentAndProductAppInfo> getAgentAndProductAppInfoList() {
        return agentAndProductAppInfoList;
    }

    public Double getTotalFinanceAmount() {
        return totalFinanceAmount;
    }

    public Double getTotalProcessingFeeAmount() {
        return totalProcessingFeeAmount;
    }

    public Double getTotalCompulsorySaving() {
        return totalCompulsorySaving;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setPurchaseLocation(String purchaseLocation) {
        this.purchaseLocation = purchaseLocation;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public void setApplicationCount(Integer applicationCount) {
        this.applicationCount = applicationCount;
    }

    public void setAgentAndProductAppInfoList(List<AgentAndProductAppInfo> agentAndProductAppInfoList) {
        this.agentAndProductAppInfoList = agentAndProductAppInfoList;
    }

    public void setTotalFinanceAmount(Double totalFinanceAmount) {
        this.totalFinanceAmount = totalFinanceAmount;
    }

    public void setTotalProcessingFeeAmount(Double totalProcessingFeeAmount) {
        this.totalProcessingFeeAmount = totalProcessingFeeAmount;
    }

    public void setTotalCompulsorySaving(Double totalCompulsorySaving) {
        this.totalCompulsorySaving = totalCompulsorySaving;
    }

}
