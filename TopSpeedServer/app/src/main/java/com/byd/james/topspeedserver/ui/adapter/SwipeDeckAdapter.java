package com.byd.james.topspeedserver.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.model.bean.VideoType;
import com.byd.james.topspeedserver.utils.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 2017/1/3.
 */

public class SwipeDeckAdapter extends BaseAdapter {
    private Context context;
    private List<VideoType> videoResList;

    public SwipeDeckAdapter(Context context, List<VideoType> videoResList) {
        this.context = context;
        this.videoResList = videoResList;
    }

    public void reAddData(List<VideoType> list) {
        //加载下一批
        this.videoResList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return videoResList != null ? videoResList.size() : 0;
    }

    @Override
    public VideoType getItem(int position) {
        return videoResList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        VideoType videoType = videoResList.get(position);
        if(videoType!=null)
        {
            holder.tvIntroduction.setText(videoType.description);
            holder.tvTitle.setText(videoType.title);
            String pic = videoType.pic;
            if(pic!=null)
            {
                ImageLoader.loadImage(context,pic,holder.offerImage);
            }
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击进入详情页
                Toast.makeText(context, "点击进入详情页", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.offer_image)
        RoundedImageView offerImage;
        @BindView(R.id.tv_introduction)
        TextView tvIntroduction;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
