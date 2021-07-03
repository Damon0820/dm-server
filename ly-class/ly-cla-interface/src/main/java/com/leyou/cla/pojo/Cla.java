package com.leyou.cla.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Cla {
    private String classId;
    private String className;
    private String userId;
    private String memo;
    private String createBy;
    private String createName;
    private Date CreateDate;
    private String active;
}
