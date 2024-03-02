package com.filedog.service;

import com.filedog.entity.Gather;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lenovo
* @description 针对表【gather】的数据库操作Service
* @createDate 2024-02-21 10:14:46
*/
public interface GatherService extends IService<Gather> {

    /**
     * 查询姓名和文件夹名查询
     * @return
     */
    List<Gather> selectAll();

    /**
     * 根据姓名和文件夹名查询
     * @param collector
     * @param folderName
     */
    List<Gather> selectByCollectorAndFolderName(String collector, String folderName);

    /**
     * 插入gather
     * @param gather
     */
    void insertGather(Gather gather);

    /**
     * 查询文件后缀名
     * @param collector
     * @param folder
     * @return
     */
    String selectSuffix(String collector, String folder);

    /**
     * 查询文件命名规则
     * @param collector
     * @param folder
     * @return
     */
    String selectRule(String collector, String folder);
}
