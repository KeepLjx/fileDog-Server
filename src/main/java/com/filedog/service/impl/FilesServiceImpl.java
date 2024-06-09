package com.filedog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.filedog.dto.FileDTO;
import com.filedog.entity.Files;
import com.filedog.mapper.GatherMapper;
import com.filedog.mapper.StudentsMapper;
import com.filedog.service.FilesService;
import com.filedog.mapper.FilesMapper;
import com.filedog.utils.AliOssUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author lenovo
 * @description 针对表【files】的数据库操作Service实现
 * @createDate 2024-02-21 09:53:30
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files>
        implements FilesService {

    @Autowired
    FilesMapper filesMapper;

    @Autowired
    StudentsMapper studentsMapper;

    @Autowired
    GatherMapper gatherMapper;

    @Autowired
    AliOssUtil aliOssUtil;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 插入文件
     *
     * @param files
     */
    @Override
    public void insertFile(Files files) {
        //若文件表中已经存在文件则不用插入
        List<Files> isExist = filesMapper.selectAllByFilePath(files.getFilePath());
        if (!isExist.isEmpty()) {
            filesMapper.updateUploadTimeByFilePath(LocalDateTime.now(),files.getFilePath());
            return;
        }
        //设置文件上传日期
        files.setUploadTime(LocalDateTime.now());
        //设置收集者姓名
        files.setCollector(files.getFolderName().split("/")[0]);
        filesMapper.insert(files);
    }

    /**
     * 动态查找文件表
     *
     * @return
     */
    @Override
    public List<Files> findFiles(String folderName) {
        List<Files> files = filesMapper.selectAllByFolderName(folderName);
        return files;
    }

    /**
     * 删除单个文件接口
     *
     * @param filePath
     */
    @Override
    public void delete(String filePath) {
        //删除数据库中的文件
        filesMapper.deleteByFilePath(filePath);
        // 获取文件所在的路径（目录）
        String folderPath = filePath.contains("/") ? filePath.substring(0, filePath.lastIndexOf('/') + 1) : "";
        //查询该文件路径中是否还有文件
        List<Files> files = filesMapper.selectAllByFolderName(folderPath);
        //如果该文件夹中没有文件则删除数据库中的文件夹
        if (files.size() == 0) {
            // 获取文件路径中的收集者和文件夹名称
            String[] target = folderPath.split("/");
            gatherMapper.deleteByCollectorAndFolderName(target[0], target[1]);
            redisTemplate.delete("cascade");
        }
        //删除oss对象存储中的文件
        aliOssUtil.deleteFile(filePath);
    }

}




