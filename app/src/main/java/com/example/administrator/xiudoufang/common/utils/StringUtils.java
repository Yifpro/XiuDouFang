package com.example.administrator.xiudoufang.common.utils;

import java.util.Map;

public class StringUtils {

    public static final String BASE_URL = "http://192.168.1.8:83";
    private static StringBuilder builder;

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
}
