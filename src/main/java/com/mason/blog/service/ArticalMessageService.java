package com.mason.blog.service;

import com.mason.blog.entity.ArticalMessage;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/18
 * Time: 10:12
 */
public interface ArticalMessageService {
    /**
     * 增加评论
     */
    Integer addArticalMessage(ArticalMessage articalMessage);

    List<ArticalMessage> getArticalMessageById(Long id,int pageSize,int start);

    Long deleteByMessId(Long id);


}
