package com.example.multisource.controller;

import com.example.multisource.entity.CreditRecord;
import com.example.multisource.srevice.CreditRecordDB1Service;
import com.example.multisource.srevice.CreditRecordDB2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @ClassName CreditRecordController
 * @Author yamei
 * @Date 2020/5/29
 **/
@RestController
@RequestMapping("/multi-source")
public class CreditRecordController {
    @Autowired
    private CreditRecordDB1Service creditRecordDB1Service;
    @Autowired
    private CreditRecordDB2Service creditRecordDB2Service;

    @GetMapping("/add")
    public String add(){
        int currentTimeMills = (int)Instant.now().getEpochSecond();
        CreditRecord creditRecord = new CreditRecord();
        creditRecord.setId(1L);
        creditRecord.setBeforeAmount(0);
        creditRecord.setChangeAmount(50);
        creditRecord.setAfterAmount(50);
        creditRecord.setCreateTime(currentTimeMills);
        creditRecord.setUpdateTime(currentTimeMills);
        creditRecordDB1Service.save(creditRecord);
        return "ok";
    }

    @GetMapping("/add2")
    public String add2(){
        int currentTimeMills = (int)Instant.now().getEpochSecond();
        CreditRecord creditRecord = new CreditRecord();
        creditRecord.setId(1L);
        creditRecord.setBeforeAmount(0);
        creditRecord.setChangeAmount(50);
        creditRecord.setAfterAmount(50);
        creditRecord.setCreateTime(currentTimeMills);
        creditRecord.setUpdateTime(currentTimeMills);
        creditRecordDB2Service.add(creditRecord);
        return "ok";
    }

    @GetMapping("/mix-add")
    public String mixAdd(){
        int currentTimeMills = (int)Instant.now().getEpochSecond();
        CreditRecord creditRecordA = new CreditRecord();
        creditRecordA.setId(2L);
        creditRecordA.setBeforeAmount(50);
        creditRecordA.setChangeAmount(50);
        creditRecordA.setAfterAmount(100);
        creditRecordA.setCreateTime(currentTimeMills);
        creditRecordA.setUpdateTime(currentTimeMills);
        CreditRecord creditRecordB = new CreditRecord();
        creditRecordB.setId(2L);
        creditRecordB.setBeforeAmount(50);
        creditRecordB.setChangeAmount(-50);
        creditRecordB.setAfterAmount(0);
        creditRecordB.setCreateTime(currentTimeMills);
        creditRecordB.setUpdateTime(currentTimeMills);
        creditRecordDB1Service.save(creditRecordA);
        creditRecordDB2Service.add(creditRecordB);
        return "ok";
    }
    @GetMapping("/mix-add/transation")
    public String mixAddWithTransation(){
        creditRecordDB1Service.addWithTransation();
        return "ok";
    }

}
