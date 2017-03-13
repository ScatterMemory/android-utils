package com.lhg.sangong.utils;

import android.os.Environment;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ethan on 2016/6/24.
 * 文件辅助类
 */
public class FileUtil {
    /**
     * 默认下载文件地址.
     */
    private static String downPathRootDir = File.separator + "SGgame" + File.separator;
    /**
     * 默认下载图片文件地址.
     */
    private static String downPathImageDir = downPathRootDir + "SGImages" + File.separator;
    /**
     * 默认下载文件地址.
     */
    private static String downPathFileDir = downPathRootDir + "SGFiles" + File.separator;
    /**
     * 默认日志存放地址
     */
    private static String logFileDir = downPathRootDir + "SGLogs" + File.separator;
    /**
     * MB  单位B
     */
    private static int MB = 1024 * 1024;
    /**
     * 剩余空间大于100M才使用缓存
     */
    private static int freeSdSpaceNeededToCache = 100 * MB;

    /**
     * isCanUseSD SD卡是否能用.
     * @return true 可用,false不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取下载文件路径
     * @return 下载文件路径
     */
    public static String getDownPathFileDir() {
        return downPathFileDir;
    }

    /**
     * 获取日志文件路径
     * @return 日志文件路径
     */
    public static String getLogFileDir() {
        return logFileDir;
    }


    /**
     * 获取文件名，外链模式和通过网络获取.
     * @param url 文件地址
     * @return 文件名
     */
    public static String getFileNameFromUrl(String url, HttpURLConnection response) {
        if (SGUtils.isEmpty(url)) {
            return null;
        }

        if (url.contains("?")){
            url=url.split("\\?")[0];
        }

        String name = null;
        try {
            String suffix = null;
            //获取后缀
            if (url.lastIndexOf(".") != -1) {
                suffix = url.substring(url.lastIndexOf("."));
                if (suffix.indexOf("/") != -1 || suffix.indexOf("?") != -1 || suffix.indexOf("&") != -1) {
                    suffix = null;
                }
            }
            if (suffix == null) {
                //获取文件名
                String fileName = "unknow.tmp";

                Map<String, List<String>> headers = response.getHeaderFields();

                for (HashMap.Entry<String, List<String>> entry : headers.entrySet()) {
                    Matcher m = Pattern.compile(".*filename=(.*)").matcher((CharSequence) entry.getValue());
                    if (m.find()) {
                        fileName = m.group(1).replace("\"", "");
                    }
                }

                if (fileName != null && fileName.lastIndexOf(".") != -1) {
                    suffix = fileName.substring(fileName.lastIndexOf("."));
                }
            }

            name = SGMD5Util.MD5(url) + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}
