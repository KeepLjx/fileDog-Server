package com.filedog.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.filedog.entity.Files;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【files】的数据库操作Mapper
* @createDate 2024-02-21 09:53:30
* @Entity com.filedog.entity.Files
*/
@Mapper
public interface FilesMapper extends BaseMapper<Files> {

    /**
     * 根据文件夹名动态查询文件
     * @param folderName
     * @return
     */
    List<Files> selectAllByFolderName(@Param("folderName") String folderName);


    /**
     * 查询文件是否存在
     * @param filePath
     * @return
     */
    List<Files> selectAllByFilePath(@Param("filePath") String filePath);

    /**
     * 根据文件路径删除文件
     * @param filePath
     * @return
     */
    int deleteByFilePath(@Param("filePath") String filePath);
}




