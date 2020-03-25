/**************************************************************************
 * $Date: 2018-09-18$
 * $Author: Shoon Latt Winne $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.judgementStatusUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.util.StringUtils;

import mm.aeon.com.ass.base.service.judgementStatusUpdateService.JudgementStatusUpdateServiceReqBean;
import mm.aeon.com.ass.base.service.judgementStatusUpdateService.JudgementStatusUpdateServiceResBean;
import mm.aeon.com.ass.front.common.abstractController.AbstractASSController;
import mm.aeon.com.ass.front.common.constants.CommonConstant;
import mm.aeon.com.ass.front.common.constants.DisplayItem;
import mm.aeon.com.ass.front.common.constants.HeaderConstant;
import mm.aeon.com.ass.front.common.constants.MessageId;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.common.util.CommonUtils;
import mm.aeon.com.ass.front.common.util.DisplayItemBean;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class JudgementStatusUploadController extends AbstractASSController
        implements IControllerAccessor<JudgementStatusUploadFormBean, JudgementStatusUploadFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";
    private static Map<String, Integer> headerMap;

    private static String errorStringList;

    @Override
    public JudgementStatusUploadFormBean process(JudgementStatusUploadFormBean formBean) {

        applicationLogger.log("Judgement Status Upload Process started.", LogLevel.INFO);

        formBean.getMessageContainer().clearAllMessages(true);
        if (!isValidData(formBean)) {
            return formBean;
        }
        CSVParser csvParser = null;
        MessageBean msgBean;
        try {

            // boolean isUploadFile = false;

            InputStream inputStream;
            inputStream = formBean.getHeaderBean().getFile().getInputstream();
            File temp = streamTofile(inputStream);

            // SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            String serviceStatus = null;
            JudgementStatusUpdateServiceReqBean updateServiceReqBean;
            csvParser = new CSVParser(new FileReader(temp), CSVFormat.EXCEL.withHeader().withFirstRecordAsHeader()
                    .withIgnoreHeaderCase().withAllowMissingColumnNames(false).withTrim());

            headerMap = csvParser.getHeaderMap();

            for (CSVRecord csvRecord : csvParser) {

                ImportCustomerInfo importCustomerInfo = new ImportCustomerInfo();

                importCustomerInfo = getValidImportCustomerInfo(csvRecord);

                if (importCustomerInfo == null) {
                    formBean.getMessageContainer().clearAllMessages(true);
                    msgBean = new MessageBean(MessageId.ME1056, errorStringList);
                    msgBean.setMessageType(MessageType.ERROR);
                    formBean.getMessageContainer().addMessage(msgBean);
                    return formBean;
                }

                updateServiceReqBean = new JudgementStatusUpdateServiceReqBean();

                updateServiceReqBean.setName(importCustomerInfo.getName());
                updateServiceReqBean.setMemberCardStatus(importCustomerInfo.getMemberCardStatus());
                updateServiceReqBean.setAge(importCustomerInfo.getAge());
                updateServiceReqBean.setCompanyName(importCustomerInfo.getCompanyName());
                updateServiceReqBean.setCreatedTime(importCustomerInfo.getCreatedTime());
                updateServiceReqBean.setCustomerId(importCustomerInfo.getCustomerId());
                updateServiceReqBean.setCustomerNo(importCustomerInfo.getCustomerNo());
                updateServiceReqBean.setDelFlag(importCustomerInfo.getDelFlag());
                updateServiceReqBean.setDob(importCustomerInfo.getDob());
                updateServiceReqBean.setGender(importCustomerInfo.getGender());
                updateServiceReqBean.setMemberCardId(importCustomerInfo.getMemberCardId());
                updateServiceReqBean.setMemberCardStatus(importCustomerInfo.getMemberCardStatus());
                updateServiceReqBean.setMemberFlag(importCustomerInfo.getMemberFlag());
                updateServiceReqBean.setNrcNo(importCustomerInfo.getNrcNo());
                updateServiceReqBean.setPhoneNo(importCustomerInfo.getPhoneNo());
                updateServiceReqBean.setSalary(importCustomerInfo.getSalary());
                updateServiceReqBean.setTownship(importCustomerInfo.getTownship());
                updateServiceReqBean.setStatus(importCustomerInfo.getStatus());
                updateServiceReqBean.setUpdatedTime(importCustomerInfo.getUpdatedTime());
                updateServiceReqBean.setCustAgreementListList(importCustomerInfo.getCustAgreementListList());

                this.getServiceInvoker().addRequest(updateServiceReqBean);
                ResponseMessage responseMessage = this.getServiceInvoker().invoke();
                JudgementStatusUpdateServiceResBean resBean = responseMessage.getMessageBean(0);
                serviceStatus = resBean.getServiceStatus();

                if (ServiceStatusCode.OK.equals(serviceStatus)) {
                    formBean.getMessageContainer().clearAllMessages(true);
                    msgBean = new MessageBean(MessageId.MI0026);
                    msgBean.setMessageType(MessageType.INFO);
                    formBean.getMessageContainer().addMessage(msgBean);
                    applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                } else {
                    msgBean = new MessageBean(MessageId.ME1056, resBean.getErrorMessage());
                    msgBean.setMessageType(MessageType.ERROR);
                    formBean.getMessageContainer().addMessage(msgBean);
                    return formBean;
                }

            }
            csvParser.close();

        } catch (Exception e) {
            try {
                csvParser.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            msgBean = new MessageBean(MessageId.ME1056, e.getMessage());
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            return formBean;

        }

        return formBean;

    }

    private boolean isValidData(JudgementStatusUploadFormBean formBean) {
        boolean isValid = true;
        UploadedFile file = formBean.getHeaderBean().getFile();

        if (file == null) {
            MessageBean msgBean =
                    new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.FILE));
            msgBean.addColumnId(DisplayItem.CSV_FILE);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;

        } else if (!FilenameUtils.getExtension(file.getFileName()).equalsIgnoreCase(VCSCommon.CSV)) {
            MessageBean msgBean = new MessageBean(MessageId.ME1020, VCSCommon.CSV);
            msgBean.addColumnId(DisplayItem.CSV_FILE);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;

        } else if (file.getSize() > CommonUtil.getCSVFileUploadSize()) {
            MessageBean msgBean = new MessageBean(MessageId.ME1023);
            msgBean.addColumnId(DisplayItem.CSV_FILE);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        return isValid;
    }

    public static File streamTofile(InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }

    public static ImportCustomerInfo getValidImportCustomerInfo(CSVRecord csvRecord) throws ParseException {
        ImportCustomerInfo customerInfo = new ImportCustomerInfo();
        List<CustAgreementList> agreementList = new ArrayList<CustAgreementList>();

        String tempStr = "";
        boolean hasInvalidAgreement = false;
        int agCount = 1;
        int agIndex = headerMap.get(HeaderConstant.APPLICATION_FORM_NO_1);

        for (Entry<String, Integer> entry : headerMap.entrySet()) {

            if (entry.getValue() >= csvRecord.size()) {
                continue;
            }

            tempStr = csvRecord.get(entry.getValue());

            if (CommonUtils.isNullOrTrimedEmpty(tempStr)) {
                if (!entry.getKey().toLowerCase().contains(CommonConstant.AGREEMENT_LOWERE_CASE)
                        && !entry.getKey().equals(HeaderConstant.MEMBER_CARD_ID_HEADER)
                        && !entry.getKey().equals(HeaderConstant.MEMBER_CARD_STATUS_HEADER)
                        && !entry.getKey().toLowerCase().contains(HeaderConstant.APPLICATION_FORM_NO_LOWER_CASE)
                        && !entry.getKey().toLowerCase().contains(HeaderConstant.JUDGEMENT_DATE_LOWER_CASE)
                        && !entry.getKey().toLowerCase().contains(HeaderConstant.JUDGEMENT_RESULT_LOWER_CASE)) {
                    errorStringList = CommonUtils.recordEmptyErrorLog(entry.getKey(), csvRecord);
                    return null;
                }
            }

            switch (entry.getKey()) {

                case HeaderConstant.COMPANY_NAME_HEADER:

                    customerInfo.setCompanyName(tempStr);
                    break;

                case HeaderConstant.CUSTOMER_NAME_HEADER:

                    customerInfo.setName(tempStr);
                    break;

                case HeaderConstant.CUSTOMER_NO_HEADER:

                    if (!CommonUtils.isValidCustomerNo(tempStr)) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.CUSTOMER_NO_HEADER, csvRecord);
                        return null;
                    }
                    customerInfo.setCustomerNo(tempStr);
                    break;

                case HeaderConstant.DOB_HEADER:

                    if (!CommonUtils.isValidDOB(tempStr)) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.DOB_HEADER, csvRecord);
                        return null;
                    }

                    Date date = CommonUtils.dobStringToDate(tempStr);
                    if (null == date) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.DOB_HEADER, csvRecord);
                        return null;
                    }

                    customerInfo.setDob(date);
                    break;

                case HeaderConstant.GENDER_HEADER:
                    int i = CommonUtils.checkGender(tempStr);
                    if (i == 0) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.GENDER_HEADER, csvRecord);
                        return null;
                    }
                    customerInfo.setGender(i);
                    break;

                case HeaderConstant.MEMBER_CARD_ID_HEADER:
                    if (!CommonUtils.isValidMemberCardId(tempStr)) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.MEMBER_CARD_ID_HEADER, csvRecord);
                        return null;
                    }
                    customerInfo.setMemberCardId(tempStr);
                    break;

                case HeaderConstant.MEMBER_CARD_STATUS_HEADER:
                    if (!StringUtils.isEmpty(tempStr)) {
                        customerInfo.setMemberCardStatus(Integer.parseInt(tempStr));
                    }
                    break;

                case HeaderConstant.NRC_HEADER:
                    if (!CommonUtils.isValidNRCNo(tempStr)) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.NRC_HEADER, csvRecord);
                        return null;
                    }

                    customerInfo.setNrcNo(tempStr);
                    break;

                case HeaderConstant.PHONE_NO_HEADER:
                    String modPhoneNo = tempStr;
                    String phoneStartChar = modPhoneNo.substring(0, 1);
                    if (phoneStartChar.equals("9")) {
                        modPhoneNo = 0 + modPhoneNo;
                    }

                    if (!CommonUtils.isValidNumber(modPhoneNo)) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.PHONE_NO_HEADER, csvRecord);
                        return null;
                    }
                    customerInfo.setPhoneNo(modPhoneNo);
                    break;

                case HeaderConstant.SALARY_HEADER:

                    if (!CommonUtils.isValidCurrency(tempStr)) {
                        errorStringList = CommonUtils.recordErrorLog(HeaderConstant.SALARY_HEADER, csvRecord);
                        return null;
                    }
                    customerInfo.setSalary(Double.valueOf(tempStr.replaceAll(",", "").toString()));
                    break;

                case HeaderConstant.TOWNSHIP_ADDRESS_HEADER:

                    customerInfo.setTownship(tempStr);
                    break;

                case HeaderConstant.DEL_FLAG_HEADER:

                    customerInfo.setDelFlag(CommonUtils.checkDelFlag(tempStr));
                    break;

            }
        }

        while (csvRecord.size() >= agIndex + 8) {

            if (!isAgreementEmpty(agIndex, csvRecord)) {

                CustAgreementList cusAgr = new CustAgreementList();

                // check agreement no
                if (!!StringUtils.isEmpty(csvRecord.get(agIndex))) {
                    errorStringList =
                            CommonUtils.recordErrorLog(CommonConstant.APPLICATION_FORM_NO + agCount, csvRecord);
                    hasInvalidAgreement = true;
                } else {

                    cusAgr.setApplicationNo(csvRecord.get(agIndex));

                    // Check Judgement Result
                    if (!CommonUtils.isValidNumber(csvRecord.get(agIndex + 1))) {
                        hasInvalidAgreement = true;
                        errorStringList =
                                CommonUtils.recordErrorLog(CommonConstant.JUDGEMENT_RESULT + agCount, csvRecord);
                    } else {
                        cusAgr.setJudgementResult(Integer.valueOf(csvRecord.get(agIndex + 1)));
                    }

                    // Check Judgement Date
                    if (!CommonUtils.isValidNumber(csvRecord.get(agIndex + 2))) {
                        hasInvalidAgreement = true;
                        errorStringList =
                                CommonUtils.recordErrorLog(CommonConstant.JUDGEMENT_DATE + agCount, csvRecord);
                    } else {
                        Date judgementDate = CommonUtils.dobStringToDate(csvRecord.get(agIndex + 2));
                        if (null == judgementDate) {
                            CommonUtils.recordErrorLog(HeaderConstant.DOB_HEADER, csvRecord);
                            return null;
                        }
                        cusAgr.setJudgementDate(judgementDate);
                    }

                    if (!StringUtils.isEmpty(csvRecord.get(agIndex + 3))) {
                        // Check Agreement No
                        if (!CommonUtils.isValidAgreementNumber(csvRecord.get(agIndex + 3))) {
                            errorStringList =
                                    CommonUtils.recordErrorLog(CommonConstant.AGREEMENT_NO + agCount, csvRecord);
                            hasInvalidAgreement = true;
                        } else {
                            cusAgr.setAgreementNo(csvRecord.get(agIndex + 3));
                        }

                        // Check QRshow
                        if (!CommonUtils.isValidNumber(csvRecord.get(agIndex + 4))) {
                            hasInvalidAgreement = true;
                            errorStringList =
                                    CommonUtils.recordErrorLog(CommonConstant.AGREEMENT_QR_SHOW + agCount, csvRecord);
                        } else {
                            cusAgr.setQrShow(Integer.valueOf(csvRecord.get(agIndex + 4)));
                        }

                        // Check FinancialAmount
                        if (!CommonUtils.isValidCurrency(csvRecord.get(agIndex + 5))) {
                            hasInvalidAgreement = true;
                            errorStringList = CommonUtils
                                    .recordErrorLog(CommonConstant.AGREEMENT_FINANCIAL_AMT + agCount, csvRecord);
                        } else {
                            cusAgr.setFinancialAmt(Double.valueOf(csvRecord.get(agIndex + 5)));
                        }

                        // Check FinancialTerm
                        if (!CommonUtils.isValidNumber(csvRecord.get(agIndex + 6))) {
                            hasInvalidAgreement = true;
                            errorStringList = CommonUtils
                                    .recordErrorLog(CommonConstant.AGREEMENT_FINANCIAL_TERM + agCount, csvRecord);
                        } else {
                            cusAgr.setFinancialTerm(Integer.valueOf(csvRecord.get(agIndex + 6)));
                        }

                        // Check FinancialStatus
                        if (!CommonUtils.isValidNumber(csvRecord.get(agIndex + 7))) {
                            hasInvalidAgreement = true;
                            errorStringList = CommonUtils
                                    .recordErrorLog(CommonConstant.AGREEMENT_FINANCIAL_STATUS + agCount, csvRecord);
                        } else {
                            cusAgr.setFinancialStatus(Integer.valueOf(csvRecord.get(agIndex + 7)));
                        }
                    }
                    if (!hasInvalidAgreement) {
                        cusAgr.setCreatedTime(CommonUtil.getCurrentTimeStamp());
                        cusAgr.setUpdatedTime(CommonUtil.getCurrentTimeStamp());

                        agreementList.add(cusAgr);
                    }
                }

            } else {

                // CommonUtils.recordEmptyErrorLog("Agreement " + agCount,
                // csvRecord);

            }

            agCount += 1;
            agIndex += 8;

        }

        if (hasInvalidAgreement) {
            return null;
        }

        customerInfo.setCustAgreementListList(agreementList);
        customerInfo.setCreatedTime(CommonUtil.getCurrentTimeStamp());
        customerInfo.setUpdatedTime(CommonUtil.getCurrentTimeStamp());

        return customerInfo;

    }

    private static boolean isAgreementEmpty(int stratIndex, CSVRecord csvRecord) {

        return CommonUtils.isNullOrTrimedEmpty(csvRecord.get(stratIndex))
                && CommonUtils.isNullOrTrimedEmpty(csvRecord.get(stratIndex + 1))
                && CommonUtils.isNullOrTrimedEmpty(csvRecord.get(stratIndex + 2))
                && CommonUtils.isNullOrTrimedEmpty(csvRecord.get(stratIndex + 3))
                && CommonUtils.isNullOrTrimedEmpty(csvRecord.get(stratIndex + 4));

    }

}
