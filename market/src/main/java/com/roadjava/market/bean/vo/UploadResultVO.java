package com.roadjava.market.bean.vo;

import lombok.Data;

/**
* 文件上传结果
*
* @author 乐之者java
* @see <a href="http://www.roadjava.com">乐之者java</a>
*/
@Data
public class UploadResultVO {
    /**
    * 单位:字节,文件大小
    */
    private Long size;
    /**
    * 原始文件名称
    */
    private String originalFilename;
    /**
    * 文件的相对路径,格式:resources/ type/ 20230523/ 123.jpg
    */
    private String relativePath;
}