package com.roadjava.market.service;


import com.roadjava.market.bean.req.uploadfile.UploadFileReq;
import com.roadjava.market.bean.vo.UploadResultVO;

/**
 * 存储业务类
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface StorageService {
    /**
     * @return 格式如: resources/type/20230523/123.jpg
     */
    UploadResultVO uploadFile(UploadFileReq uploadFileReq);
}
