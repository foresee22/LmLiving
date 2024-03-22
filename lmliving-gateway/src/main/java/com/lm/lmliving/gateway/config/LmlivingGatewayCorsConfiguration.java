package com.lm.lmliving.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @version 1.0
 * 在Gateway中统一解决跨域问题，写专门的Gateway中的类解决跨域，和其他web微服务解决方式不一样，因为gateway不需要引入web-starter
 * 通过在Gateway中统一解决跨域问题，在其他微服务模块就不用设置解决跨域了，不然控制台报错
 * The 'Access-Control-Allow-Origin' header contains multiple values 'http://localhost:8001, http://localhost:8001',
 * but only one is allowed.
 */
@Configuration
public class LmlivingGatewayCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 配置跨域
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }
}
