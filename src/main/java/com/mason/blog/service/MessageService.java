package com.mason.blog.service;

import com.mason.blog.entity.Message;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/18
 * Time: 10:43
 */
public interface MessageService {

    Message getMessageById(Long id);
    Boolean addMessage(Message message);
    Integer updateStatueById(Long id, Long statue);
    Integer deleteById(Long id);
}
