package jmyu.ufl.edu.mydribbbo.view.shot_detail;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import jmyu.ufl.edu.mydribbbo.R;
import jmyu.ufl.edu.mydribbbo.view.base.BaseViewHolder;

/**
 * Created by jmyu on 6/30/18.
 */

class ShotInfoViewHolder extends BaseViewHolder {

    @BindView(R.id.shot_title) TextView title;
    @BindView(R.id.shot_description) TextView description;
    @BindView(R.id.shot_author_picture) ImageView authorPicture;
    @BindView(R.id.shot_author_name) TextView authorName;
    @BindView(R.id.shot_like_count) TextView likeCount;
    @BindView(R.id.shot_view_count) TextView viewCount;
    @BindView(R.id.shot_bucket_count) TextView bucketCount;
    @BindView(R.id.shot_action_like) ImageButton likeButton;
    @BindView(R.id.shot_action_bucket) ImageButton bucketButton;
    @BindView(R.id.shot_action_share) TextView shareButton;

    public ShotInfoViewHolder(View view) {
        super(view);
    }
}
