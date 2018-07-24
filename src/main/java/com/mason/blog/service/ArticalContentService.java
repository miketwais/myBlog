package com.mason.blog.service;

import com.mason.blog.entity.ArticleContent;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/10
 * Time: 15:14
 */
public interface ArticalContentService {
    Integer addArticleContent(ArticleContent articleContent);

    String getContentById(Long id);

    Long updateById(ArticleContent articleContent);

    Long deleteByArticleId(Long id);
}
