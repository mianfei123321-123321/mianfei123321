package com.roadjava.market.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.roadjava.market.bean.req.uploadfile.UploadFileReq;
import com.roadjava.market.bean.vo.UploadResultVO;
import com.roadjava.market.config.properties.UploadProperties;
import com.roadjava.market.constants.Constants;
import com.roadjava.market.ex.BizEx;
import com.roadjava.market.service.StorageService;
import com.roadjava.market.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;

/**
 * 存储业务实现类
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private UploadProperties uploadProperties;
    @Resource
    private HttpServletResponse resp;

    @Override
    public UploadResultVO uploadFile(UploadFileReq uploadFileReq) {
        MultipartFile multipartFile = uploadFileReq.getFile();
        check(multipartFile);
        String originalFilename = multipartFile.getOriginalFilename();
        String module = uploadFileReq.getModule();
        String relativePath = spliceRelativePath4File(originalFilename, module);
        // 进行文件存储
        doStoreFile(relativePath, multipartFile);
        // 设置返回结果
        UploadResultVO vo = new UploadResultVO();
        vo.setOriginalFilename(originalFilename);
        vo.setRelativePath(relativePath);
        vo.setSize(multipartFile.getSize());
        return vo;
    }

    private void check(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            log.warn("未上传或上传的文件内容为空");
            throw new BizEx("未上传或上传的文件内容为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            log.warn("上传的文件原始名称为空");
            throw new BizEx("上传的文件原始名称为空");
        }
    }

    /**
     * 进行文件存储
     * @param relativePath  非分片:resources/ type/ 20230523/ 123.jpg
     *                      分片: temp/requestId/0_xxx.mp4
     */
    private void doStoreFile(String relativePath, MultipartFile multipartFile) {
        // d:/a/ + relativePath
        String localAbsPath = uploadProperties.getStaticDir() + relativePath;
        File destFile = new File(localAbsPath);
        PathUtil.createDir(destFile.getParentFile());
        try {
            multipartFile.transferTo(destFile);
        } catch (Exception e) {
            log.error("store image or file error,filePath:{}", localAbsPath, e);
            throw new BizEx("存储文件出错");
        }
    }

    /**
     * 拼接上传文件在本地存储的相对路径,格式如: resources/ type/ 20230523/ 123.jpg
     *
     * @param originalFilename 原始文件名
     */
    private String spliceRelativePath4File(String originalFilename, String module) {
        String patternDate = DateUtil.format(LocalDateTime.now(), Constants.UPLOAD_DATE_FORMAT);
        String baseName = IdUtil.fastSimpleUUID();
        String suffix = Constants.DOT + FilenameUtils.getExtension(originalFilename);
        //123.jpg
        String newFileName = baseName + suffix;
        //relativePath: resources/ type/ 20230523/ 123.jpg
        StringBuilder relativePath = new StringBuilder();
        relativePath.append(Constants.RESOURCES_PREFIX);
        // 校验模块名是否为空
        if (StringUtils.isNoneBlank(module)) {
            relativePath.append(module).append(Constants.SLASH);
        }
        relativePath.append(patternDate).append(newFileName);
        return relativePath.toString();
    }
}
