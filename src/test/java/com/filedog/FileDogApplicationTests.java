package com.filedog;

import com.filedog.entity.Gather;
import com.filedog.mapper.GatherMapper;
import com.filedog.mapper.StudentsMapper;
import com.filedog.utils.AliOssUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.List;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/2/16
 * @version:1.0
 */
@SpringBootTest
public class FileDogApplicationTests {

    @Autowired
    GatherMapper gatherMapper;

    @Autowired
    StudentsMapper studentsMapper;

    @Autowired
    AliOssUtil aliOssUtil;



    @Test
    void testInsert() {
        System.out.println(gatherMapper.selectByCollectorAndFolderName("刘佳鑫", "数学作业"));
        System.out.println(gatherMapper.selectCollectorAndFolderName());
        System.out.println(studentsMapper.selectByStudentName("刘佳鑫"));
        System.out.println(gatherMapper.selectFileSuffixByCollectorAndFolderName("刘佳鑫", "数学作业"));
        System.out.println(studentsMapper.selectStudentIdByStudentName("胡景程"));
        System.out.println(gatherMapper.selectFileRuleByCollectorAndFolderName("刘佳鑫", "数学作业"));
    }


}
