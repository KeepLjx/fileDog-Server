package com.filedog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.filedog.entity.Students;
import com.filedog.service.StudentsService;
import com.filedog.mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lenovo
* @description 针对表【students】的数据库操作Service实现
* @createDate 2024-02-21 09:53:31
*/
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students>
    implements StudentsService{

    @Autowired
    StudentsMapper studentsMapper;

    /**
     * 根据姓名查询学生表
     * @param name
     * @return
     */
    @Override
    public List<Students> selectByName(String name) {
        List<Students> student = studentsMapper.selectByStudentName(name);
        return student;
    }

    /**
     * 根据学生姓名获取id
     * @param name
     * @return
     */
    @Override
    public List<Students> selectIdByName(String name) {
        List<Students> students = studentsMapper.selectStudentIdByStudentName(name);
        return students;
    }
}




