package com.shusi.modules.menu_management.base;


import lombok.Data;

import java.io.Serializable;


/**
 * The type Base result.
 *
 * @author linqb
 * @date 2020 /11/24 9:10
 */
@Data
public class BaseResult implements Serializable {

    /**
     * 状态码
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    /**
     * 成功
     */
    public static final String SUCCESS = "success";

    /**
     * 失败
     */
    public static final String FAIL = "fail";

    /**
     * 英文感叹号
     */
    private static final String SUFFIX_OLD = "!";

    /**
     * 中文感叹号
     */
    private static final String SUFFIX_NEW = "！";


    /**
     * 成功
     *
     * @return
     */
    public static BaseResult success() {
        return new BaseResult(SUCCESS, null);
    }

    /**
     * 成功
     *
     * @param msg
     * @return
     */
    public static BaseResult success(String msg) {
        return new BaseResult(SUCCESS, msg);
    }

    /**
     * 失败
     *
     * @return
     */
    public static BaseResult fail() {
        return new BaseResult(FAIL, null);
    }

    /**
     * 失败
     *
     * @param msg
     * @return
     */
    public static BaseResult fail(String msg) {
        return new BaseResult(FAIL, msg);
    }

    /**
     * 构造方法
     */
    public BaseResult() {
        this.code = SUCCESS;
    }

    /**
     * 构造方法
     *
     * @param code
     * @param message
     */
    public BaseResult(String code, String message) {
        this.code = code;
        this.setMessage(message);
    }

    /**
     * 构造方法
     *
     * @param code
     * @param message
     */
    public BaseResult(int code, String message) {
        this.code = code + "";
        this.setMessage(message);
    }

    /**
     * 成功标识
     *
     * @return
     */
    public boolean isSuccess() {
        return SUCCESS.equalsIgnoreCase(code);
    }

    /**
     * 设置消息
     *
     * @param message
     */
    public void setMessage(String message) {
        if (message != null) {
            if (message.endsWith((SUFFIX_OLD))) {
                message = message.substring(0, message.lastIndexOf(SUFFIX_OLD)) + SUFFIX_NEW;
            }
            if (!message.endsWith(SUFFIX_NEW)) {
                message = message + SUFFIX_NEW;
            }
        }
        this.message = message;
    }
}
