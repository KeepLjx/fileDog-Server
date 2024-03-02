package com.filedog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName students
 */
@TableName(value ="students")
@Data
public class Students implements Serializable {
    /**
     * 
     */
    @TableId
    private Long studentId;

    /**
     * 
     */
    private String studentName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}