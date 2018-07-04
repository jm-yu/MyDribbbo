package jmyu.ufl.edu.mydribbbo.dribbbo.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmyu.ufl.edu.mydribbbo.R;

/**
 * Created by jmyu on 7/4/18.
 */

public class AuthActivity extends AppCompatActivity{

    public static final String KEY_URL = "url";
    public static final String KEY_CODE = "code";


    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.webview) WebView webView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Log into Dribbbo");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(Auth.REDIRECT_URI)){
                    Uri uri = Uri.parse(url);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(KEY_CODE, uri.getQueryParameter(KEY_CODE));
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        String url = getIntent().getStringExtra(KEY_URL);
        System.out.println(url);
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
