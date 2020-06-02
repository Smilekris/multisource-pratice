package com.example.multisource.srevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.multisource.annonation.DataSource;
import com.example.multisource.dao.db2.CreditRecordDB2Mapper;
import com.example.multisource.entity.CreditRecord;
import com.example.multisource.enums.DataSourceEnum;
import com.example.multisource.srevice.CreditRecordDB2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName CreditRecordDB2ServiceImpl
 * @Author yamei
 * @Date 2020/6/1
 **/
@Service
public class CreditRecordDB2ServiceImpl extends ServiceImpl<CreditRecordDB2Mapper, CreditRecord> implements CreditRecordDB2Service {

    @Override
    @Transactional("db2TransactionManager")
    public int add(CreditRecord creditRecord) {
        return this.baseMapper.insert(creditRecord);
    }
}
