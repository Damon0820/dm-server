package com.leyou.cla.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Person {
    private String personId;
    private String personName;
    private String classId;
    private String memo;
    private String createBy;
    private String createName;
    private Date createDate;
    private String active;
}
