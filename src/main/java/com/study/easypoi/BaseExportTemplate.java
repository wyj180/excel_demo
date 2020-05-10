package com.study.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.study.utils.FileUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Easy POI导出excel ; 模板导出excel抽象父类
 */
@Slf4j
@Getter
@Setter
@ToString
public abstract class BaseExportTemplate {

    // 导出的模板
    protected String templatePath;

    // 导出的文件名
    protected String downLoadFileName;

    // 导出到excel的数据
    protected Map<String, Object> excelData = new HashMap<>();

    private InputStream buildExcelInputStream(Map<String, Object> excelData) {
        Workbook workbook = null;
        ByteArrayOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        try {
            TemplateExportParams params = new TemplateExportParams(templatePath);
            workbook = ExcelExportUtil.exportExcel(params, excelData);

            // 获取excel输出流
            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            // 根据输出流程获取excel的输入流
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            log.error("导出excel出错", e);
        } finally {
            FileUtils.close(outputStream, workbook);
        }
        return inputStream;
    }

    /**
     * 給excel设置数据
     *
     * @param
     * @param sourceListData
     * @return
     */
    public <T> void setRowList(List<T> sourceListData) {
        this.excelData.put("rowList", sourceListData);
    }

    /**
     * 下载方式一：这种方式适用于下载列表数据
     *
     * @param sourceListData
     * @param templatePath
     * @param downFileName
     * @param response
     * @param <T>
     */
    protected <T> void simpleDownLoadExcel(List<T> sourceListData, String templatePath, String downFileName, HttpServletResponse response) {
        setRowList(sourceListData);
        this.commonDownLoadExcel(this.excelData, templatePath, downFileName, response);
    }

    /**
     * 下载方式二：这种方式，需要子类封装好下载的Map数据
     */
    protected void commonDownLoadExcel(Map<String, Object> excelData, String templatePath, String downFileName, HttpServletResponse response) {
        this.excelData = excelData;
        this.templatePath = templatePath;
        this.downLoadFileName = downFileName;

        InputStream inputStream = null;
        try {
            setExcelData();
            inputStream = buildExcelInputStream(this.excelData);
            FileUtils.writeToResponse(response, inputStream, downFileName);
        } finally {
            FileUtils.close(inputStream);
        }
    }

    /**
     * 模板方法，子类设置excel中需要展示的数据
     */
    protected void setExcelData() {
        // 设置excelData
    }

}
