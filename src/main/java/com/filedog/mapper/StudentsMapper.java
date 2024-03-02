package com.filedog.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.filedog.entity.Students;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenovo
* @description 针对表【students】的数据库操作Mapper
* @createDate 2024-02-21 09:53:31
* @Entity com.filedog.entity.Students
*/
@Mapper
public interface StudentsMapper extends BaseMapper<Students> {

    /**
     * 根据姓名查询学生表
     * @param studentName
     * @return
     */
    List<Students> selectByStudentName(@Param("studentName") String studentName);


    /**
     * 根据学生姓名获取id
     * @param studentName
     * @return
     */
    List<Students> selectStudentIdByStudentName(@Param("studentName") String studentName);

}




