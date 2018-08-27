package com.example.administrator.xiudoufang.common.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import com.example.administrator.xiudoufang.R;
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
    public static final String PIC_URL = "http://192.168.1.8:81/pic/";
    public static final String PIC_SMALL_URL = "http://192.168.1.8:81/pic_small/";
    public static final String FILE_URL = "http://192.168.1.8:81/file/";

//    public static final String BASE_URL = "http://aoxing.pro:83";
//    public static final String PIC_URL = "http://aoxing.pro:81/pic/";
//    public static final String PIC_SMALL_URL = "http://aoxing.pro:81/pic_small/";
//    public static final String FILE_URL = "http://aoxing.pro:81/file/";

    private static StringBuilder builder;
    public static final String LOGIN_INFO = "login_info.txt";

    public static String getUrl(String url, Map<String, String> map) {
        if (builder == null) {
            builder = new StringBuilder();
        } else {
            builder.setLength(0);
        }
        builder.append(BASE_URL).append(url);
        for (Map.Entry entry : map.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
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

    public static SpannableString getSpannableString(String text, int position) {
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(XiuDouFangApplication.getContext(), R.color.red));
        spannableString.setSpan(colorSpan, position, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static String checkEmpty(String value, String defaultValue) {
        String result = TextUtils.isEmpty(value) ? defaultValue : value;
        return result;
    }
}
