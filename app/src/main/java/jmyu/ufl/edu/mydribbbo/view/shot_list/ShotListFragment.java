package jmyu.ufl.edu.mydribbbo.view.shot_list;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmyu.ufl.edu.mydribbbo.R;
import jmyu.ufl.edu.mydribbbo.model.Shot;
import jmyu.ufl.edu.mydribbbo.model.User;


/**
 * Created by jmyu on 6/26/18.
 */

public class ShotListFragment extends Fragment {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ShotListAdapter adapter;
    private static final int COUNT_PER_PAGE = 10;

    public static ShotListFragment newInstance() {
        return new ShotListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final Handler handler = new Handler();
        adapter = new ShotListAdapter(fakeData(0), new ShotListAdapter.LoadMoreListener() {
            @Override
            public void loadMore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                List<Shot> moreData =fakeData(adapter.getDataCount() / COUNT_PER_PAGE);
                                adapter.append(moreData);
                                adapter.setShowLoading(moreData.size() == COUNT_PER_PAGE);
                            }
                        });
                    }
                }).start();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private List<Shot> fakeData(int page) {
        List<Shot> shotList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < (page < 2 ? COUNT_PER_PAGE : 5); ++i) {
            Shot shot = new Shot();
            shot.title = "shot" + i;
            shot.views_count = random.nextInt(10000);
            shot.likes_count = random.nextInt(200);
            shot.buckets_count = random.nextInt(50);
            shot.description = shot.title +  " description";

            shot.images = new HashMap<>();
            shot.images.put(Shot.IMAGE_HIDPI, imageUrls[random.nextInt(imageUrls.length)]);

            shot.user = new User();
            shot.user.name = shot.title + " author";

            shotList.add(shot);
        }
        return shotList;
    }

    private static final String[] imageUrls = {
            "http://35.196.58.1/yelp-business-photos/-EgKmv2dcfZPP9sKPrpFRQ.jpg",
            "http://35.196.58.1/yelp-business-photos/kFARd0Ci3ZUlMq3wnOh7pA.jpg",
            "http://35.196.58.1/yelp-business-photos/_ZYTeDR44RCd5TfT-0iBcQ.jpg",
            "http://35.196.58.1/yelp-business-photos/kfARhz31OuZTDPfrCI2eLg.jpg",
            "http://35.196.58.1/yelp-business-photos/Z-Yz_a8fAP3qImq2dKVUqg.jpg",
            "http://35.196.58.1/yelp-business-photos/_kFSMrEdKS6XEVtubBchJg.jpg",
            "http://35.196.58.1/yelp-business-photos/ZZdtWxptjYN1cFxPd6a5bw.jpg",
            "http://35.196.58.1/yelp-business-photos/KFwfNcfVAk25eqIhC6C6Bg.jpg",
            "http://35.196.58.1/yelp-business-photos/Z_zhnfy-mdFYqzlmKI7Wng.jpg",
            "http://35.196.58.1/yelp-business-photos/kH6couimx13HIgYNgq9F2g.jpg",
            "http://35.196.58.1/yelp-business-photos/zZjQ22iQlIAQ7C2xClvaOw.jpg"
    };
}
