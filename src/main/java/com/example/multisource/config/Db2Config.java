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
 * @ClassName Db2Config
 * @Author yamei
 * @Date 2020/6/2
 **/
@Configuration
@MapperScan(basePackages = "com.example.multisource.dao.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class Db2Config {
    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2" )
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "db2TransactionManager")
    @Primary
    public DataSourceTransactionManager db2TransactionManager() {
        return new DataSourceTransactionManager(db2());
    }
    @Bean(name = "db2SqlSessionFactory")
    @Primary
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2") DataSource db2)
            throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(db2);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/db2/*.xml"));

        /*设置sql控制台打印*/
        com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();
    }
}
