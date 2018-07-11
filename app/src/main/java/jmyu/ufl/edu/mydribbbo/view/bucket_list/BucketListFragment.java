package jmyu.ufl.edu.mydribbbo.view.bucket_list;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmyu.ufl.edu.mydribbbo.R;
import jmyu.ufl.edu.mydribbbo.dribbbo.Dribbbo;
import jmyu.ufl.edu.mydribbbo.model.Bucket;

/**
 * Created by jmyu on 6/26/18.
 */

public class BucketListFragment extends Fragment {

    private static final int COUNT_PER_PAGE = 12;
    public static final int REQ_CODE_NEW_BUCKET = 100;
    @BindView(R.id.recycler_view_fab) RecyclerView recyclerView;
    @BindView(R.id.fab) FloatingActionButton fab;
    BucketListAdapter adapter;

    public static BucketListFragment newInstance() {
        return new BucketListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_recycler_view_with_fab, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BucketListAdapter(new ArrayList<>(), new BucketListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                new LoadBucketTask(adapter.getDataCount()/ COUNT_PER_PAGE + 1).execute();
            }
        });
        fab.setOnClickListener(v -> {
            NewBucketDialogFragment dialogFragment = NewBucketDialogFragment.newInstance();
            dialogFragment.show(getFragmentManager(), NewBucketDialogFragment.TAG);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_NEW_BUCKET && resultCode == Activity.RESULT_OK) {
            String bucketName = data.getStringExtra(NewBucketDialogFragment.KEY_BUCKET_NAME);
            String bucketDescription = data.getStringExtra(NewBucketDialogFragment.KEY_BUCKET_DESCRIPTION);
            if (!TextUtils.isEmpty(bucketName)) {
                Dribbbo.newBucket(bucketName, bucketDescription);
            }
        }
    }

    private class LoadBucketTask extends AsyncTask<Void, Void, List<Bucket>> {

        private int page;

        public LoadBucketTask(int page) {
            this.page = page;
        }

        @Override
        protected List<Bucket> doInBackground(Void... voids) {
            try {
                return Dribbbo.getBuckets(page);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Bucket> buckets) {
            if (buckets != null) {
                if (buckets.size() < COUNT_PER_PAGE) {
                    adapter.setShowLoading(false);
                }
                adapter.append(buckets);
            } else {
                Snackbar.make(getView(), "Error!", Snackbar.LENGTH_LONG).show();
            }
        }

    }
}
