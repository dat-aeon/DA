/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.saleClaimApplicationList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorEditFileSearchReqDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorEditFileSearchResDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorStaffCommentSearchReqDto;
import mm.aeon.com.ass.base.dto.agentDocumentErrorApplicationListSearch.AgentDocumentErrorStaffCommentSearchResDto;
import mm.aeon.com.ass.base.dto.saleClaimApplicationListSearch.SaleClaimApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.saleClaimApplicationListSearch.SaleClaimApplicationSearchResDto;
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

public class SaleClaimApplicationListPaginationController extends LazyDataModel<SaleClaimApplicationListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private SaleClaimApplicationSearchReqDto appSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public SaleClaimApplicationListPaginationController(int rowCount,
            SaleClaimApplicationSearchReqDto appSearchReqDto) {
        this.rowCount = rowCount;
        this.appSearchReqDto = appSearchReqDto;
    }

    @Override
    public Object getRowKey(SaleClaimApplicationListLineBean appListLineBean) {
        return appListLineBean.getApplicationId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<SaleClaimApplicationListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Sale Claim info search pagination process started.", LogLevel.INFO);
        appSearchReqDto.setLimit(pageSize);
        appSearchReqDto.setOffset(first);
        appSearchReqDto.setSortField(sortField);
        appSearchReqDto.setSortOrder(sortOrder.toString());

        List<SaleClaimApplicationListLineBean> lineBeanList = new ArrayList<SaleClaimApplicationListLineBean>();
        try {
            List<SaleClaimApplicationSearchResDto> resApplicationList =
                    (List<SaleClaimApplicationSearchResDto>) CommonUtil.getDaoServiceInvoker().execute(appSearchReqDto);
            UserInfoReferReqDto userInfoReferReqDto;
            UserInfoReferResDto userInfoReferResDto;
            String[] str;
            for (SaleClaimApplicationSearchResDto resdto : resApplicationList) {
                SaleClaimApplicationListLineBean lineBean = new SaleClaimApplicationListLineBean();
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
                lineBean.setLoanTypeId(resdto.getLoanTypeId());
                lineBean.setInterestId(resdto.getInterestId());
                lineBean.setInterestRate(resdto.getInterestRate());
                lineBean.setCompulsoryId(resdto.getCompulsoryId());
                lineBean.setCompulsoryAmount(resdto.getCompulsoryAmount());
                lineBean.setFinanceAmount(resdto.getFinanceAmount());
                lineBean.setFinanceTerm(resdto.getFinanceTerm());
                lineBean.setApprovedFinanceAmount(resdto.getApprovedFinanceAmount());
                lineBean.setApprovedFinanceTerm(resdto.getApprovedFinanceTerm());
                lineBean.setProductId(resdto.getProductId());
                lineBean.setProductDescription(resdto.getProductDescription());
                lineBean.setChannelType(resdto.getChannelType());
                lineBean.setCreatedBy(resdto.getCreatedBy());
                lineBean.setCreatedTime(resdto.getCreatedTime());
                lineBean.setUpdatedBy(resdto.getUpdatedBy());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
                lineBean.setDelFlag(resdto.getDelFlag());
                lineBean.setPendingComment(resdto.getPendingComment());
                lineBean.setLockFlag(resdto.getLockFlag());
                lineBean.setLockTime(resdto.getLockTime());
                lineBean.setLockBy(resdto.getLockBy());
                lineBean.setHighestEducationTypeId(resdto.getHighestEducationTypeId());
                lineBean.setApprovedAmount(resdto.getApprovedAmount());

                lineBean.setApplicantCompanyDto(resdto.getApplicantCompanyDto());
                lineBean.setEmergencyContactDto(resdto.getEmergencyContactDto());
                lineBean.setGuarantorDto(resdto.getGuarantorDto());
                lineBean.setPurchaseDto(resdto.getPurchaseDto());
                lineBean.setAttachmentDtoList(resdto.getAttachmentDtoList());
                lineBean.setPurchaseAttachmentDtoList(resdto.getPurchaseDto().getPurchaseAttachmentDtoList());
                lineBean.setPurchaseProductDtoList(resdto.getPurchaseDto().getPurchaseProductDtoList());

                lineBean.setLastRequestedStaffName(resdto.getLastRequestedStaffName());
                if (lineBean.getLastRequestedStaffName() != null) {
                    if (lineBean.getLastRequestedStaffName().contains(",")) {
                        userInfoReferReqDto = new UserInfoReferReqDto();
                        userInfoReferResDto = new UserInfoReferResDto();
                        str = new String[2];
                        str = lineBean.getLastRequestedStaffName().split(VCSCommon.REXCOMMA);
                        userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                        userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                        if (!userInfoReferReqDto.getUser_type_id().equals(3)) {
                            userInfoReferResDto = (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                    .execute(userInfoReferReqDto);
                            lineBean.setLastRequestedStaffName(userInfoReferResDto.getUser_name());
                        } else {
                            CustomerIdReferReqDto customerReferReqDto = new CustomerIdReferReqDto();
                            customerReferReqDto.setCustomer_id(userInfoReferReqDto.getUser_id());

                            CustomerIdReferResDto customerReferResDto = (CustomerIdReferResDto) CommonUtil
                                    .getDaoServiceInvoker().execute(customerReferReqDto);
                            lineBean.setLastRequestedStaffName(customerReferResDto.getName());
                        }
                    }
                }

                AgentDocumentErrorStaffCommentSearchReqDto staffReqDto =
                        new AgentDocumentErrorStaffCommentSearchReqDto();
                staffReqDto.setApplicationNo(lineBean.getApplicationNo());
                staffReqDto.setStatus(19);

                List<AgentDocumentErrorStaffCommentSearchResDto> staffResList =
                        (List<AgentDocumentErrorStaffCommentSearchResDto>) CommonUtil.getDaoServiceInvoker()
                                .execute(staffReqDto);
                List<SaleClaimStaffBean> staffLineBeanList = new ArrayList<SaleClaimStaffBean>();
                for (AgentDocumentErrorStaffCommentSearchResDto staffRes : staffResList) {
                    SaleClaimStaffBean staffLineBean = new SaleClaimStaffBean();
                    staffLineBean.setPendingComment(staffRes.getPendingComment());
                    staffLineBean.setUpdatedTime(staffRes.getUpdatedTime());
                    staffLineBean.setUpdatedBy(staffRes.getUpdatedBy());

                    if (staffLineBean.getUpdatedBy() != null) {
                        if (staffLineBean.getUpdatedBy().contains(",")) {
                            userInfoReferReqDto = new UserInfoReferReqDto();
                            userInfoReferResDto = new UserInfoReferResDto();
                            str = new String[2];
                            str = staffLineBean.getUpdatedBy().split(VCSCommon.REXCOMMA);
                            userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                            userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                            userInfoReferResDto = (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker()
                                    .execute(userInfoReferReqDto);
                            staffLineBean.setStaffName(userInfoReferResDto.getUser_name());
                            staffLineBean.setDepartmentId(userInfoReferResDto.getDepartmentId());
                        }
                    }
                    staffLineBeanList.add(staffLineBean);
                }
                lineBean.setStaffBeanList(staffLineBeanList);

                AgentDocumentErrorEditFileSearchReqDto editReqDto = new AgentDocumentErrorEditFileSearchReqDto();
                editReqDto.setDelFlag(false);
                editReqDto.setOriginalFlag(false);
                editReqDto.setIsErrorFile(true);
                editReqDto.setPurchaseId(lineBean.getPurchaseDto().getPurchaseId());

                List<AgentDocumentErrorEditFileSearchResDto> editResList =
                        (List<AgentDocumentErrorEditFileSearchResDto>) CommonUtil.getDaoServiceInvoker()
                                .execute(editReqDto);
                List<SaleClaimEditImageBean> fileLineBeanList = new ArrayList<SaleClaimEditImageBean>();

                for (AgentDocumentErrorEditFileSearchResDto fileRes : editResList) {
                    SaleClaimEditImageBean fileLineBean = new SaleClaimEditImageBean();
                    fileLineBean.setAttachmentId(fileRes.getAttachmentId());
                    fileLineBean.setPurchaseId(lineBean.getPurchaseDto().getPurchaseId());
                    fileLineBean.setFilePath(fileRes.getFilePath());
                    fileLineBean.setFileType(fileRes.getFileType());
                    fileLineBean.setCreatedBy(fileRes.getCreatedBy());
                    fileLineBean.setCreatedTime(fileRes.getCreatedTime());
                    fileLineBean.setUpdatedBy(fileRes.getUpdatedBy());
                    fileLineBean.setUpdatedTime(fileRes.getUpdatedTime());

                    fileLineBeanList.add(fileLineBean);
                }

                lineBean.setSaleClaimEditImageBeanList(fileLineBeanList);
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
