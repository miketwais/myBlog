package com.mason.blog.service;

import com.mason.blog.entity.ArticalSort;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/4
 * Time: 14:00
 */
public interface ArticalSortService {
    /**
     * 获取文章与目录信息对应关系列表
     * @return
     */
    List<ArticalSort> articalSorts();

    /**
     * 根据文章ID获取所属目录，一篇文章可以属于多个目录
     */
    List<ArticalSort> getSortsById(Long articalId);

    Integer addArticalSort(ArticalSort articalSort);

    Integer addMutilArticalSort(String[] sortIds,ArticalSort articalSort);

    Long updateArticleSortById(Long id,String sortIds,ArticalSort articalSort);

    Long deleteByArticleId(Long id);

    List<ArticalSort> getListsById(Long id,int pageSize, int start);
}
