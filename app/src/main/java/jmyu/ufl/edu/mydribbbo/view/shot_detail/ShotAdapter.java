package jmyu.ufl.edu.mydribbbo.view.shot_detail;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import jmyu.ufl.edu.mydribbbo.model.Shot;

/**
 * Created by jmyu on 6/29/18.
 */

class ShotAdapter extends RecyclerView.Adapter {


    Shot shot;

    private static final int VIEW_SHOT_TYPE_IMAGE = 1;
    private static final int VIEW_SHOT_TYPE_INFO = 2;

    public ShotAdapter(Shot shot) {
        this.shot = shot;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // todo
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // todo

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // todo
        return super.getItemViewType(position);
    }
}
