package com.mason.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//事物配置1
@EnableTransactionManagement
public class BlogApplication {
    public static void main(String[] args) {

        //Logger logger = LogManager.getLogger("RollingRandomAccessFileLogger");

        SpringApplication.run(BlogApplication.class, args);
//        for(int i = 0; i < 50000; i++) {
//            logger.trace("trace level");
//            logger.debug("debug level");
//            logger.info("info level");
//            logger.warn("warn level");
//            logger.error("error level");
//            logger.fatal("fatal level");
//        }

    }
}
