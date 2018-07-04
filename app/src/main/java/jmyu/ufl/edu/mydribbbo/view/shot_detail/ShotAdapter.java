package jmyu.ufl.edu.mydribbbo.view.shot_detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import jmyu.ufl.edu.mydribbbo.R;
import jmyu.ufl.edu.mydribbbo.model.Shot;

/**
 * Created by jmyu on 6/29/18.
 */

class ShotAdapter extends RecyclerView.Adapter {


    Shot shot;

    private static final int VIEW_SHOT_TYPE_IMAGE = 1;
    private static final int VIEW_SHOT_TYPE_INFO = 2;

    public ShotAdapter(@NonNull Shot shot) {
        this.shot = shot;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_SHOT_TYPE_IMAGE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item_image, parent, false);
            return new ShotImageViewHolder(view);
        } else if(viewType == VIEW_SHOT_TYPE_INFO){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item_info, parent, false);
            return new ShotInfoViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_SHOT_TYPE_IMAGE :
                System.out.println(shot.getImageUrl());
                Picasso.with(holder.itemView.getContext())
                        .load(shot.getImageUrl())
                        .placeholder(R.drawable.shot_placeholder)
                        .into(((ShotImageViewHolder) holder).image);
                break;
            case VIEW_SHOT_TYPE_INFO :
                ShotInfoViewHolder shotDetailViewHolder = (ShotInfoViewHolder) holder;

                shotDetailViewHolder.title.setText(shot.title);
                shotDetailViewHolder.authorName.setText(shot.user.name);
                shotDetailViewHolder.description.setText(shot.description);
                shotDetailViewHolder.likeCount.setText(String.valueOf(shot.likes_count));
                shotDetailViewHolder.bucketCount.setText(String.valueOf(shot.buckets_count));
                shotDetailViewHolder.viewCount.setText(String.valueOf(shot.views_count));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_SHOT_TYPE_IMAGE;
        } else if (position == 1){
            return VIEW_SHOT_TYPE_INFO;
        }
        return -1;
    }
}
