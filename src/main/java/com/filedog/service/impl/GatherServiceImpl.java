package com.filedog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.filedog.entity.Gather;
import com.filedog.service.GatherService;
import com.filedog.mapper.GatherMapper;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lenovo
 * @description 针对表【gather】的数据库操作Service实现
 * @createDate 2024-02-21 10:14:46
 */
@Service
public class GatherServiceImpl extends ServiceImpl<GatherMapper, Gather>
        implements GatherService {

    @Autowired
    GatherMapper gatherMapper;

    /**
     * 查询姓名和文件夹名
     *
     * @return
     */
    @Override
    public List<Gather> selectAll() {
        List<Gather> gathers = gatherMapper.selectCollectorAndFolderName();
        return gathers;
    }

    /**
     * 根据姓名和文件夹名查询
     *
     * @param collector
     * @param folderName
     */
    @Override
    public List<Gather> selectByCollectorAndFolderName(String collector, String folderName) {
        List<Gather> gather = gatherMapper.selectByCollectorAndFolderName(collector, folderName);
        return gather;

    }

    /**
     * 插入gather
     *
     * @param gather
     */
    @Override
    public void insertGather(Gather gather) {
        switch (gather.getFileSuffix()) {
            case "pdf": {
                gather.setFileSuffix(".pdf");
                break;
            }
            case "word": {
                gather.setFileSuffix(".doc/.docx");
                break;
            }
            case "图片": {
                gather.setFileSuffix(".png/.jpg");
                break;
            }
            case "表格": {
                gather.setFileSuffix(".xls/.xlsx");
                break;
            }
            case "ppt": {
                gather.setFileSuffix(".ppt/.pptx");
                break;
            }
        }
        gatherMapper.insert(gather);
    }

    /**
     * 查询文件后缀名
     *
     * @param collector
     * @param folder
     * @return
     */
    @Override
    public String selectSuffix(String collector, String folder) {
        List<Gather> suffix = gatherMapper.selectFileSuffixByCollectorAndFolderName(collector, folder);
        return suffix.get(0).getFileSuffix();
    }

    /**
     * 查询文件命名规则
     *
     * @param collector
     * @param folder
     * @return
     */
    @Override
    public String selectRule(String collector, String folder) {
        List<Gather> fileRule = gatherMapper.selectFileRuleByCollectorAndFolderName(collector, folder);
        return fileRule.get(0).getFileRule();
    }
}




