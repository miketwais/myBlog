package com.mason.blog.service;

import com.mason.blog.dao.KeyWordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/4
 * Time: 14:05
 */
@Service("KeyWordsServiceImpl")
public class KeyWordsServiceImpl implements KeyWordsService {
    @Autowired
    private KeyWordsMapper keyWordsMapper;

    @Override
    public String getKeyWords() {
        String res = keyWordsMapper.getKeyWords();
        return res;
    }

    @Override
    public int updateKeyWords(String keywords) {
        //keyWordsMapper.updateKeyWords("政府,社会,伦理,斗争,111,22");
        keyWordsMapper.updateKeyWords(keywords);
        return 0;
    }
}
