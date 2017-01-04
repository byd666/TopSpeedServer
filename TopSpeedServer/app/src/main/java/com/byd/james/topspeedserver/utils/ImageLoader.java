package com.byd.james.topspeedserver.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by james on 2016/12/21.
 */

public class ImageLoader {
    public  static void loadImage(Context context, String url, ImageView imageView)
    {
        if(url!=null && imageView!=null) {
            Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        }
    }
}
