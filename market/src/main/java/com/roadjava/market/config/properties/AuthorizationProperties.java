package com.roadjava.market.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@ConfigurationProperties(prefix = "auth")
public class AuthorizationProperties {
    /**
     * 不需要拦截器拦截的url列表
     */
    private List<String> whiteList;
}
