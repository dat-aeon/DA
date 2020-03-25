/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.interestRateList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.interestRateSearch.InterestRateSearchReqDto;
import mm.aeon.com.ass.base.dto.interestRateSearch.InterestRateSearchResDto;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class InterestRateListSearchController extends AbstractController
        implements IControllerAccessor<InterestRateListFormBean, InterestRateListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public InterestRateListFormBean process(InterestRateListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        /*
         * if (!VCSCommon.ONE.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
         * logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
         * LogLevel.ERROR); throw new InvalidScreenTransitionException(); }
         */

        applicationLogger.log("Interest Rate Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        InterestRateSearchReqDto reqDto = new InterestRateSearchReqDto();

        try {
            List<InterestRateSearchResDto> resSecurityList =
                    (List<InterestRateSearchResDto>) this.getDaoServiceInvoker().execute(reqDto);
            List<InterestRateListLineBean> lineBeanList = new ArrayList<InterestRateListLineBean>();

            for (InterestRateSearchResDto resdto : resSecurityList) {
                InterestRateListLineBean lineBean = new InterestRateListLineBean();
                lineBean.setInterestId(resdto.getInterestId());
                lineBean.setInterestRate(resdto.getInterestRate());
                lineBean.setDelFlag(resdto.getDelFlag());
                lineBean.setCreatedBy(resdto.getCreatedBy());
                lineBean.setCreatedTime(resdto.getCreatedTime());
                lineBean.setUpdatedBy(resdto.getUpdatedBy());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
                lineBeanList.add(lineBean);
            }

            formBean.setLineBeans(lineBeanList);

            if (lineBeanList.size() == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(lineBeanList.size()));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Interest Rate searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
