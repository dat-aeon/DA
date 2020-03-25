/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.dto.saleClaimApplicationListSearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class SaleClaimApplicationPurchaseAttachmentSearchResDto implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer attachmentId;

    private String filePath;

    private Integer fileType;

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

}
