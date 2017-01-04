package com.byd.james.topspeedserver.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by james on 2016/12/27.
 */

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.ShowImageViewHolder>{

    private ArrayList<String> pathList;
    private Context context;
    private OnImageItemLongClickListener mImageItemLongClickListener;

    public interface OnImageItemLongClickListener{
        void itemLongClickListener(String url,int pos);
    }
    public void setOnImageItemLongClickListener(OnImageItemLongClickListener clickListener)
    {
        this.mImageItemLongClickListener=clickListener;
    }

    public void deleteData(int position)
    {
        pathList.remove(position);
        notifyItemRemoved(position);
    }
    //删除数据的方法

    public ShowImageAdapter(Context context,ArrayList<String> pathList) {
        this.context=context;
        this.pathList=pathList;
    }

    @Override
    public ShowImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.share_mood_show_image_litem_ayout, parent, false);
        ShowImageViewHolder myHolder=new ShowImageViewHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final ShowImageViewHolder holder, final int position) {

        String s = pathList.get(position);
        Log.e("BBBB", " ShowImageAdapter onBindViewHolder: "+s );
        if(s!=null && s.length()>0)
        {
            ImageLoader.loadImage(context,s,holder.image);
        }
       /* //单击弹出Dialog，淡化主activity的颜色，原图放大展示图片
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
       /* holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按删除
                int visibility = holder.unselected.getVisibility();
                if(visibility==View.GONE)
                {
                    holder.unselected.setVisibility(View.VISIBLE);
                }
                if(visibility==View.VISIBLE)
                {
                   deleteData(position);
                }
                return false;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return pathList!=null?pathList.size():0;
    }

    class ShowImageViewHolder extends RecyclerView.ViewHolder{
        ImageView image,unselected;

        public ShowImageViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.share_mood_image_show);
            unselected= (ImageView) itemView.findViewById(R.id.unselected);
        }
    }
}
