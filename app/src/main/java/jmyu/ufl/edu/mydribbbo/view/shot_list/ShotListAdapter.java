package jmyu.ufl.edu.mydribbbo.view.shot_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import jmyu.ufl.edu.mydribbbo.R;
import jmyu.ufl.edu.mydribbbo.model.Shot;
import jmyu.ufl.edu.mydribbbo.view.shot_detail.ShotActivity;
import jmyu.ufl.edu.mydribbbo.view.shot_detail.ShotFragment;

/**
 * Created by jmyu on 6/27/18.
 */

class ShotListAdapter extends RecyclerView.Adapter {

    private List<Shot> data;
    private static final int VIEW_TYPE_SHOT = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    public ShotListAdapter(List<Shot> shots) {
        this.data = shots;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SHOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_list_item, parent, false);
            return new ShotViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_list_loading, parent, false);
            return new RecyclerView.ViewHolder(view) {};
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_SHOT) {
            Shot shot = data.get(position);
            ShotViewHolder shotViewHolder = (ShotViewHolder) holder;
            shotViewHolder.likeCount.setText(String.valueOf(shot.likes_count));
            shotViewHolder.bucketCount.setText(String.valueOf(shot.buckets_count));
            shotViewHolder.viewCount.setText(String.valueOf(shot.views_count));
            shotViewHolder.image.setImageResource(R.drawable.shot_placeholder);

            shotViewHolder.cover.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ShotActivity.class);
                Gson gson = new Gson();
                intent.putExtra(ShotFragment.KEY_SHOT, gson.toJson(shot, new TypeToken<Shot>(){}.getType()));
                intent.putExtra(ShotActivity.KEY_SHOT_TITLE, shot.title);
                context.startActivity(intent);
            });
        } else if (viewType == VIEW_TYPE_LOADING) {
            // do nothing now
        }

    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position < data.size() ? VIEW_TYPE_SHOT : VIEW_TYPE_LOADING;
    }
}
