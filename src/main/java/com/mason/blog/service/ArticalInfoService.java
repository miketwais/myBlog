package com.mason.blog.service;

import com.mason.blog.entity.ArticalInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/3
 * Time: 14:02
 */
public interface ArticalInfoService {
    List<ArticalInfo> articalList(String title,int pageSize, int start );

    Integer articalsSize(String title,int pageSize, int start);

    //返回文章id
    Long addArticalInfo(ArticalInfo articalInfo);

    Long updateArticleById(ArticalInfo articalInfo);

    Long deleteArticleInfoById(Long id);

    ArticalInfo getArticalInfoById(Long id);

    /**
     *
     * @param id
     * @return 更新后的浏览量
     */
    Integer updateTrafficById(Long id);
}
