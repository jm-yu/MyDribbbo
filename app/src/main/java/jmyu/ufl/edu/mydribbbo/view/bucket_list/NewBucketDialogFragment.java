package jmyu.ufl.edu.mydribbbo.view.bucket_list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmyu.ufl.edu.mydribbbo.R;

/**
 * Created by jmyu on 7/9/18.
 */

@SuppressLint("ValidFragment")
public class NewBucketDialogFragment extends DialogFragment {

    public static final String TAG = "NewBucketDialogFragment";

    public static final String KEY_BUCKET_NAME = "bucket_name";
    public static final String KEY_BUCKET_DESCRIPTION = "bucket_description";

    @BindView(R.id.new_bucket_name)
    EditText bucketName;
    @BindView(R.id.new_bucket_description) EditText bucketDescription;

    public static NewBucketDialogFragment newInstance() {
        return new NewBucketDialogFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_bucket, null);
        ButterKnife.bind(this, view);
        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle("Create new Bucket")
                .setPositiveButton(R.string.bucket_create, (dialog, which) -> {
                    Intent result = new Intent();
                    result.putExtra(KEY_BUCKET_NAME, bucketName.getText().toString());
                    result.putExtra(KEY_BUCKET_DESCRIPTION, bucketDescription.getText().toString());
                    System.out.println(getTargetFragment());
                    getTargetFragment().onActivityResult(BucketListFragment.REQ_CODE_NEW_BUCKET,
                            Activity.RESULT_OK,
                            result);
                    dismiss();
                })
                .setNegativeButton(R.string.bucket_create_cancel, null)
                .show();
    }
}
