package com.filedog.service;

import com.filedog.dto.FileDTO;
import com.filedog.entity.Files;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author lenovo
* @description 针对表【files】的数据库操作Service
* @createDate 2024-02-21 09:53:31
*/
public interface FilesService extends IService<Files> {

    /**
     * 将文件插入文件表
     * @param files
     */
    //缓存
    @CacheEvict(cacheNames = "fileDog", allEntries = true)
    void insertFile(Files files);

    /**
     * 动态查找文件表
     * @return
     */
    //缓存
    @Cacheable(cacheNames = "fileDog", key = "#folderName ?: 'all'")
    List<Files> findFiles(String folderName);

    /**
     * 删除单个文件接口
     * @param filePath
     */
    //清除缓存
    @CacheEvict(cacheNames = "fileDog", allEntries = true)
    void delete(String filePath);

}
