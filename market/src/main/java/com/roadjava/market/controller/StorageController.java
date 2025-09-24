package com.roadjava.market.controller;

import com.roadjava.market.bean.req.uploadfile.UploadFileReq;
import com.roadjava.market.bean.res.Result;
import com.roadjava.market.bean.vo.UploadResultVO;
import com.roadjava.market.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 存储相关接口
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestController
@RequestMapping("/storage")
@Validated
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;
    @PostMapping("/uploadFile")
    public Result<UploadResultVO> uploadFile(@Validated UploadFileReq uploadFileReq) {
        return Result.buildSuccess(storageService.uploadFile(uploadFileReq));
    }
}
