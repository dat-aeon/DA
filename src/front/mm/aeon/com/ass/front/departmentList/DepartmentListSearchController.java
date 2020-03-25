/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.departmentList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.applicationListSearch.ApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.applicationListSearch.ApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSearchReqDto;
import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSelectCountReqDto;
import mm.aeon.com.ass.base.dto.interestRateSearch.InterestRateSearchReqDto;
import mm.aeon.com.ass.base.dto.interestRateSearch.InterestRateSearchResDto;
import mm.aeon.com.ass.front.applicationList.ApplicationListHeaderBean;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.util.CommonUtil;
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

public class DepartmentListSearchController extends AbstractController
        implements IControllerAccessor<DepartmentListFormBean, DepartmentListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public DepartmentListFormBean process(DepartmentListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Department List Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        DepartmentListSelectCountReqDto countReqDto = new DepartmentListSelectCountReqDto();
        DepartmentListSearchReqDto reqDto = new DepartmentListSearchReqDto();

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setTotalCount(totalCount);
            formBean.setApplicationSearchReqDto(reqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Department List searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
