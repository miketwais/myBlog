package com.mason.blog.service;

import com.mason.blog.entity.ArticalInfo;
import com.mason.blog.entity.ArticalSortInfo;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/10
 * Time: 14:54
 */
@Service("ArticalSortInfoServiceImpl")
public class ArticalSortInfoServiceImpl implements ArticalSortInfoService{
    @Override
    public String addArticle(ArticalSortInfo articalSortInfo) {
        ArticalInfo articalInfo =new ArticalInfo();//文章基础信息
        //文章基础信息
        articalInfo.setTitle(articalSortInfo.getTitle());
        articalInfo.setSummary(articalSortInfo.getSummary());
        articalInfo.setIs_top(articalSortInfo.getIs_top());
        articalInfo.setIs_effective(articalSortInfo.getIs_effective());
        //articalInfo.setCreate_by(dateStr);
        //articalInfo.setModified_by(dateStr);
        return null;
    }
}
