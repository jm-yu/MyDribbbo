package jmyu.ufl.edu.mydribbbo.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by jmyu on 7/7/18.
 */

public class ModelUtils {

    private static Gson gson = new Gson();
    private static String PREF_NAME = "models";


    public static <T> T toObject(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> String toString(T object, TypeToken<T> typeToken) {
        return gson.toJson(object, typeToken.getType());
    }
}
