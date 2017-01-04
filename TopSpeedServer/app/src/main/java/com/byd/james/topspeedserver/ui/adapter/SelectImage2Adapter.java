package com.byd.james.topspeedserver.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 2016/12/26.
 */

public class SelectImage2Adapter extends RecyclerView.Adapter<SelectImage2Adapter.SelectIamge2ViewHolder>{
    protected List<String> strings;
    private Context context;
    //文件夹的路径
    private String mFilePath;
    //图片的完整路径
    private ArrayList<String> mImagePath;

    public interface OnImageClickListener{
        void onImageItemClickListener(ArrayList<String> mImagePath);
    }
    private OnImageClickListener mImageClickListener;

    public void setOnImageClickListener(OnImageClickListener imageClickListener)
    {
        this.mImageClickListener=imageClickListener;
    }

    public SelectImage2Adapter(List<String> strings, Context context,String filePath) {
        this.strings = strings;
        this.context = context;
        mFilePath=filePath;
        mImagePath=new ArrayList<>();
    }

    @Override
    public SelectIamge2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.select_image_2,parent,false);
        SelectIamge2ViewHolder holder=new SelectIamge2ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final SelectIamge2ViewHolder holder, int position) {
        final String url = mFilePath+"/"+strings.get(position);
        if(url!=null && !url.equals(""))
        {
            ImageLoader.loadImage(context,url,holder.imageView);
        }
        //将装有选择过的图片的地址通过接口回调返回
        if(mImageClickListener!=null)
        {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item的单击事件
                //已经选择过该图片
                if(mImagePath.contains(url))
                {
                    mImagePath.remove(url);
                    holder.button.setImageResource(R.mipmap.uncheck);
                    holder.imageView.setColorFilter(null);
                }else{
                    //未选择
                    mImagePath.add(url);
                    holder.button.setImageResource(R.mipmap.check);
                    holder.imageView.setColorFilter(Color.parseColor("#77000000"));
                }
                mImageClickListener.onImageItemClickListener(mImagePath);
                Log.d(mImagePath.toString(), "onImageItemClickListener: ");
            }
        });
        /**
         * 已经选择过的图片，显示出选择过的效果
         */
        if (mImagePath.contains(url))
        {
            holder.button.setImageResource(R.mipmap.check);
            holder.imageView.setColorFilter(Color.parseColor("#77000000"));
        }
            Log.d(mImagePath.toString(), "onImageItemClickListener: ");
            mImageClickListener.onImageItemClickListener(mImagePath);
        }
    }

    @Override
    public int getItemCount() {
        return strings!=null?strings.size():0;
    }

    class SelectIamge2ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView  button;
        public SelectIamge2ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.select_image_iv_show);
            button= (ImageView) itemView.findViewById(R.id.select_image_rb_checked);
        }
    }
}
