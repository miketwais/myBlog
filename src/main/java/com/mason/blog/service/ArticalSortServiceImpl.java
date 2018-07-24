package com.mason.blog.service;

import com.mason.blog.dao.ArticalSortMapper;
import com.mason.blog.entity.ArticalSort;
import com.mason.blog.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/4
 * Time: 14:05
 */
@Service("ArticalSortServiceImpl")
public class ArticalSortServiceImpl implements ArticalSortService{
    @Autowired
    private ArticalSortMapper articalSortMapper;
    @Override
    public List<ArticalSort> articalSorts() {
        return null;
    }

    @Override
    public Integer addMutilArticalSort(String[] sortIds, ArticalSort articalSort) {
        Integer id=0;
        for(int i = 0 ; i <sortIds.length ; i++){
            articalSort.setSort_id(Integer.valueOf(sortIds[i]));
            id = articalSortMapper.insert(articalSort);
        }
        return id;
    }

    @Override
    public List<ArticalSort> getSortsById(Long articalId) {
        List<ArticalSort> sortsList = articalSortMapper.getSortsById(articalId);
        return sortsList;
    }

    @Override
    public Integer addArticalSort(ArticalSort articalSort) {
        Integer id = articalSortMapper.insert(articalSort);
        return id;
    }

    @Override
    public Long updateArticleSortById(Long id, String sortIds,ArticalSort articalSort) {

        if(sortIds.indexOf(",")!=-1){
            //多选的话，先删再新增
            String[] sortIdsArr = sortIds.split(",");
            articalSortMapper.deleteByArticleId(id);
            //ArticalSort articalSort1 = new ArticalSort();
            articalSort.setArticle_id(id);
            articalSort.setIs_effective(true);
            articalSort.setCreate_by(DateUtil.getCurrentDate());
            articalSort.setModified_by(DateUtil.getCurrentDate());
            for(int i = 0 ; i <sortIdsArr.length ; i++) {
                articalSort.setSort_id(Integer.valueOf(sortIdsArr[i]));
                articalSortMapper.insert(articalSort);
            }
        }else{//单选,直接更新
                articalSort.setSort_id(Integer.valueOf(sortIds));
                articalSortMapper.updateByArticleId(articalSort);
             }
        return id;
    }

    @Override
    public Long deleteByArticleId(Long id) {
        articalSortMapper.deleteByArticleId(id);
        return id;
    }

    @Override
    public List<ArticalSort> getListsById(Long id,int pageSize, int start) {
        return articalSortMapper.getListsBySortId(id,pageSize,start);
    }
}
