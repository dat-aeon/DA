/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.operatorManagement;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

public class OperatorManagementHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -573584367570393877L;

    private Integer userId;

    private Integer delFlag;

    private String userName;

    private String userLoginId;

    private String password;

    private String confirmPassword;

    private Timestamp updatedTime;

    private Boolean resetPassword;

    private boolean forUpdate;

    // new item
    private ArrayList<SelectItem> roleList = new ArrayList<SelectItem>();

    private ArrayList<SelectItem> departmentSelectItemList;

    private ArrayList<SelectItem> groupSelectItemList;

    private Integer selectedDepartment;

    private Integer groupId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public boolean getForUpdate() {
        return forUpdate;
    }

    public void setForUpdate(boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public ArrayList<SelectItem> getRoleList() {
        return roleList;
    }

    public void setRoleList(ArrayList<SelectItem> roleList) {
        this.roleList = roleList;
    }

    public Integer getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Integer selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public ArrayList<SelectItem> getDepartmentSelectItemList() {
        return departmentSelectItemList;
    }

    public void setDepartmentSelectItemList(ArrayList<SelectItem> departmentSelectItemList) {
        this.departmentSelectItemList = departmentSelectItemList;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public ArrayList<SelectItem> getGroupSelectItemList() {
        return groupSelectItemList;
    }

    public void setGroupSelectItemList(ArrayList<SelectItem> groupSelectItemList) {
        this.groupSelectItemList = groupSelectItemList;
    }

    public Boolean getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(Boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public OperatorManagementHeaderBean copyOperatorManagementHeaderBean(
            OperatorManagementHeaderBean operatorManagementHeaderBean) {

        this.userLoginId = operatorManagementHeaderBean.getUserLoginId();
        this.userId = operatorManagementHeaderBean.getUserId();
        this.userName = operatorManagementHeaderBean.getUserName();
        this.updatedTime = operatorManagementHeaderBean.getUpdatedTime();
        this.selectedDepartment = operatorManagementHeaderBean.getSelectedDepartment();
        this.groupId = operatorManagementHeaderBean.getGroupId();
        this.forUpdate = operatorManagementHeaderBean.getForUpdate();

        return this;
    }

}
