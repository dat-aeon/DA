/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationRequestList;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;
import org.springframework.util.StringUtils;

import mm.aeon.com.ass.base.dto.agreementModificationRequestListSearch.AgreementModificationRequestAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.agreementModificationRequestListSearch.AgreementModificationRequestSearchReqDto;
import mm.aeon.com.ass.front.agreementModificationRequestListManagement.AgreementModificationRequestHeaderBean;
import mm.aeon.com.ass.front.common.LoanCalculator;
import mm.aeon.com.ass.front.common.ZipDir;
import mm.aeon.com.ass.front.common.constants.CommonMenu;
import mm.aeon.com.ass.front.common.constants.DACommon;
import mm.aeon.com.ass.front.common.constants.LinkTarget;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.FileHandler;
import mm.aeon.com.ass.front.interestRateList.InterestRateListLineBean;
import mm.aeon.com.ass.front.interestRateManagement.InterestRateManagementHeaderBean;
import mm.aeon.com.ass.front.sessions.LoginUserInfo;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.LogLevel;

@Name("agreementModificationRequestFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgreementModificationRequestListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private String loginUserName;
    private boolean checkNotYetFlag;

    private List<AgreementModificationRequestListLineBean> lineBeans;

    private LazyDataModel<AgreementModificationRequestListLineBean> lazyModel;

    private AgreementModificationRequestListLineBean lineBean;
    private Boolean notyetFlag;
    @Out(required = false, value = "newApplicationUpdateParam")
    private AgreementModificationRequestListHeaderBean newApplicationUpdateParam;

    private ArrayList<SelectItem> statusList;

    private AgreementModificationRequestListHeaderBean searchHeaderBean;

    private ArrayList<SelectItem> applicationStatusSelectItemList;

    private ArrayList<SelectItem> loanTypeSelectItemList;

    private ArrayList<SelectItem> genderSelectItemList;

    private ArrayList<SelectItem> applicationTypeSelectItemList;

    private ArrayList<SelectItem> nationalitySelectItemList;

    private ArrayList<SelectItem> maritalStatusSelectItemList;

    private ArrayList<SelectItem> typeOfResidenceSelectItemList;

    private ArrayList<SelectItem> livingWithSelectItemList;

    private ArrayList<SelectItem> productTypeSelectItemList;

    private ArrayList<SelectItem> channelSelectItemList;

    private ArrayList<SelectItem> relationshipSelectItemList;

    private ArrayList<SelectItem> companyStatusSelectItemList;

    private ArrayList<SelectItem> fileTypeSelectItemList;

    private ArrayList<SelectItem> townshipSelectItemList;

    private ArrayList<SelectItem> citySelectItemList;

    private ArrayList<SelectItem> educationSelectItemList;

    private int totalCount;

    private AgreementModificationRequestHeaderBean headerBean;

    private AgreementModificationRequestSearchReqDto agreementModificationRequestSearchReqDto;

    private Integer newApplicationId;

    @Out(required = false, value = "securityUpdateParam")
    private InterestRateManagementHeaderBean updateParam;

    private boolean init = true;
    public Boolean newStatus = false;
    public Boolean cancelStatus = false;
    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    private Integer applicationId;

    private Boolean lockFlag;

    private Timestamp lockTime;

    private String lockBy;

    @Begin(nested = true)
    @Action
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.AGREEMENT_MODIFICATION_REQUEST);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new AgreementModificationRequestListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgreementModificationRequestListPaginationController(totalCount,
                    agreementModificationRequestSearchReqDto);
        }
        loginUserName = CommonUtil.getLoginUserInfo().getId().toString();
        LoginUserInfo loginUserInfo = CommonUtil.getLoginUserInfo();
        System.out.println(loginUserInfo);
        return LinkTarget.OK;
    }

    @Action
    public String cancelStatus(AgreementModificationRequestListLineBean lineBean) {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            searchHeaderBean = new AgreementModificationRequestListHeaderBean();
            init = false;
            return LinkTarget.SEARCH;
        }
        doReload = new Boolean(true);
        cancelStatus = true;
        newStatus = false;
        this.lineBean = lineBean;
        searchHeaderBean = new AgreementModificationRequestListHeaderBean();
        return LinkTarget.OK;
    }

    @Action
    public String uploaded(AgreementModificationRequestListLineBean lineBean) {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            searchHeaderBean = new AgreementModificationRequestListHeaderBean();
            init = true;
            return LinkTarget.SEARCH;
        }
        newStatus = true;
        cancelStatus = false;
        doReload = new Boolean(true);
        this.lineBean = lineBean;
        searchHeaderBean = new AgreementModificationRequestListHeaderBean();
        return LinkTarget.OK;

    }

    @Action
    public String cancel(AgreementModificationRequestListLineBean lineBean) {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            searchHeaderBean = new AgreementModificationRequestListHeaderBean();
            init = true;
            return LinkTarget.SEARCH;
        }
        newStatus = true;
        cancelStatus = false;
        doReload = new Boolean(true);
        this.lineBean = lineBean;
        this.headerBean = null;
        searchHeaderBean = new AgreementModificationRequestListHeaderBean();
        return LinkTarget.OK;

    }

    public void downloadPhotoZip() throws IOException {
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
    public String notYet(AgreementModificationRequestListLineBean lineBean) {
        if (checkNotYetFlag) {
            return LinkTarget.OK;
        }
        /*
         * if (notyetFlag != null) if (notyetFlag == false ||
         * lineBean.getLockBy().equals(CommonUtil.getLoginUserInfo().getUserName())) return LinkTarget.OK;
         */
        notyetFlag = true;
        this.getMessageContainer().clearAllMessages(true);

        try {
            FileHandler
                    .forceDelete(FileHandler.getSystemPath() + "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (AgreementModificationRequestAttachmentSearchResDto resFileDto : lineBean.getAttachmentDtoList()) {
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
        this.headerBean = new AgreementModificationRequestHeaderBean();
        newApplicationId = lineBean.getApplicationId();
        return LinkTarget.NOTYET;

    }

    public String detail(AgreementModificationRequestListLineBean lineBean) {

        notyetFlag = false;
        this.getMessageContainer().clearAllMessages(true);

        for (AgreementModificationRequestAttachmentSearchResDto resFileDto : lineBean.getAttachmentDtoList()) {
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
        this.searchHeaderBean = new AgreementModificationRequestListHeaderBean();
    }

    @Action
    public String back() {
        return LinkTarget.BACK;
    }

    public Integer getNewApplicationId() {
        return newApplicationId;
    }

    public void setNewApplicationId(Integer newApplicationId) {
        this.newApplicationId = newApplicationId;
    }

    public AgreementModificationRequestListHeaderBean getNewApplicationUpdateParam() {
        return newApplicationUpdateParam;
    }

    public void setNewApplicationUpdateParam(AgreementModificationRequestListHeaderBean newApplicationUpdateParam) {
        this.newApplicationUpdateParam = newApplicationUpdateParam;
    }

    public InterestRateManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(InterestRateManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<AgreementModificationRequestListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<AgreementModificationRequestListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<AgreementModificationRequestListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgreementModificationRequestListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AgreementModificationRequestListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgreementModificationRequestListLineBean lineBean) {
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

    public ArrayList<SelectItem> getStatusList() {

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

    public void setStatusList(ArrayList<SelectItem> statusList) {
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

    public AgreementModificationRequestListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(AgreementModificationRequestListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = newApplicationId;
    }

    public ArrayList<SelectItem> getApplicationStatusSelectItemList() {
        return applicationStatusSelectItemList;
    }

    public void setApplicationStatusSelectItemList(ArrayList<SelectItem> applicationStatusSelectItemList) {
        this.applicationStatusSelectItemList = applicationStatusSelectItemList;
    }

    public ArrayList<SelectItem> getLoanTypeSelectItemList() {
        return loanTypeSelectItemList;
    }

    public void setLoanTypeSelectItemList(ArrayList<SelectItem> loanTypeSelectItemList) {
        this.loanTypeSelectItemList = loanTypeSelectItemList;
    }

    public ArrayList<SelectItem> getGenderSelectItemList() {
        return genderSelectItemList;
    }

    public Boolean getNotyetFlag() {
        return notyetFlag;
    }

    public void setNotyetFlag(Boolean notyetFlag) {
        this.notyetFlag = notyetFlag;
    }

    public void setGenderSelectItemList(ArrayList<SelectItem> genderSelectItemList) {
        this.genderSelectItemList = genderSelectItemList;
    }

    public ArrayList<SelectItem> getApplicationTypeSelectItemList() {
        return applicationTypeSelectItemList;
    }

    public void setApplicationTypeSelectItemList(ArrayList<SelectItem> applicationTypeSelectItemList) {
        this.applicationTypeSelectItemList = applicationTypeSelectItemList;
    }

    public ArrayList<SelectItem> getNationalitySelectItemList() {
        return nationalitySelectItemList;
    }

    public void setNationalitySelectItemList(ArrayList<SelectItem> nationalitySelectItemList) {
        this.nationalitySelectItemList = nationalitySelectItemList;
    }

    public ArrayList<SelectItem> getMaritalStatusSelectItemList() {
        return maritalStatusSelectItemList;
    }

    public void setMaritalStatusSelectItemList(ArrayList<SelectItem> maritalStatusSelectItemList) {
        this.maritalStatusSelectItemList = maritalStatusSelectItemList;
    }

    public ArrayList<SelectItem> getTypeOfResidenceSelectItemList() {
        return typeOfResidenceSelectItemList;
    }

    public void setTypeOfResidenceSelectItemList(ArrayList<SelectItem> typeOfResidenceSelectItemList) {
        this.typeOfResidenceSelectItemList = typeOfResidenceSelectItemList;
    }

    public ArrayList<SelectItem> getLivingWithSelectItemList() {
        return livingWithSelectItemList;
    }

    public void setLivingWithSelectItemList(ArrayList<SelectItem> livingWithSelectItemList) {
        this.livingWithSelectItemList = livingWithSelectItemList;
    }

    public ArrayList<SelectItem> getProductTypeSelectItemList() {
        return productTypeSelectItemList;
    }

    public void setProductTypeSelectItemList(ArrayList<SelectItem> productTypeSelectItemList) {
        this.productTypeSelectItemList = productTypeSelectItemList;
    }

    public ArrayList<SelectItem> getChannelSelectItemList() {
        return channelSelectItemList;
    }

    public void setChannelSelectItemList(ArrayList<SelectItem> channelSelectItemList) {
        this.channelSelectItemList = channelSelectItemList;
    }

    public ArrayList<SelectItem> getRelationshipSelectItemList() {
        return relationshipSelectItemList;
    }

    public void setRelationshipSelectItemList(ArrayList<SelectItem> relationshipSelectItemList) {
        this.relationshipSelectItemList = relationshipSelectItemList;
    }

    public ArrayList<SelectItem> getCompanyStatusSelectItemList() {
        return companyStatusSelectItemList;
    }

    public void setCompanyStatusSelectItemList(ArrayList<SelectItem> companyStatusSelectItemList) {
        this.companyStatusSelectItemList = companyStatusSelectItemList;
    }

    public ArrayList<SelectItem> getFileTypeSelectItemList() {
        return fileTypeSelectItemList;
    }

    public void setFileTypeSelectItemList(ArrayList<SelectItem> fileTypeSelectItemList) {
        this.fileTypeSelectItemList = fileTypeSelectItemList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public AgreementModificationRequestSearchReqDto getAgreementModificationRequestSearchReqDto() {
        return agreementModificationRequestSearchReqDto;
    }

    public void setAgreementModificationRequestSearchReqDto(
            AgreementModificationRequestSearchReqDto agreementModificationRequestSearchReqDto) {
        this.agreementModificationRequestSearchReqDto = agreementModificationRequestSearchReqDto;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
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

    public AgreementModificationRequestHeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(AgreementModificationRequestHeaderBean headerBean) {
        this.headerBean = headerBean;
    }

}
