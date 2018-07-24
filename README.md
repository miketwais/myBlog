# 关于本项目
> 本项目是一个blog项目，做这个blog项目的目的是实践"前后端分离"架构.本项目分两个部分：
>> 前台部分：包括博客前台和博客管理后台，主要基于vue.

>> 后台部分：包括提供服务和资源数据的java后台,主要基于springboot

* 前台部分

     > 项目简介
     
        项目基于node,使用了vue的开源插件：vue-carousel，并整合了TinyMCE，界面主要基于iview框架。
        后台管理员账户密码：admin/admin
     > 技术栈
     
     - vue2
     - vuex
     - vue-router
     - axios
     - [ivew](https://www.iviewui.com/)
     - webpack
     
     > 启动方法：
     
     - 进入src/resources/web/src目录，执行npm install
     - 运行npm run init初始化项目
     - 运行npm run dev启动前端工程
     
     
     
* 后台部分

    > 项目简介
    
        项目基于springboot,maven管理，主要实现了博客的分类管理，文章管理，评论，评论管理，敏感词管理等功能。
        主要使用:
        mybatis (mybatis-generator自动生成实体等类)
        事务 (@Transactional 事务注解保证数据完整一致性)
        AOP && log4j2(AOP+log4j2，使用AOP技术实现记录完整请求日志)
    
    >技术栈
    
    - springboot
    - mybatis
    - spring security
    - Spring Security OAuth2
    - redis
    - RESTful
    - mysql
    
    > 启动方法：
    
    - 启动Redis(Redis用来缓存token等信息，必须开启)
    - 启动speingboot应用
    
    
    
* 说明

 > 简单的blog项目，后续会不断完善功能。    