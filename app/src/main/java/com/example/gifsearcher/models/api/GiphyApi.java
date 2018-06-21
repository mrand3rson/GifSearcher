package com.example.gifsearcher.models.api;

import com.example.gifsearcher.models.GiphyApiResponse;
import com.example.gifsearcher.models.SimpleCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andrei on 20.06.2018.
 */

public class GiphyApi {

    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(GiphyApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void trending(final SimpleCallback<GiphyApiResponse> callback) {
        GiphyApiService service = getRetrofit().create(GiphyApiService.class);
        service.getTrending(GiphyApiService.API_KEY, 15)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GiphyApiResponse>() {
                    @Override
                    public void accept(GiphyApiResponse giphyApiResponse) throws Exception {
                        callback.process(giphyApiResponse);
                    }
                });
    }

    public static void search(String query, final SimpleCallback<GiphyApiResponse> callback) {
        GiphyApiService service = getRetrofit().create(GiphyApiService.class);
        service.search(GiphyApiService.API_KEY, query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GiphyApiResponse>() {
                    @Override
                    public void accept(GiphyApiResponse giphyApiResponse) throws Exception {
                        callback.process(giphyApiResponse);
                    }
                });
    }
}
