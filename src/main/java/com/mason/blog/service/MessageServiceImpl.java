package com.mason.blog.service;

import com.mason.blog.dao.KeyWordsMapper;
import com.mason.blog.dao.MessageMapper;
import com.mason.blog.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/18
 * Time: 10:45
 */
@Service("MessageServiceImpl")
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private KeyWordsMapper keyWordsMapper;
    @Override
    public Message getMessageById(Long id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean addMessage(Message message) {
        //关键字屏蔽
        String keywords = keyWordsMapper.getKeyWords();
        String[] kWords = keywords.split(",");
        boolean isCanInsert = true;
        for(int i=0;i<kWords.length;i++){
            if(message.getContent().indexOf(kWords[i])!=-1){
                isCanInsert =false;
                break;
            }
        }
        if(isCanInsert==true){
            messageMapper.insert(message);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Integer updateStatueById(Long id, Long statue) {
        messageMapper.updateStatueById(id,statue);
        return null;
    }

    @Override
    public Integer deleteById(Long id) {
        messageMapper.deleteByPrimaryKey(id);
        return null;
    }
}
