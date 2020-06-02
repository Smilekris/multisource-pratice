package com.example.multisource.srevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.multisource.entity.CreditRecord;

/**
 * @ClassName CreditRecordDB2Service
 * @Author yamei
 * @Date 2020/6/1
 **/
public interface CreditRecordDB2Service extends IService<CreditRecord> {

    int add(CreditRecord creditRecord);
}
