/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentDocumentErrorApplicationList;

import org.springframework.util.StringUtils;

import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorApplicationSelectCountReqDto;
import mm.aeon.com.ass.front.common.constants.DisplayItem;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.DisplayItemBean;
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

public class AgentDocumentErrorApplicationListSearchController extends AbstractController implements
        IControllerAccessor<AgentDocumentErrorApplicationListFormBean, AgentDocumentErrorApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentDocumentErrorApplicationListFormBean process(AgentDocumentErrorApplicationListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Agent And Product Application Searching Process started.", LogLevel.INFO);

        if (!isValidData(formBean)) {
            return formBean;
        }

        try {

            MessageBean msgBean;

            AgentDocumentErrorApplicationSelectCountReqDto countReqDto =
                    new AgentDocumentErrorApplicationSelectCountReqDto();
            AgentDocumentErrorApplicationSearchReqDto reqDto = new AgentDocumentErrorApplicationSearchReqDto();
            AgentDocumentErrorApplicationListHeaderBean formBeanSearchHeaderBean = formBean.getSearchHeaderBean();
            if (formBeanSearchHeaderBean.getApplicationNo() != null) {
                countReqDto.setApplicationNo(formBeanSearchHeaderBean.getApplicationNo());
            }
            if (formBeanSearchHeaderBean.getStatus() != null) {
                countReqDto.setStatus(formBeanSearchHeaderBean.getStatus());
            }
            if (formBeanSearchHeaderBean.getApplicantName() != null) {
                countReqDto.setApplicantName(formBeanSearchHeaderBean.getApplicantName());
            }
            if (formBeanSearchHeaderBean.getApplicantPhoneNo() != null) {
                countReqDto.setApplicantPhoneNo(formBeanSearchHeaderBean.getApplicantPhoneNo());
            }
            if (formBeanSearchHeaderBean.getApplicantNrcNo() != null) {
                countReqDto.setApplicantNrcNo(formBeanSearchHeaderBean.getApplicantNrcNo());
            }
            if (formBeanSearchHeaderBean.getNewApplicationDateFrom() != null) {
                countReqDto.setNewApplicationDateFrom(formBeanSearchHeaderBean.getNewApplicationDateFrom());
            }
            if (formBeanSearchHeaderBean.getNewApplicationDateTo() != null) {
                countReqDto.setNewApplicationDateTo(formBeanSearchHeaderBean.getNewApplicationDateTo());
            }
            if (formBeanSearchHeaderBean.getAgentName() != null) {
                countReqDto.setAgentName(formBeanSearchHeaderBean.getAgentName());
            }
            if (formBeanSearchHeaderBean.getOutletName() != null) {
                countReqDto.setOutletName(formBeanSearchHeaderBean.getOutletName());
            }

            if (formBeanSearchHeaderBean.getApplicationNo() != null) {
                reqDto.setApplicationNo(formBeanSearchHeaderBean.getApplicationNo());
            }
            if (formBeanSearchHeaderBean.getStatus() != null) {
                reqDto.setStatus(formBeanSearchHeaderBean.getStatus());
            }
            if (formBeanSearchHeaderBean.getApplicantName() != null) {
                reqDto.setApplicantName(formBeanSearchHeaderBean.getApplicantName());
            }
            if (formBeanSearchHeaderBean.getApplicantPhoneNo() != null) {
                reqDto.setApplicantPhoneNo(formBeanSearchHeaderBean.getApplicantPhoneNo());
            }
            if (formBeanSearchHeaderBean.getApplicantNrcNo() != null) {
                reqDto.setApplicantNrcNo(formBeanSearchHeaderBean.getApplicantNrcNo());
            }
            if (formBeanSearchHeaderBean.getNewApplicationDateFrom() != null) {
                reqDto.setNewApplicationDateFrom(formBeanSearchHeaderBean.getNewApplicationDateFrom());
            }
            if (formBeanSearchHeaderBean.getNewApplicationDateTo() != null) {
                reqDto.setNewApplicationDateTo(formBeanSearchHeaderBean.getNewApplicationDateTo());
            }
            if (formBeanSearchHeaderBean.getAgentName() != null) {
                reqDto.setAgentName(formBeanSearchHeaderBean.getAgentName());
            }
            if (formBeanSearchHeaderBean.getOutletName() != null) {
                reqDto.setOutletName(formBeanSearchHeaderBean.getOutletName());
            }

            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setTotalCount(totalCount);
            formBean.setAgentDocumentErrorApplicationSearchReqDto(reqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Agent And Product Application searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

    private boolean isValidData(AgentDocumentErrorApplicationListFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;

        if (!StringUtils.isEmpty(formBean.getSearchHeaderBean().getStaffName())) {
            if (formBean.getSearchHeaderBean().getUserTypeId() == null) {
                msgBean = new MessageBean(MessageId.ME0003,
                        DisplayItemBean.getDisplayItemName(DisplayItem.REQUESTED_STAFF_USER_TYPE));
                msgBean.addColumnId(DisplayItem.REQUESTED_STAFF_USER_TYPE);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                isValid = false;
            }

        }

        return isValid;
    }

}
