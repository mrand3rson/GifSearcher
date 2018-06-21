package com.example.gifsearcher.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.gifsearcher.databinding.GiphyTrendingListItemBinding;
import com.example.gifsearcher.models.GiphyData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GifTrendingListAdapter extends RecyclerView.Adapter<GifTrendingListAdapter.ViewHolder> {

    private final Context mContext;
    private ArrayList<GiphyData> mData;

    public GifTrendingListAdapter(Context context, ArrayList<GiphyData> data) {
        mContext = context;
        mData = data;

        Collections.sort(mData, new Comparator<GiphyData>() {
            @Override
            public int compare(GiphyData o1, GiphyData o2) {
                int h1 = Integer.valueOf(o1.getImages().fixedWidthGif.getHeight());
                int h2 = Integer.valueOf(o2.getImages().fixedWidthGif.getHeight());
                return Integer.compare(h1, h2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        GiphyTrendingListItemBinding itemBinding = GiphyTrendingListItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GiphyData gif = mData.get(position);
        holder.bind(gif);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private GiphyTrendingListItemBinding binding;

        ViewHolder(GiphyTrendingListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(GiphyData item) {
            this.setIsRecyclable(false);
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}