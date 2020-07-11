package com.testcreditservice.api.exceptions;

import lombok.Getter;

public class ApiException extends RuntimeException {
    @Getter
    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
