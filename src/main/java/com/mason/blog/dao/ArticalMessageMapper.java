package com.mason.blog.dao;

import com.mason.blog.entity.ArticalMessage;
import com.mason.blog.entity.ArticalMessageExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ArticalMessageMapper")
public interface ArticalMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticalMessage record);

    int insertSelective(ArticalMessage record);

    List<ArticalMessage> selectByExample(ArticalMessageExample example);

    List<ArticalMessage> selectByArticalId(@Param("id") Long id,@Param("pageSize") int pageSize,@Param("start") int start);

    ArticalMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticalMessage record);

    int updateByPrimaryKey(ArticalMessage record);

    int deleteByMessId(@Param("id") Long id);
}