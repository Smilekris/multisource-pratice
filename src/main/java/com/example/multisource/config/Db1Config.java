package com.example.multisource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName Db1Config
 * @Author yamei
 * @Date 2020/6/2
 **/
@Configuration
@MapperScan(basePackages = "com.example.multisource.dao.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class Db1Config {
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1" )
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }
    // 事务控制器
    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager dp1TransactionManager() {
        return new DataSourceTransactionManager(db1());
    }
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1") DataSource db1)
            throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(db1);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/db1/*.xml"));

        /*设置sql控制台打印*/
        com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();
    }
}
