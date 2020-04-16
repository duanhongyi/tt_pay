package com.bytedance.caijing.tt_pay.util;

import com.google.gson.Gson;

public class JsonUtil {

    private static final Gson GSON = new Gson();

    public static String toJSON(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJSON(String data, java.lang.Class<T> classOfT) {

        return GSON.fromJson(data, classOfT);
    }

}
