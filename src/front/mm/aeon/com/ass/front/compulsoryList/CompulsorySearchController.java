/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.compulsoryList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.compulsoryInfoSearch.CompulsorySearchReqDto;
import mm.aeon.com.ass.base.dto.compulsoryInfoSearch.CompulsorySearchResDto;
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

public class CompulsorySearchController extends AbstractController
        implements IControllerAccessor<CompulsoryListFormBean, CompulsoryListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public CompulsoryListFormBean process(CompulsoryListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Compulsory Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        CompulsorySearchReqDto reqDto = new CompulsorySearchReqDto();

        try {
            List<CompulsorySearchResDto> resCompulsoryList =
                    (List<CompulsorySearchResDto>) this.getDaoServiceInvoker().execute(reqDto);
            List<CompulsoryListLineBean> lineBeanList = new ArrayList<CompulsoryListLineBean>();

            for (CompulsorySearchResDto resdto : resCompulsoryList) {
                CompulsoryListLineBean lineBean = new CompulsoryListLineBean();
                lineBean.setCompulsoryId(resdto.getCompulsoryId());
                lineBean.setCompulsoryAmount(resdto.getCompulsoryAmount());
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
            applicationLogger.log("Compulsory searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
