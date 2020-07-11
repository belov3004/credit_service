package com.testcreditservice.api.exceptions;

public class NotValidException extends ApiException {
    public NotValidException(int code, String msg) {
        super(code, msg);
    }
}
