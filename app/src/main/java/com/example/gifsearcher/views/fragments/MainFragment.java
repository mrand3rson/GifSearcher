package com.example.gifsearcher.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gifsearcher.R;
import com.example.gifsearcher.models.GiphyApiResponse;
import com.example.gifsearcher.models.GiphyData;
import com.example.gifsearcher.models.SimpleCallback;
import com.example.gifsearcher.models.api.GiphyApi;
import com.example.gifsearcher.views.adapters.GifTrendingListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public RecyclerView getRecycler() {
        return mRecycler;
    }

    private RecyclerView mRecycler;


    public MainFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mRecycler = (RecyclerView) v.findViewById(R.id.recycler);

        GiphyApi.trending(new SimpleCallback<GiphyApiResponse>() {
            @Override
            public void process(GiphyApiResponse response) {
                initRecycler(response.data);
            }
        });
        return v;
    }

    private void initRecycler(ArrayList<GiphyData> data) {
        GifTrendingListAdapter mAdapter = new GifTrendingListAdapter(getActivity(), data);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.generateLayoutParams(
                new GridLayoutManager.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mAdapter);
    }
}
