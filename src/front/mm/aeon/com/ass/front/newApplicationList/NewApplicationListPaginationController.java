/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.newApplicationList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.newApplicationListSearch.NewApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.newApplicationListSearch.NewApplicationSearchResDto;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class NewApplicationListPaginationController extends LazyDataModel<NewApplicationListLineBean> {

    private static final long serialVersionUID = 2895710328228440321L;

    private int rowCount;

    private NewApplicationSearchReqDto appSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public NewApplicationListPaginationController(int rowCount, NewApplicationSearchReqDto appSearchReqDto) {
        this.rowCount = rowCount;
        this.appSearchReqDto = appSearchReqDto;
    }

    @Override
    public Object getRowKey(NewApplicationListLineBean appListLineBean) {
        return appListLineBean.getApplicationId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<NewApplicationListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("News info search pagination process started.", LogLevel.INFO);
        appSearchReqDto.setLimit(pageSize);
        appSearchReqDto.setOffset(first);
        appSearchReqDto.setSortField(sortField);
        appSearchReqDto.setSortOrder(sortOrder.toString());

        List<NewApplicationListLineBean> lineBeanList = new ArrayList<NewApplicationListLineBean>();
        try {
            List<NewApplicationSearchResDto> resApplicationList =
                    (List<NewApplicationSearchResDto>) CommonUtil.getDaoServiceInvoker().execute(appSearchReqDto);

            for (NewApplicationSearchResDto resdto : resApplicationList) {
                NewApplicationListLineBean lineBean = new NewApplicationListLineBean();
                lineBean.setApplicationId(resdto.getApplicationId());
                lineBean.setApplicationNo(resdto.getApplicationNo());
                lineBean.setApplicationTypeId(resdto.getApplicationTypeId());
                lineBean.setAppliedDate(resdto.getAppliedDate());
                lineBean.setApplicantName(resdto.getApplicantName());
                lineBean.setDob(resdto.getDob());
                lineBean.setNrcNo(resdto.getNrcNo());
                lineBean.setFatherName(resdto.getFatherName());
                lineBean.setNationality(resdto.getNationality());
                lineBean.setNationalityOther(resdto.getNationalityOther());
                lineBean.setGender(resdto.getGender());
                lineBean.setMaritalStatus(resdto.getMaritalStatus());
                lineBean.setCurrentAddress(resdto.getCurrentAddress());
                lineBean.setCurrentAddressBuildingNo(resdto.getCurrentAddressBuildingNo());
                lineBean.setCurrentAddressRoomNo(resdto.getCurrentAddressRoomNo());
                lineBean.setCurrentAddressFloor(resdto.getCurrentAddressFloor());
                lineBean.setCurrentAddressStreet(resdto.getCurrentAddressStreet());
                lineBean.setCurrentAddressQtr(resdto.getCurrentAddressQtr());
                lineBean.setCurrentAddressTownship(resdto.getCurrentAddressTownship());
                lineBean.setCurrentAddressCity(resdto.getCurrentAddressCity());
                lineBean.setPermanentAddress(resdto.getPermanentAddress());
                lineBean.setPermanentAddressBuildingNo(resdto.getPermanentAddressBuildingNo());
                lineBean.setPermanentAddressRoomNo(resdto.getPermanentAddressRoomNo());
                lineBean.setPermanentAddressFloor(resdto.getPermanentAddressFloor());
                lineBean.setPermanentAddressStreet(resdto.getPermanentAddressStreet());
                lineBean.setPermanentAddressQtr(resdto.getPermanentAddressQtr());
                lineBean.setPermanentAddressTownship(resdto.getPermanentAddressTownship());
                lineBean.setPermanentAddressCity(resdto.getPermanentAddressCity());
                lineBean.setTypeOfResident(resdto.getTypeOfResident());
                lineBean.setTypeOfResidentOther(resdto.getTypeOfResidentOther());
                lineBean.setLivingWith(resdto.getLivingWith());
                lineBean.setLivingWithOther(resdto.getLivingWithOther());
                lineBean.setStayYear(resdto.getStayYear());
                lineBean.setStayMonth(resdto.getStayMonth());
                lineBean.setMobileNo(resdto.getMobileNo());
                lineBean.setResidentTelNo(resdto.getResidentTelNo());
                lineBean.setOtherPhoneNo(resdto.getOtherPhoneNo());
                lineBean.setEmail(resdto.getEmail());
                lineBean.setCustomerId(resdto.getCustomerId());
                lineBean.setApplicantCompanyId(resdto.getApplicantCompanyId());
                lineBean.setEmergencyContactId(resdto.getEmergencyContactId());
                lineBean.setGuarantorId(resdto.getGuarantorId());
                lineBean.setStatus(resdto.getStatus());
                lineBean.setLoanTypeId(resdto.getLoanTypeId());
                lineBean.setInterestId(resdto.getInterestId());
                lineBean.setInterestRate(resdto.getInterestRate());
                lineBean.setCompulsoryId(resdto.getCompulsoryId());
                lineBean.setCompulsoryAmount(resdto.getCompulsoryAmount());
                lineBean.setFinanceAmount(resdto.getFinanceAmount());
                lineBean.setFinanceTerm(resdto.getFinanceTerm());
                lineBean.setProductId(resdto.getProductId());
                lineBean.setProductDescription(resdto.getProductDescription());
                lineBean.setChannelType(resdto.getChannelType());
                lineBean.setCreatedBy(resdto.getCreatedBy());
                lineBean.setCreatedTime(resdto.getCreatedTime());
                lineBean.setUpdatedBy(resdto.getUpdatedBy());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
                lineBean.setDelFlag(resdto.getDelFlag());
                lineBean.setApplicationId(resdto.getApplicationId());
                lineBean.setLockFlag(resdto.getLockFlag());
                lineBean.setLockTime(resdto.getLockTime());
                lineBean.setLockBy(resdto.getLockBy());
                lineBean.setHighestEducationTypeId(resdto.getHighestEducationTypeId());

                lineBean.setApplicantCompanyDto(resdto.getApplicantCompanyDto());
                lineBean.setEmergencyContactDto(resdto.getEmergencyContactDto());
                lineBean.setGuarantorDto(resdto.getGuarantorDto());
                lineBean.setAttachmentDtoList(resdto.getAttachmentDtoList());

                lineBeanList.add(lineBean);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
        applicationLogger.log("News info search pagination process finished.", LogLevel.INFO);
        return lineBeanList;

    }
}
