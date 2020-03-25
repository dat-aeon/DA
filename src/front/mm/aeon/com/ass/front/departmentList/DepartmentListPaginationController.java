/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.departmentList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSearchReqDto;
import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSearchResDto;
import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.DocumentFollowUpApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.DocumentFollowUpApplicationSearchResDto;
import mm.aeon.com.ass.front.common.LoanCalculator;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.documentFollowUpApplicationList.DocumentFollowUpApplicationListLineBean;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class DepartmentListPaginationController extends LazyDataModel<DepartmentListLineBean> {

    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private DepartmentListSearchReqDto appSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public DepartmentListPaginationController(int rowCount,
            DepartmentListSearchReqDto appSearchReqDto) {
        this.rowCount = rowCount;
        this.appSearchReqDto = appSearchReqDto;
    }

    @Override
    public Object getRowKey(DepartmentListLineBean appListLineBean) {
        return appListLineBean.getDepartmentId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<DepartmentListLineBean> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {

        applicationLogger.log("Document Follow Up info search pagination process started.", LogLevel.INFO);
        appSearchReqDto.setLimit(pageSize);
        appSearchReqDto.setOffset(first);
        appSearchReqDto.setSortField(sortField);
        appSearchReqDto.setSortOrder(sortOrder.toString());

        List<DepartmentListLineBean> lineBeanList =
                new ArrayList<DepartmentListLineBean>();
        try {
            List<DepartmentListSearchResDto> resApplicationList =
                    (List<DepartmentListSearchResDto>) CommonUtil.getDaoServiceInvoker()
                            .execute(appSearchReqDto);

            for (DepartmentListSearchResDto resdto : resApplicationList) {
                DepartmentListLineBean lineBean = new DepartmentListLineBean();
                lineBean.setDepartmentId(resdto.getDepartmentId());
                lineBean.setName(resdto.getName());
                lineBean.setDelFlag(resdto.getDelFlag());
                lineBean.setCreatedBy(resdto.getCreatedBy());
                lineBean.setCreatedTime(resdto.getCreatedTime());
                lineBean.setUpdatedBy(resdto.getUpdatedBy());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
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
