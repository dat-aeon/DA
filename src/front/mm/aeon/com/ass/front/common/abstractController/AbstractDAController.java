/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Khin Yadanar Thein $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.common.abstractController;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import mm.aeon.com.ass.base.dto.applicationTypeList.ApplicationTypeSelectListReqDto;
import mm.aeon.com.ass.base.dto.applicationTypeList.ApplicationTypeSelectListResDto;
import mm.aeon.com.ass.base.dto.citySearch.CitySearchReqDto;
import mm.aeon.com.ass.base.dto.citySearch.CitySearchResDto;
import mm.aeon.com.ass.base.dto.customerTypeList.CustomerTypeSelectListReqDto;
import mm.aeon.com.ass.base.dto.customerTypeList.CustomerTypeSelectListResDto;
import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSearchReqDto;
import mm.aeon.com.ass.base.dto.departmentListSearch.DepartmentListSearchResDto;
import mm.aeon.com.ass.base.dto.loanTypeList.LoanTypeSelectListReqDto;
import mm.aeon.com.ass.base.dto.loanTypeList.LoanTypeSelectListResDto;
import mm.aeon.com.ass.base.dto.menuDataSelectList.MainMenuDataSelectListReqDto;
import mm.aeon.com.ass.base.dto.menuDataSelectList.MainMenuDataSelectListResDto;
import mm.aeon.com.ass.base.dto.menuDataSelectList.MenuDataSelectListReqDto;
import mm.aeon.com.ass.base.dto.menuDataSelectList.MenuDataSelectListResDto;
import mm.aeon.com.ass.base.dto.menuDataSelectList.SubMenuDataSelectListReqDto;
import mm.aeon.com.ass.base.dto.menuDataSelectList.SubMenuDataSelectListResDto;
import mm.aeon.com.ass.base.dto.productTypeList.ProductTypeSelectListReqDto;
import mm.aeon.com.ass.base.dto.productTypeList.ProductTypeSelectListResDto;
import mm.aeon.com.ass.base.dto.townshipSearch.TownshipSearchReqDto;
import mm.aeon.com.ass.base.dto.townshipSearch.TownshipSearchResDto;
import mm.aeon.com.ass.base.dto.userGroupInfoSearch.UserGroupSearchReqDto;
import mm.aeon.com.ass.base.dto.userGroupInfoSearch.UserGroupSearchResDto;
import mm.aeon.com.ass.front.common.constants.VCSCommon;
import mm.aeon.com.ass.front.common.util.CommonUtil;
import mm.aeon.com.ass.front.sessions.LoginUserInfo;
import mm.aeon.com.ass.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

/**
 * AbstractProjectController Class.
 * <p>
 * 
 * <pre>
 * 
 * </pre>
 * 
 * </p>
 */
public abstract class AbstractDAController extends AbstractController {

    private ASSLogger logger = new ASSLogger();

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    protected ArrayList<SelectItem> getEmptyList() {

        applicationLogger.log("User Search Process started.", LogLevel.INFO);

        ArrayList<SelectItem> emptyList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        item.setLabel(VCSCommon.SPACE);
        item.setValue(null);
        emptyList.add(item);

        return emptyList;
    }

