package com.filedog.controller;

import com.filedog.dto.UploadDTO;
import com.filedog.entity.Files;
import com.filedog.entity.Gather;
import com.filedog.entity.Students;
import com.filedog.result.Result;
import com.filedog.service.FilesService;
import com.filedog.service.GatherService;
import com.filedog.service.StudentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/2/19
 * @version:1.0
 */
@RestController
@RequestMapping("/filedog")
@Api("文件提交接口")
public class UploadController {

    public static String newPath;

    @Autowired
    GatherService gatherService;
    @Autowired
    StudentsService studentsService;

    @Autowired
    FilesService filesService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 给级联选择器中的内容赋值
     * @return
     */
    @GetMapping("/cascade")
    @ApiOperation("给级联选择器中的内容赋值")
    public Result Cascade() {
        //构造redis中的key:cascade
        String key = "cascade";

        //查询redis中是否存在菜品数据
        List<Gather> list = (List<Gather>) redisTemplate.opsForValue().get(key);
        if (list != null && list.size() > 0) {
            //如果存在，直接返回，无需查询数据库
            return Result.success(list);
        }
        //如果不存在，直接返回，无需查询数据库
        list = gatherService.selectAll();
        redisTemplate.opsForValue().set(key,list);
        System.out.println(list);
        return Result.success(list);
    }

    @PostMapping("/suffix")
    @ApiOperation("获取要求的文件后缀")
    public Result Suffix(@RequestBody  Map<String, List<String>> folder) {
        List<String> values = folder.get("value");
        String suffix = gatherService.selectSuffix(values.get(0), values.get(1));
        return Result.success(suffix);
    }

    @PostMapping("/fileRule")
    @ApiOperation("获取要求的文件命名规则")
    public Result fileRule(@RequestBody  Map<String, List<String>> folder) {
        List<String> values = folder.get("value");
        String fileRule = gatherService.selectRule(values.get(0), values.get(1));
        return Result.success(fileRule);
    }

    @PostMapping("/getId")
    @ApiOperation("根据姓名获取id")
    public Result getId(@RequestBody String name) {
        List<Students> student = studentsService.selectIdByName(name);
        if (student == null || student.size() == 0) {
            return  Result.msg("该姓名不存在！");
        }
        return Result.success(student.get(0).getStudentId() + "");
    }

    @PostMapping("/newPath")
    @ApiOperation("修改文件名")
    public Result newPath(@RequestBody String path) {
        newPath = path;
        return Result.success();
    }

    /**
     * 将文件插入文件表
     * @param files
     */
    @PostMapping("/insertFile")
    @ApiOperation("将文件插入文件表")
    public Result insertFile(@RequestBody Files files) {
        filesService.insertFile(files);
        return Result.success();
    }

}
