/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentSaleClaimFinishedReport;

import java.io.Serializable;
import java.util.List;

import mm.aeon.com.ass.base.dto.agentSaleClaimFinishedReportSearch.AgentSaleClaimFinishedAppInfo;

public class AgentSaleClaimFinishedReportLineBean implements Serializable {

    /**
     * 
     */
    private Integer agentId;
    private String agentName;
    private String purchaseLocation;
    private Integer outletId;
    private String outletName;
    private Integer applicationCount;
    private List<AgentSaleClaimFinishedAppInfo> agentSaleClaimFinishedAppInfoList;
    private Double totalFinanceAmount;
    private Double totalProcessingFeeAmount;
    private Double totalCompulsorySaving;
    private Double totalSettlementAmount;

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

    public void setTotalFinanceAmount(Double totalFinanceAmount) {
        this.totalFinanceAmount = totalFinanceAmount;
    }

    public void setTotalProcessingFeeAmount(Double totalProcessingFeeAmount) {
        this.totalProcessingFeeAmount = totalProcessingFeeAmount;
    }

    public void setTotalCompulsorySaving(Double totalCompulsorySaving) {
        this.totalCompulsorySaving = totalCompulsorySaving;
    }

    public List<AgentSaleClaimFinishedAppInfo> getAgentSaleClaimFinishedAppInfoList() {
        return agentSaleClaimFinishedAppInfoList;
    }

    public void setAgentSaleClaimFinishedAppInfoList(
            List<AgentSaleClaimFinishedAppInfo> agentSaleClaimFinishedAppInfoList) {
        this.agentSaleClaimFinishedAppInfoList = agentSaleClaimFinishedAppInfoList;
    }

    public Double getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

    public void setTotalSettlementAmount(Double totalSettlementAmount) {
        this.totalSettlementAmount = totalSettlementAmount;
    }
}
