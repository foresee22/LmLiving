package com.lm.lmliving.commodity.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // 开启事务
@MapperScan("com.lm.lmliving.commodity.dao") // 表示对哪些dao生效
public class MyBatisPlusConfig {
    // 引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 做基本设置
        // 溢出总页数，设置第一页
        paginationInterceptor.setOverflow(true);
        // 单页限制100条，小于0 如 -1不受限制
        paginationInterceptor.setLimit(100);
        return paginationInterceptor;
    }
}
