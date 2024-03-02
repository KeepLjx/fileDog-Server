package com.filedog.controller;

import com.filedog.dto.FileDTO;
import com.filedog.entity.Files;
import com.filedog.entity.Gather;
import com.filedog.result.Result;
import com.filedog.service.FilesService;
import com.filedog.service.StudentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/2/24
 * @version:1.0
 */
@RestController
@RequestMapping("/filedog")
@Slf4j
@Api(tags = "文件管理接口")
public class DownloadController {

    @Autowired
    FilesService filesService;

    @Autowired
    StudentsService studentsService;



    /**
     * 动态查找查找文件
     * @return
     */
    @PostMapping("/findFiles")
    @ApiOperation("动态查找查找文件")
    public Result findFiles(@RequestBody String folderName) {
        //1.查询文件表中的信息
        if (folderName.equals("all")) {
            folderName = null;
        }
        List<Files> files = filesService.findFiles(folderName);
        //2.将查询到的数据和学号赋值给FileDTO
        List<FileDTO> fileData = new ArrayList<>();
        for (Files file : files) {
            //根据提交人姓名获取学号
            Long studentId = studentsService.selectIdByName(file.getSubmiter()).get(0).getStudentId();
            FileDTO fileDTO = FileDTO.builder()
                    .studentId(studentId)
                    .submiter(file.getSubmiter())
                    .uploadTime(file.getUploadTime())
                    .folderName(file.getFolderName())
                    .fileSuffix(file.getFileSuffix())
                    .fileRule(file.getFileRule())
                    .collector(file.getCollector())
                    .filePath(file.getFilePath())
                    .build();
            fileData.add(fileDTO);
        }
        return Result.success(fileData);
    }

    /**
     * 删除单个文件接口
     * @param filePath
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation("删除单个文件接口")
    public Result delete(@RequestBody String filePath) {
        filesService.delete(filePath);
        return Result.success();
    }

    @PostMapping("/deleteFolder")
    @ApiOperation("删除文件夹即其中的内容")
    public Result deleteFolder(@RequestBody Gather gather) {
        String folderName = gather.getCollector() + "/" + gather.getFolderName() + "/";
        List<Files> files = filesService.findFiles(folderName);
        if (files.size() == 0) {
            filesService.delete(folderName);
        }
        for (Files file : files) {
            filesService.delete(file.getFilePath());
        }
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    @ApiOperation("批量删除文件")
    public Result deleteBatch (@RequestBody String[] files) {
        for (String file : files) {
            filesService.delete(file);
        }
        return Result.success();
    }

}
