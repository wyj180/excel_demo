package com.study.service;

import com.study.easypoi.CommomExportExcel;
import com.study.entity.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.study.utils.ExcelConstants.UserExcel.*;

@Service
public class EasyPoiUse1Serive {

    @Autowired
    private CommomExportExcel commomExportExcel;

    public void downloadExcel(HttpServletResponse response) {
        List<TestUser> qualitySpills = new ArrayList<>();
        setList(qualitySpills);

        commomExportExcel.commonDownLoad(qualitySpills, TEMPLATE_PATH, DOWNLOAD_FILE_NAME, response);
    }

    private void setList(List<TestUser> sourceList) {
        TestUser userA = new TestUser();
        userA.setUserId(1);
        userA.setUserName("用户名AAA");
        userA.setHobby("游泳");
        userA.setCreateTime(new Date());

        TestUser userB = new TestUser();
        userB.setUserId(2);
        userB.setUserName("用户名BBB");
        userB.setHobby("跑步");
        userB.setCreateTime(new Date());

        sourceList.add(userA);
        sourceList.add(userB);
    }
}
