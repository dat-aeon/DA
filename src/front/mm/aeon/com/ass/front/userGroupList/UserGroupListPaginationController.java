/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.userGroupList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class UserGroupListPaginationController extends LazyDataModel<UserGroupListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private List<UserGroupListLineBean> allDataList;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public UserGroupListPaginationController(int rowCount, List<UserGroupListLineBean> allDataList) {
        this.rowCount = rowCount;
        this.allDataList = allDataList;
    }

    @Override
    public Object getRowKey(UserGroupListLineBean line) {
        return line.getGroupId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<UserGroupListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Security Search Pagination Process started.", LogLevel.INFO);

        List<UserGroupListLineBean> resultList = new ArrayList<UserGroupListLineBean>();

        int endIndex = first + pageSize;

        if (endIndex > allDataList.size()) {
            endIndex = allDataList.size();
        }

        if (sortField != null) {
            Collections.sort(allDataList, new UserGroupListLazySorter(sortField, sortOrder));
        }

        resultList = allDataList.subList(first, endIndex);

        applicationLogger.log("Security Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

}
