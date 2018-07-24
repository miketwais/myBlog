package com.mason.blog.dao;

import com.mason.blog.entity.ArticalInfo;
import com.mason.blog.entity.ArticalInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ArticalInfoMapper")
public interface ArticalInfoMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(ArticalInfo record);

    int insertSelective(ArticalInfo record);

    public List<ArticalInfo> selectArticals(@Param("title") String title, @Param("pageSize") int pageSize, @Param("start") int start);

    public Integer articalsSize(@Param("title") String title,@Param("pageSize") int pageSize, @Param("start") int start);

    List<ArticalInfo> selectByExample(ArticalInfoExample example);

    ArticalInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticalInfo record);

    int updateByPrimaryKey(ArticalInfo record);

    Long updateByArticleId(@Param("id") Integer id,@Param("articleInfo") ArticalInfo articalInfo);

    Long updateTrafficById(@Param("id") Long id);

    Integer selectTrafficById(Long id);

}