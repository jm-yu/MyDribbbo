package jmyu.ufl.edu.mydribbbo.view.bucket_list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    boolean isChosenMode;

    private static final int VIEW_TYPE_BUCKET = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    public BucketListAdapter(List<Bucket> buckets, LoadMoreListener loadMoreListener, boolean isChosenMode) {
        this.loadMoreListener = loadMoreListener;
        this.data = buckets;
        this.showLoading = true;
        this.isChosenMode = isChosenMode;
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
            BucketViewHolder bucketViewHolder = (BucketViewHolder) holder;
            Context context = holder.itemView.getContext();

            String bucketShotCountString = MessageFormat.format(
                    holder.itemView.getContext().getResources().getString(R.string.shot_count),
                    bucket.shots_count);

            bucketViewHolder.bucketName.setText(bucket.name);
            bucketViewHolder.bucketShotCount.setText(bucketShotCountString);

            if (isChosenMode) {
                bucketViewHolder.bucketChosen.setVisibility(View.VISIBLE);
                bucketViewHolder.bucketChosen.setImageDrawable(
                        bucket.isChosen
                                ? ContextCompat.getDrawable(context, R.drawable.ic_check_box_black_24dp)
                                : ContextCompat.getDrawable(context, R.drawable.ic_check_box_outline_blank_black_24dp));
                bucketViewHolder.bucketLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bucket.isChosen = !bucket.isChosen;
                        notifyItemChanged(position);
                    }
                });
            } else {
                bucketViewHolder.bucketChosen.setVisibility(View.GONE);
                bucketViewHolder.bucketLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "show shots in this bucket", Toast.LENGTH_LONG).show();
                    }
                });
            }




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
