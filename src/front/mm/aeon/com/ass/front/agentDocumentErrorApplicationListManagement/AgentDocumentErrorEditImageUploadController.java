/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentDocumentErrorApplicationListManagement;

import java.io.File;
import java.io.IOException;

import org.primefaces.model.UploadedFile;

import mm.aeon.com.ass.front.agentDocumentErrorApplicationList.AgentDocumentErrorApplicationListFormBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.FileHandler;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentDocumentErrorEditImageUploadController extends AbstractController implements
        IControllerAccessor<AgentDocumentErrorApplicationListFormBean, AgentDocumentErrorApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public AgentDocumentErrorApplicationListFormBean process(AgentDocumentErrorApplicationListFormBean formBean) {
        MessageBean msgBean;

        formBean.getMessageContainer().clearAllMessages(true);
        applicationLogger.log("Coupon Image Upload Process Stared.", LogLevel.INFO);

        try {
            UploadedFile editImageUploadFile = formBean.getErrorEditImageBean().getEditImage();
            String[] str = new String[2];
            str = formBean.getLineBean().getCreatedBy().split(VCSCommon.REXCOMMA);
            Integer customerId = Integer.parseInt(str[0]);
            Integer purchaseId = formBean.getPurchaseId();

            String fileName = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".jpeg";
            String temp = "/temp";
            String editImageFilePath = CommonUtil.getPurchaseInfoUploadImageFolder();
            String tempEditImageFilePath = temp + editImageFilePath + customerId + "/" + purchaseId + "/" + fileName;

            applicationLogger.log("Image file path " + tempEditImageFilePath, LogLevel.INFO);

            applicationLogger.log("Edit Image Upload Process Started." + tempEditImageFilePath, LogLevel.INFO);

            applicationLogger.log("Created File." + tempEditImageFilePath, LogLevel.INFO);

            FileHandler.createFile(new File(FileHandler.getSystemPath() + tempEditImageFilePath),
                    editImageUploadFile.getContents());

            formBean.setOldUploadImageFilePath(formBean.getErrorApplicationHeaderBean().getUploadedImageFilePath());
            formBean.setTempUploadImageFilePath(tempEditImageFilePath);
            formBean.setUploadImageFileName(fileName);
            formBean.getErrorEditImageBean().setFilePath(fileName);
            formBean.getErrorApplicationHeaderBean().setUploadedImageFilePath(fileName);

            msgBean = new MessageBean(MessageId.MI0012, "Edit Image");
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Edit Image Upload process finished.", LogLevel.INFO);

        } catch (IOException e) {
            e.printStackTrace();
            applicationLogger.log("ERROR------" + e.getMessage(), LogLevel.INFO);
        }
        return formBean;
    }

}
