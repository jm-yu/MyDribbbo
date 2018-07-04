package jmyu.ufl.edu.mydribbbo.dribbbo;

import android.content.Context;

import jmyu.ufl.edu.mydribbbo.model.User;

/**
 * Created by jmyu on 7/4/18.
 */

public class Dribbbo {

    private static String token;
    private static User user;

    public static void init(Context context) {
        token = loadToken(context);
        if (token != null) {
            // load user
        }
    }

    private static String loadToken(Context context) {
        // todo
        return "";
    }

    public static boolean isLoggedIn() {
        // todo
        return false;
    }
}
