package com.bytedance.caijing.tt_pay.exception;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TTPayException extends Exception {

    private String code;

    private String msg;

    private String subCode;

    private String subMsg;

    private String detail;

    private static final long serialVersionUID = 1L;

    public TTPayException(String code, String msg, String subCode, String subMsg, String detail) {
        super(msg, null);
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.detail = detail;
    }

    public TTPayException(String code, String msg, String subCode, String subMsg, String detail, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.detail = detail;
    }
}
