package com.mason.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "KeyWordsMapper")
public interface KeyWordsMapper {
    String getKeyWords();

    //int updateKeyWords(@Param("keywords") String keywords);
    int updateKeyWords(String keywords);

}