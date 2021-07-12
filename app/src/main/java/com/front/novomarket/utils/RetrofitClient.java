package com.front.novomarket.utils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.0.15:5050/api/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    //client petición
    private RetrofitClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //servidor tomcat
    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public API getAPI () {
        return retrofit.create(API.class);
    }
}
