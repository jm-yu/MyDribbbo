package jmyu.ufl.edu.mydribbbo.view.shot_detail;

import android.app.Fragment;
import android.support.annotation.NonNull;

import jmyu.ufl.edu.mydribbbo.view.base.SingleFragmentActivity;

/**
 * Created by jmyu on 6/29/18.
 */

public class ShotActivity extends SingleFragmentActivity {

    public static final String KEY_SHOT_TITLE = "shot_title";

    @NonNull
    @Override
    protected Fragment newFragment() {
        return ShotFragment.newInstance(getIntent().getExtras());
    }

    @NonNull
    @Override
    protected String getActivityTitle() {
        return getIntent().getStringExtra(KEY_SHOT_TITLE);
    }
}
