package com.mason.blog.controller;

import com.mason.blog.controller.sys.UserController;
import com.mason.blog.entity.*;
import com.mason.blog.service.*;
import com.mason.blog.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/3
 * Time: 14:00
 */
@RestController
@RequestMapping("/api/artical")
public class ArticalInfoController {
    private Logger log = LoggerFactory.getLogger(UserController.class);


    @Resource(name = "ArticalInfoServiceImpl")
    private ArticalInfoService articalInfoService;
    @Resource(name="ArticalSortServiceImpl")
    private ArticalSortService articalSortService;
    @Resource(name="SortServiceImpl")
    private SortService sortService;

    @Resource(name="ArticalSortInfoServiceImpl")
    private ArticalSortInfoService articalSortInfoService;

    @Resource(name="ArticalContentServiceImpl")
    private ArticalContentService articalContentService;

    @ApiOperation("获取所有文章及分类信息")
    @GetMapping("/list")
    public PageResult listAllSortInfo(String title, int pageSize, int page) {
        PageResult pageResult = new PageResult();
        List<ArticalSortInfo> articalSortInfoList=new ArrayList<ArticalSortInfo>();
        List<ArticalInfo> articalInfoList = articalInfoService.articalList(title,pageSize, page * pageSize);
        for(Iterator it = articalInfoList.iterator();it.hasNext();){
            ArticalSortInfo articalSortInfo =new ArticalSortInfo();
            //System.out.println(it.next());
            ArticalInfo articalInfo = (ArticalInfo) it.next();
            Long article_id = articalInfo.getId();
            //根据文章id获取所属目录id集合
            List<ArticalSort> sortsList = articalSortService.getSortsById(article_id);
            //根据文章id查出文章内容content
            String content = articalContentService.getContentById(article_id);
            //根据目录ID查出目录名称
            String sortNames= "";
            String ids = "";
            //根据目录id获取目录名称
            if(!sortsList.isEmpty()){
                for(ArticalSort tmp:sortsList){
                    Integer id = tmp.getSort_id();//目录id
                    sortNames = sortNames + sortService.getNameById(id)+",";
                    ids = ids+id+",";
                }
                sortNames = sortNames.substring(0,sortNames.length() - 1);
                ids = ids.substring(0,ids.length() - 1);
            }else{
                 sortNames= "";
                ids = "";
            }
            articalSortInfo.setContent(content);
            articalSortInfo.setSortIds(ids);
            articalSortInfo.setSortNames(sortNames);
            articalSortInfo.setId(articalInfo.getId());
            articalSortInfo.setTraffic(articalInfo.getTraffic());
            articalSortInfo.setCreate_by(articalInfo.getCreate_by());
            articalSortInfo.setIs_top(articalInfo.getIs_top());
            articalSortInfo.setModified_by(articalInfo.getModified_by());
            //articalSortInfo.setTraffic(articalInfo.getTraffic());
            articalSortInfo.setSummary(articalInfo.getSummary());
            articalSortInfo.setTitle(articalInfo.getTitle());
            articalSortInfo.setIs_effective(articalInfo.getIs_effective());
            articalSortInfoList.add(articalSortInfo);
        }
        pageResult.setData(articalSortInfoList);
        pageResult.setTotalCount(articalInfoService.articalsSize(title,pageSize, page * pageSize));
        log.debug("The method is ending");
        return pageResult;
    }

