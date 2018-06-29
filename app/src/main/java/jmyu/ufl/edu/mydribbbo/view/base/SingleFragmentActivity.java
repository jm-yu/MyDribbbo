package jmyu.ufl.edu.mydribbbo.view.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmyu.ufl.edu.mydribbbo.R;

/**
 * Created by jmyu on 6/29/18.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getActivityTitle());

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_frame, newFragment())
                    .commit();
        }
    }

    protected String getActivityTitle() { return "";
    }

    @NonNull
    protected abstract Fragment newFragment();
}
