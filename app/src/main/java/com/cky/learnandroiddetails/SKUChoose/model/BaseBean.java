package com.cky.learnandroiddetails.SKUChoose.model;

import java.io.Serializable;

/**
 * Created by cuikangyuan on 2017/2/16.
 */

public class BaseBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 204214775755010438L;
    private String status;
    private String errCode;
    private String errMsg;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getErrCode() {
        return errCode;
    }
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

//	    "errMsg": ""
}
