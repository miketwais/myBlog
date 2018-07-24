package com.mason.blog.service;

import com.mason.blog.entity.SortInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/6/26
 * Time: 15:14
 */
public interface SortService {
    /**
     * 得到菜单List
     *
     * @param pageSize
     * @param start
     * @return
     */
    List<SortInfo> sortList(String catName,int pageSize, int start);

    List<SortInfo> allLists();
    /**
     * 获取sorts列表
     *
     * @param pageSize
     * @param start
     * @return
     */
    List<SortInfo> sortsList(int pageSize, int start);

    /**
     * 获取sorts列表的总量
     *
     * @param pageSize
     * @param pageSize
     * @param start
     * @return
     */
    Integer sortsSize(String catName,int pageSize, int start);

    /**
     * 新建菜单信息
     *
     * @param sortInfo
     */
    void insertsort(SortInfo sortInfo);

    /**
     * 修改菜单信息
     *
     * @param sortInfo
     */
    void updatesort(SortInfo sortInfo);

    /**
     * 删除菜单信息
     *
     * @param groupId
     */
    void deletesorts(List<String> groupId);

    /**
     * 通过parentId得到sorts列表
     *
     * @param parentId
     * @return
     */
    List<SortInfo> sortsByParentId(int parentId);

    /**
     * 获取二级菜单
     * @return
     */
    List<SortInfo> getSubsorts();

    /**
     * 删除
     */
    void deleteSorts(List<String> groupId);
    /**
     * 根据ID查询名称
     */
    String getNameById(Integer id);
}
