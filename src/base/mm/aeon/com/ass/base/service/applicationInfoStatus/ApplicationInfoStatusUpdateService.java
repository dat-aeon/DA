/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.applicationInfoStatus;

import mm.aeon.com.ass.base.dto.applicationUpdateStatus.ApplicationUpdateStatusUpdateReqBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class ApplicationInfoStatusUpdateService extends AbstractService
        implements IService<ApplicationInfoStatusUpdateServiceReqBean, ApplicationInfoStatusUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public ApplicationInfoStatusUpdateServiceResBean execute(ApplicationInfoStatusUpdateServiceReqBean reqBean) {
        ApplicationInfoStatusUpdateServiceResBean resBean = new ApplicationInfoStatusUpdateServiceResBean();

        ApplicationUpdateStatusUpdateReqBean updateReqDto = new ApplicationUpdateStatusUpdateReqBean();
        if (reqBean.getApplicationNo() != null) {
            updateReqDto.setApplicationNo(reqBean.getApplicationNo());
            updateReqDto.setApplicationStatus(reqBean.getApplicationStatus());
            updateReqDto.setFinancialAmount(reqBean.getFinancialAmount());
            updateReqDto.setFinancialTerm(reqBean.getFinancialTerm());
            updateReqDto.setStatusChangedReadFlag(false);

            if (reqBean.getJudgementStatus() == 1 || reqBean.getJudgementStatus() == 4
                    || reqBean.getJudgementStatus() == 5 || reqBean.getJudgementStatus() == 0) {
                // Approve
                updateReqDto.setStatus(10);
            }

            if (reqBean.getJudgementStatus() == 2) {
                // Reject
                updateReqDto.setStatus(9);
            }

            if (reqBean.getJudgementStatus() == 3) {
                // Cancel
                updateReqDto.setStatus(8);
            }

            if (reqBean.getFinancialStatus() == 6) {
                // fully paid
                updateReqDto.setFullyPaidFlag(true);
            } else {
                updateReqDto.setFullyPaidFlag(false);
            }
        }

        try {
            this.getDaoServiceInvoker().execute(updateReqDto);
            resBean.setServiceStatus(ServiceStatusCode.OK);
        } catch (Exception e) {
            if (e instanceof RecordDuplicatedException) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_DUPLICATED_ERROR);

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
