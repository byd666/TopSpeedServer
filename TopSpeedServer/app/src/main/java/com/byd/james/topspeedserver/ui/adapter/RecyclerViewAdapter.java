package com.byd.james.topspeedserver.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.model.bean.VideoInfo;
import com.byd.james.topspeedserver.model.bean.VideoType;
import com.byd.james.topspeedserver.utils.JumpUtils;
import com.byd.james.topspeedserver.utils.StringUtils;
import com.byd.james.topspeedserver.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 2017/1/5.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {


    /*头布局和尾布局*/
    private View mHeaderView;
    private View mFooterView;

    private static final int ITEM_TYPE_HEADER=0x1112;
    private static final int ITEM_TYPE_FOOTER=0x1113;

    private Context context;
    private List<VideoType> videoList;
    private List<VideoInfo> videoInfoList;

    private RecyclerViewItemAdapter mAdapter;

    public RecyclerViewAdapter(Context context, List<VideoType> videoList) {
        this.context = context;
        this.videoList = videoList;
        videoInfoList=new ArrayList<>();

    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rercyclervewadapter_item_layout, parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        videoInfoList.clear();
        if(videoList!=null)
        {
            final VideoType videoType = videoList.get(position);
            String showType = videoType.showType;
            final String moreURL = videoType.moreURL;
            final String title = videoType.title;
            if(title!=null && !title.equals(""))
            {
                holder.tvType.setText(title);
            }
            if(showType.equals("adv"))
            {
                holder.llType.setVisibility(View.GONE);
            }else if(showType.equals("IN")){
                holder.llType.setVisibility(View.VISIBLE);
                if(moreURL.equals(""))
                {
                    holder.ivNext.setVisibility(View.GONE);
                    holder.tvMore.setVisibility(View.GONE);
                }else{
                    //点击加载更多
                    holder.ivNext.setVisibility(View.VISIBLE);
                    holder.tvMore.setVisibility(View.VISIBLE);

                    //item的点击事件
                    holder.llType.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转到更多的activity
                        JumpUtils.jump2VideoListActivity(context, StringUtils.getCatalogId(moreURL),title);
                        }
                    });
                }
            }
            List<VideoInfo> childList = videoType.childList;
            videoInfoList.addAll(childList);
            //给子RecyclerView添加数据
            setItemRecyclerView(holder,showType);
        }
    }

    private void setItemRecyclerView(RecyclerViewHolder holder, String showType) {
        holder.recyclerView.addItemDecoration(new DividerGridItemDecoration(context));
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        mAdapter=new RecyclerViewItemAdapter(context,showType,videoInfoList);
        holder.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return videoList != null ? videoList.size() : 0;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_more)
        TextView tvMore;
        @BindView(R.id.iv_next)
        ImageView ivNext;
        @BindView(R.id.ll_type)
        LinearLayout llType;
        @BindView(R.id.recyclerViewAdapter_item_recyclerView)
        RecyclerView recyclerView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
