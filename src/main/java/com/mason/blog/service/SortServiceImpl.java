package com.mason.blog.service;

import com.mason.blog.dao.SortInfoMapper;
import com.mason.blog.entity.SortInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/6/26
 * Time: 15:16
 */
@Service("SortServiceImpl")

public class SortServiceImpl implements SortService{

    @Autowired
    private SortInfoMapper sortInfoMapper;

    @Override
    public void deleteSorts(List<String> groupId) {
        sortInfoMapper.deleteSorts(groupId);
    }

    @Override
    public List<SortInfo> sortList(String catName,int pageSize, int start) {
        //sortInfoMapper.insertSelective();
        List<SortInfo> sortLists = sortInfoMapper.selectSorts(catName,pageSize,start);
        return sortLists;
    }

    @Override
    public List<SortInfo> allLists() {
        List<SortInfo> allSorts = sortInfoMapper.allSorts();
        return allSorts;
    }

    @Override
    public List<SortInfo> sortsList(int pageSize, int start) {
        return null;
    }

    @Override
    public Integer sortsSize(String catName,int pageSize, int start) {
        Integer counts =sortInfoMapper.sortsSize(catName,pageSize, start);
        return counts;
    }

    @Override
    public void insertsort(SortInfo sortInfo) {
        sortInfoMapper.insert(sortInfo);
    }

    @Override
    public void updatesort(SortInfo sortInfo) {
        sortInfoMapper.updateByPrimaryKeySelective(sortInfo);
    }

    @Override
    public void deletesorts(List<String> groupId) {

    }

    @Override
    public List<SortInfo> sortsByParentId(int parentId) {
        return null;
    }

    @Override
    public String getNameById(Integer id) {
        String name = sortInfoMapper.getNameById(id);
        return name;
    }

    @Override
    public List<SortInfo> getSubsorts() {
        return null;
    }

}
