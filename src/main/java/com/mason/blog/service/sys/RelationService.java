package com.mason.blog.service.sys;

import com.mason.blog.entity.Relation;

import java.util.List;

public interface RelationService {

	/**
	 * 通过userId得到关系List
	 * @param userId
	 * @return
	 */
	public List<Relation> getRelationByUserId(int userId);

	/**
	 * 批量插入关系数据
	 * @param relationList
	 */
	public void insertRelations(List<Relation> relationList);

	
}
