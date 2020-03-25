/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.custEditReqUpdateService;

 import mm.aeon.com.ass.base.dto.custEditRequestUpdate.CustEditRequestInfoUpdateReqDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
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

public class CustEditReqUpdateService extends AbstractService
        implements IService<CustEditReqUpdateServiceReqBean, CustEditReqUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public CustEditReqUpdateServiceResBean execute(CustEditReqUpdateServiceReqBean reqBean) {

        CustEditReqUpdateServiceResBean resBean = new CustEditReqUpdateServiceResBean();
 
        try {
              
                CustEditRequestInfoUpdateReqDto updateReqDto = new CustEditRequestInfoUpdateReqDto();

                updateReqDto.setCustEditReqId(reqBean.getCustEditReqId());
                updateReqDto.setCustomerId(reqBean.getCustomerId());
                updateReqDto.setName(reqBean.getName());
                updateReqDto.setDob(reqBean.getDob());
                updateReqDto.setNrc_no(reqBean.getNrc_no());
                updateReqDto.setPhone_no(reqBean.getPhone_no());
                updateReqDto.setStatus(reqBean.getStatus());
                updateReqDto.setUpdatedBy(CommonUtil.getLoginUserInfo().getUserId());
                updateReqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());

                this.getDaoServiceInvoker().execute(updateReqDto);

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
