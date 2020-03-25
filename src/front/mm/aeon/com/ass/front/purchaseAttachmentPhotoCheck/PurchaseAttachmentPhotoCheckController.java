/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.purchaseAttachmentPhotoCheck;

import mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoCheck.PurchaseAttachmentPhotoCheckReqDto;
import mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoCheck.PurchaseAttachmentPhotoCheckResDto;
import mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoCheck.PurchaseAttachmentPhotoCountReqDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.LogLevel;

public class PurchaseAttachmentPhotoCheckController extends AbstractController
        implements IControllerAccessor<PurchaseAttachmentPhotoCheckFormBean, PurchaseAttachmentPhotoCheckFormBean> {
    private ASSLogger logger = new ASSLogger();

    @Override
    public PurchaseAttachmentPhotoCheckFormBean process(PurchaseAttachmentPhotoCheckFormBean formBean) {
        // TODO Auto-generated method stub
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        PurchaseAttachmentPhotoCheckLineBean lineBean = new PurchaseAttachmentPhotoCheckLineBean();
        PurchaseAttachmentPhotoCheckReqDto reqDto = new PurchaseAttachmentPhotoCheckReqDto();

        try {
            PurchaseAttachmentPhotoCheckResDto resPhotoCheck =
                    (PurchaseAttachmentPhotoCheckResDto) CommonUtil.getDaoServiceInvoker().execute(reqDto);
            if (resPhotoCheck != null) {
                lineBean.setCheckingAttachmentId(resPhotoCheck.getCheckingAttachmentId());
                lineBean.setPurchaseInfoId(resPhotoCheck.getPurchaseInfoId());
                lineBean.setAgreementNo(resPhotoCheck.getAgreementNo());
                lineBean.setOldFilePath(resPhotoCheck.getOldFilePath());
                lineBean.setNewFilePath(resPhotoCheck.getNewFilePath());
                lineBean.setFileType(resPhotoCheck.getFileType());
                lineBean.setStatus(resPhotoCheck.getStatus());
                lineBean.setCreatedTime(resPhotoCheck.getCreatedTime());
                lineBean.setUpdatedTime(resPhotoCheck.getUpdatedTime());
                formBean.setLineBean(lineBean);
            } else {
                formBean.setLineBean(null);
            }

            PurchaseAttachmentPhotoCountReqDto countReqDto = new PurchaseAttachmentPhotoCountReqDto();

            Integer totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);

            formBean.setTotalCount(totalCount);

        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
