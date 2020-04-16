package com.bytedance.caijing.tt_pay.exception;

public class InvalidRequestException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidRequestException(String message) {
        super(message, null);
    }

    public InvalidRequestException(String message, Throwable e) {
        super(message, e);
    }
}

