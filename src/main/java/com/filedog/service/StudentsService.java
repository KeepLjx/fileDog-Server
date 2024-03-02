package com.filedog.service;

import com.filedog.entity.Students;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author lenovo
* @description 针对表【students】的数据库操作Service
* @createDate 2024-02-21 09:53:31
*/
public interface StudentsService extends IService<Students> {

    /**
     * 根据姓名查询学生表
     * @param name
     * @return
     */
    List<Students> selectByName(String name);

    /**
     * 根据姓名获取学生id
     * @param name
     */
    @Cacheable(cacheNames = "fileDog", key = "#name")
    List<Students> selectIdByName(String name);
}
