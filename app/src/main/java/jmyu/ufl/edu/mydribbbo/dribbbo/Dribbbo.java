package jmyu.ufl.edu.mydribbbo.dribbbo;

import android.content.Context;
import android.content.SharedPreferences;

import jmyu.ufl.edu.mydribbbo.model.User;

/**
 * Created by jmyu on 7/4/18.
 */

public class Dribbbo {

    private static final String SP_AUTH = "auth";
    private static final String KEY_TOKEN = "token";


    private static String token;
    private static User user;

    public static void init(Context context) {
        token = loadToken(context);
        if (token != null) {
            // load user
        }
    }

    private static String loadToken(Context context) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(SP_AUTH, Context.MODE_PRIVATE);
        return sp.getString(KEY_TOKEN, null);
    }

    public static boolean isLoggedIn() {
        return token != null;
    }

    public static void login(Context context, String token) {
        Dribbbo.token = token;
        storeToken(context, token);
        //todo deal with user
    }

    private static void storeToken(Context context, String token) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(SP_AUTH, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_TOKEN, token).apply();
    }
}
