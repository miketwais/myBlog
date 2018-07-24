package com.mason.blog.dao;

import com.mason.blog.entity.SortInfo;
import com.mason.blog.entity.SortInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "SortInfoMapper")
public interface SortInfoMapper {
    public int deleteByPrimaryKey(Long id);

    public int insert(SortInfo record);

    public int insertSelective(SortInfo record);

    public List<SortInfo> selectByExample(SortInfoExample example);

    public List<SortInfo> selectSorts(@Param("catName") String catName,@Param("pageSize") int pageSize, @Param("start") int start);

    public Integer sortsSize(@Param("catName") String catName,@Param("pageSize") int pageSize, @Param("start") int start);

    public List<SortInfo> allSorts();

    public SortInfo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(SortInfo record);

    public void deleteSorts(@Param("groupId")List<String> groupId);

    public String getNameById(@Param(("id")) Integer id);

}