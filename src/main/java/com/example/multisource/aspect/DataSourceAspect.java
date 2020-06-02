package com.example.multisource.aspect;

import com.example.multisource.annonation.DataSource;
import com.example.multisource.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @ClassName DataSourceAspect
 * @Author yamei
 * @Date 2020/5/29
 **/
//@Component
@Slf4j
//@Aspect
//@Order(-1)
public class DataSourceAspect {

    @Pointcut("@within(com.example.multisource.annonation.DataSource) || @annotation(com.example.multisource.annonation.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        log.info("选择数据源---"+dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clear();
    }
}
