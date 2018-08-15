package com.example.administrator.xiudoufang.common.utils;

import com.example.administrator.xiudoufang.base.XiuDouFangApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

public class StringUtils {

    public static final String BASE_URL = "http://192.168.1.8:83";
    private static StringBuilder builder;
    public static final String LOGIN_INFO = "login_info.txt";

    public static String getUrl(String url, Map<String, String> map) {
        if (builder == null) {
            builder = new StringBuilder();
        } else {
            builder.setLength(0);
        }
        builder.append(BASE_URL).append(url);
        for (String key : map.keySet()) {
            builder.append(key).append("=").append(map.get(key)).append("&");
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    public static String readInfoForFile(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(XiuDouFangApplication.getContext().getCacheDir().getAbsolutePath() + File.separator + fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            LogUtils.e("error->" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void cacheInfoToFile(String content, String fileName) {
        FileOutputStream outStream = null;
        String path = XiuDouFangApplication.getContext().getCacheDir().getAbsolutePath() + File.separator + fileName;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.YEAR)).append("-").append(calendar.get(Calendar.MONTH)+1).append("-").append(calendar.get(Calendar.DATE));
        return builder.toString();
    }
}
