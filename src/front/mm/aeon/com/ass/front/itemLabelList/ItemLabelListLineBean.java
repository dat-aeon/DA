/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.itemLabelList;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItemLabelListLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1734777366621170975L;

    private Integer itemLabelId;

    private String itemLabelCode;

    private String itemLabelMym;

    private String itemLabelEng;

    private String description;

    private String category;

    private Timestamp updatedTime;

    private Timestamp createdTime;

    private String createdBy;

    private String updatedBy;

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

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
