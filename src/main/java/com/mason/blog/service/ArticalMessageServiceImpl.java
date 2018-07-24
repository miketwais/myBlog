package com.mason.blog.service;

import com.mason.blog.dao.ArticalMessageMapper;
import com.mason.blog.entity.ArticalMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/18
 * Time: 10:27
 */
@Service("ArticalMessageServiceImpl")
public class ArticalMessageServiceImpl implements ArticalMessageService{
    @Autowired
    private ArticalMessageMapper articalMessageMapper;

    @Override
    public List<ArticalMessage> getArticalMessageById(Long id,int pageSize,int start) {
        return articalMessageMapper.selectByArticalId(id,pageSize,start);
    }

    @Override
    public Integer addArticalMessage(ArticalMessage articalMessage) {
        return articalMessageMapper.insert(articalMessage);
    }

    @Override
    public Long deleteByMessId(Long id) {
        articalMessageMapper.deleteByMessId(id);
        return null;
    }
}
