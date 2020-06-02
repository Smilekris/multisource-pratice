package com.example.multisource.dao.db2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.multisource.annonation.DataSource;
import com.example.multisource.entity.CreditRecord;
import com.example.multisource.enums.DataSourceEnum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@DataSource(DataSourceEnum.DB2)
public interface CreditRecordDB2Mapper extends BaseMapper<CreditRecord> {
}
