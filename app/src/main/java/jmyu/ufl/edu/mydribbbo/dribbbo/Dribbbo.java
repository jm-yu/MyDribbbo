package jmyu.ufl.edu.mydribbbo.dribbbo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.List;

import jmyu.ufl.edu.mydribbbo.model.Shot;
import jmyu.ufl.edu.mydribbbo.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jmyu on 7/4/18.
 */

public class Dribbbo {

    private static final String SP_AUTH = "auth";
    private static final String KEY_TOKEN = "token";

    private static final String API_URL = "https://api.dribbble.com/v2/";

    private static final String SHOTS_END_POINT = API_URL + "shots";
    private static final String USER_END_POINT = API_URL + "user";


    private static String token;
    private static User user;
    private static OkHttpClient client;

    public static void init(Context context) {
        client = new OkHttpClient();
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

    public static List<Shot> getShots(int page) throws IOException {
        String url = SHOTS_END_POINT + "?page=" + page;
        Response response = makeGetRequest(url);
        return null;
    }

    private static Response makeGetRequest(String url) throws IOException {
        Request request = authRequestBuilder(url).build();
        return makeRequest(request);
    }

    private static Response makeRequest(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    public static void getUser() throws IOException, JsonSyntaxException {
        Response response =  makeGetRequest(USER_END_POINT);
        Log.d("Jimmy_response", response.body().string());
    }

    private static Request.Builder authRequestBuilder(String url) {
        return new Request.Builder().addHeader("Authorization", "Bearer " + token)
                .url(url);
    }

}
