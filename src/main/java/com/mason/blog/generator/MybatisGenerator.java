package com.mason.blog.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mason
 * Date: 2018/6/19
 * Time: 16:01
 */
public class MybatisGenerator {

    public static void main(String[] args) throws Exception{
        String tody="2018-6-21";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Date now = sdf.parse(tody);
        Date d = new Date();
        if(d.getTime()>now.getTime()+1000*60*60*24){
            System.err.println("——————未成功运行——————");
            System.err.println("——————未成功运行——————");
            System.err.println("本程序具有破坏作用，应该只运行一次，如果必须要再运行，需要修改today变量为今天，如:" + sdf.format(new Date()));
            return ;
        }

        if(false) {
            return;
        }

        List<String> warning = new ArrayList<String>();
        boolean overwrite = true;
        InputStream is = MybatisGenerator.class.getClassLoader().getResource("generator.xml").openStream();
        ConfigurationParser cf = new ConfigurationParser(warning);
        Configuration cfg = cf.parseConfiguration(is);
        is.close();
        DefaultShellCallback ds = new DefaultShellCallback(overwrite);
        MyBatisGenerator mybatisGenerator = new MyBatisGenerator(cfg,ds,warning);
        mybatisGenerator.generate(null);
        System.out.println("生成代码成功，只能执行一次，以后执行会覆盖掉mapper,pojo,xml 等文件上做的修改");
    }
}
