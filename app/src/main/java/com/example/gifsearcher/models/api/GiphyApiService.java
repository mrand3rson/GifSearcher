package com.example.gifsearcher.models.api;

import com.example.gifsearcher.models.GiphyApiResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Andrei on 20.06.2018.
 */

public interface GiphyApiService {

    String API_KEY = "I4vV7Fd9YzLQcZWIEmcL1AFMmM2rJecX";
    String BASE_URL = "http://api.giphy.com/";


    @GET("v1/gifs/trending")
    Observable<GiphyApiResponse> getTrending(@Query("api_key") String apiKey, @Query("limit") int limit);

    @GET("v1/gifs/search")
    Observable<GiphyApiResponse> search(@Query("api_key") String apiKey, @Query("q") String query);
}
