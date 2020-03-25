/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.purchaseAttachmentPhotoCheckService;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoCheckApproveReject.PurchaseAttachmentPhotoCheckApproveRejectReqDto;
import mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoSelectForUpdate.PurchaseAttachmentPhotoSelectForUpdateReqDto;
import mm.aeon.com.ass.base.dto.purchaseAttachmentPhotoSelectForUpdate.PurchaseAttachmentPhotoSelectForUpdateResDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;

public class PurchaseAttachmentPhotoCheckService extends AbstractService
        implements IService<PurchaseAttachmentPhotoCheckServiceReqBean, PurchaseAttachmentPhotoCheckServiceResBean> {

    @Override
    public PurchaseAttachmentPhotoCheckServiceResBean execute(PurchaseAttachmentPhotoCheckServiceReqBean reqBean) {
        // TODO Auto-generated method stub
        PurchaseAttachmentPhotoSelectForUpdateReqDto selectForUpdateReqDto =
                new PurchaseAttachmentPhotoSelectForUpdateReqDto();
        selectForUpdateReqDto.setCheckingAttachmentId(reqBean.getCheckingAttachmentId());

        PurchaseAttachmentPhotoCheckServiceResBean resBean = new PurchaseAttachmentPhotoCheckServiceResBean();
        try {
            PurchaseAttachmentPhotoSelectForUpdateResDto selectForUpdateResDto =
                    (PurchaseAttachmentPhotoSelectForUpdateResDto) this.getDaoServiceInvoker()
                            .execute(selectForUpdateReqDto);
            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdatedTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                PurchaseAttachmentPhotoCheckApproveRejectReqDto reqDto =
                        new PurchaseAttachmentPhotoCheckApproveRejectReqDto();
                reqDto.setStatus(reqBean.getStatus());
                reqDto.setRejectComment(reqBean.getRejectComment());
                reqDto.setCheckingAttachmentId(reqBean.getCheckingAttachmentId());
                reqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
                this.getDaoServiceInvoker().execute(reqDto);
                resBean.setServiceStatus(ServiceStatusCode.OK);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return resBean;
    }

}
