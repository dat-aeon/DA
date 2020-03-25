/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.custEditRequestList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class CustEditReqListPaginationController extends LazyDataModel<CustEditReqListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private List<CustEditReqListLineBean> allDataList;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public CustEditReqListPaginationController(int rowCount, List<CustEditReqListLineBean> allDataList) {
        this.rowCount = rowCount;
        this.allDataList = allDataList;
    }

    @Override
    public Object getRowKey(CustEditReqListLineBean line) {
        return line.getCustEditReqId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<CustEditReqListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Item Info Search Pagination Process started.", LogLevel.INFO);

        List<CustEditReqListLineBean> resultList = null;

        int endIndex = first + pageSize;

        if (endIndex > allDataList.size()) {
            endIndex = allDataList.size();
        }

        if (sortField != null) {
            Collections.sort(allDataList, new CustEditReqListLazySorter(sortField, sortOrder));
        }

        resultList = allDataList.subList(first, endIndex);

        applicationLogger.log("Item Info Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

}
