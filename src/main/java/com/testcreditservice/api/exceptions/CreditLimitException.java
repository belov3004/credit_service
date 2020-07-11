package com.testcreditservice.api.exceptions;

public class CreditLimitException extends ApiException {
    public CreditLimitException(int code, String msg) {
        super(code, msg);
    }
}
