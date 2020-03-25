/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.uploadedApplicationList;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.RequestedStaffSearchReqDto;
import mm.aeon.com.ass.base.dto.uploadedApplicationListSearch.UploadedApplicationAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.uploadedApplicationListSearch.UploadedApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.CustomerIdReferReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.CustomerIdReferResDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.UserInfoReferReqDto;
import mm.aeon.com.ass.base.dto.userInfoRefer.UserInfoReferResDto;
import mm.aeon.com.ass.front.common.LoanCalculator;
import mm.aeon.com.ass.front.common.ZipDir;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.DACommon;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.FileHandler;
import mm.aeon.com.ass.front.documentFollowUpApplicationList.RequestedStaffLineBean;
import mm.aeon.com.ass.front.interestRateList.InterestRateListLineBean;
import mm.aeon.com.ass.front.interestRateManagement.InterestRateManagementHeaderBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Name("uploadedApplicationListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class UploadedApplicationListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private List<UploadedApplicationListLineBean> lineBeans;

    private LazyDataModel<UploadedApplicationListLineBean> lazyModel;

    private UploadedApplicationListLineBean lineBean;

    private List<SelectItem> statusList;

    private boolean checkNotYetFlag;
    private Boolean notyetFlag;

    private int totalCount;

    private UploadedApplicationSearchReqDto uploadedApplicationSearchReqDto;

    private UploadedApplicationListHeaderBean searchHeaderBean;

    private List<SelectItem> applicationStatusSelectItemList;

    private List<SelectItem> loanTypeSelectItemList;

    private List<SelectItem> genderSelectItemList;

    private List<SelectItem> applicationTypeSelectItemList;

    private List<SelectItem> nationalitySelectItemList;

    private List<SelectItem> maritalStatusSelectItemList;

    private List<SelectItem> typeOfResidenceSelectItemList;

    private List<SelectItem> livingWithSelectItemList;

    private List<SelectItem> productTypeSelectItemList;

    private List<SelectItem> channelSelectItemList;

    private List<SelectItem> relationshipSelectItemList;

    private List<SelectItem> companyStatusSelectItemList;

    private List<SelectItem> fileTypeSelectItemList;

    private List<SelectItem> judgementStatusSelectItemList;

    private ArrayList<SelectItem> townshipSelectItemList;

    private ArrayList<SelectItem> citySelectItemList;

    private ArrayList<SelectItem> educationSelectItemList;

    private Boolean nrcFrontEditFlag;

    private Boolean nrcBackEditFlag;

    private Boolean residentProofEditFlag;

    private Boolean incomeProofEditFlag;

    private Boolean guarantorNrcFrontEditFlag;

    private Boolean guarantorNrcBackEditFlag;

    private Boolean householdCriminalEditFlag;

    private Boolean applicantPhotoEditFlag;

    private Boolean signatureEditFlag;

    private Boolean guarantorSignatureEditFlag;

    @Out(required = false, value = "securityUpdateParam")
    private InterestRateManagementHeaderBean updateParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    private Integer applicationId;

    private List<RequestedStaffLineBean> dataEntryStaffLineBeanList;

    private List<RequestedStaffLineBean> docFollowUpStaffLineBeanList;

    private boolean dataEntryStaffDialogVisible;

    private boolean docFollowUpStaffDialogVisible;

    private String loginUserName;

    @Autowired
    private ResourceLoader resourceLoader;

    @Begin(nested = true)
    @Action
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.UPLOADED_APPLICATIONS);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new UploadedApplicationListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new UploadedApplicationListPaginationController(totalCount, uploadedApplicationSearchReqDto);
        }
        loginUserName = CommonUtil.getLoginUserInfo().getId().toString();
        return LinkTarget.OK;
    }

    @Action
    public String permit(UploadedApplicationListLineBean lineBean) {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            searchHeaderBean = new UploadedApplicationListHeaderBean();
            init = true;
            return LinkTarget.SEARCH;
        }
        doReload = new Boolean(true);
        this.lineBean = lineBean;
        searchHeaderBean = new UploadedApplicationListHeaderBean();
        nrcFrontEditFlag = false;
        nrcBackEditFlag = false;
        residentProofEditFlag = false;
        incomeProofEditFlag = false;
        guarantorNrcFrontEditFlag = false;
        guarantorNrcBackEditFlag = false;
        householdCriminalEditFlag = false;
        signatureEditFlag = false;
        applicantPhotoEditFlag = false;
        guarantorSignatureEditFlag = false;
        return LinkTarget.OK;
    }

    public void downloadPhotoZip() throws IOException {
        downloadApplicationForm();
        String zipFileName = lineBean.getApplicantName().replaceAll("\\s+", "") + "_" + lineBean.getApplicationNo()
                + "_" + CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddHHmmss") + ".zip";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(FileHandler.getSystemPath() + "/temp/" + zipFileName);

            ZipOutputStream zos = new ZipOutputStream(fos);
            Path sourceDir =
                    Paths.get(FileHandler.getSystemPath() + "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/");
            Files.walkFileTree(sourceDir, new ZipDir(zos, sourceDir));

            zos.close();
            fos.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        File file = new File(FileHandler.getSystemPath() + "/temp/" + zipFileName);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.reset();
        response.setBufferSize(10240);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file), 10240);
            output = new BufferedOutputStream(response.getOutputStream(), 10240);
            byte[] buffer = new byte[10240];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            input.close();
            output.close();
        }
        context.responseComplete();
    }

    public void downloadApplicationForm() {

        String dirPath = FileHandler.getSystemPath() + "/temp/applicationForm/" + System.currentTimeMillis() + "/";
        String fileName = "applicationForm.pdf";

        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy / MM / dd");
        Map<String, Object> parameterMap = new HashMap<>();

        parameterMap.put("customerSignaturePath", FileHandler.getSystemPath() + lineBean.getSignatureImageFilePath());
        parameterMap.put("guarantorSignature",
                FileHandler.getSystemPath() + lineBean.getGuarantorSignatureImageFilePath());
        parameterMap.put("logoPath", FileHandler.getSystemPath() + "/images/aeon.jpg");

        parameterMap.put("applicantName", lineBean.getApplicantName());
        parameterMap.put("applicationDate", dateFormat.format(lineBean.getAppliedDate()));
        parameterMap.put("applicationNo", lineBean.getApplicationNo());
        parameterMap.put("applicentDOB", dateFormat.format(lineBean.getDob()));
        parameterMap.put("applicentNRC", lineBean.getNrcNo());
        parameterMap.put("applicentFatherName", lineBean.getFatherName());
        parameterMap.put("applicentNRC", lineBean.getNrcNo());
        // Applicant Nationality
        if (getNationalityValue(lineBean.getNationality()).equalsIgnoreCase("Myanmar")) {
            parameterMap.put("applicentMyanmar", "X");
        } else if (getNationalityValue(lineBean.getNationality()).equalsIgnoreCase("Other")) {
            parameterMap.put("applicentOther", "X");
            parameterMap.put("applicentOtherDec", lineBean.getNationalityOther());
        }
        // Applicant Nationality
        if (getGenderValue(lineBean.getGender()).equalsIgnoreCase("Male")) {
            parameterMap.put("applicentMale", "X");
        } else if (getGenderValue(lineBean.getGender()).equalsIgnoreCase("Female")) {
            parameterMap.put("applicentFamale", "X");
        }
        // Applicant Marital Status
        if (getMaritalStatusValue(lineBean.getMaritalStatus()).equalsIgnoreCase("Single")) {
            parameterMap.put("applicentSingle", "X");
        } else if (getMaritalStatusValue(lineBean.getMaritalStatus()).equalsIgnoreCase("Married")) {
            parameterMap.put("applicentMarrie", "X");
        }

        parameterMap.put("applicentCAddBuildNo", lineBean.getCurrentAddressBuildingNo());
        parameterMap.put("applicentCRmNo", lineBean.getCurrentAddressRoomNo());
        parameterMap.put("applicentCFloor", lineBean.getCurrentAddressFloor());
        parameterMap.put("applicentCStreet", lineBean.getCurrentAddressStreet());
        parameterMap.put("applicentCQtr", lineBean.getCurrentAddressQtr());
        parameterMap.put("applicentCTownship", getTownshipValue(lineBean.getCurrentAddressTownship()));
        parameterMap.put("applicentCCity", getCityValue(lineBean.getCurrentAddressCity()));

        parameterMap.put("applicentPAddBuildNo", lineBean.getPermanentAddressBuildingNo());
        parameterMap.put("applicentPRmNo", lineBean.getPermanentAddressRoomNo());
        parameterMap.put("applicentPFloor", lineBean.getPermanentAddressFloor());
        parameterMap.put("applicentPStreet", lineBean.getPermanentAddressStreet());
        parameterMap.put("applicentPQtr", lineBean.getPermanentAddressQtr());
        parameterMap.put("applicentPTownship", getTownshipValue(lineBean.getPermanentAddressTownship()));
        parameterMap.put("applicentPCity", getCityValue(lineBean.getPermanentAddressCity()));

        // Applicant Type Of Residence
        if (getTypeOfResidenceValue(lineBean.getTypeOfResident()).equalsIgnoreCase("Owner")) {
            parameterMap.put("applicentOwner", "X");
        } else if (getTypeOfResidenceValue(lineBean.getTypeOfResident()).equalsIgnoreCase("Parental")) {
            parameterMap.put("applicentParental", "X");
        } else if (getTypeOfResidenceValue(lineBean.getTypeOfResident()).equalsIgnoreCase("Rental")) {
            parameterMap.put("applicentRental", "X");
        } else if (getTypeOfResidenceValue(lineBean.getTypeOfResident()).equalsIgnoreCase("Relative")) {
            parameterMap.put("applicentRelative", "X");
        } else if (getTypeOfResidenceValue(lineBean.getTypeOfResident()).equalsIgnoreCase("Hostel/Other")) {
            parameterMap.put("applicentHostelOther", "X");
            parameterMap.put("applicentHostelOtherDec", lineBean.getTypeOfResidentOther());
        }

        // Applicant Living With
        if (getLivingWithValue(lineBean.getLivingWith()).equalsIgnoreCase("Parent")) {
            parameterMap.put("applicentParent", "X");
        } else if (getLivingWithValue(lineBean.getLivingWith()).equalsIgnoreCase("Spouse")) {
            parameterMap.put("applicentSpouse", "X");
        } else if (getLivingWithValue(lineBean.getLivingWith()).equalsIgnoreCase("Relative")) {
            parameterMap.put("applicentLWRelative", "X");
        } else if (getLivingWithValue(lineBean.getLivingWith()).equalsIgnoreCase("Friend")) {
            parameterMap.put("applicentLWFriend", "X");
        } else if (getLivingWithValue(lineBean.getLivingWith()).equalsIgnoreCase("Alone")) {
            parameterMap.put("applicentAlone", "X");
        }

        parameterMap.put("applicentYOSYrs", lineBean.getStayYear().toString());
        parameterMap.put("applicentYOSMths", lineBean.getStayMonth().toString());

        parameterMap.put("applicentMobile1", lineBean.getMobileNo());
        parameterMap.put("applicentResidentTelNo1", lineBean.getResidentTelNo());
        parameterMap.put("applientOtherPhNo1", lineBean.getOtherPhoneNo());
        parameterMap.put("applicentEmail", lineBean.getEmail());

        // Occupation
        parameterMap.put("occCompanyName", lineBean.getApplicantCompanyDto().getCompanyName());
        parameterMap.put("occCompanyAddBuildNo", lineBean.getApplicantCompanyDto().getCompanyAddressBuildingNo());
        parameterMap.put("occRmNo", lineBean.getApplicantCompanyDto().getCompanyAddressRoomNo());
        parameterMap.put("occFloor", lineBean.getApplicantCompanyDto().getCompanyAddressFloor());
        parameterMap.put("occStreetRd", lineBean.getApplicantCompanyDto().getCompanyAddressStreet());
        parameterMap.put("occQtr", lineBean.getApplicantCompanyDto().getCompanyAddressQtr());
        parameterMap.put("occTownship",
                getTownshipValue(lineBean.getApplicantCompanyDto().getCompanyAddressTownship()));
        parameterMap.put("occCity", getCityValue(lineBean.getApplicantCompanyDto().getCompanyAddressCity()));

        parameterMap.put("occCompanyTelNo", lineBean.getApplicantCompanyDto().getCompanyTelNo());

        parameterMap.put("occContactTimeAM", lineBean.getApplicantCompanyDto().getContactTimeFrom());
        parameterMap.put("occContactTimePM", lineBean.getApplicantCompanyDto().getContactTimeTo());
        parameterMap.put("occDepartment", lineBean.getApplicantCompanyDto().getDepartment());
        parameterMap.put("occPosition", lineBean.getApplicantCompanyDto().getPosition());
        parameterMap.put("occYOSYrs", lineBean.getApplicantCompanyDto().getServiceYear().toString());
        parameterMap.put("occYOSMths", lineBean.getApplicantCompanyDto().getServiceMonth().toString());

        if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Public Company")) {
            parameterMap.put("publicCompany", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Private Company")) {
            parameterMap.put("privateCompany", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Factory")) {
            parameterMap.put("factory", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("SME Owner")) {
            parameterMap.put("sme", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Specialist")) {
            parameterMap.put("specialist", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("NGO")) {
            parameterMap.put("ngo", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Government Office")) {
            parameterMap.put("governmentOffice", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Military")) {
            parameterMap.put("military", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Police")) {
            parameterMap.put("police", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("SME Officer")) {
            parameterMap.put("smeOfficer", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Taxi Owner")) {
            parameterMap.put("taxiOwner", "X");
        } else if (getCompanyStatusValue(lineBean.getApplicantCompanyDto().getCompanyStatus())
                .equalsIgnoreCase("Other")) {
            parameterMap.put("companyStatusOther", "X");
            parameterMap.put("companyStatusOtherDec", lineBean.getApplicantCompanyDto().getCompanyStatusOther());
        }

        parameterMap.put("occMonthlyBasicIncome",
                decimalFormat.format(lineBean.getApplicantCompanyDto().getMonthlyBasicIncome()));
        parameterMap.put("occOtherIncome", decimalFormat.format(lineBean.getApplicantCompanyDto().getOtherIncome()));
        parameterMap.put("occTotalIncome", decimalFormat.format(lineBean.getApplicantCompanyDto().getTotalIncome()));
        parameterMap.put("occSalaryDate", lineBean.getApplicantCompanyDto().getSalaryDay().toString());

        // Emergency Contact
        parameterMap.put("emgName", lineBean.getEmergencyContactDto().getEmergencyContactName());
        if (getRelatinshipValue(lineBean.getEmergencyContactDto().getRelationship()).equalsIgnoreCase("Parent")) {
            parameterMap.put("emgParent", "X");
        } else if (getRelatinshipValue(lineBean.getEmergencyContactDto().getRelationship())
                .equalsIgnoreCase("Spouse")) {
            parameterMap.put("emgSpouse", "X");
        } else if (getRelatinshipValue(lineBean.getEmergencyContactDto().getRelationship())
                .equalsIgnoreCase("Relative")) {
            parameterMap.put("emgRelative", "X");
        } else if (getRelatinshipValue(lineBean.getEmergencyContactDto().getRelationship())
                .equalsIgnoreCase("Friend")) {
            parameterMap.put("emgFriend", "X");
        } else if (getRelatinshipValue(lineBean.getEmergencyContactDto().getRelationship()).equalsIgnoreCase("Other")) {
            parameterMap.put("emgOther", "X");
            parameterMap.put("emgOtherDec", lineBean.getEmergencyContactDto().getRelationshipOther());
        }

        parameterMap.put("emgBuildNo", lineBean.getEmergencyContactDto().getCurrentAddressBuildingNo());
        parameterMap.put("emgRmNo", lineBean.getEmergencyContactDto().getCurrentAddressRoomNo());
        parameterMap.put("emgFloor", lineBean.getEmergencyContactDto().getCurrentAddressFloor());
        parameterMap.put("emgStreet", lineBean.getEmergencyContactDto().getCurrentAddressStreet());
        parameterMap.put("emgQtr", lineBean.getEmergencyContactDto().getCurrentAddressQtr());
        parameterMap.put("emgTownship",
                getTownshipValue(lineBean.getEmergencyContactDto().getCurrentAddressTownship()));
        parameterMap.put("emgCity", getCityValue(lineBean.getEmergencyContactDto().getCurrentAddressCity()));

        parameterMap.put("emgMobileNo", lineBean.getEmergencyContactDto().getMobileNo());
        parameterMap.put("emgResidentTelNo", lineBean.getEmergencyContactDto().getResidentTelNo());
        parameterMap.put("emgOtherPhoneNo1", lineBean.getEmergencyContactDto().getOtherPhoneNo());

        // Guarantor

        parameterMap.put("guarentorName", lineBean.getGuarantorDto().getGuarantorName());
        parameterMap.put("guarentorDOB", dateFormat.format(lineBean.getGuarantorDto().getDob()));
        parameterMap.put("guarentorNrcNo", lineBean.getGuarantorDto().getNrcNo());

        // Guarantor Nationality
        if (getNationalityValue(lineBean.getNationality()).equalsIgnoreCase("Myanmar")) {
            parameterMap.put("guarentorMyanmar", "X");
        } else if (getNationalityValue(lineBean.getNationality()).equalsIgnoreCase("Other")) {
            parameterMap.put("guarentorNationOther", "X");
            parameterMap.put("guarentorNationOtherDec", lineBean.getGuarantorDto().getNationalityOther());
        }

        parameterMap.put("guarentorMobileNo", lineBean.getGuarantorDto().getMobileNo());
        parameterMap.put("guarentorResidentTelNo", lineBean.getGuarantorDto().getResidentTelNo());

        // RelationShip Guarantor
        if (getRelatinshipValue(lineBean.getGuarantorDto().getRelationship()).equalsIgnoreCase("Parent")) {
            parameterMap.put("guarentorParent", "X");
        } else if (getRelatinshipValue(lineBean.getGuarantorDto().getRelationship()).equalsIgnoreCase("Spouse")) {
            parameterMap.put("guarentorSpouse", "X");
        } else if (getRelatinshipValue(lineBean.getGuarantorDto().getRelationship()).equalsIgnoreCase("Relative")) {
            parameterMap.put("guarentorRelative", "X");
        } else if (getRelatinshipValue(lineBean.getGuarantorDto().getRelationship()).equalsIgnoreCase("Friend")) {
            parameterMap.put("guarentorFriend", "X");
        } else if (getRelatinshipValue(lineBean.getGuarantorDto().getRelationship()).equalsIgnoreCase("Other")) {
            parameterMap.put("guarentorRelationshipOther", "X");
            parameterMap.put("guarentorRelationshipOtherDec", lineBean.getGuarantorDto().getRelationshipOther());
        }

        parameterMap.put("guarentorCurrentAddBNo", lineBean.getGuarantorDto().getCurrentAddressBuildingNo());
        parameterMap.put("guarentorCurrentRmNo", lineBean.getGuarantorDto().getCurrentAddressRoomNo());
        parameterMap.put("guarentorCurrentFloor", lineBean.getGuarantorDto().getCurrentAddressFloor());
        parameterMap.put("guarentorCurrentStreetRd", lineBean.getGuarantorDto().getCurrentAddressStreet());
        parameterMap.put("guarentorCurrentQtr", lineBean.getGuarantorDto().getCurrentAddressQtr());
        parameterMap.put("guarentorCurrentTownship",
                getTownshipValue(lineBean.getGuarantorDto().getCurrentAddressTownship()));
        parameterMap.put("guarentorCurrentCity", getCityValue(lineBean.getGuarantorDto().getCurrentAddressCity()));

        // Guarantor Type of residence
        if (getTypeOfResidenceValue(lineBean.getGuarantorDto().getTypeOfResident()).equalsIgnoreCase("Owner")) {
            parameterMap.put("guarentorOwner", "X");
        } else if (getTypeOfResidenceValue(lineBean.getGuarantorDto().getTypeOfResident())
                .equalsIgnoreCase("Parental")) {
            parameterMap.put("guarentorParental", "X");
        } else if (getTypeOfResidenceValue(lineBean.getGuarantorDto().getTypeOfResident()).equalsIgnoreCase("Rental")) {
            parameterMap.put("guarentorRental", "X");
        } else if (getTypeOfResidenceValue(lineBean.getGuarantorDto().getTypeOfResident())
                .equalsIgnoreCase("Relative")) {
            parameterMap.put("guarentorResidenceRelative", "X");
        } else if (getTypeOfResidenceValue(lineBean.getGuarantorDto().getTypeOfResident())
                .equalsIgnoreCase("Hostel/Other")) {
            parameterMap.put("guarentorHostel", "X");
            parameterMap.put("guarentorHostelDec", lineBean.getGuarantorDto().getRelationshipOther());
        }
        // Guarantor livingwith
        if (getLivingWithValue(lineBean.getGuarantorDto().getLivingWith()).equalsIgnoreCase("Parent")) {
            parameterMap.put("guarentorLWParent", "X");
        } else if (getLivingWithValue(lineBean.getGuarantorDto().getLivingWith()).equalsIgnoreCase("Spouse")) {
            parameterMap.put("guarentorLWSpouse", "X");
        } else if (getLivingWithValue(lineBean.getGuarantorDto().getLivingWith()).equalsIgnoreCase("Relative")) {
            parameterMap.put("guarentorLWRelative", "X");
        } else if (getLivingWithValue(lineBean.getGuarantorDto().getLivingWith()).equalsIgnoreCase("Friend")) {
            parameterMap.put("guarentorLWFriend", "X");
        } else {
            parameterMap.put("guarentorLWOther", "X");
            parameterMap.put("guarentorLWOtherDec", lineBean.getGuarantorDto().getLivingWithOther());
        }

        // Guarantor Gender
        if (getGenderValue(lineBean.getGuarantorDto().getGender()).equalsIgnoreCase("Male")) {
            parameterMap.put("guarentorMale", "X");
        } else if (getGenderValue(lineBean.getGuarantorDto().getGender()).equalsIgnoreCase("Female")) {
            parameterMap.put("guarentorFamale", "X");
        }

        // Guarantor Marital Status
        if (getMaritalStatusValue(lineBean.getGuarantorDto().getMaritalStatus()).equalsIgnoreCase("Single")) {
            parameterMap.put("guarentorSingle", "X");
        } else if (getMaritalStatusValue(lineBean.getGuarantorDto().getMaritalStatus()).equalsIgnoreCase("Married")) {
            parameterMap.put("guarentorMarrie", "X");
        }

        parameterMap.put("guarentorYOStayYrs", lineBean.getGuarantorDto().getYearStay().toString());
        parameterMap.put("guarentorYOStayMths", lineBean.getGuarantorDto().getMonthStay().toString());
        parameterMap.put("guarentorComName", lineBean.getGuarantorDto().getCompanyName());
        parameterMap.put("guarentorComTelNo", lineBean.getGuarantorDto().getCompanyTelNo());

        parameterMap.put("guarentorComAddBNo", lineBean.getGuarantorDto().getCompanyAddressBuildingNo());
        parameterMap.put("guarentorComRmNo", lineBean.getGuarantorDto().getCompanyAddressRoomNo());
        parameterMap.put("guarentorComFloor", lineBean.getGuarantorDto().getCompanyAddressFloor());
        parameterMap.put("guarentorComStreetRd", lineBean.getGuarantorDto().getCompanyAddressStreet());
        parameterMap.put("guarentorComQtr", lineBean.getGuarantorDto().getCompanyAddressQtr());
        parameterMap.put("guarentorComTownship",
                getTownshipValue(lineBean.getGuarantorDto().getCompanyAddressTownship()));
        parameterMap.put("guarentorComCity", getCityValue(lineBean.getGuarantorDto().getCompanyAddressCity()));

        parameterMap.put("guarentorDepartment", lineBean.getGuarantorDto().getDepartment());
        parameterMap.put("guarentorPosition", lineBean.getGuarantorDto().getPosition());
        parameterMap.put("guarentorYOSYrs", lineBean.getGuarantorDto().getServiceYear().toString());
        parameterMap.put("guarentorYOSMths", lineBean.getGuarantorDto().getServiceMonth().toString());
        parameterMap.put("guarentorMonthlyBasicIncome",
                decimalFormat.format(lineBean.getGuarantorDto().getMonthlyIncome()));
        parameterMap.put("guarentorTotalIncome", decimalFormat.format(lineBean.getGuarantorDto().getTotalIncome()));

        // Loan Calculation

        parameterMap.put("financeAmount1", decimalFormat.format(lineBean.getFinanceAmount()));
        parameterMap.put("termOfFinance1", lineBean.getFinanceTerm().toString());
        parameterMap.put("totalRepaymentAmount1", decimalFormat.format(lineBean.getModifyTotalRepayment()));
        parameterMap.put("monthlyRepaymentAmount1", decimalFormat.format(lineBean.getMonthlyInstallment()));
        parameterMap.put("processingFee1", decimalFormat.format(lineBean.getProcessingFees()));
        parameterMap.put("compulsorySaving1", decimalFormat.format(lineBean.getCompulsoryAmount()));

        FacesContext facesContext = FacesContext.getCurrentInstance();

        JasperReport japserReport;
        try {
            ClassLoader classLoader = new UploadedApplicationListFormBean().getClass().getClassLoader();
            File file = new File(classLoader.getResource("report-template/DigitalForm.jrxml").getFile());
            InputStream inputStream = new FileInputStream(file);
            japserReport = JasperCompileManager.compileReport(inputStream);

            JRDataSource jrDataSource = new JREmptyDataSource();
            JasperPrint jasperPrint = JasperFillManager.fillReport(japserReport, parameterMap, jrDataSource);
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            FileUtils.forceMkdir(new File(dirPath));

            JasperExportManager.exportReportToPdfFile(jasperPrint, dirPath + fileName);
            Image image = JasperPrintManager.printPageToImage(jasperPrint, 0, 2.0f);

            savePic(image, "jpg");
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void savePic(Image image, String type) {

        String destinationFilePath =
                FileHandler.getSystemPath() + "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                        + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "applicationForm.jpg";

        int width = image.getWidth(null);
        int height = image.getHeight(null);
        try {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            Graphics g = bi.getGraphics();

            g.drawImage(image, 0, 0, null);
            ImageIO.write(bi, type, new File(destinationFilePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Action
    public String detail(UploadedApplicationListLineBean lineBean) {
        if (checkNotYetFlag) {
            return LinkTarget.OK;
        }
        notyetFlag = true;

        this.getMessageContainer().clearAllMessages(true);

        for (UploadedApplicationAttachmentSearchResDto resFileDto : lineBean.getAttachmentDtoList()) {
            if (resFileDto.getFileType() == DACommon.NRC_FRONT_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "nrcFront.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {
                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setNrcFrontImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.NRC_BACK_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "nrcBack.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {
                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setNrcBackImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.RESIDENT_PROOF_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "residentProof.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {
                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setResidentProofImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.INCOME_PROOF_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "incomeProof.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setIncomeProofImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.GUARANTOR_NRC_FRONT_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "guarantorNrcFront.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setGuarantorNrcFrontImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.GUARANTOR_NRC_BACK_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "guarantorNrcBack.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setGuarantorNrcBackImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.HOUSEHOLD_OR_CRIMINAL_CLEARANCE_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "houseHoldOrCriminalClearance.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setHouseholdCriminalImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.APPLICANT_PHOTO_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "applicantPhoto.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setApplicantPhotoImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.SIGNATURE_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "signature.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setSignatureImageFilePath(destinationFilePath);
                }
            }

            if (resFileDto.getFileType() == DACommon.GUARANTOR_SIGNATURE_ATTACHMENT) {
                if (!StringUtils.isEmpty(resFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getAttachmentInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "guarantorSignature.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setGuarantorSignatureImageFilePath(destinationFilePath);
                }
            }
        }

        double totalProductPrice = lineBean.getFinanceAmount();
        boolean motorcycleLoanFlag = false;
        int loanTerm = lineBean.getFinanceTerm();
        double processingFees;
        double totalRepayment;
        double modifyTotalRepayment;
        double firstPayment;
        double monthlyPayment;
        double financeAmount;
        double totalInterest;
        double downPayment = 0;
        double promoDiscount = 0;
        double insPremium = 0;
        double vatAmount = 0;
        double financeAmountForPSG = 0;
        double interestRateMonthly = lineBean.getInterestRate();
        double totalInterestForPSG = 0;
        double monthlyInstallmentForPSG = 0;
        double monthlyInstallment = 0;
        double conSaving = lineBean.getCompulsoryAmount();
        double totalConSaving = 0;
        double initialPaymentForPSG = 0;
        double firstPaymentForPSG = 0;
        double lastPayment = 0;

        processingFees = LoanCalculator.calculateProcessingFees(motorcycleLoanFlag, totalProductPrice);
        financeAmount = LoanCalculator.calculateFinanceAmountForPSG(totalProductPrice, downPayment, promoDiscount,
                insPremium, vatAmount, financeAmountForPSG);
        totalInterest = LoanCalculator.calculateTotalInterest(financeAmount, loanTerm, interestRateMonthly,
                totalInterestForPSG);
        totalRepayment = LoanCalculator.calculateTotalRepayment(financeAmount, totalInterest);
        monthlyInstallment =
                LoanCalculator.calculateMonthlyInstallment(totalRepayment, monthlyInstallmentForPSG, loanTerm);
        totalConSaving = LoanCalculator.calculateTotalConSaving(conSaving, loanTerm);
        firstPaymentForPSG = LoanCalculator.calculateFirstPaymentForPSG(initialPaymentForPSG);
        firstPayment = LoanCalculator.calculateFirstPayment(initialPaymentForPSG, totalRepayment, monthlyInstallment,
                loanTerm, firstPaymentForPSG);
        monthlyPayment = monthlyInstallment;
        lastPayment = LoanCalculator.calculateLastPayment(totalProductPrice, firstPayment, monthlyPayment,
                totalInterest, loanTerm, monthlyInstallment);
        modifyTotalRepayment =
                LoanCalculator.modifyCalculateTotalRepayment(monthlyPayment, loanTerm, firstPayment, lastPayment);

        lineBean.setProcessingFees(processingFees);
        lineBean.setTotalInterest(totalInterest);
        lineBean.setTotalRepayment(totalRepayment);
        lineBean.setMonthlyInstallment(monthlyInstallment);
        lineBean.setFirstPaymentForPSG(firstPaymentForPSG);
        lineBean.setFirstPayment(firstPayment);
        lineBean.setLastPayment(lastPayment);
        lineBean.setModifyTotalRepayment(modifyTotalRepayment);
        lineBean.setCompulsoryAmount(totalConSaving);

        this.lineBean = lineBean;
        return LinkTarget.DETAIL;
    }

    public String prepareRegister() {
        this.updateParam = null;
        return LinkTarget.REGISTER;
    }

    @Action
    public String toggleValidStatus(InterestRateListLineBean lineBean) {
        this.lineBean = null;
        if (!this.getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }
        return LinkTarget.OK;
    }

    @Action
    public String delete() {
        this.doReload = false;
        this.lineBean = null;
        if (!getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }

        return LinkTarget.OK;
    }

    public void reset() {
        this.searchHeaderBean = new UploadedApplicationListHeaderBean();
    }

    @Action
    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        nrcFrontEditFlag = false;
        nrcBackEditFlag = false;
        residentProofEditFlag = false;
        incomeProofEditFlag = false;
        guarantorNrcFrontEditFlag = false;
        guarantorNrcBackEditFlag = false;
        householdCriminalEditFlag = false;
        signatureEditFlag = false;
        applicantPhotoEditFlag = false;
        guarantorSignatureEditFlag = false;
        return LinkTarget.BACK;
    }

    public void closeDocFollowUpStaffDialog() {
        docFollowUpStaffDialogVisible = false;
    }

    public void closeDataEntryStaffDialog() {
        dataEntryStaffDialogVisible = false;
    }

    public String viewDocFollowUpStaff(UploadedApplicationListLineBean lineBean) {
        RequestedStaffSearchReqDto requestedStaffSearchReqDto = new RequestedStaffSearchReqDto();
        requestedStaffSearchReqDto.setDaApplicationInfoId(lineBean.getApplicationId());
        requestedStaffSearchReqDto.setStatus(7);
        try {
            docFollowUpStaffLineBeanList = (List<RequestedStaffLineBean>) CommonUtil.getDaoServiceInvoker()
                    .execute(requestedStaffSearchReqDto);
            UserInfoReferReqDto userInfoReferReqDto;
            UserInfoReferResDto userInfoReferResDto;
            String[] str;
            for (RequestedStaffLineBean requestedStaffLineBean : docFollowUpStaffLineBeanList) {
                if (requestedStaffLineBean.getName().contains(",")) {
                    userInfoReferReqDto = new UserInfoReferReqDto();
                    userInfoReferResDto = new UserInfoReferResDto();
                    str = new String[2];
                    str = requestedStaffLineBean.getName().split(VCSCommon.REXCOMMA);
                    userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                    userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                    if (!userInfoReferReqDto.getUser_type_id().equals(3)) {
                        userInfoReferResDto =
                                (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker().execute(userInfoReferReqDto);
                        requestedStaffLineBean.setName(userInfoReferResDto.getUser_name());
                    } else {
                        CustomerIdReferReqDto customerReferReqDto = new CustomerIdReferReqDto();
                        customerReferReqDto.setCustomer_id(userInfoReferReqDto.getUser_id());

                        CustomerIdReferResDto customerReferResDto =
                                (CustomerIdReferResDto) CommonUtil.getDaoServiceInvoker().execute(customerReferReqDto);
                        requestedStaffLineBean.setName(customerReferResDto.getName());
                    }
                }

            }
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                throw new BaseException(e.getCause());
            }
        }
        doReload = false;
        docFollowUpStaffDialogVisible = true;
        return LinkTarget.OK;
    }

    public String viewDataEntryStaff(UploadedApplicationListLineBean lineBean) {
        RequestedStaffSearchReqDto requestedStaffSearchReqDto = new RequestedStaffSearchReqDto();
        requestedStaffSearchReqDto.setDaApplicationInfoId(lineBean.getApplicationId());
        requestedStaffSearchReqDto.setStatus(lineBean.getStatus());
        try {
            dataEntryStaffLineBeanList = (List<RequestedStaffLineBean>) CommonUtil.getDaoServiceInvoker()
                    .execute(requestedStaffSearchReqDto);
            UserInfoReferReqDto userInfoReferReqDto;
            UserInfoReferResDto userInfoReferResDto;
            String[] str;
            for (RequestedStaffLineBean requestedStaffLineBean : dataEntryStaffLineBeanList) {
                if (requestedStaffLineBean.getName().contains(",")) {
                    userInfoReferReqDto = new UserInfoReferReqDto();
                    userInfoReferResDto = new UserInfoReferResDto();
                    str = new String[2];
                    str = requestedStaffLineBean.getName().split(VCSCommon.REXCOMMA);
                    userInfoReferReqDto.setUser_id(Integer.parseInt(str[0]));
                    userInfoReferReqDto.setUser_type_id(Integer.parseInt(str[1]));

                    if (!userInfoReferReqDto.getUser_type_id().equals(3)) {
                        userInfoReferResDto =
                                (UserInfoReferResDto) CommonUtil.getDaoServiceInvoker().execute(userInfoReferReqDto);
                        requestedStaffLineBean.setName(userInfoReferResDto.getUser_name());
                    } else {
                        CustomerIdReferReqDto customerReferReqDto = new CustomerIdReferReqDto();
                        customerReferReqDto.setCustomer_id(userInfoReferReqDto.getUser_id());

                        CustomerIdReferResDto customerReferResDto =
                                (CustomerIdReferResDto) CommonUtil.getDaoServiceInvoker().execute(customerReferReqDto);
                        requestedStaffLineBean.setName(customerReferResDto.getName());
                    }
                }

            }
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                throw new BaseException(e.getCause());
            }
        }
        doReload = false;
        dataEntryStaffDialogVisible = true;
        return LinkTarget.OK;
    }

    public InterestRateManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(InterestRateManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<UploadedApplicationListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<UploadedApplicationListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<UploadedApplicationListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<UploadedApplicationListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public UploadedApplicationListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(UploadedApplicationListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public String getApplicationStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : applicationStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getGenderValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : genderSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getLoanTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : loanTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getApplicationTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : applicationTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getNationalityValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : nationalitySelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getMaritalStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : maritalStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getTypeOfResidenceValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : typeOfResidenceSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getLivingWithValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : livingWithSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getProductTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : productTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getChannelValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : channelSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getRelatinshipValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : relationshipSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getCompanyStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : companyStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getFiletTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : fileTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getJudgementStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : judgementStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getTownshipValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : townshipSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getCityValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : citySelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getEducationValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : educationSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public List<SelectItem> getStatusList() {

        statusList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        item.setLabel(VCSCommon.SPACE);
        item.setValue(VCSCommon.TWO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.DISABLED);
        item.setValue(VCSCommon.ZERO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.ENABLED);
        item.setValue(VCSCommon.ONE);
        statusList.add(item);

        return statusList;
    }

    public void setStatusList(List<SelectItem> statusList) {
        this.statusList = statusList;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public UploadedApplicationListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(UploadedApplicationListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public List<SelectItem> getApplicationStatusSelectItemList() {
        return applicationStatusSelectItemList;
    }

    public void setApplicationStatusSelectItemList(List<SelectItem> applicationStatusSelectItemList) {
        this.applicationStatusSelectItemList = applicationStatusSelectItemList;
    }

    public List<SelectItem> getLoanTypeSelectItemList() {
        return loanTypeSelectItemList;
    }

    public void setLoanTypeSelectItemList(List<SelectItem> loanTypeSelectItemList) {
        this.loanTypeSelectItemList = loanTypeSelectItemList;
    }

    public List<SelectItem> getGenderSelectItemList() {
        return genderSelectItemList;
    }

    public void setGenderSelectItemList(List<SelectItem> genderSelectItemList) {
        this.genderSelectItemList = genderSelectItemList;
    }

    public List<SelectItem> getApplicationTypeSelectItemList() {
        return applicationTypeSelectItemList;
    }

    public void setApplicationTypeSelectItemList(List<SelectItem> applicationTypeSelectItemList) {
        this.applicationTypeSelectItemList = applicationTypeSelectItemList;
    }

    public List<SelectItem> getNationalitySelectItemList() {
        return nationalitySelectItemList;
    }

    public void setNationalitySelectItemList(List<SelectItem> nationalitySelectItemList) {
        this.nationalitySelectItemList = nationalitySelectItemList;
    }

    public List<SelectItem> getMaritalStatusSelectItemList() {
        return maritalStatusSelectItemList;
    }

    public void setMaritalStatusSelectItemList(List<SelectItem> maritalStatusSelectItemList) {
        this.maritalStatusSelectItemList = maritalStatusSelectItemList;
    }

    public List<SelectItem> getTypeOfResidenceSelectItemList() {
        return typeOfResidenceSelectItemList;
    }

    public void setTypeOfResidenceSelectItemList(List<SelectItem> typeOfResidenceSelectItemList) {
        this.typeOfResidenceSelectItemList = typeOfResidenceSelectItemList;
    }

    public List<SelectItem> getLivingWithSelectItemList() {
        return livingWithSelectItemList;
    }

    public void setLivingWithSelectItemList(List<SelectItem> livingWithSelectItemList) {
        this.livingWithSelectItemList = livingWithSelectItemList;
    }

    public List<SelectItem> getProductTypeSelectItemList() {
        return productTypeSelectItemList;
    }

    public void setProductTypeSelectItemList(List<SelectItem> productTypeSelectItemList) {
        this.productTypeSelectItemList = productTypeSelectItemList;
    }

    public List<SelectItem> getChannelSelectItemList() {
        return channelSelectItemList;
    }

    public void setChannelSelectItemList(List<SelectItem> channelSelectItemList) {
        this.channelSelectItemList = channelSelectItemList;
    }

    public List<SelectItem> getRelationshipSelectItemList() {
        return relationshipSelectItemList;
    }

    public void setRelationshipSelectItemList(List<SelectItem> relationshipSelectItemList) {
        this.relationshipSelectItemList = relationshipSelectItemList;
    }

    public List<SelectItem> getCompanyStatusSelectItemList() {
        return companyStatusSelectItemList;
    }

    public void setCompanyStatusSelectItemList(List<SelectItem> companyStatusSelectItemList) {
        this.companyStatusSelectItemList = companyStatusSelectItemList;
    }

    public List<SelectItem> getFileTypeSelectItemList() {
        return fileTypeSelectItemList;
    }

    public void setFileTypeSelectItemList(List<SelectItem> fileTypeSelectItemList) {
        this.fileTypeSelectItemList = fileTypeSelectItemList;
    }

    public Boolean getNrcFrontEditFlag() {
        return nrcFrontEditFlag;
    }

    public void setNrcFrontEditFlag(Boolean nrcFrontEditFlag) {
        this.nrcFrontEditFlag = nrcFrontEditFlag;
    }

    public Boolean getNrcBackEditFlag() {
        return nrcBackEditFlag;
    }

    public void setNrcBackEditFlag(Boolean nrcBackEditFlag) {
        this.nrcBackEditFlag = nrcBackEditFlag;
    }

    public Boolean getResidentProofEditFlag() {
        return residentProofEditFlag;
    }

    public void setResidentProofEditFlag(Boolean residentProofEditFlag) {
        this.residentProofEditFlag = residentProofEditFlag;
    }

    public Boolean getIncomeProofEditFlag() {
        return incomeProofEditFlag;
    }

    public void setIncomeProofEditFlag(Boolean incomeProofEditFlag) {
        this.incomeProofEditFlag = incomeProofEditFlag;
    }

    public Boolean getGuarantorNrcFrontEditFlag() {
        return guarantorNrcFrontEditFlag;
    }

    public void setGuarantorNrcFrontEditFlag(Boolean guarantorNrcFrontEditFlag) {
        this.guarantorNrcFrontEditFlag = guarantorNrcFrontEditFlag;
    }

    public Boolean getGuarantorNrcBackEditFlag() {
        return guarantorNrcBackEditFlag;
    }

    public void setGuarantorNrcBackEditFlag(Boolean guarantorNrcBackEditFlag) {
        this.guarantorNrcBackEditFlag = guarantorNrcBackEditFlag;
    }

    public Boolean getHouseholdCriminalEditFlag() {
        return householdCriminalEditFlag;
    }

    public void setHouseholdCriminalEditFlag(Boolean householdCriminalEditFlag) {
        this.householdCriminalEditFlag = householdCriminalEditFlag;
    }

    public Boolean getApplicantPhotoEditFlag() {
        return applicantPhotoEditFlag;
    }

    public void setApplicantPhotoEditFlag(Boolean applicantPhotoEditFlag) {
        this.applicantPhotoEditFlag = applicantPhotoEditFlag;
    }

    public Boolean getSignatureEditFlag() {
        return signatureEditFlag;
    }

    public void setSignatureEditFlag(Boolean signatureEditFlag) {
        this.signatureEditFlag = signatureEditFlag;
    }

    public List<SelectItem> getJudgementStatusSelectItemList() {
        return judgementStatusSelectItemList;
    }

    public void setJudgementStatusSelectItemList(List<SelectItem> judgementStatusSelectItemList) {
        this.judgementStatusSelectItemList = judgementStatusSelectItemList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public UploadedApplicationSearchReqDto getUploadedApplicationSearchReqDto() {
        return uploadedApplicationSearchReqDto;
    }

    public void setUploadedApplicationSearchReqDto(UploadedApplicationSearchReqDto uploadedApplicationSearchReqDto) {
        this.uploadedApplicationSearchReqDto = uploadedApplicationSearchReqDto;
    }

    public List<RequestedStaffLineBean> getDataEntryStaffLineBeanList() {
        return dataEntryStaffLineBeanList;
    }

    public void setDataEntryStaffLineBeanList(List<RequestedStaffLineBean> dataEntryStaffLineBeanList) {
        this.dataEntryStaffLineBeanList = dataEntryStaffLineBeanList;
    }

    public List<RequestedStaffLineBean> getDocFollowUpStaffLineBeanList() {
        return docFollowUpStaffLineBeanList;
    }

    public void setDocFollowUpStaffLineBeanList(List<RequestedStaffLineBean> docFollowUpStaffLineBeanList) {
        this.docFollowUpStaffLineBeanList = docFollowUpStaffLineBeanList;
    }

    public boolean isDataEntryStaffDialogVisible() {
        return dataEntryStaffDialogVisible;
    }

    public void setDataEntryStaffDialogVisible(boolean dataEntryStaffDialogVisible) {
        this.dataEntryStaffDialogVisible = dataEntryStaffDialogVisible;
    }

    public boolean isDocFollowUpStaffDialogVisible() {
        return docFollowUpStaffDialogVisible;
    }

    public void setDocFollowUpStaffDialogVisible(boolean docFollowUpStaffDialogVisible) {
        this.docFollowUpStaffDialogVisible = docFollowUpStaffDialogVisible;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public Boolean getNotyetFlag() {
        return notyetFlag;
    }

    public void setNotyetFlag(Boolean notyetFlag) {
        this.notyetFlag = notyetFlag;
    }

    public boolean isCheckNotYetFlag() {
        return checkNotYetFlag;
    }

    public void setCheckNotYetFlag(boolean checkNotYetFlag) {
        this.checkNotYetFlag = checkNotYetFlag;
    }

    public ArrayList<SelectItem> getTownshipSelectItemList() {
        return townshipSelectItemList;
    }

    public ArrayList<SelectItem> getCitySelectItemList() {
        return citySelectItemList;
    }

    public void setTownshipSelectItemList(ArrayList<SelectItem> townshipSelectItemList) {
        this.townshipSelectItemList = townshipSelectItemList;
    }

    public void setCitySelectItemList(ArrayList<SelectItem> citySelectItemList) {
        this.citySelectItemList = citySelectItemList;
    }

    public ArrayList<SelectItem> getEducationSelectItemList() {
        return educationSelectItemList;
    }

    public void setEducationSelectItemList(ArrayList<SelectItem> educationSelectItemList) {
        this.educationSelectItemList = educationSelectItemList;
    }

    public Boolean getGuarantorSignatureEditFlag() {
        return guarantorSignatureEditFlag;
    }

    public void setGuarantorSignatureEditFlag(Boolean guarantorSignatureEditFlag) {
        this.guarantorSignatureEditFlag = guarantorSignatureEditFlag;
    }

}
