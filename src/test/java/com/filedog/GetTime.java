package com.filedog;

/**
 * @author:CookieBoy
 * @Description
 * @create 2023/12/8
 * @version:1.0
 */

// 测试获取时间
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd");
        Date now = new Date();
        String formattedDate = dateFormat.format(now);
        System.out.println("当前时间是: " + formattedDate);
    }
}