    public void loadMenu(LoginUserInfo loginUserInfo) throws PrestoDBException {

        List<MainMenuDataSelectListResDto> mainMenuDataList = new ArrayList<MainMenuDataSelectListResDto>();

        try {

            MainMenuDataSelectListReqDto mainMenuDataSelectListReqDto = new MainMenuDataSelectListReqDto();
            mainMenuDataSelectListReqDto.setGroupId(loginUserInfo.getGroupId());

            mainMenuDataList = (List<MainMenuDataSelectListResDto>) CommonUtil.getDaoServiceInvoker()
                    .execute(mainMenuDataSelectListReqDto);

            MenuModel model = new DefaultMenuModel();

            for (MainMenuDataSelectListResDto mainMenuData : mainMenuDataList) {

                if (mainMenuData.getMainMenuFlag() == VCSCommon.ONE_INTEGER) {
                    DefaultMenuItem item = new DefaultMenuItem(mainMenuData.getMainMenuName());
                    item.setCommand(mainMenuData.getMenuAction());
                    item.setIcon("ui-icon-gear");
                    model.addElement(item);
                } else {
                    DefaultSubMenu subMenu = new DefaultSubMenu(mainMenuData.getMainMenuName());
                    subMenu.setIcon("ui-icon-gear");

                    List<MenuDataSelectListResDto> menuDataList = new ArrayList<MenuDataSelectListResDto>();

                    MenuDataSelectListReqDto menuDataSelectListReqDto = new MenuDataSelectListReqDto();
                    menuDataSelectListReqDto.setGroupId(loginUserInfo.getGroupId());
                    menuDataSelectListReqDto.setMainMenuId(mainMenuData.getMainMenuId());
                    menuDataSelectListReqDto.setSubMenuFlag(true);

                    menuDataList = (List<MenuDataSelectListResDto>) CommonUtil.getDaoServiceInvoker()
                            .execute(menuDataSelectListReqDto);

                    for (MenuDataSelectListResDto menuData : menuDataList) {

                        if (menuData.getSubMenuLevel() == VCSCommon.ZERO_INTEGER && menuData.getSubMenuRef() == null) {
                            DefaultMenuItem item = new DefaultMenuItem(menuData.getSubMenuName());
                            item.setCommand(menuData.getMenuAction());
                            item.setIcon("ui-icon-search");
                            subMenu.addElement(item);
                        } else {
                            DefaultSubMenu seSubMenuItem = new DefaultSubMenu(menuData.getSubMenuName());
                            seSubMenuItem.setIcon("ui-icon-search");

                            List<SubMenuDataSelectListResDto> subMenuDataList =
                                    new ArrayList<SubMenuDataSelectListResDto>();

                            SubMenuDataSelectListReqDto subMenuDataSelectListReqDto = new SubMenuDataSelectListReqDto();
                            subMenuDataSelectListReqDto.setSubMenuId(menuData.getSubMenuId());

                            subMenuDataList = (List<SubMenuDataSelectListResDto>) CommonUtil.getDaoServiceInvoker()
                                    .execute(subMenuDataSelectListReqDto);

                            for (SubMenuDataSelectListResDto subMenuData : subMenuDataList) {
                                DefaultMenuItem subItem = new DefaultMenuItem(subMenuData.getSubMenuName());
                                subItem.setCommand(subMenuData.getMenuAction());
                                subItem.setIcon("ui-icon-search");
                                seSubMenuItem.addElement(subItem);
                            }
                            subMenu.addElement(seSubMenuItem);
                        }

                    }
                    model.addElement(subMenu);
                }

            }

            model.generateUniqueIds();

            loginUserInfo.setMenuModel(model);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }
    }

