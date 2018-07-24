package com.mason.blog.service.impl.sys;

import com.mason.blog.dao.RelationMapper;
import com.mason.blog.entity.Relation;
import com.mason.blog.service.sys.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "relationServiceImpl")
public class RelationServiceImpl implements RelationService {

	@Autowired
	private RelationMapper relationMapper;

	@Override
	public List<Relation> getRelationByUserId(int userId) {
		return relationMapper.getRelationByUserId(userId);
	}

	@Transactional
	@Override
	public void insertRelations(List<Relation> relationList) {
		relationMapper.delById(relationList.get(0).getUserId());
		if (relationList.get(0).getRoleId() != null) {
			relationMapper.insertRelations(relationList);
		}
	}

}
