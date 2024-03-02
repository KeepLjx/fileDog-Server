package com.filedog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName files
 */
@TableName(value ="files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Files implements Serializable {
    /**
     * 
     */
    private String submiter;

    /**
     * 
     */
    private LocalDateTime uploadTime;

    /**
     * 
     */
    private String folderName;

    /**
     * 
     */
    private String fileSuffix;

    /**
     * 
     */
    private String fileRule;

    /**
     * 
     */
    private String collector;

    /**
     * 
     */
    private String filePath;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}