package com.mason.blog.dao;

import com.mason.blog.entity.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "RelationMapper")
public interface RelationMapper {
    /**
     * 通过userId得到关系List
     *
     * @param userId
     * @return
     */
    public List<Relation> getRelationByUserId(@Param("userId") int userId);

    /**
     * 通过userId删除关系
     *
     * @param userId
     */
    public void delById(@Param("userId") int userId);

    /**
     * 批量插入关系数据
     *
     * @param relationList
     */
    public void insertRelations(List<Relation> relationList);
}