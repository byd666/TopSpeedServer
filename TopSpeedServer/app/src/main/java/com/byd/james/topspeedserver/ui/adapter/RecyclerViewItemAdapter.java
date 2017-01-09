package com.byd.james.topspeedserver.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.model.bean.VideoInfo;
import com.byd.james.topspeedserver.utils.ImageLoader;
import com.byd.james.topspeedserver.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.byd.james.topspeedserver.R.id.recyclerView_item_adv;

/**
 * Created by james on 2017/1/6.
 */

public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //标识多条不同布局的itemType,普通，广告
    private static final int ITEM_TYPE_IN = 0x1114;
    private static final int ITEM_TYPE_ADV = 0x1115;


    private Context context;
    private List<VideoInfo> videoInfoList;
    private static String showType;

    private int width;
    public RecyclerViewItemAdapter(Context context, String showType, List<VideoInfo> videoInfoList) {
        this.context = context;
        this.videoInfoList = videoInfoList;
        this.showType = showType;
        width= ScreenUtil.getScreenWidth(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ADV) {
            //动态设置ImageView的宽高，根据自己每行item数量计算
            //dm.widthPixels-dip2px(20)即屏幕宽度-左右10dp+10dp=20dp再转换为px的宽度，最后/3得到每个item的宽高
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((width - ScreenUtil.dip2px(context,10)), width*1/3);
            View inflate = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_adv, parent, false);
            inflate.setLayoutParams(lp);
            return new AdViewHolder(inflate);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_in, parent, false);
        return new INViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoInfo videoInfo = videoInfoList.get(position);
        if(videoInfo!=null)
        {
            String pic = videoInfo.pic;
            String title=videoInfo.title;
            if(holder instanceof AdViewHolder && pic!=null && !pic.equals(""))
            {
                //给广告布局添加数据
                RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(width,width*1/3);
                ((AdViewHolder) holder).recyclerViewItemAdvPic.setLayoutParams(params);
                ((AdViewHolder) holder).recyclerViewItemAdvPic.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.loadImage(context,pic,((AdViewHolder) holder).recyclerViewItemAdvPic);
            }
            if (holder instanceof INViewHolder && !pic.equals("") && title!=null)
            {
                //普通的布局添加数据
                RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(width/2,width*1/3);
                ((INViewHolder) holder).recyclerViewItemInPic.setLayoutParams(params);
                ((INViewHolder) holder).recyclerViewItemInPic.setScaleType(ImageView.ScaleType.FIT_XY);
                ((INViewHolder) holder).recyclerViewItemInTitle.setText(title);
                ImageLoader.loadImage(context,pic,((INViewHolder) holder).recyclerViewItemInPic);
            }
        }
    }

    @Override//根据不同的布局返回不同的标识
    public int getItemViewType(int position) {
        if (showType != null && showType.equals("adv"))
            return ITEM_TYPE_ADV;
        else
            return ITEM_TYPE_IN;
    }

    @Override
    public int getItemCount() {
        return videoInfoList != null ? videoInfoList.size() : 0;
    }

    //单条广告的布局
    class AdViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView_item_adv_pic)
        ImageView recyclerViewItemAdvPic;
        @BindView(recyclerView_item_adv)
        RelativeLayout recyclerViewItemInfo;

        public AdViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //正常的单条布局
    class INViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView_item_in_pic)
        ImageView recyclerViewItemInPic;
        @BindView(R.id.recyclerView_item_in_title)
        TextView recyclerViewItemInTitle;
        @BindView(R.id.recyclerView_item_in)
        RelativeLayout recyclerViewItemInfo;
        public INViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
