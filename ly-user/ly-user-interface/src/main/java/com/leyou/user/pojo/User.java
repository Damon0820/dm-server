package com.leyou.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private Date createDate;
    private String createBy;
    private String createName;
    private String active;
    @JsonIgnore
    private String salt;
}
