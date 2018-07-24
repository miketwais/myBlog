package com.mason.blog.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/18
 * Time: 17:03
 */
@Aspect
@Component//将这个类引入spring容器中去
public class HttpAspect {
    //private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);//参数为当前使用的类名
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("RollingRandomAccessFileLogger");
    @Pointcut("execution(public * com.mason.blog.controller.MessageController.*(..))")//要处理的方法，包名+类名+方法名
    public void cut(){
    }

    @Before("cut()")//在调用上面 @Pointcut标注的方法前执行以下方法
    public void doBefore(JoinPoint joinPoint){//用于获取类方法
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();
        //url
        logger.info("url ={}",request.getRequestURI());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+'.'+ joinPoint.getSignature().getName());//获取类名及类方法
        //参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("cut()")//无论Controller中调用方法以何种方式结束，都会执行
    public void doAfter(){
        logger.info("----doAfter-----------");
    }

    @AfterReturning(returning = "obj",pointcut = "cut()")//在调用上面 @Pointcut标注的方法后执行。用于获取返回值
    public void doAfterReturning(Object obj){
        logger.info("response={}",obj.toString());
    }
}
