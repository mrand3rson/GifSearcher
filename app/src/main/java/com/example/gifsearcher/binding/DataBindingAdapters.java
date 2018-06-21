package com.example.gifsearcher.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.gifsearcher.utils.GlideImageLoader;
import com.felipecsl.gifimageview.library.GifImageView;

import java.io.InputStream;

/**
 * Created by Andrei on 21.06.2018.
 */

public class DataBindingAdapters {

    @BindingAdapter("imageResource")
    public static void setImageFromUrl(GifImageView imageView, String url){
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(android.R.drawable.ic_menu_camera);
//        new GlideImageLoader(imageView, ) need Progress Bar
//                .load(url, options);
        Glide.with(imageView.getContext())
                .asGif()
                .load(url)
                .apply(options)
                .into(imageView);
    }
}