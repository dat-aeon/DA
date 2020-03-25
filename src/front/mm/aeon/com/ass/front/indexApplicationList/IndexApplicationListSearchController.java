/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.indexApplicationList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import mm.aeon.com.ass.base.dto.indexApplicationListSearch.IndexApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.indexApplicationListSearch.IndexApplicationSelectCountReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.RequestedStaffInfoReferListReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.RequestedStaffInfoReferResDto;
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

public class IndexApplicationListSearchController extends AbstractController
        implements IControllerAccessor<IndexApplicationListFormBean, IndexApplicationListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public IndexApplicationListFormBean process(IndexApplicationListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        /*
         * if (!VCSCommon.ONE.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
         * logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
         * LogLevel.ERROR); throw new InvalidScreenTransitionException(); }
         */

        applicationLogger.log("Interest Rate Searching Process started.", LogLevel.INFO);

        if (!isValidData(formBean)) {
            return formBean;
        }

        RequestedStaffInfoReferListReqDto requestedStaffInfoReferListReqDto = new RequestedStaffInfoReferListReqDto();
        List<RequestedStaffInfoReferResDto> requestedStaffInfoReferResDtoList =
                new ArrayList<RequestedStaffInfoReferResDto>();

        try {
            if (!StringUtils.isEmpty(formBean.getSearchHeaderBean().getStaffName())
                    && !StringUtils.isEmpty(formBean.getSearchHeaderBean().getUserTypeId())) {
                requestedStaffInfoReferListReqDto.setName(formBean.getSearchHeaderBean().getStaffName());
                requestedStaffInfoReferListReqDto.setUserTypeId(formBean.getSearchHeaderBean().getUserTypeId());
                requestedStaffInfoReferResDtoList = (List<RequestedStaffInfoReferResDto>) CommonUtil
                        .getDaoServiceInvoker().execute(requestedStaffInfoReferListReqDto);
            }

            MessageBean msgBean;

            IndexApplicationSelectCountReqDto countReqDto = new IndexApplicationSelectCountReqDto();
            IndexApplicationSearchReqDto reqDto = new IndexApplicationSearchReqDto();
            IndexApplicationListHeaderBean formBeanSearchHeaderBean = formBean.getSearchHeaderBean();
            if (formBeanSearchHeaderBean.getApplicationNo() != null) {
                countReqDto.setApplicationNo(formBeanSearchHeaderBean.getApplicationNo());
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
            if (formBeanSearchHeaderBean.getIndexedDateFrom() != null) {
                countReqDto.setIndexedDateFrom(formBeanSearchHeaderBean.getIndexedDateFrom());
            }
            if (formBeanSearchHeaderBean.getIndexedDateTo() != null) {
                countReqDto.setIndexedDateTo(formBeanSearchHeaderBean.getIndexedDateTo());
            }
            List<String> requestStaffList = new ArrayList<String>();
            for (RequestedStaffInfoReferResDto dto : requestedStaffInfoReferResDtoList) {
                requestStaffList.add(dto.getUserId() + "," + formBean.getSearchHeaderBean().getUserTypeId());
            }
            if (CollectionUtils.isEmpty(requestedStaffInfoReferResDtoList)) {
                if (!StringUtils.isEmpty(formBean.getSearchHeaderBean().getStaffName())) {
                    requestStaffList.add(formBean.getSearchHeaderBean().getStaffName());
                }
            }
            countReqDto.setStaffNameList(requestStaffList);
            if (CollectionUtils.isEmpty(requestStaffList)) {
                countReqDto.setStaffNameSearchFlag(false);
            } else {
                countReqDto.setStaffNameSearchFlag(true);
            }
            if (formBeanSearchHeaderBean.getApplicationNo() != null) {
                reqDto.setApplicationNo(formBeanSearchHeaderBean.getApplicationNo());
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
            if (formBeanSearchHeaderBean.getIndexedDateFrom() != null) {
                reqDto.setIndexedDateFrom(formBeanSearchHeaderBean.getIndexedDateFrom());
            }
            if (formBeanSearchHeaderBean.getIndexedDateTo() != null) {
                reqDto.setIndexedDateTo(formBeanSearchHeaderBean.getIndexedDateTo());
            }
            reqDto.setStaffNameList(countReqDto.getStaffNameList());
            reqDto.setStaffNameSearchFlag(countReqDto.getStaffNameSearchFlag());

            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setTotalCount(totalCount);
            formBean.setIndexApplicationSearchReqDto(reqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
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

    private boolean isValidData(IndexApplicationListFormBean formBean) {
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
