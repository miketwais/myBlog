package com.mason.blog.controller;

import com.mason.blog.controller.sys.UserController;
import com.mason.blog.entity.PageResult;
import com.mason.blog.entity.SortInfo;
import com.mason.blog.service.SortService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/6/20
 * Time: 11:17
 */
@RestController
@RequestMapping("/api/sort")
public class SortController {
    private Logger log = LoggerFactory.getLogger(UserController.class);


    @Resource(name = "SortServiceImpl")
    private SortService sortService;
    /**
     * 获取所有分类列表
     * @return
     */
    @ApiOperation("获取所有分类信息")
    @GetMapping("/list")
    public PageResult listAllSortInfo(String catName,int pageSize, int page) {
        PageResult pageResult = new PageResult();
        pageResult.setData(sortService.sortList(catName,pageSize, page * pageSize));
        pageResult.setTotalCount(sortService.sortsSize(catName,pageSize, page * pageSize));
        log.debug("The method is ending");
        return pageResult;
    }
    @ApiOperation("无条件获取所有分类信息")
    @GetMapping("/allLists")
    public List<SortInfo> AllSortInfo() {
        List<SortInfo> allLists =new ArrayList<SortInfo>();
        allLists = sortService.allLists();
        log.debug("The method is ending");
        return allLists;
    }
    /**
     * 查询分类信息
     * @param id
     * @return
     */
    @ApiOperation("查询分类信息")
    @ApiImplicitParam(name = "id",value = "分类ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public SortInfo getSortInfoById(@PathVariable Long id){
        return null;
    }

    /**
     * 增加一条分类信息数据
     *
     * @return
     */
    @ApiOperation("增加分类信息")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    @PostMapping("/sort")
    public SortInfo addSortInfo(@RequestBody SortInfo sortInfo) {
        //try{
            Date now = new Date();
            Date newDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr =sdf.format(now);
            sortInfo.setCreate_by(dateStr);
            sortInfo.setModified_by(dateStr);
            sortInfo.setNumber(0);
            sortService.insertsort(sortInfo);
            log.debug("The method is ending");
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        return sortInfo;
    }

    /**
     * 更新/编辑一条数据
     *
     * @param id
     * @return
     */
    @ApiOperation("更新/编辑分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @PutMapping("/update/{id}")
    public SortInfo updateSortInfoById(@PathVariable Long id,@RequestBody SortInfo sortInfo) {
        Date now = new Date();
        Date newDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr =sdf.format(now);
        sortInfo.setCreate_by(dateStr);
        sortInfo.setModified_by(dateStr);
        //sortInfo.setNumber(0);
        sortService.updatesort(sortInfo);
        return sortInfo;
    }
    /**
     * 删除菜单信息
     *
     * @param groupId
     * @return
     */
    @DeleteMapping("/delete")
    public List<String> deleteMenus(@RequestBody List<String> groupId) {
        sortService.deleteSorts(groupId);
        return groupId;
    }
    /**
     * 根据ID删除分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public String deleteSortInfo(@PathVariable Long id) {
        return null;
    }
}
