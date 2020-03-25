/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.custEditRequestList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.custEditRequestSearch.CustEditReqSelectListReqDto;
import mm.aeon.com.ass.base.dto.custEditRequestSearch.CustEditReqSelectListResDto;
import mm.aeon.com.ass.base.dto.custEditRequestSearch.ItemStatusSelectListReqDto;
import mm.aeon.com.ass.base.dto.custEditRequestSearch.ItemStatusSelectListResDto;
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

public class CustEditReqListController extends AbstractController
        implements IControllerAccessor<CustEditReqListFormBean, CustEditReqListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public CustEditReqListFormBean process(CustEditReqListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Item Info searching process stared.", LogLevel.INFO);
        MessageBean messageBean;

        CustEditReqSelectListReqDto reqDto = new CustEditReqSelectListReqDto();

        try {
            List<CustEditReqSelectListResDto> resDtoList =
                    (List<CustEditReqSelectListResDto>) this.getDaoServiceInvoker().execute(reqDto);

            List<CustEditReqListLineBean> lineBeanList = new ArrayList<CustEditReqListLineBean>();

            for (CustEditReqSelectListResDto resDto : resDtoList) {
                CustEditReqListLineBean lineBean = new CustEditReqListLineBean();
                lineBean.setCustomerId(resDto.getCustomerId());
                lineBean.setCustEditReqId(resDto.getCustEditReqId());
                lineBean.setName(resDto.getName());
                lineBean.setDob(resDto.getDob());
                lineBean.setNrc_no(resDto.getNrc_no());
                lineBean.setPhone_no(resDto.getPhone_no());
                lineBean.setStatus(resDto.getStatus());
                lineBean.setCreatedTime(resDto.getCreatedTime());
                lineBean.setCreatedBy(resDto.getCreatedBy());
                lineBean.setUpdatedTime(resDto.getUpdatedTime());
                lineBean.setUpdatedBy(resDto.getUpdatedBy());
                
                lineBean.setCurrent_customerId(resDto.getCurrent_customerId());
                lineBean.setCurrent_name(resDto.getCurrent_name());
                lineBean.setCurrent_dob(resDto.getCurrent_dob());
                lineBean.setCurrent_phoneNo(resDto.getCurrent_phoneNo());
                lineBean.setCurrent_nrcNo(resDto.getCurrent_nrcNo());
                lineBean.setLockFlag(resDto.getLockFlag());
                lineBean.setLockBy(resDto.getLockBy());
                
                lineBeanList.add(lineBean);
            }

           /* ItemStatusSelectListReqDto catReqDto = new ItemStatusSelectListReqDto();

            List<ItemStatusSelectListResDto> catResDtoList =
                    (List<ItemStatusSelectListResDto>) this.getDaoServiceInvoker().execute(catReqDto);

            List<CustEditReqListLineBean> catLineBeanList = new ArrayList<CustEditReqListLineBean>();
            for (ItemStatusSelectListResDto catDto : catResDtoList) {
                CustEditReqListLineBean itemCatBean = new CustEditReqListLineBean();
                itemCatBean.setStatus(catDto.getStatus());
                catLineBeanList.add(itemCatBean);
            }
*/
            // formBean.setItemStatusList(catLineBeanList);
             formBean.setLineBeanList(lineBeanList);

            if (lineBeanList.size() == 0) {
                messageBean = new MessageBean(MessageId.MI0008);
            } else {
                messageBean = new MessageBean(MessageId.MI0007, String.valueOf(lineBeanList.size()));
            }
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
 
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
