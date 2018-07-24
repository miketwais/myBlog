package com.mason.blog.controller.sys;

import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/6
 * Time: 11:20
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Value("${upload.filePath}")
    private String filePath;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UploadController.class);

    @ApiOperation("单文件上传")
    @PostMapping("/singleUpload")

    public String singleUpload(@RequestBody MultipartFile file){
        if(file.isEmpty()){
            return "文件为空";
        }
        String fileName =file.getOriginalFilename();
        logger.info("文件名："+fileName);
        String suffixName =fileName.substring(fileName.lastIndexOf("."));
        logger.info("文件后缀名："+suffixName);
        //获取项目根目录
        //String rootPath = ProjectPath.getProjectPath();
        File dest = new File(filePath+"//"+fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
            String resPath = "../dist/upload/"+fileName;
            //return dest.getPath();//要求返回绝对URL
            return resPath;
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "上传失败";
    }
}
