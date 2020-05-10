package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Slf4j
public class FileUtils {

    /**
     * 下载文件到浏览器
     *
     * @param response
     * @param inputStream
     * @param fileName    浏览器下载的文件名称
     */
    public static void writeToResponse(HttpServletResponse response, InputStream inputStream, String fileName) {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(fileName, "UTF-8"));
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            log.error("下载文件出错, fileName:{}", fileName, e);
        } finally {
            close(inputStream, outputStream);
        }
    }

    public static void close(Closeable... closables) {
        if (closables == null || closables.length == 0) {
            return;
        }
        for (Closeable closable : closables) {
            try {
                if (closable != null) {
                    closable.close();
                }
            } catch (IOException e) {
                log.error("close stream error", e);
            }
        }
    }

}
