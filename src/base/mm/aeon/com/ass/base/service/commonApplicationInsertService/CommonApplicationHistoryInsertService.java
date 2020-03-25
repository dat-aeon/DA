/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.commonApplicationInsertService;

import mm.aeon.com.ass.base.dto.commonApplicationUpdate.CommonApplicationHistoryInsertReqDto;
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

public class CommonApplicationHistoryInsertService extends AbstractService implements
        IService<CommonApplicationHistoryInsertServiceReqBean, CommonApplicationHistoryInsertServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public CommonApplicationHistoryInsertServiceResBean execute(CommonApplicationHistoryInsertServiceReqBean reqBean) {

        CommonApplicationHistoryInsertServiceResBean resBean = new CommonApplicationHistoryInsertServiceResBean();

        try {

            CommonApplicationHistoryInsertReqDto insertReqDto = new CommonApplicationHistoryInsertReqDto();
            insertReqDto.setDaApplicationInfoId(reqBean.getDaApplicationInfoId());
            insertReqDto.setApplicationNo(reqBean.getApplicationNo());
            insertReqDto.setDaApplicationTypeId(reqBean.getDaApplicationTypeId());
            insertReqDto.setAppliedDate(reqBean.getAppliedDate());
            insertReqDto.setName(reqBean.getName());
            insertReqDto.setDob(reqBean.getDob());
            insertReqDto.setNrcNo(reqBean.getNrcNo());
            insertReqDto.setFatherName(reqBean.getFatherName());
            insertReqDto.setNationality(reqBean.getNationality());
            insertReqDto.setNationalityOther(reqBean.getNationalityOther());
            insertReqDto.setGender(reqBean.getGender());
            insertReqDto.setMaritalStatus(reqBean.getMaritalStatus());
            insertReqDto.setCurrentAddress(reqBean.getCurrentAddress());
            insertReqDto.setCurrentAddressBuildingNo(reqBean.getCurrentAddressBuildingNo());
            insertReqDto.setCurrentAddressRoomNo(reqBean.getCurrentAddressRoomNo());
            insertReqDto.setCurrentAddressFloor(reqBean.getCurrentAddressFloor());
            insertReqDto.setCurrentAddressStreet(reqBean.getCurrentAddressStreet());
            insertReqDto.setCurrentAddressQtr(reqBean.getCurrentAddressQtr());
            insertReqDto.setCurrentAddressTownship(reqBean.getCurrentAddressTownship());
            insertReqDto.setCurrentAddressCity(reqBean.getCurrentAddressCity());
            insertReqDto.setPermanentAddress(reqBean.getPermanentAddress());
            insertReqDto.setPermanentAddressBuildingNo(reqBean.getPermanentAddressBuildingNo());
            insertReqDto.setPermanentAddressRoomNo(reqBean.getPermanentAddressRoomNo());
            insertReqDto.setPermanentAddressFloor(reqBean.getPermanentAddressFloor());
            insertReqDto.setPermanentAddressStreet(reqBean.getPermanentAddressStreet());
            insertReqDto.setPermanentAddressQtr(reqBean.getPermanentAddressQtr());
            insertReqDto.setPermanentAddressTownship(reqBean.getPermanentAddressTownship());
            insertReqDto.setPermanentAddressCity(reqBean.getPermanentAddressCity());
            insertReqDto.setTypeOfResidence(reqBean.getTypeOfResidence());
            insertReqDto.setTypeOfResidenceOther(reqBean.getTypeOfResidenceOther());
            insertReqDto.setLivingWith(reqBean.getLivingWith());
            insertReqDto.setLivingWithOther(reqBean.getLivingWithOther());
            insertReqDto.setYearOfStayMonth(reqBean.getYearOfStayMonth());
            insertReqDto.setYearOfStayYear(reqBean.getYearOfStayYear());
            insertReqDto.setMobileNo(reqBean.getMobileNo());
            insertReqDto.setResidentTelNo(reqBean.getResidentTelNo());
            insertReqDto.setOtherPhoneNo(reqBean.getOtherPhoneNo());
            insertReqDto.setEmail(reqBean.getEmail());
            insertReqDto.setCustomerId(reqBean.getCustomerId());
            insertReqDto.setStatus(reqBean.getStatus());
            insertReqDto.setDaInterestInfoId(reqBean.getDaInterestInfoId());
            insertReqDto.setDaCompulsoryInfoId(reqBean.getDaCompulsoryInfoId());
            insertReqDto.setDaLoanTypeId(reqBean.getDaLoanTypeId());
            insertReqDto.setFinanceAmount(reqBean.getFinanceAmount());
            insertReqDto.setFinanceTerm(reqBean.getFinanceTerm());
            insertReqDto.setDaProductTypeId(reqBean.getDaProductTypeId());
            insertReqDto.setProductDescription(reqBean.getProductDescription());
            insertReqDto.setChannelType(reqBean.getChannelType());
            insertReqDto.setCreatedTime(reqBean.getCreatedTime());
            insertReqDto.setCreatedBy(reqBean.getCreatedBy());
            insertReqDto.setUpdatedBy(reqBean.getUpdatedBy());
            insertReqDto.setUpdatedTime(reqBean.getUpdatedTime());
            insertReqDto.setDelFlag(reqBean.getDelFlag());
            insertReqDto.setSettlementPendingComment(reqBean.getSettlementPendingComment());
            insertReqDto.setHighestEducationTypeId(reqBean.getHighestEducationTypeId());
            insertReqDto.setApprovedFinanceAmount(reqBean.getApprovedFinanceAmount());
            insertReqDto.setApprovedFinanceTerm(reqBean.getApprovedFinanceTerm());
            insertReqDto.setModifyFinanceAmount(reqBean.getModifyFinanceAmount());
            insertReqDto.setModifyFinanceTerm(reqBean.getModifyFinanceTerm());
            insertReqDto.setModifyComment(reqBean.getModifyComment());
            insertReqDto.setModifyStatus(reqBean.getModifyStatus());

            this.getDaoServiceInvoker().execute(insertReqDto);

            reqBean.setDaApplicationInfoHistoryId(insertReqDto.getDaApplicationInfoHistoryId());
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
