package com.example.gifsearcher.models;

import com.example.gifsearcher.viewmodels.GiphyGif;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrei on 21.06.2018.
 */

public class GiphyObject {
    @SerializedName("fixed_width_small")
    public GiphyGif fixedWidthGif;

    @SerializedName("fixed_height_small")
    public GiphyGif fixedHeightGif;
}
