package com.roadjava.market.config;


import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.roadjava.market.config.properties.AuthorizationProperties;
import com.roadjava.market.config.properties.UploadProperties;
import com.roadjava.market.interceptor.AuthorizationInterceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义的webmvc配置
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Configuration
@EnableConfigurationProperties({AuthorizationProperties.class, UploadProperties.class})
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private AuthorizationProperties authorizationProperties;
    @Resource
    private UploadProperties uploadProperties;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor())
                .addPathPatterns("/**")
                // 排除哪些路径
                .excludePathPatterns(authorizationProperties.getWhiteList());
    }

    /**
     * 配置允许跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            // 允许跨域访问的url
            .addMapping("/**")
            // 允许跨域的域名
            .allowedOriginPatterns("*")
            .allowedHeaders("*")
            .allowCredentials(true)
            // get,options,post...
            .allowedMethods("*")
            // 单位:s,用来设置options请求(预检请求)在客户端的缓存时间
            .maxAge(3600);
    }

    /**
     * 定制化ObjectMapper
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer() {
        return  jacksonObjectMapperBuilder -> {
            // Long类型序列化为字符串,避免精度丢失
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            // long类型序列化为字符串,避免精度丢失
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            // date类型格式化
            jacksonObjectMapperBuilder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            //LocalDateTime类型格式化
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        };
    }

    /**
     * 静态资源的位置增加指定的file目录
     * http://localhost:8000/resources/images/20240601/123.jpg
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] locations = { "classpath:/META-INF/resources/",
            "classpath:/resources/", "classpath:/static/", "classpath:/public/",
            // 自定义的本地路径
            "file:"+ uploadProperties.getStaticDir()};
        registry.addResourceHandler("/**")
        .addResourceLocations(locations);
    }
}
