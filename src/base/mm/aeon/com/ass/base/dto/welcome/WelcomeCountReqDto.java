/**************************************************************************
 * $Date :10.9.2019* $
 * $Author : Ponnya$
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.welcome;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "Welcome")
public class WelcomeCountReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 504430290702570546L;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.COUNT;
    }

    private boolean allApplicationFlag;

    private boolean newApplicationFlag;

    private boolean indexApplicationFlag;

    private boolean uploadedApplicationFlag;

    private boolean cancelledApplicationFlag;

    private boolean documentFollowUpApplicationFlag;

    private boolean settlementIndexApplicationFlag;

    private boolean settlementUploadedApplicationFlag;

    public boolean isAllApplicationFlag() {
        return allApplicationFlag;
    }

    public void setAllApplicationFlag(boolean allApplicationFlag) {
        this.allApplicationFlag = allApplicationFlag;
    }

    public boolean isNewApplicationFlag() {
        return newApplicationFlag;
    }

    public void setNewApplicationFlag(boolean newApplicationFlag) {
        this.newApplicationFlag = newApplicationFlag;
    }

    public boolean isIndexApplicationFlag() {
        return indexApplicationFlag;
    }

    public void setIndexApplicationFlag(boolean indexApplicationFlag) {
        this.indexApplicationFlag = indexApplicationFlag;
    }

    public boolean isUploadedApplicationFlag() {
        return uploadedApplicationFlag;
    }

    public void setUploadedApplicationFlag(boolean uploadedApplicationFlag) {
        this.uploadedApplicationFlag = uploadedApplicationFlag;
    }

    public boolean isCancelledApplicationFlag() {
        return cancelledApplicationFlag;
    }

    public void setCancelledApplicationFlag(boolean cancelledApplicationFlag) {
        this.cancelledApplicationFlag = cancelledApplicationFlag;
    }

    public boolean isDocumentFollowUpApplicationFlag() {
        return documentFollowUpApplicationFlag;
    }

    public void setDocumentFollowUpApplicationFlag(boolean documentFollowUpApplicationFlag) {
        this.documentFollowUpApplicationFlag = documentFollowUpApplicationFlag;
    }

    public boolean isSettlementIndexApplicationFlag() {
        return settlementIndexApplicationFlag;
    }

    public void setSettlementIndexApplicationFlag(boolean settlementIndexApplicationFlag) {
        this.settlementIndexApplicationFlag = settlementIndexApplicationFlag;
    }

    public boolean isSettlementUploadedApplicationFlag() {
        return settlementUploadedApplicationFlag;
    }

    public void setSettlementUploadedApplicationFlag(boolean settlementUploadedApplicationFlag) {
        this.settlementUploadedApplicationFlag = settlementUploadedApplicationFlag;
    }

}
