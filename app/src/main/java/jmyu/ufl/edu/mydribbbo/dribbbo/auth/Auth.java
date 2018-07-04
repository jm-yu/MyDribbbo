package jmyu.ufl.edu.mydribbbo.dribbbo.auth;

import android.net.Uri;

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

    public static final String REDIRECT_URI = "http://www.google.com";

    private static final String SCOPE = "public+write";


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
}
