package com.shusi.modules.menu_management.base;

import lombok.Getter;


/**
 * The type Common exception.
 *
 * @author linqb
 * @date 2020 /11/24 9:05
 */
@Getter
public class CommonException extends RuntimeException {

    /**
     * 英文感叹号
     */
    private final String SUFFIX_OLD = "!";

    /**
     * 中文感叹号
     */
    private final String SUFFIX_NEW = "！";

    /**
     * 代码
     */
    private String code;

    public CommonException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public String getMessage() {
        String BaseMsg = super.getMessage();
        if (BaseMsg.endsWith((SUFFIX_OLD))) {
            BaseMsg = BaseMsg.substring(0, BaseMsg.lastIndexOf(SUFFIX_OLD)) + SUFFIX_NEW;
        }
        if (!BaseMsg.endsWith(SUFFIX_NEW)) {
            BaseMsg = BaseMsg + SUFFIX_NEW;
        }
        return BaseMsg;
    }
}
