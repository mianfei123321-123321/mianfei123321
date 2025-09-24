package com.roadjava.market.bean.req.uploadfile;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class UploadFileReq {
    @NotNull
    private MultipartFile file;
    /**
     * 指定上传的子目录 /resources/ [module/] 20240601/ uuid.jpg
     */
    private String module;
}
