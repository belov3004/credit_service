package com.testcreditservice.api.exceptions;

import lombok.Getter;

public class AlreadyExistException extends ApiException {
    @Getter
    private int code;

    public AlreadyExistException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
