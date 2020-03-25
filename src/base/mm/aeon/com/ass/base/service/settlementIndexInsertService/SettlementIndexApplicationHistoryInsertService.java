/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.settlementIndexInsertService;

import mm.aeon.com.ass.base.dto.SettlementIndexApplicationUpdate.SettlementIndexApplicationHistoryInsertReqDto;
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

public class SettlementIndexApplicationHistoryInsertService  extends AbstractService
implements IService<SettlementIndexApplicationHistoryInsertServiceReqBean, SettlementIndexApplicationHistoryInsertServiceResBean> {

private ASSLogger logger = new ASSLogger();

@Override
public SettlementIndexApplicationHistoryInsertServiceResBean execute(SettlementIndexApplicationHistoryInsertServiceReqBean reqBean) {

SettlementIndexApplicationHistoryInsertServiceResBean resBean = new SettlementIndexApplicationHistoryInsertServiceResBean();
 
try {
   
        SettlementIndexApplicationHistoryInsertReqDto insertReqDto=new SettlementIndexApplicationHistoryInsertReqDto();
        insertReqDto.setDa_application_info_id(reqBean.getDa_application_info_id());
        insertReqDto.setApplication_no(reqBean.getApplication_no());
        insertReqDto.setDa_application_type_id(reqBean.getDa_application_type_id());
        insertReqDto.setApplied_date(reqBean.getApplied_date());
        insertReqDto.setName(reqBean.getName());
        insertReqDto.setDob(reqBean.getDob());
        insertReqDto.setNrc_no(reqBean.getNrc_no());
        insertReqDto.setFather_name(reqBean.getFather_name());
        insertReqDto.setNationality(reqBean.getNationality());
        insertReqDto.setNationality_other(reqBean.getNationality_other());
        insertReqDto.setGender(reqBean.getGender());
        insertReqDto.setMarital_status(reqBean.getMarital_status());
        insertReqDto.setCurrent_address(reqBean.getCurrent_address());
        insertReqDto.setPermanent_address(reqBean.getPermanent_address());
        insertReqDto.setType_of_residence(reqBean.getType_of_residence());
        insertReqDto.setType_of_residence_other(reqBean.getType_of_residence_other());
        insertReqDto.setLiving_with(reqBean.getLiving_with());
        insertReqDto.setLiving_with_other(reqBean.getLiving_with_other());
        insertReqDto.setYear_of_stay_month(reqBean.getYear_of_stay_month());
        insertReqDto.setYear_of_stay_year(reqBean.getYear_of_stay_year());
        insertReqDto.setMobile_no(reqBean.getMobile_no());
        insertReqDto.setResident_tel_no(reqBean.getResident_tel_no());
        insertReqDto.setOther_phone_no(reqBean.getOther_phone_no());
        insertReqDto.setEmail(reqBean.getEmail());
        insertReqDto.setCustomer_id(reqBean.getCustomer_id());
        insertReqDto.setStatus(reqBean.getStatus());
        insertReqDto.setDa_interest_info_id(reqBean.getDa_interest_info_id());
        insertReqDto.setDa_compulsory_info_id(reqBean.getDa_compulsory_info_id());
        insertReqDto.setDa_loan_type_id(reqBean.getDa_loan_type_id());
        insertReqDto.setFinance_amount(reqBean.getFinance_amount());
        insertReqDto.setFinance_term(reqBean.getFinance_term());
        insertReqDto.setDa_product_type_id(reqBean.getDa_product_type_id());
        insertReqDto.setProduct_description(reqBean.getProduct_description());
        insertReqDto.setChannel_type(reqBean.getChannel_type());
        insertReqDto.setCreated_time(reqBean.getCreated_time());
        insertReqDto.setCreated_by(reqBean.getCreated_by());
        insertReqDto.setUpdated_by(reqBean.getUpdated_by());
        insertReqDto.setUpdated_time(reqBean.getUpdated_time());
        insertReqDto.setDel_flag(reqBean.getDel_flag());
        insertReqDto.setSettlement_pending_comment(reqBean.getSettlement_pending_comment());
        
        this.getDaoServiceInvoker().execute(insertReqDto);
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
