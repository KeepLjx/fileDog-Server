package com.filedog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName gather
 */
@TableName(value ="gather")
@Data
public class Gather implements Serializable {
    /**
     * 收集者
     */
    private String collector;

    /**
     * 文件夹名称
     */
    private String folderName;

    /**
     * 文件命名规则
     */
    private String fileRule;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}