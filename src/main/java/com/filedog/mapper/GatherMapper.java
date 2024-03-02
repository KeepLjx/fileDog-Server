package com.filedog.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.filedog.entity.Gather;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【gather】的数据库操作Mapper
* @createDate 2024-02-21 10:14:46
* @Entity com.filedog.entity.Gather
*/
@Mapper
public interface GatherMapper extends BaseMapper<Gather> {

    /**
     * 查询收集者和文件夹名
     * @return
     */
    List<Gather> selectCollectorAndFolderName();


    /**
     * 根据姓名和文件夹名查询
     * @param collector
     * @param folderName
     * @return
     */
    List<Gather> selectByCollectorAndFolderName(@Param("collector") String collector, @Param("folderName") String folderName);


    /**
     * 查询文件后缀名
     * @param collector
     * @param folderName
     * @return
     */
    List<Gather> selectFileSuffixByCollectorAndFolderName(@Param("collector") String collector, @Param("folderName") String folderName);

    /**
     * 查询文件命名规则
     * @param collector
     * @param folderName
     * @return
     */
    List<Gather> selectFileRuleByCollectorAndFolderName(@Param("collector") String collector, @Param("folderName") String folderName);

    /**
     * 根据文件夹名和收集者删除gather
     * @param folderName
     * @return
     */
    int deleteByCollectorAndFolderName(@Param("collector") String collector, @Param("folderName") String folderName);
}




