package com.example.multisource.aspect;

import com.example.multisource.annonation.MultiTm;
import com.example.multisource.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Stack;

/**
 * @ClassName TsetAspect
 * @Author yamei
 * @Date 2020/6/1
 **/
@Component
@Slf4j
@Aspect
@Order(-1)
public class TsetAspect {
    @Pointcut("@within(com.example.multisource.annonation.MultiTm) || @annotation(com.example.multisource.annonation.MultiTm)")
    public void TsetAspect(){

    }
    @Around(value = "TsetAspect() && @annotation(multiTm)")
    public Object transactionalGroupAspectArround(ProceedingJoinPoint pjp, MultiTm multiTm) throws Throwable{
        Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack = new Stack<>();
        Stack<TransactionStatus> transactionStatusStack = new Stack<>();
        if (multiTm.transactionManagers().length<1){
            log.info("[开启事务失败]：无指定多数据源管理器");
            return null;
        }
        for(String transationMangaeName: multiTm.transactionManagers()){
            DataSourceTransactionManager dbManager = (DataSourceTransactionManager) SpringContextUtil.getBean(transationMangaeName);
            TransactionStatus transactionDBStatus = dbManager.getTransaction(new DefaultTransactionDefinition());
            dataSourceTransactionManagerStack.push(dbManager);
            transactionStatusStack.push(transactionDBStatus);
        }

        try{
            Object obj = pjp.proceed();
            while(!dataSourceTransactionManagerStack.isEmpty()){
                dataSourceTransactionManagerStack.pop().commit(transactionStatusStack.pop());
            }
            return obj;
        }catch(Exception e){
            log.info(e.getMessage());
            while(!dataSourceTransactionManagerStack.isEmpty()){
                dataSourceTransactionManagerStack.pop().rollback(transactionStatusStack.pop());
            }
            return null;
        }
    }
}
