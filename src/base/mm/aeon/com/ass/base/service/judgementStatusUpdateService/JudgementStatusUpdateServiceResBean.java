/**************************************************************************
 * $Date: 2018-09-21$
 * $Author: SHOON LATT WINNE $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.base.service.judgementStatusUpdateService;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;

public class JudgementStatusUpdateServiceResBean extends AbstractServiceResBean {

    private static final long serialVersionUID = -6473762488720461939L;

    private JudgementStatusUpdateServiceReqBean reqBean;

    private String errorMessage;

    public JudgementStatusUpdateServiceReqBean getReqBean() {
        return reqBean;
    }

    public void setReqBean(JudgementStatusUpdateServiceReqBean reqBean) {
        this.reqBean = reqBean;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
