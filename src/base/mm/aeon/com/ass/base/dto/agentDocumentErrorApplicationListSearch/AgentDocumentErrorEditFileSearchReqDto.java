/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgentDocumentErrorApplicationHistoryInfo")
public class AgentDocumentErrorEditFileSearchReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 622234158884618063L;

    private Boolean isErrorFile;

    private Boolean editFlag;

    private Boolean delFlag;

    private Integer purchaseId;

    private Boolean originalFlag;

    public Boolean getIsErrorFile() {
        return isErrorFile;
    }

    public void setIsErrorFile(Boolean isErrorFile) {
        this.isErrorFile = isErrorFile;
    }

    public Boolean getOriginalFlag() {
        return originalFlag;
    }

    public void setOriginalFlag(Boolean originalFlag) {
        this.originalFlag = originalFlag;
    }

    public Boolean getEditFlag() {
        return editFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setEditFlag(Boolean editFlag) {
        this.editFlag = editFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_LIST;
    }
}
