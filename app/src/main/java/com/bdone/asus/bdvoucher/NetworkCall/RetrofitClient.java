package com.bdone.asus.bdvoucher.NetworkCall;

/**
 * Created by ASUS on 3/24/2017.
 */

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static Retrofit sRetrofit;

    private RetrofitClient() {
    }

    public synchronized static Retrofit getInstance(final Context context) {
        if (sRetrofit == null) {
            createRetrofit(context);
        }
        return sRetrofit;
    }


    private static void createRetrofit(final Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .build();
        sRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.bdvoucher.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}