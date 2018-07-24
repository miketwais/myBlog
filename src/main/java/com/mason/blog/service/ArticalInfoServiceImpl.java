package com.mason.blog.service;

import com.mason.blog.dao.ArticalInfoMapper;
import com.mason.blog.entity.ArticalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/3
 * Time: 14:02
 */
@Service("ArticalInfoServiceImpl")
public class ArticalInfoServiceImpl implements ArticalInfoService {

    @Autowired
    private ArticalInfoMapper articalInfoMapper;
    @Override
    public List<ArticalInfo> articalList(String title, int pageSize, int start) {
       List<ArticalInfo> articalInfoList= articalInfoMapper.selectArticals(title,pageSize,start);
        return articalInfoList;
    }

    @Override
    public Long addArticalInfo(ArticalInfo articalInfo) {
        Long id = articalInfoMapper.insert(articalInfo);
        return id;
    }

    @Override
    public Integer articalsSize(String title, int pageSize, int start) {
        Integer counts = articalInfoMapper.articalsSize(title,pageSize,start);
        return counts;
    }

    @Override
    public Long updateArticleById(ArticalInfo articalInfo) {
        //Long res = articalInfoMapper.updateByArticleId(id,articalInfo);
        articalInfoMapper.updateByPrimaryKey(articalInfo);
        return articalInfo.getId();
    }

    @Override
    public Long deleteArticleInfoById(Long id) {
        articalInfoMapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public ArticalInfo getArticalInfoById(Long id) {
        return articalInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateTrafficById(Long id) {
        articalInfoMapper.updateTrafficById(id);
        Integer traffic = articalInfoMapper.selectTrafficById(id);
        return traffic;
    }
}
