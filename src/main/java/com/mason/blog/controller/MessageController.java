package com.mason.blog.controller;

import com.mason.blog.controller.sys.UserController;
import com.mason.blog.entity.ArticalMessage;
import com.mason.blog.entity.Message;
import com.mason.blog.entity.MessageDetails;
import com.mason.blog.entity.PageResult;
import com.mason.blog.service.ArticalMessageService;
import com.mason.blog.service.MessageService;
import com.mason.blog.util.DateUtil;
import com.mason.blog.util.ToolUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/18
 * Time: 10:18
 */
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "ArticalMessageServiceImpl")
    private ArticalMessageService articalMessageService;

    @Resource(name="MessageServiceImpl")
    private MessageService messageService;

    @ApiOperation("获取指定文章的所有评论")
    @GetMapping("/list/{id}")
    public PageResult getMessageListsById(@PathVariable Long id, int pageSize, int page){
        PageResult pageResult = new PageResult();
        List<MessageDetails> messageDetailsList = new ArrayList<MessageDetails>();
        List<ArticalMessage> articalMessageList = articalMessageService.getArticalMessageById(id,pageSize,page * pageSize);
        for(ArticalMessage tmp:articalMessageList){
            //根据message_id获取content
            Message message = messageService.getMessageById(tmp.getMessage_id());
            if(null!=message){
                MessageDetails messageDetails = new MessageDetails();
                messageDetails.setArticle_id(id);
                messageDetails.setMessage_id(message.getId());
                messageDetails.setId(tmp.getId());
                messageDetails.setContent(message.getContent());
                messageDetails.setEmail(message.getEmail());
                messageDetails.setName(message.getName());
                messageDetails.setCreate_by(tmp.getCreate_by());
                messageDetails.setIp(message.getIp());
                messageDetails.setIs_effective(message.getIs_effective());
                messageDetailsList.add(messageDetails);
            }
        }
        pageResult.setData(messageDetailsList);
        pageResult.setTotalCount(articalMessageList.size());
        return pageResult;
    }
    @ApiOperation("新增评论")
    @PostMapping("/add")
    //事物配置2
    @Transactional
    public String addMessage(@RequestBody MessageDetails messageDetails,HttpServletRequest request){
        logger.info("info log");
        logger.trace("trace log");
        //记录日志变量的方法
        logger.info("url ={}",request.getRequestURI());//逗号后面跟要记录的变量
        //记录日志常量
        logger.info("url");
        String ipAddr = ToolUtil.getIpAddr(request);
        ArticalMessage articalMessage = new ArticalMessage();
        Message message = new Message();
        message.setContent(messageDetails.getContent());
        message.setEmail(messageDetails.getEmail());
        message.setIp(ipAddr);
        message.setIs_effective(true);
        String currentDateStr = DateUtil.getCurrentDate();
        message.setCreate_by(currentDateStr);
        message.setName(messageDetails.getName());
        //插入message返回message_id
        Boolean res = messageService.addMessage(message);
        if(res){
            Long message_id = message.getId();
            articalMessage.setMessage_id(message_id);
            //测试事物成功
            //articalMessage.setMessage_id(messageDetails.getMessage_id());
            articalMessage.setCreate_by(currentDateStr);
            articalMessage.setIs_effective(true);
            articalMessage.setArticle_id(messageDetails.getArticle_id());
            //插入articlemessage
            Integer id = articalMessageService.addArticalMessage(articalMessage);
            return "success!";
        }else{
            return "评论中包含不允许的关键词！";
        }

    }

    @ApiOperation("更新评论状态")
    @PutMapping("/update/{id}/{statue}")
    public Long aupdateMessageStatue(@PathVariable Long id,@PathVariable String statue){
        int is_effective = statue.equals("true") ? 1 : 0;
        messageService.updateStatueById(id,Long.valueOf(is_effective));
        return id;
    }
    @ApiOperation("删除评论（单选或多选）")
    @DeleteMapping("/deleteByIds/")
    @Transactional
    public String deleteByIds(@RequestBody String ids) {
        if(ids.indexOf(",")!=-1) {//多选
            String[] mesIds= ids.split(",");
            for(int i=0;i<mesIds.length;i++){
                messageService.deleteById(Long.valueOf(mesIds[i]));
                articalMessageService.deleteByMessId(Long.valueOf(mesIds[i]));
            }
            return mesIds[0];
        }else{
            messageService.deleteById(Long.valueOf(ids));
            articalMessageService.deleteByMessId(Long.valueOf(ids));
            return ids;
        }
    }

}
