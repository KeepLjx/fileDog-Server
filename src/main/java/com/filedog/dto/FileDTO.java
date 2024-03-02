package com.filedog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/2/24
 * @version:1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

    //学号
    private Long studentId;

    //提交人姓名
    private String submiter;

    //提交时间
    private LocalDateTime uploadTime;

    //所属文件夹
    private String folderName;;

    //文件类型
    private String fileSuffix;

    //命名规则
    private String fileRule;

    //收集人姓名
    private String collector;

    //文件路径
    private String filePath;
}

