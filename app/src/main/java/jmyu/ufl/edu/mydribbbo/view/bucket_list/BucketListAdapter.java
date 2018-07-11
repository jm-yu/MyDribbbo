package jmyu.ufl.edu.mydribbbo.view.bucket_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.MessageFormat;
import java.util.List;

import jmyu.ufl.edu.mydribbbo.R;
import jmyu.ufl.edu.mydribbbo.model.Bucket;

/**
 * Created by jmyu on 6/28/18.
 */

class BucketListAdapter extends RecyclerView.Adapter {

    private List<Bucket> data;
    LoadMoreListener loadMoreListener;
    boolean showLoading;

    private static final int VIEW_TYPE_BUCKET = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    public BucketListAdapter(List<Bucket> buckets, LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
        this.data = buckets;
        showLoading = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_BUCKET) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bucket_list_item, parent, false);
            return new BucketViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_list_loading, parent, false);
            return new RecyclerView.ViewHolder(view) {};
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_BUCKET) {
            Bucket bucket = data.get(position);

            String bucketShotCountString = MessageFormat.format(
                    holder.itemView.getContext().getResources().getString(R.string.shot_count),
                    bucket.shots_count);

            BucketViewHolder bucketViewHolder = (BucketViewHolder) holder;
            bucketViewHolder.bucketName.setText(bucket.name);
            bucketViewHolder.bucketShotCount.setText(bucketShotCountString);
        } else if (viewType == VIEW_TYPE_LOADING) {
            loadMoreListener.loadMore();
        }
    }

    public void setShowLoading(boolean showLoading) {
        if (showLoading != this.showLoading){
            this.showLoading = showLoading;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return showLoading ? data.size() + 1 : data.size();
    }

    public int getItemViewType(int position) {
        return position < data.size() ? VIEW_TYPE_BUCKET : VIEW_TYPE_LOADING;
    }

    public void append(List<Bucket> shots) {
        data.addAll(shots);
        notifyDataSetChanged();
    }

    public int getDataCount() {
        return data.size();
    }

    public void prepend(List<Bucket> buckets) {
        data.addAll(0, buckets);
        notifyDataSetChanged();
    }

    public interface LoadMoreListener {
        public void loadMore();
    }
}