    protected ArrayList<SelectItem> getCustomerTypeSelectList() {
        ArrayList<SelectItem> customerTypeSelectList = new ArrayList<>();
        CustomerTypeSelectListReqDto reqDto = new CustomerTypeSelectListReqDto();
        reqDto.setDelFlag(0);

        try {
            ArrayList<CustomerTypeSelectListResDto> resDtoList =
                    (ArrayList<CustomerTypeSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (CustomerTypeSelectListResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getCustType());
                selectItem.setValue(resDto.getCustomerTypeId());

                customerTypeSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return customerTypeSelectList;
    }

    protected ArrayList<SelectItem> getGenderSelectList() {
        ArrayList<SelectItem> genderSelectList = new ArrayList<>();

        genderSelectList.add(new SelectItem(new Integer(1), "Male"));
        genderSelectList.add(new SelectItem(new Integer(2), "Female"));

        return genderSelectList;
    }

    protected ArrayList<SelectItem> getOperatorRoleSelectList() {
        ArrayList<SelectItem> roleOperatorSelectList = new ArrayList<>();

        roleOperatorSelectList.add(new SelectItem(new Integer(1), "Mobile"));
        roleOperatorSelectList.add(new SelectItem(new Integer(2), "Non-Mobile"));
        roleOperatorSelectList.add(new SelectItem(new Integer(3), "Personal-Loan"));
        roleOperatorSelectList.add(new SelectItem(new Integer(4), "Motorcycle-Loan"));

        return roleOperatorSelectList;
    }

    protected ArrayList<SelectItem> getApplicationStatuselectList() {
        ArrayList<SelectItem> applicationStatusSelectList = new ArrayList<>();

        /* applicationStatusSelectList.add(new SelectItem(new Integer(1), "DRAFT")); */
        applicationStatusSelectList.add(new SelectItem(new Integer(2), "NEW"));
        applicationStatusSelectList.add(new SelectItem(new Integer(4), "UPLOADED"));
        applicationStatusSelectList.add(new SelectItem(new Integer(5), "DOCUMENT FOLLOW UP WAITING"));
        applicationStatusSelectList.add(new SelectItem(new Integer(6), "DOCUMENT FOLLOW UP APPLICANT UPLOADED"));
        applicationStatusSelectList.add(new SelectItem(new Integer(7), "DOCUMENT FOLLOW UP CHECKED"));
        applicationStatusSelectList.add(new SelectItem(new Integer(8), "CANCEL"));
        applicationStatusSelectList.add(new SelectItem(new Integer(9), "REJECTED"));
        applicationStatusSelectList.add(new SelectItem(new Integer(10), "APPROVED"));

        applicationStatusSelectList.add(new SelectItem(new Integer(11), "MODIFY REQUEST"));
        applicationStatusSelectList.add(new SelectItem(new Integer(12), "MODIFY UPLOAD"));

        applicationStatusSelectList.add(new SelectItem(new Integer(13), "PURCHASE CANCEL"));
        applicationStatusSelectList.add(new SelectItem(new Integer(14), "PURCHASE INITIAL"));
        applicationStatusSelectList.add(new SelectItem(new Integer(15), "PURCHASE CONFIRM WAITING"));
        applicationStatusSelectList.add(new SelectItem(new Integer(16), "PURCHASE CONFIRM"));
        applicationStatusSelectList.add(new SelectItem(new Integer(17), "PURCHASE COMPLETE"));
        applicationStatusSelectList.add(new SelectItem(new Integer(18), "SALE CLAIM"));
        applicationStatusSelectList.add(new SelectItem(new Integer(19), "AGENT DOCUMENT ERROR"));
        applicationStatusSelectList.add(new SelectItem(new Integer(20), "SALE ENTRY"));

        return applicationStatusSelectList;
    }

    protected ArrayList<SelectItem> getLoanTypeSelectList() {
        ArrayList<SelectItem> loanTypeSelectList = new ArrayList<>();
        LoanTypeSelectListReqDto reqDto = new LoanTypeSelectListReqDto();
        reqDto.setDelFlag(false);

        try {
            ArrayList<LoanTypeSelectListResDto> resDtoList =
                    (ArrayList<LoanTypeSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (LoanTypeSelectListResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getName());
                selectItem.setValue(resDto.getLoanTypeId());

                loanTypeSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return loanTypeSelectList;
    }

    protected ArrayList<SelectItem> getApplicationTypeSelectList() {
        ArrayList<SelectItem> applicationTypeSelectList = new ArrayList<>();
        ApplicationTypeSelectListReqDto reqDto = new ApplicationTypeSelectListReqDto();
        reqDto.setDelFlag(false);

        try {
            ArrayList<ApplicationTypeSelectListResDto> resDtoList =
                    (ArrayList<ApplicationTypeSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (ApplicationTypeSelectListResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getName());
                selectItem.setValue(resDto.getApplicationTypeId());

                applicationTypeSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return applicationTypeSelectList;
    }

    protected ArrayList<SelectItem> getNationalitySelectList() {
        ArrayList<SelectItem> nationalitySelectList = new ArrayList<>();

        nationalitySelectList.add(new SelectItem(new Integer(1), "Myanmar"));
        nationalitySelectList.add(new SelectItem(new Integer(2), "Other"));

        return nationalitySelectList;
    }

    protected ArrayList<SelectItem> getJudgementStatusSelectList() {
        ArrayList<SelectItem> judgementStatusSelectList = new ArrayList<>();

        judgementStatusSelectList.add(new SelectItem(new Integer(0), "APPROVED"));
        judgementStatusSelectList.add(new SelectItem(new Integer(1), "APPROVED"));
        judgementStatusSelectList.add(new SelectItem(new Integer(4), "APPROVED"));
        judgementStatusSelectList.add(new SelectItem(new Integer(5), "APPROVED"));
        judgementStatusSelectList.add(new SelectItem(new Integer(2), "REJECTED"));
        judgementStatusSelectList.add(new SelectItem(new Integer(3), "CANCELED"));

        return judgementStatusSelectList;
    }

    protected ArrayList<SelectItem> getJudgementStatusSearchSelectList() {
        ArrayList<SelectItem> searchJudgementStatusSelectList = new ArrayList<>();

        searchJudgementStatusSelectList.add(new SelectItem(new Integer(1), "APPROVED"));
        searchJudgementStatusSelectList.add(new SelectItem(new Integer(2), "REJECTED"));
        searchJudgementStatusSelectList.add(new SelectItem(new Integer(3), "CANCELED"));

        return searchJudgementStatusSelectList;
    }

    protected ArrayList<SelectItem> getMaritalStatusSelectList() {
        ArrayList<SelectItem> maritalStatusSelectList = new ArrayList<>();

        maritalStatusSelectList.add(new SelectItem(new Integer(1), "Single"));
        maritalStatusSelectList.add(new SelectItem(new Integer(2), "Married"));

        return maritalStatusSelectList;
    }

    protected ArrayList<SelectItem> getUserTypeSelectList() {
        ArrayList<SelectItem> userTypeSelectList = new ArrayList<>();
        userTypeSelectList.add(new SelectItem(new Integer(5), "Operator"));
        userTypeSelectList.add(new SelectItem(new Integer(4), "Admin"));
        return userTypeSelectList;
    }

    protected ArrayList<SelectItem> getTypeOfResidenceSelectList() {
        ArrayList<SelectItem> typeOfResidenceSelectList = new ArrayList<>();

        typeOfResidenceSelectList.add(new SelectItem(new Integer(1), "Owner"));
        typeOfResidenceSelectList.add(new SelectItem(new Integer(2), "Parental"));
        typeOfResidenceSelectList.add(new SelectItem(new Integer(3), "Rental"));
        typeOfResidenceSelectList.add(new SelectItem(new Integer(4), "Relative"));
        typeOfResidenceSelectList.add(new SelectItem(new Integer(5), "Hostel/Other"));

        return typeOfResidenceSelectList;
    }

    protected ArrayList<SelectItem> getLivingWithSelectList() {
        ArrayList<SelectItem> livingWithSelectList = new ArrayList<>();

        livingWithSelectList.add(new SelectItem(new Integer(1), "Parent"));
        livingWithSelectList.add(new SelectItem(new Integer(2), "Spouse"));
        livingWithSelectList.add(new SelectItem(new Integer(3), "Relative"));
        livingWithSelectList.add(new SelectItem(new Integer(4), "Friend"));
        livingWithSelectList.add(new SelectItem(new Integer(5), "Alone"));

        return livingWithSelectList;
    }

    protected ArrayList<SelectItem> getProductTypeSelectList() {
        ArrayList<SelectItem> productTypeSelectList = new ArrayList<>();
        ProductTypeSelectListReqDto reqDto = new ProductTypeSelectListReqDto();
        reqDto.setDelFlag(false);

        try {
            ArrayList<ProductTypeSelectListResDto> resDtoList =
                    (ArrayList<ProductTypeSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (ProductTypeSelectListResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getProductName());
                selectItem.setValue(resDto.getProductTypeId());

                productTypeSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return productTypeSelectList;
    }

    protected ArrayList<SelectItem> getChannelSelectList() {
        ArrayList<SelectItem> channelSelectList = new ArrayList<>();

        channelSelectList.add(new SelectItem(new Integer(1), "Mobile"));
        channelSelectList.add(new SelectItem(new Integer(2), "Web"));

        return channelSelectList;
    }

    protected ArrayList<SelectItem> getRelationshipSelectList() {
        ArrayList<SelectItem> relationshipSelectList = new ArrayList<>();

        relationshipSelectList.add(new SelectItem(new Integer(1), "Parent"));
        relationshipSelectList.add(new SelectItem(new Integer(2), "Spouse"));
        relationshipSelectList.add(new SelectItem(new Integer(3), "Relative"));
        relationshipSelectList.add(new SelectItem(new Integer(4), "Friend"));
        relationshipSelectList.add(new SelectItem(new Integer(5), "Other"));

        return relationshipSelectList;
    }

    protected ArrayList<SelectItem> getCompanyStatusSelectList() {
        ArrayList<SelectItem> companyStatusSelectList = new ArrayList<>();

        companyStatusSelectList.add(new SelectItem(new Integer(1), "Public Company"));
        companyStatusSelectList.add(new SelectItem(new Integer(2), "Factory"));
        companyStatusSelectList.add(new SelectItem(new Integer(3), "Police"));
        companyStatusSelectList.add(new SelectItem(new Integer(4), "Private Company"));
        companyStatusSelectList.add(new SelectItem(new Integer(5), "SME Owner"));
        companyStatusSelectList.add(new SelectItem(new Integer(6), "Government Office"));
        companyStatusSelectList.add(new SelectItem(new Integer(7), "Taxi Owner"));
        companyStatusSelectList.add(new SelectItem(new Integer(8), "Specialist"));
        companyStatusSelectList.add(new SelectItem(new Integer(9), "SME Officer"));
        companyStatusSelectList.add(new SelectItem(new Integer(10), "Military"));
        companyStatusSelectList.add(new SelectItem(new Integer(11), "NGO"));
        companyStatusSelectList.add(new SelectItem(new Integer(12), "Other"));
        return companyStatusSelectList;
    }

    protected ArrayList<SelectItem> getFileTypeSelectList() {
        ArrayList<SelectItem> fileTypeSelectList = new ArrayList<>();

        fileTypeSelectList.add(new SelectItem(new Integer(1), "NRC Front"));
        fileTypeSelectList.add(new SelectItem(new Integer(2), "NRC Back"));
        fileTypeSelectList.add(new SelectItem(new Integer(3), "Resident Proof"));
        fileTypeSelectList.add(new SelectItem(new Integer(4), "Income Proof"));
        fileTypeSelectList.add(new SelectItem(new Integer(5), "Guarantor's NRC Front"));
        fileTypeSelectList.add(new SelectItem(new Integer(6), "Guarantor's NRC Back"));
        fileTypeSelectList.add(new SelectItem(new Integer(7), "Household or Criminal Clearance"));
        fileTypeSelectList.add(new SelectItem(new Integer(8), "Applicant Photo"));
        fileTypeSelectList.add(new SelectItem(new Integer(9), "Signature"));
        fileTypeSelectList.add(new SelectItem(new Integer(10), "Other"));
        fileTypeSelectList.add(new SelectItem(new Integer(11), "Guarantor Signature"));

        return fileTypeSelectList;
    }

    protected ArrayList<SelectItem> getCustomerEditStatusList() {
        ArrayList<SelectItem> customerStatusList = new ArrayList<>();

        customerStatusList.add(new SelectItem(new Integer(1), "Edit Request"));
        customerStatusList.add(new SelectItem(new Integer(3), "Rejected"));
        return customerStatusList;
    }

    protected ArrayList<SelectItem> getDocFollowUpStatusSelectList() {
        ArrayList<SelectItem> statusTypeSelectList = new ArrayList<>();

        statusTypeSelectList.add(new SelectItem(new Integer(5), "Waiting"));
        statusTypeSelectList.add(new SelectItem(new Integer(7), "Checked"));
        statusTypeSelectList.add(new SelectItem(new Integer(6), "Applicant Updated"));

        return statusTypeSelectList;
    }

    protected ArrayList<SelectItem> getSettlementStatusSelectList() {
        ArrayList<SelectItem> statusTypeSelectList = new ArrayList<>();

        statusTypeSelectList.add(new SelectItem(new Integer(15), "Index"));
        statusTypeSelectList.add(new SelectItem(new Integer(17), "Pending"));

        return statusTypeSelectList;
    }

    protected ArrayList<SelectItem> getSettlementUploadedStatusSelectList() {
        ArrayList<SelectItem> statusTypeSelectList = new ArrayList<>();

        statusTypeSelectList.add(new SelectItem(new Integer(16), "Complete"));
        statusTypeSelectList.add(new SelectItem(new Integer(17), "Pending"));

        return statusTypeSelectList;
    }

    protected ArrayList<SelectItem> getDepartmentSelectList() {
        ArrayList<SelectItem> departmentSelectList = new ArrayList<>();
        DepartmentListSearchReqDto reqDto = new DepartmentListSearchReqDto();
        reqDto.setDelFlag(false);

        try {
            ArrayList<DepartmentListSearchResDto> resDtoList =
                    (ArrayList<DepartmentListSearchResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (DepartmentListSearchResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getName());
                selectItem.setValue(resDto.getDepartmentId());

                departmentSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return departmentSelectList;
    }

    protected ArrayList<SelectItem> getGroupSelectList() {
        ArrayList<SelectItem> groupSelectList = new ArrayList<>();
        UserGroupSearchReqDto reqDto = new UserGroupSearchReqDto();

        try {
            ArrayList<UserGroupSearchResDto> resDtoList =
                    (ArrayList<UserGroupSearchResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (UserGroupSearchResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getGroupName());
                selectItem.setValue(resDto.getGroupId());

                groupSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return groupSelectList;
    }

    protected ArrayList<SelectItem> getTownshipSelectList() {
        ArrayList<SelectItem> townshipSelectList = new ArrayList<>();
        TownshipSearchReqDto reqDto = new TownshipSearchReqDto();

        try {
            ArrayList<TownshipSearchResDto> resDtoList =
                    (ArrayList<TownshipSearchResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (TownshipSearchResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getTownshipName());
                selectItem.setValue(resDto.getTownshipId());

                townshipSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return townshipSelectList;
    }

    protected ArrayList<SelectItem> getCitySelectList() {
        ArrayList<SelectItem> citySelectList = new ArrayList<>();
        CitySearchReqDto reqDto = new CitySearchReqDto();

        try {
            ArrayList<CitySearchResDto> resDtoList =
                    (ArrayList<CitySearchResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (CitySearchResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getCityName());
                selectItem.setValue(resDto.getCityId());

                citySelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return citySelectList;
    }

    protected ArrayList<SelectItem> getHighestEducationTypeSelectList() {
        ArrayList<SelectItem> highestEducationTypeSelectList = new ArrayList<>();

        highestEducationTypeSelectList.add(new SelectItem(new Integer(1), "High School"));
        highestEducationTypeSelectList.add(new SelectItem(new Integer(2), "University/College"));
        highestEducationTypeSelectList.add(new SelectItem(new Integer(3), "Post-Graduate"));

        return highestEducationTypeSelectList;
    }

    protected ArrayList<SelectItem> getPurchaseFileTypeSelectList() {
        ArrayList<SelectItem> purchaseFileTypeSelectList = new ArrayList<>();

        purchaseFileTypeSelectList.add(new SelectItem(new Integer(1), "MEMBER CARD"));
        purchaseFileTypeSelectList.add(new SelectItem(new Integer(2), "ULOAN"));
        purchaseFileTypeSelectList.add(new SelectItem(new Integer(3), "INVOICE"));
        purchaseFileTypeSelectList.add(new SelectItem(new Integer(4), "OTHER"));
        purchaseFileTypeSelectList.add(new SelectItem(new Integer(5), "LETTER OF AGREEMENT"));
        purchaseFileTypeSelectList.add(new SelectItem(new Integer(6), "CASH RECEIPT"));

        return purchaseFileTypeSelectList;
    }

    protected ArrayList<SelectItem> getSaleEntryTypeSelectList() {
        ArrayList<SelectItem> saleEntryTypeSelectList = new ArrayList<>();

        saleEntryTypeSelectList.add(new SelectItem(new Integer(1), "Checked"));
        saleEntryTypeSelectList.add(new SelectItem(new Integer(2), "Not Yet"));

        return saleEntryTypeSelectList;
    }

}
