package com.shusi.modules.menu_management.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * The type My meta object handler.
 *
 * @author linqb
 * @date 2020 /11/24 9:20
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        //aop entity中的属性名称,并不是表中字段的名称
        this.setFieldValByName("gmtCreate", LocalDate.now(), metaObject);
        this.setFieldValByName("gmtModify", LocalDate.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModify", LocalDate.now(), metaObject);
    }
}
