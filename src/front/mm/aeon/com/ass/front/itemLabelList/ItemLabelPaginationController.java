/**************************************************************************
 * $Date: 2018-09-05$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.itemLabelList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.indexApplicationListSearch.IndexApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.indexApplicationListSearch.IndexApplicationSearchResDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemCategorySelectListReqDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemCategorySelectListResDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemLabelSelectListReqDto;
import mm.aeon.com.ass.base.dto.itemLabelSearch.ItemLabelSelectListResDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.indexApplicationList.IndexApplicationListLineBean;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class ItemLabelPaginationController extends LazyDataModel<ItemLabelListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private ItemLabelSelectListReqDto appSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public ItemLabelPaginationController(int rowCount, ItemLabelSelectListReqDto appSearchReqDto) {
        this.rowCount = rowCount;
        this.appSearchReqDto = appSearchReqDto;
    }

    @Override
    public Object getRowKey(ItemLabelListLineBean appListLineBean) {
        return appListLineBean.getItemLabelId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<ItemLabelListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Index info search pagination process started.", LogLevel.INFO);
        appSearchReqDto.setLimit(pageSize);
        appSearchReqDto.setOffset(first);
        appSearchReqDto.setSortField(sortField);
        appSearchReqDto.setSortOrder(sortOrder.toString());

        List<ItemLabelListLineBean> lineBeanList = new ArrayList<ItemLabelListLineBean>();
        try {
            List<ItemLabelSelectListResDto> resDtoList =
                    (List<ItemLabelSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(appSearchReqDto);    

            for (ItemLabelSelectListResDto resDto : resDtoList) {
                ItemLabelListLineBean lineBean = new ItemLabelListLineBean();
                lineBean.setItemLabelId(resDto.getItemLabelId());
                lineBean.setItemLabelCode(resDto.getItemLabelCode());
                lineBean.setItemLabelEng(resDto.getItemLabelEng());
                lineBean.setItemLabelMym(resDto.getItemLabelMym());
                lineBean.setDescription(resDto.getDescription());
                lineBean.setCategory(resDto.getCategory());
                lineBean.setCreatedTime(resDto.getCreatedTime());
                lineBean.setCreatedBy(resDto.getCreatedBy());
                lineBean.setUpdatedTime(resDto.getUpdatedTime());
                lineBean.setUpdatedBy(resDto.getUpdatedBy());

                lineBeanList.add(lineBean);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
        applicationLogger.log("Index info search pagination process finished.", LogLevel.INFO);
        return lineBeanList;

    }

}
