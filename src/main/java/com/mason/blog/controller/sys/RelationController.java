package com.mason.blog.controller.sys;

import com.mason.blog.entity.Relation;
import com.mason.blog.service.sys.RelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RelationController {

	private Logger log = LoggerFactory.getLogger(RelationController.class);

	@Resource(name = "relationServiceImpl")
	private RelationService relationService;

	/**
	 * 通过userId得到关系List
	 * @param userId
	 * @return
	 */
	@GetMapping("/relations/{userId}")
	public List<Relation> getRelationByUserId(@PathVariable int userId){
		log.debug("The method is ending");
		return relationService.getRelationByUserId(userId);
	}
	
	/**
	 * 批量插入关系数据
	 * @param relationList
	 * @return
	 */
	@PostMapping("/relations")
	public List<Relation> insertRelations(@RequestBody() List<Relation> relationList){
		relationService.insertRelations(relationList);
		log.debug("The method is ending");
		return relationList;
	}
}
