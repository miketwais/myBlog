package com.mason.blog.controller;

import com.mason.blog.controller.sys.UserController;
import com.mason.blog.service.KeyWordsService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/6/20
 * Time: 11:17
 */
@RestController
@RequestMapping("/api/keywords")
public class KeyWordsController {
    private Logger log = LoggerFactory.getLogger(UserController.class);


    @Resource(name = "KeyWordsServiceImpl")
    private KeyWordsService keyWordsService;
    /**
     * 获取所有分类列表
     * @return
     */
    @ApiOperation("获取所有信息")
    @GetMapping("/getKeyWords")
    public String getKeyWords() {
        String res = keyWordsService.getKeyWords();
        log.debug("The method is ending");
        return res;
    }

    @ApiOperation("更新信息")
    @PutMapping("/updateKeyWords/{keywords}")
    public String updateKeyWords(@PathVariable String keywords){
        keyWordsService.updateKeyWords(keywords);
        return keywords;
    }

}
