/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Swe Hsu Mon $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.judgementStatusUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import mm.aeon.com.ass.base.dto.judgementResultStatusUpdate.JudgementStatusResultUpdateReqDto;
import mm.aeon.com.ass.base.dto.judgementStatusUpdate.JudgementStatusUpdateReqDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.judgementStatusUpload.CustAgreementList;
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

public class JudgementStatusUpdateService extends AbstractService
        implements IService<JudgementStatusUpdateServiceReqBean, JudgementStatusUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Autowired
    private MessageSource messageSource;

    @Override
    public JudgementStatusUpdateServiceResBean execute(JudgementStatusUpdateServiceReqBean reqBean) {

        JudgementStatusUpdateServiceResBean resBean = new JudgementStatusUpdateServiceResBean();
        try {

            JudgementStatusUpdateReqDto updateReqDto = new JudgementStatusUpdateReqDto();

            updateReqDto.setName(reqBean.getName());
            updateReqDto.setMemberCardStatus(reqBean.getMemberCardStatus());
            updateReqDto.setAge(reqBean.getAge());
            updateReqDto.setCompanyName(reqBean.getCompanyName());
            updateReqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
            updateReqDto.setCustomerId(reqBean.getCustomerId());
            updateReqDto.setCustomerNo(reqBean.getCustomerNo());
            updateReqDto.setDelFlag(reqBean.getDelFlag());
            updateReqDto.setDob(reqBean.getDob());
            updateReqDto.setGender(reqBean.getGender());
            updateReqDto.setMemberCardId(reqBean.getMemberCardId());
            updateReqDto.setMemberCardStatus(reqBean.getMemberCardStatus());
            updateReqDto.setMemberFlag(reqBean.getMemberFlag());
            updateReqDto.setNrcNo(reqBean.getNrcNo());
            updateReqDto.setPhoneNo(reqBean.getPhoneNo());
            updateReqDto.setSalary(reqBean.getSalary());
            updateReqDto.setTownship(reqBean.getTownship());
            updateReqDto.setStatus(reqBean.getStatus());
            updateReqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
            updateReqDto.setCustAgreementListList(reqBean.getCustAgreementListList());

            this.getDaoServiceInvoker().execute(updateReqDto);

            if (updateReqDto.getCustomerId() != null) {
                JudgementStatusResultUpdateReqDto applicationReqDto = new JudgementStatusResultUpdateReqDto();

                for (CustAgreementList custAgreementList : updateReqDto.getCustAgreementListList()) {
                    applicationReqDto.setImportCustomerId(updateReqDto.getCustomerId());
                    applicationReqDto.setAgreementNo(custAgreementList.getAgreementNo());
                    applicationReqDto.setQrShow(custAgreementList.getQrShow());
                    applicationReqDto.setFinancialTerm(custAgreementList.getFinancialTerm());
                    applicationReqDto.setFinancialAmt(custAgreementList.getFinancialAmt());
                    applicationReqDto.setFinancialStatus(custAgreementList.getFinancialStatus());
                    applicationReqDto.setApplicationNo(custAgreementList.getApplicationNo());
                    applicationReqDto.setJudgementResult(custAgreementList.getJudgementResult());
                    applicationReqDto.setJudgementDate(custAgreementList.getJudgementDate());
                    applicationReqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
                    applicationReqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());

                    this.getDaoServiceInvoker().execute(applicationReqDto);

                }

                resBean.setServiceStatus(ServiceStatusCode.OK);
            }

        } catch (PrestoDBException e) {
            if (e instanceof RecordDuplicatedException) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_DUPLICATED_ERROR);
                String msg = e.getMessage();
                Integer start = msg.indexOf("Key ");
                String detailMsg = msg.substring(start) + " Already used by other customer.";
                resBean.setErrorMessage(detailMsg);
                return resBean;
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
