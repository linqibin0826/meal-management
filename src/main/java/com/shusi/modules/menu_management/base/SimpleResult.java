package com.shusi.modules.menu_management.base;

import lombok.Data;

/**
 * The type Simple result.
 *
 * @param <T> the type parameter
 * @author linqb
 * @date 2020 /11/24 9:14
 */
@Data
public class SimpleResult<T> extends BaseResult {

    /**
     * 数据对象
     */
    private T data;

    public SimpleResult() {
        super();
    }

    /**
     * 构造方法
     */
    public SimpleResult(T data) {
        super();
        this.data = data;
    }


    /**
     * 失败
     *
     * @param msg
     * @return
     */
    public static SimpleResult fail(String msg) {
        SimpleResult simpleResult = new SimpleResult(null);
        simpleResult.setCode(BaseResult.FAIL);
        simpleResult.setMessage(msg);
        return simpleResult;
    }

}