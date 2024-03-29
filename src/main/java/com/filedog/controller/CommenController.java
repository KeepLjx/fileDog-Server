package com.filedog.controller;

import com.filedog.constant.MessageConstant;
import com.filedog.result.Result;
import com.filedog.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/2/12
 * @version:1.0
 */
@RestController
@RequestMapping("/common")
@Api("通用接口")
@Slf4j
public class CommenController {

    @Autowired
    private AliOssUtil aliOssUtil;




    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}",file);

        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            //String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            String objectName = UploadController.newPath;
            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}