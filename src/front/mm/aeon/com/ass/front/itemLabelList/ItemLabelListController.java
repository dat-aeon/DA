/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.itemLabelList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemCategorySelectListReqDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemCategorySelectListResDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemLabelSelectCountReqDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemLabelSelectListReqDto;
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
import mm.com.dat.presto.utils.common.InputChecker;

public class ItemLabelListController extends AbstractController
        implements IControllerAccessor<ItemLabelListFormBean, ItemLabelListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public ItemLabelListFormBean process(ItemLabelListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Item Info searching process stared.", LogLevel.INFO);
        MessageBean messageBean;

        ItemLabelSelectListReqDto reqDto = new ItemLabelSelectListReqDto();

        ItemLabelSelectCountReqDto countReqDto = new ItemLabelSelectCountReqDto();

        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getItemCode())) {
            reqDto.setItemCode(formBean.getSearchHeaderBean().getItemCode().toLowerCase().trim());
        }
        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getItemLabelEng())) {
            reqDto.setItemLabelEng(formBean.getSearchHeaderBean().getItemLabelEng().toLowerCase().trim());
        }
        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getCategory())) {
            reqDto.setCategory(formBean.getSearchHeaderBean().getCategory().toLowerCase());
        }

        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getItemCode())) {
            countReqDto.setItemCode(formBean.getSearchHeaderBean().getItemCode().toLowerCase().trim());
        }
        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getItemLabelEng())) {
            countReqDto.setItemLabelEng(formBean.getSearchHeaderBean().getItemLabelEng().toLowerCase().trim());
        }
        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getCategory())) {
            countReqDto.setCategory(formBean.getSearchHeaderBean().getCategory().toLowerCase());
        }
        try {
            ItemCategorySelectListReqDto catReqDto = new ItemCategorySelectListReqDto();

            List<ItemCategorySelectListResDto> catResDtoList =
                    (List<ItemCategorySelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(catReqDto);

            List<ItemLabelListLineBean> catLineBeanList = new ArrayList<ItemLabelListLineBean>();
            for (ItemCategorySelectListResDto catDto : catResDtoList) {
                ItemLabelListLineBean itemCatBean = new ItemLabelListLineBean();
                itemCatBean.setCategory(catDto.getItemCategory());
                catLineBeanList.add(itemCatBean);
            }
            formBean.setItemCategoryList(catLineBeanList);

            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setTotalCount(totalCount);
            formBean.setItemLabelSearchReqDto(reqDto);

            if (totalCount == 0) {
                messageBean = new MessageBean(MessageId.MI0008);
            } else {
                messageBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            }
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Item Info searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
