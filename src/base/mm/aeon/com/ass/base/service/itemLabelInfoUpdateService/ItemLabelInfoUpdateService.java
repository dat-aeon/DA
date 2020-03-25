/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.itemLabelInfoUpdateService;

import mm.aeon.com.ass.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ass.base.dto.itemLabelSelectForUpdate.ItemLabelSelectForUpdateReqDto;
import mm.aeon.com.ass.base.dto.itemLabelSelectForUpdate.ItemLabelSelectForUpdateResDto;
import mm.aeon.com.ass.base.dto.itemLabelUpdate.ItemLabelUpdateReqDto;
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

public class ItemLabelInfoUpdateService extends AbstractService
        implements IService<ItemLabelInfoUpdateServiceReqBean, ItemLabelInfoUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public ItemLabelInfoUpdateServiceResBean execute(ItemLabelInfoUpdateServiceReqBean reqBean) {

        ItemLabelInfoUpdateServiceResBean resBean = new ItemLabelInfoUpdateServiceResBean();

        ItemLabelSelectForUpdateReqDto selectForUpdateReqDto = new ItemLabelSelectForUpdateReqDto();
        selectForUpdateReqDto.setItemLabelId(reqBean.getItemLabelId());

        try {
            ItemLabelSelectForUpdateResDto selectForUpdateResDto =
                    (ItemLabelSelectForUpdateResDto) this.getDaoServiceInvoker().execute(selectForUpdateReqDto);

            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdatedTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                ItemLabelUpdateReqDto updateReqDto = new ItemLabelUpdateReqDto();

                updateReqDto.setItemLabelId(reqBean.getItemLabelId());
                updateReqDto.setItemLabelCode(reqBean.getItemLabelCode());
                updateReqDto.setItemLabelEng(reqBean.getItemLabelEng());
                updateReqDto.setItemLabelMym(reqBean.getItemLabelMym());
                updateReqDto.setDescription(reqBean.getDescription());
                updateReqDto.setCategory(reqBean.getCategory());
                updateReqDto.setUpdatedBy(CommonUtil.getLoginUserInfo().getUserId());
                updateReqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
                updateReqDto.setDelFlag(false);

                this.getDaoServiceInvoker().execute(updateReqDto);

                resBean.setServiceStatus(ServiceStatusCode.OK);
            }

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
