package com.study.controller;

import com.study.service.EasyPoiUse1Serive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Easy Poi 接口
 */
@RestController
public class EasyPoiController {

    @Autowired
    private EasyPoiUse1Serive easyPoiUse1Serive;

    // 访问地址：http://127.0.0.1:8080/download/demo01
    @RequestMapping("download/demo01")
    public String downloadExcel(HttpServletResponse response){
        easyPoiUse1Serive.downloadExcel(response);
        return "success";
    }
}
