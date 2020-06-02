package com.example.multisource.annonation;

import com.example.multisource.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @ClassName DataSource
 * @Author yamei
 * @Date 2020/5/29
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DataSourceEnum value() default DataSourceEnum.DB1;
}
