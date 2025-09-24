package com.roadjava.market.config.properties;

import cn.hutool.core.util.StrUtil;
import com.roadjava.market.util.PathUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {
    /**
     * 存储文件的绝对路径,格式如: e:/a/
     */
    private String staticDir;

    @PostConstruct
    public void init() {
        if (StrUtil.isBlank(staticDir)) {
            throw new RuntimeException("UploadProperties.staticDir未配置");
        }
        staticDir = PathUtil.normalize(staticDir);
        PathUtil.checkAndCreateIfNotExist(staticDir);
    }
}
