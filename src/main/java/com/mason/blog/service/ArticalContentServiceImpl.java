package com.mason.blog.service;

import com.mason.blog.dao.ArticleContentMapper;
import com.mason.blog.entity.ArticleContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/10
 * Time: 15:14
 */
@Service("ArticalContentServiceImpl")
public class ArticalContentServiceImpl implements ArticalContentService{
    @Autowired
    ArticleContentMapper articleContentMapper;
    @Override
    public Integer addArticleContent(ArticleContent articleContent) {
        Integer id = articleContentMapper.insert(articleContent);
        return id;
    }
    @Override
    public String getContentById(Long id) {
        String content = articleContentMapper.getContentById(id);
        return content;
    }
    @Override
    public Long updateById(ArticleContent articleContent){
        articleContentMapper.updateByArticleId(articleContent);
        return articleContent.getId();
    }

    @Override
    public Long deleteByArticleId(Long id) {
        articleContentMapper.deleteByArticleId(id);
        return Long.valueOf(id);
    }
}
