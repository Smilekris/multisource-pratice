package com.example.multisource.srevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.multisource.annonation.MultiTm;
import com.example.multisource.dao.db1.CreditRecordDB1Mapper;
import com.example.multisource.dao.db2.CreditRecordDB2Mapper;
import com.example.multisource.entity.CreditRecord;
import com.example.multisource.srevice.CreditRecordDB1Service;
import com.example.multisource.srevice.CreditRecordDB2Service;
import com.example.multisource.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.time.Instant;

/**
 * @ClassName CreditRecordServiceImpl
 * @Author yamei
 * @Date 2020/5/29
 **/
@Service
public class CreditRecordDB1ServiceImpl extends ServiceImpl<CreditRecordDB1Mapper, CreditRecord> implements CreditRecordDB1Service {
    @Autowired
    private CreditRecordDB2Service creditRecordDB2Service;
//    @Autowired
//    private CreditRecordDB2Mapper creditRecordDB2Mapper;

    @Override
//    @Transactional(transactionManager="db2TransactionManager",
//            rollbackFor = Exception.class)
    @MultiTm(transactionManagers={"db1TransactionManager","db2TransactionManager"})
    public int addWithTransation() {
        int currentTimeMills = (int) Instant.now().getEpochSecond();
        CreditRecord creditRecordA = new CreditRecord();
        creditRecordA.setId(3L);
        creditRecordA.setBeforeAmount(100);
        creditRecordA.setChangeAmount(50);
        creditRecordA.setAfterAmount(150);
        creditRecordA.setCreateTime(currentTimeMills);
        creditRecordA.setUpdateTime(currentTimeMills);
        CreditRecord creditRecordB = new CreditRecord();
        creditRecordB.setId(3L);
        creditRecordB.setBeforeAmount(0);
        creditRecordB.setChangeAmount(-50);
        creditRecordB.setAfterAmount(-50);
        creditRecordB.setCreateTime(currentTimeMills);
        creditRecordB.setUpdateTime(currentTimeMills);

        this.baseMapper.insert(creditRecordA);


        creditRecordDB2Service.add(creditRecordB);
        if(true){
            throw new RuntimeException("抛出异常");
        }
        return 1;
    }
}