    @ApiOperation("新增文章")
    @PostMapping("/addArticle")
    public Long addArticle(@RequestBody ArticalDetails articalDetails){
        ArticalInfo articalInfo =new ArticalInfo();//文章基础信息
        ArticleContent articleContent =new ArticleContent();//文章内容
        ArticalSort articalSort =new ArticalSort();//文章所属分类
        //当前时间
        Date now = new Date();
        Date newDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr =sdf.format(now);
        //String res = articalSortInfoService.addArticle(articalSortInfo);
        //文章基础信息
        articalInfo.setTitle(articalDetails.getTitle());
        articalInfo.setSummary(articalDetails.getSummary());
        articalInfo.setIs_top(articalDetails.getIs_top());
        articalInfo.setIs_effective(articalDetails.getIs_effective());
        articalInfo.setCreate_by(dateStr);
        articalInfo.setModified_by(dateStr);
        articalInfo.setTraffic(0);
        articalInfoService.addArticalInfo(articalInfo);
        //注意：获取自增主键的方法！
        Long articalId = articalInfo.getId();
        //文章内容
        articleContent.setContent(articalDetails.getContent());
        articleContent.setCreate_by(dateStr);
        articleContent.setModifield_by(dateStr);
        articleContent.setArticle_id(articalId);
        articalContentService.addArticleContent(articleContent);
        //文章所属分类
        //articalSort.setSort_id(articalSortInfo.get);
        articalSort.setArticle_id(articalId);
        articalSort.setCreate_by(dateStr);
        articalSort.setModified_by(dateStr);
        articalSort.setIs_effective(articalDetails.getIs_effective());
        String sortIdStr = articalDetails.getSortId();
        if(sortIdStr.indexOf(",")!=-1){//多选
            String[] sortIds = sortIdStr.split(",");
            articalSortService.addMutilArticalSort(sortIds,articalSort);
        }else{//单选
            articalSort.setSort_id(Integer.valueOf(sortIdStr));
            articalSortService.addArticalSort(articalSort);
        }



        return articalId;
    }
    @ApiOperation("更新文章")
    @PutMapping("/updateArticle/{id}")
    public Long updateArticle(@RequestBody ArticalDetails articalDetails,@PathVariable int id){
        ArticalInfo articalInfo =new ArticalInfo();//文章基础信息
        ArticleContent articleContent =new ArticleContent();//文章内容
        ArticalSort articalSort =new ArticalSort();//文章所属分类
        articalInfo.setId(articalDetails.getId());
        articalInfo.setTitle(articalDetails.getTitle());
        articalInfo.setSummary(articalDetails.getSummary());
        articalInfo.setTraffic(articalDetails.getTraffic());
        articalInfo.setTraffic(articalDetails.getTraffic());
        articalInfo.setIs_effective(articalDetails.getIs_effective());
        articalInfo.setCreate_by(articalDetails.getCreate_by());
        articalInfo.setModified_by(DateUtil.getCurrentDate());
        articalInfo.setIs_top(articalDetails.getIs_top());
        //根据id更新articleInfo内容
        articalInfoService.updateArticleById(articalInfo);
        articleContent.setContent(articalDetails.getContent());
        articleContent.setModifield_by(DateUtil.getCurrentDate());
        articleContent.setArticle_id(articalDetails.getId());
        articalContentService.updateById(articleContent);
        //根据id更新文章和目录关系数据
        String sortIds = articalDetails.getSortId();
        articalSort.setCreate_by(articalDetails.getCreate_by());
        articalSort.setModified_by(DateUtil.getCurrentDate());
        articalSort.setIs_effective(true);
        articalSort.setArticle_id(Long.valueOf(id));
        articalSortService.updateArticleSortById(Long.valueOf(id),sortIds,articalSort);
        return Long.valueOf(id);
    }

    @DeleteMapping("/deleteArticles")
    public String deleteRoles(@RequestBody String ids) {
        if(ids.indexOf(",")!=-1) {//多选
            String[] sortIds= ids.split(",");
            for(int i=0;i<sortIds.length;i++){
                articalInfoService.deleteArticleInfoById(Long.valueOf(sortIds[i]));
                articalContentService.deleteByArticleId(Long.valueOf(sortIds[i]));
                articalSortService.deleteByArticleId(Long.valueOf(sortIds[i]));
            }
            return sortIds[0];
        }else{
            articalInfoService.deleteArticleInfoById(Long.valueOf(ids));
            articalContentService.deleteByArticleId(Long.valueOf(ids));
            articalSortService.deleteByArticleId(Long.valueOf(ids));
            return ids;
        }
    }

