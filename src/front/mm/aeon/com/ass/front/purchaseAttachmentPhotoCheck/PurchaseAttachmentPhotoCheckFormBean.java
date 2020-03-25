/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.purchaseAttachmentPhotoCheck;

import java.io.File;
import java.io.IOException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.springframework.util.StringUtils;

import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.FileHandler;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("purchaseAttachmentPhotoCheckFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class PurchaseAttachmentPhotoCheckFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 248502117014243161L;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private PurchaseAttachmentPhotoCheckLineBean lineBean;
    private String tempUploadOldFilePath;
    private String tempUploadNewFilePath;
    private PurchaseAttachmentPhotoCheckHeaderBean headerBean;
    private boolean rejectFlag;
    private Integer totalCount;

    @Begin(nested = true)
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.INSPECT_PHOTO);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        headerBean = new PurchaseAttachmentPhotoCheckHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        if (lineBean != null) {
            if (!StringUtils.isEmpty(lineBean.getOldFilePath())) {
                String uploadPath =
                        CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getDigitalApplicationImageFolder();
                File sourceFile = new File(uploadPath + lineBean.getOldFilePath());
                File destinationFile = new File(FileHandler.getSystemPath() + "/temp"
                        + CommonUtil.getDigitalApplicationImageFolder() + lineBean.getOldFilePath());

                try {

                    FileHandler.copyFile(sourceFile, destinationFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tempUploadOldFilePath =
                        "/temp" + CommonUtil.getDigitalApplicationImageFolder() + lineBean.getOldFilePath();
            }
            if (!StringUtils.isEmpty(lineBean.getNewFilePath())) {
                String uploadPath = CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getCheckingImageFolder();
                File sourceFile = new File(uploadPath + lineBean.getNewFilePath());
                File destinationFile = new File(FileHandler.getSystemPath() + "/temp"
                        + CommonUtil.getCheckingImageFolder() + lineBean.getNewFilePath());

                try {

                    FileHandler.copyFile(sourceFile, destinationFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tempUploadNewFilePath = "/temp" + CommonUtil.getCheckingImageFolder() + lineBean.getNewFilePath();
            }
        } else {
            tempUploadNewFilePath = null;
            tempUploadOldFilePath = null;
        }

        this.doReload = new Boolean(false);
        return LinkTarget.OK;

    }

    @Action
    public String rejectApprove() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }
        this.doReload = new Boolean(true);
        tempUploadNewFilePath = null;
        tempUploadOldFilePath = null;
        headerBean = new PurchaseAttachmentPhotoCheckHeaderBean();
        return LinkTarget.OK;

    }

    public String getTempUploadOldFilePath() {
        return tempUploadOldFilePath;
    }

    public void setTempUploadOldFilePath(String tempUploadOldFilePath) {
        this.tempUploadOldFilePath = tempUploadOldFilePath;
    }

    public String getTempUploadNewFilePath() {
        return tempUploadNewFilePath;
    }

    public void setTempUploadNewFilePath(String tempUploadNewFilePath) {
        this.tempUploadNewFilePath = tempUploadNewFilePath;
    }

    public PurchaseAttachmentPhotoCheckLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(PurchaseAttachmentPhotoCheckLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public boolean isRejectFlag() {
        return rejectFlag;
    }

    public void setRejectFlag(boolean rejectFlag) {
        this.rejectFlag = rejectFlag;
    }

    public PurchaseAttachmentPhotoCheckHeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(PurchaseAttachmentPhotoCheckHeaderBean headerBean) {
        this.headerBean = headerBean;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
