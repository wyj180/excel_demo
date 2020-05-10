package com.study.easypoi;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Student导出excel工具类
 */
@Component
public class CommomExportExcel extends BaseExportTemplate {

    public void commonDownLoad(Map<String, Object> excelData, String templatePath, String downFileName, HttpServletResponse response) {
        commonDownLoadExcel(excelData,templatePath, downFileName, response);
    }

    public <T> void commonDownLoad(List<T> rowList, String templatePath,  String downFileName, HttpServletResponse response) {
        simpleDownLoadExcel(rowList, templatePath, downFileName, response);
    }

}
