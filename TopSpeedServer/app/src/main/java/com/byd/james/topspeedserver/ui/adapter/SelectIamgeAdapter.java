package com.byd.james.topspeedserver.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.model.bean.ImageBean;
import com.byd.james.topspeedserver.utils.ImageLoader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 2016/12/26.
 */

public class SelectIamgeAdapter extends RecyclerView.Adapter<SelectIamgeAdapter.MyViewHolder> {

    private List<ImageBean> imageList;
    private Context context;
    ArrayList<String> addressList;
    public interface OnItemClickListener{
        void onItemClick(ArrayList<String> list,String filePath);
    }
    private OnItemClickListener mOnItemClickListener;
    //设置单击事件
    public void setOnItemClickListener(OnItemClickListener itemClickListener)
    {
        this.mOnItemClickListener=itemClickListener;
    }
    public SelectIamgeAdapter(List<ImageBean> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.select_image_recylerview_item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ImageBean imageBean = imageList.get(position);

        if(imageBean!=null)
        {
            ImageLoader.loadImage(context,imageBean.getFirstImagePath(),holder.imageView);
            //Log.e("AAAAAA",imageBean.getFirstImagePath());
            holder.fileName.setText(imageBean.getFileName());
            holder.imageCount.setText(imageBean.getCount()+"");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     File file=new File(imageBean.getFileDir());
                    //遍历文件夹，获取装有所有图片的地址的集合

                     String[] array = file.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpeg") || filename.endsWith(".jpg")
                                    || filename.endsWith(".png")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
                    addressList=new ArrayList<>();
                    for (int i = 0; i < array.length; i++) {
                       addressList.add(array[i]);
                    }
                    //接口回调，将数据传到activity
                    mOnItemClickListener.onItemClick(addressList,file.getAbsolutePath());
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return imageList!=null?imageList.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView fileName;
        TextView imageCount;
        public MyViewHolder(View itemView) {
            super(itemView);
        imageView= (ImageView) itemView.findViewById(R.id.select_image_recyclerView_item_iv);
        fileName = (TextView) itemView.findViewById(R.id.select_image_item_tv_fileName);
        imageCount = (TextView) itemView.findViewById(R.id.select_image_item_tv_count);
        }
    }
}
