package com.shusi.modules.menu_management.base;

import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 不存在异常
 *
 * @author lincl
 */
public class NotExistException extends CommonException {

    public NotExistException(String id, String name) {
        super(BAD_REQUEST.value() + "", "不存在ID为 “" + id + "” 的" + name, null);
    }

    public NotExistException(Collection<String> ids, String name) {
        super(BAD_REQUEST.value() + "", "ID集合为 “" + ids.toString() + "” 中有不存在的" + name, null);
    }

    public NotExistException(Collection<String> ids, String name, String label) {
        super(BAD_REQUEST.value() + "", label + "集合为 “" + ids.toString() + "” 中有不存在的" + name, null);
    }

}
