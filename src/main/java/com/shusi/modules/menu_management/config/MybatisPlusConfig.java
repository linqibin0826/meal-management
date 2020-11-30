package com.shusi.modules.menu_management.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * The type Mybatis plus config.
 *
 * @author linqb
 * @date 2020 /11/24 9:18
 */
@Component
@MapperScan("com.shusi.modules.menu_management.dao")
public class MybatisPlusConfig {

    @Bean
    public ISqlInjector ISqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     *
     * @author linqb
     * @date 2020/11/24 9:18
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
