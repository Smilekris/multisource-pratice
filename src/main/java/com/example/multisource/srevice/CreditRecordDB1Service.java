package com.example.multisource.srevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.multisource.entity.CreditRecord;

/**
 * @ClassName CreditRecordDB1Service
 * @Author yamei
 * @Date 2020/5/29
 **/
public interface CreditRecordDB1Service extends IService<CreditRecord> {

    int addWithTransation();
}
