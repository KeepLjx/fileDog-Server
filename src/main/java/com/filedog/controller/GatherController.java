package com.filedog.controller;

import com.filedog.entity.Gather;
import com.filedog.result.Result;
import com.filedog.service.GatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/2/16
 * @version:1.0
 */
@RestController
@RequestMapping("/filedog")
@Slf4j
@Api(tags = "发布收集接口")
public class GatherController {
    @Autowired
    private GatherService gatherService;

    @Autowired
    private RedisTemplate redisTemplate;

       /**
     * 发布收集
     * @param gather
     * @return
     */
    @PostMapping("/gather")
    @ApiOperation("发布收集接口")
    public Result publish(@RequestBody Gather gather) {
        log.info("发布收集：{}", gather);
        //1.查询收集人姓名和文件夹
        List<Gather> gathers =  gatherService.selectByCollectorAndFolderName(gather.getCollector(),gather.getFolderName());
        if (gathers.size() > 0) {
            return Result.msg("文件夹已经存在，创建后将删除原来文件夹。");
        } else {
            gatherService.insertGather(gather);
        }
        redisTemplate.delete("cascade");
        return Result.msg("创建成功");
    }
}
