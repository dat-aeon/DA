/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.itemLabelInfoUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class ItemLabelInfoUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;

    private Integer itemLabelId;

    private String itemLabelCode;

    private String itemLabelMym;

    private String itemLabelEng;

    private String description;

    private String category;

    private String updatedBy;

    private Timestamp updatedTime;

    @Override
    public String getServiceId() {
        return "ITEMINFOSU";
    }

    public Integer getItemLabelId() {
        return itemLabelId;
    }

    public void setItemLabelId(Integer itemLabelId) {
        this.itemLabelId = itemLabelId;
    }

    public String getItemLabelCode() {
        return itemLabelCode;
    }

    public void setItemLabelCode(String itemLabelCode) {
        this.itemLabelCode = itemLabelCode;
    }

    public String getItemLabelMym() {
        return itemLabelMym;
    }

    public void setItemLabelMym(String itemLabelMym) {
        this.itemLabelMym = itemLabelMym;
    }

    public String getItemLabelEng() {
        return itemLabelEng;
    }

    public void setItemLabelEng(String itemLabelEng) {
        this.itemLabelEng = itemLabelEng;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

}
