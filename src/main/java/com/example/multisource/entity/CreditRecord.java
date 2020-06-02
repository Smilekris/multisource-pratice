package com.example.multisource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName CreditRecord
 * @Author yamei
 * @Date 2020/5/29
 **/
@Data
@TableName("creadit_record")
public class CreditRecord {

    private Long id;

    private Integer beforeAmount;

    private Integer afterAmount;

    private Integer changeAmount;

    private Integer createTime;

    private Integer updateTime;
}
