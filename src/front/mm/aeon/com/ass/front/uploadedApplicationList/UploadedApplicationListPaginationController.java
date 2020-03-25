/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.uploadedApplicationList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.applicationIndexedStatusInfoRefer.ApplicationIndexedStatusInfoReferReqDto;
import mm.aeon.com.ass.base.dto.applicationIndexedStatusInfoRefer.ApplicationIndexedStatusInfoReferResDto;
import mm.aeon.com.ass.base.dto.uploadedApplicationListSearch.UploadedApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.uploadedApplicationListSearch.UploadedApplicationSearchResDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.CustomerIdReferReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.CustomerIdReferResDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.UserInfoReferReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.UserInfoReferResDto;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class UploadedApplicationListPaginationController extends LazyDataModel<UploadedApplicationListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private UploadedApplicationSearchReqDto appSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public UploadedApplicationListPaginationController(int rowCount, UploadedApplicationSearchReqDto appSearchReqDto) {
        this.rowCount = rowCount;
        this.appSearchReqDto = appSearchReqDto;
    }

    @Override
    public Object getRowKey(UploadedApplicationListLineBean appListLineBean) {
        return appListLineBean.getApplicationId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<UploadedApplicationListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Uploaded info search pagination process started.", LogLevel.INFO);
        appSearchReqDto.setLimit(pageSize);
        appSearchReqDto.setOffset(first);
        appSearchReqDto.setSortField(sortField);
        appSearchReqDto.setSortOrder(sortOrder.toString());

        List<UploadedApplicationListLineBean> lineBeanList = new ArrayList<UploadedApplicationListLineBean>();
        try {
            List<UploadedApplicationSearchResDto> resApplicationList =
                    (List<UploadedApplicationSearchResDto>) CommonUtil.getDaoServiceInvoker().execute(appSearchReqDto);
            UserInfoReferReqDto userInfoReferReqDto;
            UserInfoReferResDto userInfoReferResDto;
            String[] str;
            for (UploadedApplicationSearchResDto resdto : resApplicationList) {
                UploadedApplicationListLineBean lineBean = new UploadedApplicationListLineBean();
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
                lineBean.setHighestEducationTypeId(resdto.getHighestEducationTypeId());
                lineBean.setJudgementDate(resdto.getJudgementDate());
                lineBean.setJudgementStatus(resdto.getJudgementStatus());
                lineBean.setLockFlag(resdto.getLockFlag());
                lineBean.setLockTime(resdto.getLockTime());
                lineBean.setLockBy(resdto.getLockBy());

                lineBean.setApplicantCompanyDto(resdto.getApplicantCompanyDto());
                lineBean.setEmergencyContactDto(resdto.getEmergencyContactDto());
                lineBean.setGuarantorDto(resdto.getGuarantorDto());
                lineBean.setAttachmentDtoList(resdto.getAttachmentDtoList());

                lineBean.setLastDataEntryStaffName(resdto.getLastDataEntryStaffName());
                if (lineBean.getLastDataEntryStaffName() != null) {
                    if (lineBean.getLastDataEntryStaffName().contains(",")) {
                        userInfoReferReqDto = new UserInfoReferReqDto();
                        userInfoReferResDto = new UserInfoReferResDto();
                        str = new String[2];
                        str = lineBean.getLastDataEntryStaffName().split(VCSCommon.REXCOMMA);
                        userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                        userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                        if (!userInfoReferReqDto.getUser_type_id().equals(3)) {
                            userInfoReferResDto = (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                    .execute(userInfoReferReqDto);
                            lineBean.setLastDataEntryStaffName(userInfoReferResDto.getUser_name());
                        } else {
                            CustomerIdReferReqDto customerReferReqDto = new CustomerIdReferReqDto();
                            customerReferReqDto.setCustomer_id(userInfoReferReqDto.getUser_id());

                            CustomerIdReferResDto customerReferResDto = (CustomerIdReferResDto) CommonUtil
                                    .getDaoServiceInvoker().execute(customerReferReqDto);
                            lineBean.setLastDataEntryStaffName(customerReferResDto.getName());
                        }
                    }
                }

                lineBean.setLastDocumentFollowUpUpdatedStaffName(resdto.getLastDocumentFollowUpUpdatedStaffName());
                if (lineBean.getLastDocumentFollowUpUpdatedStaffName() != null) {
                    if (lineBean.getLastDocumentFollowUpUpdatedStaffName().contains(",")) {
                        userInfoReferReqDto = new UserInfoReferReqDto();
                        userInfoReferResDto = new UserInfoReferResDto();
                        str = new String[2];
                        str = lineBean.getLastDocumentFollowUpUpdatedStaffName().split(VCSCommon.REXCOMMA);
                        userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                        userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                        if (!userInfoReferReqDto.getUser_type_id().equals(3)) {
                            userInfoReferResDto = (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                    .execute(userInfoReferReqDto);
                            lineBean.setLastDocumentFollowUpUpdatedStaffName(userInfoReferResDto.getUser_name());
                        } else {
                            CustomerIdReferReqDto customerReferReqDto = new CustomerIdReferReqDto();
                            customerReferReqDto.setCustomer_id(userInfoReferReqDto.getUser_id());

                            CustomerIdReferResDto customerReferResDto = (CustomerIdReferResDto) CommonUtil
                                    .getDaoServiceInvoker().execute(customerReferReqDto);
                            lineBean.setLastDocumentFollowUpUpdatedStaffName(customerReferResDto.getName());
                        }
                    }
                }

                ApplicationIndexedStatusInfoReferReqDto indexStatusDto = new ApplicationIndexedStatusInfoReferReqDto();

                indexStatusDto.setApplicationNo(resdto.getApplicationNo());
                indexStatusDto.setStatus(4);
                try {
                    ApplicationIndexedStatusInfoReferResDto indexStatusResDto =
                            (ApplicationIndexedStatusInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                    .execute(indexStatusDto);

                    if (indexStatusResDto != null) {
                        lineBean.setIndexedStaff(indexStatusResDto.getIndexedStaff());

                        if (indexStatusResDto.getIndexedStaff().contains(",")) {
                            userInfoReferReqDto = new UserInfoReferReqDto();
                            userInfoReferResDto = new UserInfoReferResDto();
                            str = new String[2];
                            str = indexStatusResDto.getIndexedStaff().split(VCSCommon.REXCOMMA);
                            userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                            userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                            if (!userInfoReferReqDto.getUser_type_id().equals(3)) {
                                userInfoReferResDto = (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                        .execute(userInfoReferReqDto);
                                lineBean.setIndexedStaff(userInfoReferResDto.getUser_name());
                            } else {
                                CustomerIdReferReqDto customerReferReqDto = new CustomerIdReferReqDto();
                                customerReferReqDto.setCustomer_id(userInfoReferReqDto.getUser_id());

                                CustomerIdReferResDto customerReferResDto = (CustomerIdReferResDto) CommonUtil
                                        .getDaoServiceInvoker().execute(customerReferReqDto);
                                lineBean.setIndexedStaff(customerReferResDto.getName());
                            }
                        }
                    }

                } catch (PrestoDBException e) {
                    if (e instanceof DaoSqlException) {
                        applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                        throw new BaseException(e.getCause());
                    }
                }

                ApplicationIndexedStatusInfoReferReqDto followupStatusDto =
                        new ApplicationIndexedStatusInfoReferReqDto();

                followupStatusDto.setApplicationNo(resdto.getApplicationNo());
                followupStatusDto.setStatus(7);
                followupStatusDto.setCanDuplicate(true);
                try {
                    ApplicationIndexedStatusInfoReferResDto followupStatusResDto =
                            (ApplicationIndexedStatusInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                    .execute(followupStatusDto);

                    if (followupStatusResDto != null) {
                        lineBean.setDocFollowUpStaff(followupStatusResDto.getIndexedStaff());
                    }

                } catch (PrestoDBException e) {
                    if (e instanceof DaoSqlException) {
                        applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                        throw new BaseException(e.getCause());
                    }
                }
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
