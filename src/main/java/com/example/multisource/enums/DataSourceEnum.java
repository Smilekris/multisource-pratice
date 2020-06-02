package com.example.multisource.enums;

/**
 * @ClassName DataSourceEnum
 * @Author yamei
 * @Date 2020/5/29
 **/
public enum DataSourceEnum {
    DB1("db1"),DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
