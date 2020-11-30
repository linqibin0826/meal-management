package com.shusi.modules.menu_management.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class BadRequestException extends CommonException {

    public BadRequestException(String message) {
        super(BAD_REQUEST.value() + "", message, null);
    }

    public BadRequestException(String code, String message) {
        super(code, message, null);
    }

    public BadRequestException(HttpStatus status, String message) {
        super(status.value() + "", message, null);
    }

}
