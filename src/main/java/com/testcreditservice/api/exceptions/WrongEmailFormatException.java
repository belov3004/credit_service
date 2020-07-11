package com.testcreditservice.api.exceptions;

public class WrongEmailFormatException extends ApiException {
    private int code;

    public WrongEmailFormatException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}