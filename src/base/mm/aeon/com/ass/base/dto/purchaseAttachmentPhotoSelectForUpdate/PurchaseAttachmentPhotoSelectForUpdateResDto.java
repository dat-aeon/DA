/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoSelectForUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class PurchaseAttachmentPhotoSelectForUpdateResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2984883292668226332L;

    private Integer checkingAttachmentId;

    private Timestamp updatedTime;

    public Integer getCheckingAttachmentId() {
        return checkingAttachmentId;
    }

    public void setCheckingAttachmentId(Integer checkingAttachmentId) {
        this.checkingAttachmentId = checkingAttachmentId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
