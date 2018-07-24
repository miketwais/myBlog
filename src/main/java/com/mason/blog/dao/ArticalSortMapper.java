package com.mason.blog.dao;

import com.mason.blog.entity.ArticalSort;
import com.mason.blog.entity.ArticalSortExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ArticalSortMapper")
public interface ArticalSortMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticalSort record);

    int insertSelective(ArticalSort record);

    List<ArticalSort> selectArticalSorts();

    List<ArticalSort> getSortsById(@Param("article_id") Long article_id);

    List<ArticalSort> selectByExample(ArticalSortExample example);

    ArticalSort selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticalSort record);

    int updateByPrimaryKey(ArticalSort record);

    int deleteByArticleId(Long id);

    int updateByArticleId(ArticalSort articalSort);

    List<ArticalSort> getListsBySortId(@Param("id") Long id,@Param("pageSize") int pageSize,@Param("start") int start);
}