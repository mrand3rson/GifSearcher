package com.example.gifsearcher.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.gifsearcher.R;
import com.example.gifsearcher.databinding.FragmentResultsBinding;
import com.example.gifsearcher.databinding.GiphySearchListItemBinding;
import com.example.gifsearcher.models.GiphyApiResponse;
import com.example.gifsearcher.models.GiphyData;
import com.example.gifsearcher.models.SimpleCallback;
import com.example.gifsearcher.models.api.GiphyApi;
import com.example.gifsearcher.views.adapters.GifSearchListAdapter;
import com.example.gifsearcher.views.adapters.GifTrendingListAdapter;

import java.util.ArrayList;


public class ResultsFragment extends Fragment {

    private final static String ARG_QUERY = "query";
    private static final String ARG_HEIGHT = "height";
    private RecyclerView mRecycler;
    private View parent;
    private FragmentResultsBinding mBinding;

    public ResultsFragment() {

    }

    public static ResultsFragment newInstance(String query, int height) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        args.putInt(ARG_HEIGHT, height);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentResultsBinding.inflate(inflater, container, false);

        parent = mBinding.getRoot();
        mRecycler = parent.findViewById(R.id.recycler);
        return parent;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            resizeParent(getArguments().getInt(ARG_HEIGHT));
            String query = getArguments().getString(ARG_QUERY);
            showResults(query);
        }
    }

    private void resizeParent(int height) {
        parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height));
    }

    public void showResults(String query) {
        mBinding.setQuery(query);
        GiphyApi.search(query, new SimpleCallback<GiphyApiResponse>() {
            @Override
            public void process(GiphyApiResponse response) {
                initRecycler(response.data);
            }
        });
    }

    private void initRecycler(ArrayList<GiphyData> data) {
        GifSearchListAdapter mAdapter = new GifSearchListAdapter(getActivity(), data);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mAdapter);
    }
}