    @ApiOperation("获取所有文章及分类信息")
    @GetMapping("/details/{id}")
    public ArticalSortInfo getArticleDetails(@PathVariable Long id) {
            ArticalSortInfo articalSortInfo = new ArticalSortInfo();
            ArticalInfo articalInfo = new ArticalInfo();
            articalInfo = articalInfoService.getArticalInfoById(id);
            //根据文章id查出文章内容content
            String articleContents = articalContentService.getContentById(id);
            //根据文章id获取所属目录id集合
            List<ArticalSort> sortsList = articalSortService.getSortsById(id);
            //根据文章id查出文章内容content
            String content = articalContentService.getContentById(id);
            //根据目录ID查出目录名称
            String sortNames= "";
            String ids = "";
            //根据目录id获取目录名称
            if(!sortsList.isEmpty()){
                for(ArticalSort tmp:sortsList){
                    Integer sortId = tmp.getSort_id();//目录id
                    sortNames = sortNames + sortService.getNameById(sortId)+",";
                    ids = ids+sortId+",";
                }
                sortNames = sortNames.substring(0,sortNames.length() - 1);
                ids = ids.substring(0,ids.length() - 1);
            }else{
                sortNames= "";
                ids = "";
            }
            //组装数据
            articalSortInfo.setId(id);
            articalSortInfo.setSortIds(ids);
            articalSortInfo.setContent(articleContents);
            articalSortInfo.setTitle(articalInfo.getTitle());
            articalSortInfo.setSummary(articalInfo.getTitle());
            articalSortInfo.setTraffic(articalInfo.getTraffic());
            articalSortInfo.setIs_top(articalInfo.getIs_top());
            articalSortInfo.setSortNames(sortNames);
            articalSortInfo.setCreate_by(articalInfo.getCreate_by());
            return articalSortInfo;
    }
    @ApiOperation("获取指定目录下文章列表")
    @GetMapping("/listBySortId/{id}")
    public PageResult listBySortId(@PathVariable Long id,int pageSize, int page) {
        PageResult pageResult = new PageResult();
        List<ArticalSortInfo> articalSortInfoList =new ArrayList<ArticalSortInfo>();
        ArticalSort articalSort = new ArticalSort();
        //ArticalInfo articalInfo = new ArticalInfo();
        List<ArticalSort> articalSortList= articalSortService.getListsById(id,pageSize,page * pageSize);
        String sortNames= "";
        if(articalSortList.size()>0){
            for(ArticalSort tmp:articalSortList){
                //根据目录id获取目录名称
                Integer sortId = tmp.getSort_id();//目录id
                sortNames = sortNames + sortService.getNameById(sortId)+",";
                //根据文章id获取文章信息
                Long artId = tmp.getArticle_id();
                ArticalInfo articalInfo = articalInfoService.getArticalInfoById(artId);
                String articleContent = articalContentService.getContentById(artId);
                ArticalSortInfo articalSortInfo = new ArticalSortInfo();
                articalSortInfo.setId(articalInfo.getId());
                articalSortInfo.setTitle(articalInfo.getTitle());
                articalSortInfo.setCreate_by(articalInfo.getCreate_by());
                articalSortInfo.setSortNames(sortNames);
                articalSortInfo.setTraffic(articalInfo.getTraffic());
                articalSortInfo.setContent(articleContent);
                articalSortInfo.setSummary(articalInfo.getSummary());
                articalSortInfoList.add(articalSortInfo);
            }
            pageResult.setData(articalSortInfoList);
            pageResult.setTotalCount(articalSortList.size());
            log.debug("The method is ending");
            return pageResult;
        }else{
            return null;
        }
    }

    @ApiOperation("更新文章访问量traffic")
    @PutMapping("/updateTraffic/{id}")
    public Integer updateTrafficById(@PathVariable Long id) {
        return articalInfoService.updateTrafficById(id);
    }

}
