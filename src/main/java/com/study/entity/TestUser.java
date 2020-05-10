package com.study.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TestUser {

    private Integer userId;

    private String userName;

    private String hobby;

    private Integer status;

    private Date createTime;

}