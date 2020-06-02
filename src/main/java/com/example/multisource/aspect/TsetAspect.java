package com.example.multisource.aspect;

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
    @Around(value = "TsetAspect()")
    public Object transactionalGroupAspectArround(ProceedingJoinPoint pjp) throws Throwable{
//        db1Manager.setDataSource((DataSource) SpringContextUtil.getBean("db1"));
//        db2Manager.setDataSource((DataSource) SpringContextUtil.getBean("db2"));
        DataSourceTransactionManager db1Manager = (DataSourceTransactionManager) SpringContextUtil
                .getBean("db1TransactionManager");
        TransactionStatus transactionDB1Status = db1Manager
                .getTransaction(new DefaultTransactionDefinition());
        DataSourceTransactionManager db2Manager = (DataSourceTransactionManager) SpringContextUtil
                .getBean("db2TransactionManager");
        TransactionStatus transactionDB2Status = db2Manager
                .getTransaction(new DefaultTransactionDefinition());


        try{
            Object obj = pjp.proceed();
            db2Manager.commit(transactionDB2Status);
            db1Manager.commit(transactionDB1Status);
            return obj;
        }catch(Exception e){
            log.info(e.getMessage());
            db2Manager.rollback(transactionDB2Status);
            db1Manager.rollback(transactionDB1Status);
            return null;
        }





    }
}
