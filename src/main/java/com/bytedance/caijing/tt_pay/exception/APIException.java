package com.bytedance.caijing.tt_pay.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class APIException extends Exception {

    private String message;

    private int code;

    private static final long serialVersionUID = 1L;

    public APIException(String message, int code) {
        super(message, null);
        this.message = message;
        this.code = code;
    }

    public APIException(String message, int code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }
}
