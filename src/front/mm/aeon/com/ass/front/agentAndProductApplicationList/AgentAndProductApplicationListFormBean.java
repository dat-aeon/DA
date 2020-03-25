/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agentAndProductApplicationList;

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
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;
import org.springframework.util.StringUtils;

import mm.aeon.com.ass.base.dto.agentAndProductApplicationListSearch.AgentAndProductApplicationPurchaseAttachmentSearchResDto;
import mm.aeon.com.ass.base.dto.agentAndProductApplicationListSearch.AgentAndProductApplicationSearchReqDto;
import mm.aeon.com.ass.base.dto.documentFollowUpApplicationListSearch.RequestedStaffSearchReqDto;
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

@Name("agentAndProductApplicationListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentAndProductApplicationListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private boolean checkNotYetFlag;

    private String loginUserName;

    private List<AgentAndProductApplicationListLineBean> lineBeans;

    private LazyDataModel<AgentAndProductApplicationListLineBean> lazyModel;

    private AgentAndProductApplicationListLineBean lineBean;

    private ArrayList<SelectItem> statusList;

    private int totalCount;

    private AgentAndProductApplicationSearchReqDto agentAndProductApplicationSearchReqDto;

    private AgentAndProductApplicationListHeaderBean searchHeaderBean;

    private ArrayList<SelectItem> userTypeSelectItemList;

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

    private ArrayList<SelectItem> settlementTypeSelectItemList;

    private ArrayList<SelectItem> townshipSelectItemList;

    private ArrayList<SelectItem> citySelectItemList;

    private ArrayList<SelectItem> educationSelectItemList;

    private Boolean notyetFlag;

    @Out(required = false, value = "securityUpdateParam")
    private InterestRateManagementHeaderBean updateParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    private Integer applicationId;

    private List<RequestedStaffLineBean> requestedStaffLineBeanList;

    private boolean dialogVisible;

    @Begin(nested = true)
    @Action
    public void init() {

        ASSLogger logger = new ASSLogger();

        Boolean result = CommonUtil.validateUrlAccess(CommonMenu.AGENT_AND_PRODUCT_APPLICATIONS);
        if (result == false) {
            logger.log("Invalid URL Access.[Security question List]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new AgentAndProductApplicationListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgentAndProductApplicationListPaginationController(totalCount,
                    agentAndProductApplicationSearchReqDto);
        }
        loginUserName = CommonUtil.getLoginUserInfo().getId().toString();
        return LinkTarget.OK;
    }

    @Action
    public String upload(AgentAndProductApplicationListLineBean lineBean) {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            searchHeaderBean = new AgentAndProductApplicationListHeaderBean();
            init = true;
            return LinkTarget.SEARCH;
        }

        doReload = new Boolean(true);
        this.lineBean = lineBean;
        searchHeaderBean = new AgentAndProductApplicationListHeaderBean();
        return LinkTarget.OK;

    }

    @Action
    public String followUpUpload(AgentAndProductApplicationListLineBean lineBean) {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            searchHeaderBean = new AgentAndProductApplicationListHeaderBean();
            init = true;
            return LinkTarget.SEARCH;
        }

        doReload = new Boolean(true);
        this.lineBean = lineBean;
        searchHeaderBean = new AgentAndProductApplicationListHeaderBean();
        return LinkTarget.OK;

    }

    @Action
    public String notYet(AgentAndProductApplicationListLineBean lineBean) {
        if (checkNotYetFlag)
            return LinkTarget.OK;
        notyetFlag = true;
        this.getMessageContainer().clearAllMessages(true);
        int otherPhotoCount = 0;

        try {
            FileHandler
                    .forceDelete(FileHandler.getSystemPath() + "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        List<String> otherImageFilePath = new ArrayList<>();
        for (AgentAndProductApplicationPurchaseAttachmentSearchResDto resPurchaseFileDto : lineBean
                .getPurchaseAttachmentDtoList()) {
            if (resPurchaseFileDto.getFileType() == DACommon.MEMBER_CARD_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "membercard.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setMemberCardImageFilePath(destinationFilePath);
                }
            }
            if (resPurchaseFileDto.getFileType() == DACommon.ULOAN_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "uloan.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setUloanImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.INVOICE_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "invoice.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setInvoiceImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.OTHER_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    otherPhotoCount++;
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "other" + otherPhotoCount + ".jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    otherImageFilePath.add(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.LETTER_OF_AGREEMENT_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "letterOfAgreement.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setLetterOfAgreementImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.CASH_RECEIPT_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "cashReceipt.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setCashReceiptImageFilePath(destinationFilePath);
                }
            }
        }
        lineBean.setOthersImageFilePath(otherImageFilePath);

        double totalProductPrice = lineBean.getApprovedFinanceAmount();
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

    @Action
    public String followUp(AgentAndProductApplicationListLineBean lineBean) {
        if (checkNotYetFlag)
            return LinkTarget.OK;
        notyetFlag = true;
        this.getMessageContainer().clearAllMessages(true);
        int otherPhotoCount = 0;

        try {
            FileHandler
                    .forceDelete(FileHandler.getSystemPath() + "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        List<String> otherImageFilePath = new ArrayList<>();
        for (AgentAndProductApplicationPurchaseAttachmentSearchResDto resPurchaseFileDto : lineBean
                .getPurchaseAttachmentDtoList()) {
            if (resPurchaseFileDto.getFileType() == DACommon.MEMBER_CARD_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "membercard.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setMemberCardImageFilePath(destinationFilePath);
                }
            }
            if (resPurchaseFileDto.getFileType() == DACommon.ULOAN_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "uloan.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setUloanImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.INVOICE_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "invoice.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setInvoiceImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.OTHER_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    otherPhotoCount++;
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "other" + otherPhotoCount + ".jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    otherImageFilePath.add(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.LETTER_OF_AGREEMENT_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "letterOfAgreement.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setLetterOfAgreementImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == DACommon.CASH_RECEIPT_ATTACHMENT) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "cashRecipt.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setCashReceiptImageFilePath(destinationFilePath);
                }
            }
        }
        lineBean.setOthersImageFilePath(otherImageFilePath);

        this.lineBean = lineBean;
        return LinkTarget.FOLLOW_UP;
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

    public String detail(AgentAndProductApplicationListLineBean lineBean) {
        notyetFlag = false;
        this.getMessageContainer().clearAllMessages(true);

        int otherPhotoCount = 0;
        List<String> otherImageFilePath = new ArrayList<>();
        for (AgentAndProductApplicationPurchaseAttachmentSearchResDto resPurchaseFileDto : lineBean
                .getPurchaseAttachmentDtoList()) {
            if (resPurchaseFileDto.getFileType() == 1) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "membercard.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setMemberCardImageFilePath(destinationFilePath);
                }
            }
            if (resPurchaseFileDto.getFileType() == 2) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "uloan.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setUloanImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == 3) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "invoice.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setInvoiceImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == 4) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    otherPhotoCount++;
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "other" + otherPhotoCount + ".jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    otherImageFilePath.add(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == 5) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath =
                            "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder() + lineBean.getCustomerId() + "/"
                                    + lineBean.getApplicationNo() + "/" + "letterOfAgreement.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setLetterOfAgreementImageFilePath(destinationFilePath);
                }
            }

            if (resPurchaseFileDto.getFileType() == 6) {
                if (!StringUtils.isEmpty(resPurchaseFileDto.getFilePath())) {
                    String uploadPath =
                            CommonUtil.getUploadImageBaseFilePath() + CommonUtil.getPurchaseInfoUploadImageFolder();
                    File sourceFile = new File(uploadPath + resPurchaseFileDto.getFilePath());
                    String destinationFilePath = "/temp" + CommonUtil.getAttachmentInfoUploadImageFolder()
                            + lineBean.getCustomerId() + "/" + lineBean.getApplicationNo() + "/" + "cashReceipt.jpg";
                    File destinationFile = new File(FileHandler.getSystemPath() + destinationFilePath);

                    try {

                        FileHandler.copyFile(sourceFile, destinationFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lineBean.setCashReceiptImageFilePath(destinationFilePath);
                }
            }
        }
        lineBean.setOthersImageFilePath(otherImageFilePath);

        double totalProductPrice = lineBean.getApprovedFinanceAmount();
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

    public void closeDialog() {
        dialogVisible = false;
    }

    public String viewRequestedStaff(AgentAndProductApplicationListLineBean lineBean) {

        RequestedStaffSearchReqDto requestedStaffSearchReqDto = new RequestedStaffSearchReqDto();
        requestedStaffSearchReqDto.setDaApplicationInfoId(lineBean.getApplicationId());
        requestedStaffSearchReqDto.setStatus(lineBean.getStatus());
        try {
            requestedStaffLineBeanList = (List<RequestedStaffLineBean>) CommonUtil.getDaoServiceInvoker()
                    .execute(requestedStaffSearchReqDto);
            UserInfoReferReqDto userInfoReferReqDto;
            UserInfoReferResDto userInfoReferResDto;
            String[] str;
            for (RequestedStaffLineBean requestedStaffLineBean : requestedStaffLineBeanList) {
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
        dialogVisible = true;
        return LinkTarget.OK;
    }

    @Action
    public String toggleValidStatus(InterestRateListLineBean lineBean) {
        this.lineBean = null;
        if (!this.getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }
        return LinkTarget.OK;
    }

    public String delete() {
        this.doReload = false;
        this.lineBean = null;
        if (!getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }

        return LinkTarget.OK;
    }

    public void reset() {
        this.searchHeaderBean = new AgentAndProductApplicationListHeaderBean();
    }

    @Action
    public String back() {
        return LinkTarget.BACK;
    }

    @Action
    public String followUpBack() {
        return LinkTarget.BACK;
    }

    public InterestRateManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(InterestRateManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public List<AgentAndProductApplicationListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<AgentAndProductApplicationListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<AgentAndProductApplicationListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgentAndProductApplicationListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AgentAndProductApplicationListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgentAndProductApplicationListLineBean lineBean) {
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

    public String getSettlementTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : settlementTypeSelectItemList) {
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

    public Double getProcessingFees(Double i) {
        if (i != null) {
            return CommonUtil.calculateProcessingFees(i);
        }
        return (double) 0;
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

    public AgentAndProductApplicationListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(AgentAndProductApplicationListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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

    public ArrayList<SelectItem> getSettlementTypeSelectItemList() {
        return settlementTypeSelectItemList;
    }

    public void setSettlementTypeSelectItemList(ArrayList<SelectItem> settlementTypeSelectItemList) {
        this.settlementTypeSelectItemList = settlementTypeSelectItemList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public AgentAndProductApplicationSearchReqDto getAgentAndProductApplicationSearchReqDto() {
        return agentAndProductApplicationSearchReqDto;
    }

    public void setAgentAndProductApplicationSearchReqDto(
            AgentAndProductApplicationSearchReqDto agentAndProductApplicationSearchReqDto) {
        this.agentAndProductApplicationSearchReqDto = agentAndProductApplicationSearchReqDto;
    }

    public Boolean getNotyetFlag() {
        return notyetFlag;
    }

    public void setNotyetFlag(Boolean notyetFlag) {
        this.notyetFlag = notyetFlag;
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

    public ArrayList<SelectItem> getUserTypeSelectItemList() {
        return userTypeSelectItemList;
    }

    public void setUserTypeSelectItemList(ArrayList<SelectItem> userTypeSelectItemList) {
        this.userTypeSelectItemList = userTypeSelectItemList;
    }

    public List<RequestedStaffLineBean> getRequestedStaffLineBeanList() {
        return requestedStaffLineBeanList;
    }

    public void setRequestedStaffLineBeanList(List<RequestedStaffLineBean> requestedStaffLineBeanList) {
        this.requestedStaffLineBeanList = requestedStaffLineBeanList;
    }

    public boolean isDialogVisible() {
        return dialogVisible;
    }

    public void setDialogVisible(boolean dialogVisible) {
        this.dialogVisible = dialogVisible;
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

}
