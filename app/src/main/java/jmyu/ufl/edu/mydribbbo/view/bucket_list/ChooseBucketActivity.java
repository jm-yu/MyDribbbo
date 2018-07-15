package jmyu.ufl.edu.mydribbbo.view.bucket_list;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import jmyu.ufl.edu.mydribbbo.view.base.SingleFragmentActivity;

/**
 * Created by jmyu on 7/15/18.
 */

public class ChooseBucketActivity extends SingleFragmentActivity {

    @NonNull
    @Override
    protected Fragment newFragment() {
        // todo: pass a list of bucketIDs
        return BucketListFragment.newInstance(true, new ArrayList<String>());
    }
}
