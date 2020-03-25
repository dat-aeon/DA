/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.saleClaimAttachmentFlagUpdateService;

import mm.aeon.com.ass.base.dto.saleClaimApplicationUpdate.SaleClaimApplicationAttachmentFlagUpdateReqDto;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PhysicalRecordLockedException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class SaleClaimAttachmentFlagUpdateService extends AbstractService
        implements IService<SaleClaimAttachmentUpdateServiceReqBean, SaleClaimAttachmentUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public SaleClaimAttachmentUpdateServiceResBean execute(SaleClaimAttachmentUpdateServiceReqBean reqBean) {
        SaleClaimAttachmentUpdateServiceResBean resBean = new SaleClaimAttachmentUpdateServiceResBean();

        try {
            SaleClaimApplicationAttachmentFlagUpdateReqDto reqDto =
                    new SaleClaimApplicationAttachmentFlagUpdateReqDto();
            reqDto.setAttachmentId(reqBean.getAttachmentId());
            reqDto.setPurchaseId(reqBean.getPurchaseId());
            reqDto.setEditFlag(reqBean.getEditFlag());
            reqDto.setFlagType(reqBean.getFlagType());
            reqDto.setOriginalFlag(reqBean.getOriginalFlag());
            reqDto.setUpdatedTime(reqBean.getUpdatedTime());
            reqDto.setUpdatedBy(reqBean.getUpdatedBy());
            this.getDaoServiceInvoker().execute(reqDto);
            resBean.setServiceStatus(ServiceStatusCode.OK);
        } catch (PrestoDBException e) {
            if (e instanceof RecordDuplicatedException) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_DUPLICATED_ERROR);
            } else if (e instanceof PhysicalRecordLockedException) {
                resBean.setServiceStatus(ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR);
            } else if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                resBean.setServiceStatus(ServiceStatusCode.SQL_ERROR);
            } else {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return resBean;
    }

}
