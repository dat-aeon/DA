/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.itemLabelManagement;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItemLabelManagementHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;

    private Integer itemLabelId;

    private String itemLabelCode;

    private String itemLabelEng;

    private String itemLabelMym;

    private String description;

    private String category;

    private Timestamp updatedTime;

    private boolean isUpdate;

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

    public String getItemLabelEng() {
        return itemLabelEng;
    }

    public void setItemLabelEng(String itemLabelEng) {
        this.itemLabelEng = itemLabelEng;
    }

    public String getItemLabelMym() {
        return itemLabelMym;
    }

    public void setItemLabelMym(String itemLabelMym) {
        this.itemLabelMym = itemLabelMym;
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

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public ItemLabelManagementHeaderBean copyItemLabelManagementHeaderBean(
            ItemLabelManagementHeaderBean itemLabelManagementHeaderBean) {

        this.itemLabelId = itemLabelManagementHeaderBean.getItemLabelId();
        this.itemLabelCode = itemLabelManagementHeaderBean.getItemLabelCode();
        this.itemLabelEng = itemLabelManagementHeaderBean.getItemLabelEng();
        this.itemLabelMym = itemLabelManagementHeaderBean.getItemLabelMym();
        this.description = itemLabelManagementHeaderBean.getDescription();
        this.category = itemLabelManagementHeaderBean.getCategory();
        this.updatedTime = itemLabelManagementHeaderBean.getUpdatedTime();

        return this;
    }

}
