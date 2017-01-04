package com.byd.james.topspeedserver.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.ui.activitys.MainActivity;
import com.byd.james.topspeedserver.ui.activitys.SplashActivity;
import com.byd.james.topspeedserver.utils.ImageLoader;
import com.byd.james.topspeedserver.utils.JumpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdFragment extends Fragment {

    @BindView(R.id.ad_fragment_imageview)
    ImageView imageview;
    @BindView(R.id.ad_fragment_textview)
    TextView inPut;
    public AdFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, view);
        int id = getArguments().getInt("id");
        setImage(id);
        return view;
    }

    private void setImage(int id) {
        switch (id){
            case 0:
                ImageLoader.loadImage(getContext(),"file:///android_asset/ad1.jpg",imageview);
                inPut.setVisibility(View.GONE);
                break;
            case 1:
                ImageLoader.loadImage(getContext(),"file:///android_asset/ad2.jpg",imageview);
                inPut.setVisibility(View.GONE);
                break;
            case 2:
                ImageLoader.loadImage(getContext(),"file:///android_asset/ad3.jpg",imageview);
                inPut.setVisibility(View.GONE);
                break;
            case 3:
                ImageLoader.loadImage(getContext(),"file:///android_asset/ad4.jpg",imageview);
                inPut.setVisibility(View.VISIBLE);
                inPut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpUtils.jump(getContext(), MainActivity.class);
                        ((SplashActivity)getContext()).finish();
                    }
                });
                break;
        }
    }
}
