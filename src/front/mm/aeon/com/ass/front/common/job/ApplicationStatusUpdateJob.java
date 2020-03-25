/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.common.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import mm.aeon.com.ass.base.dto.applicationUpdateStatus.ApplicationUpdateStatusSearchReqBean;
import mm.aeon.com.ass.base.dto.applicationUpdateStatus.ApplicationUpdateStatusSearchResBean;
import mm.aeon.com.ass.base.dto.applicationUpdateStatus.ApplicationUpdateStatusUpdateReqBean;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class ApplicationStatusUpdateJob extends QuartzJobBean {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        applicationLogger.log("Application status update job started.", LogLevel.INFO);

        ApplicationUpdateStatusSearchReqBean reqSearchBean = new ApplicationUpdateStatusSearchReqBean();

        try {
            List<ApplicationUpdateStatusSearchResBean> resDtoList =
                    (List<ApplicationUpdateStatusSearchResBean>) CommonUtil.getDaoServiceInvoker()
                            .execute(reqSearchBean);

            for (ApplicationUpdateStatusSearchResBean resDto : resDtoList) {

                ApplicationUpdateStatusUpdateReqBean updateReqDto = new ApplicationUpdateStatusUpdateReqBean();

                updateReqDto.setApplicationNo(resDto.getApplicationNo());
                updateReqDto.setApplicationStatus(resDto.getApplicationStatus());
                updateReqDto.setFinancialAmount(resDto.getFinancialAmount());
                updateReqDto.setFinancialTerm(resDto.getFinancialTerm());
                updateReqDto.setStatusChangedReadFlag(false);

                if (resDto.getJudgementStatus() == 1 || resDto.getJudgementStatus() == 4
                        || resDto.getJudgementStatus() == 5 || resDto.getJudgementStatus() == 0) {
                    // Approve
                    updateReqDto.setStatus(10);
                }

                if (resDto.getJudgementStatus() == 2) {
                    // Reject
                    updateReqDto.setStatus(9);
                }

                if (resDto.getJudgementStatus() == 3) {
                    // Cancel
                    updateReqDto.setStatus(8);
                }

                if (resDto.getFinancialStatus() == 6) {
                    // fully paid
                    updateReqDto.setFullyPaidFlag(true);
                } else {
                    updateReqDto.setFullyPaidFlag(false);
                }

                if (resDto.getApplicationStatus() < 8 || resDto.getApplicationStatus() == 12) {
                    if (resDto.getApplicationStatus() == 12) {
                        if (resDto.getUpdatedTime().before(resDto.getJudgementDate())) {
                            CommonUtil.getDaoServiceInvoker().execute(updateReqDto);
                        }

                    } else {
                        CommonUtil.getDaoServiceInvoker().execute(updateReqDto);
                    }
                }
                applicationLogger.log("Application status update job finished.", LogLevel.INFO);

            }
        } catch (Exception e) {
        }

    }

}
