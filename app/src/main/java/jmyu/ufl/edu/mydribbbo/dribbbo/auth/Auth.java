package jmyu.ufl.edu.mydribbbo.dribbbo.auth;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jmyu on 7/4/18.
 */

public class Auth {

    private static final String URI_AUTHORIZE = "https://dribbble.com/oauth/authorize";

    private static final String CLIENT_ID = "d5223632f3ec66514d2a4b13e0cea3ca4f71cc5a98ad597097c084d9e5520e3c";

    private static final String CLIENT_SECRET = "919769119f12b29ba8f87c93e347b8952f4a1616bbd396768b7f5109da754045";

    private static final String KEY_CODE = "code";
    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_CLIENT_SECRET = "client_secret";
    private static final String KEY_REDIRECT_URI = "redirect_uri";
    private static final String KEY_SCOPE = "scope";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    public static final String REDIRECT_URI = "https://www.google.com";

    private static final String SCOPE = "public+write";
    private static final String URI_TOKEN = "https://dribbble.com/oauth/token";


    public static String getAuthorizeUrl() {
        String url = Uri.parse(URI_AUTHORIZE)
                .buildUpon()
                .appendQueryParameter(KEY_CLIENT_ID, CLIENT_ID)
                .build()
                .toString();

        // fix encode issue
        url += "&" + KEY_REDIRECT_URI + "=" + REDIRECT_URI;
        url += "&" + KEY_SCOPE + "=" + SCOPE;

        return url;
    }

    public static String getTokenfromCode(String authCode) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody postBody = new FormBody.Builder()
                .add(KEY_CLIENT_ID, CLIENT_ID)
                .add(KEY_CLIENT_SECRET, CLIENT_SECRET)
                .add(KEY_CODE, authCode)
                .add(KEY_REDIRECT_URI, REDIRECT_URI)
                .build();
        Request request = new Request.Builder()
                .url(URI_TOKEN)
                .post(postBody)
                .build();
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseString);
            return jsonObject.getString(KEY_ACCESS_TOKEN);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
