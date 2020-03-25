/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ass.front.agreementModificationRequestListManagement;

import java.io.Serializable;

public class AgreementModificationRequestHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;

    private String modifyComment;

    public String getModifyComment() {
        return modifyComment;
    }

    public void setModifyComment(String modifyComment) {
        this.modifyComment = modifyComment;
    }

}
