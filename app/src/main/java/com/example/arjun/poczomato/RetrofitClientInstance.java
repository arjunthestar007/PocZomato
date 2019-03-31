package com.example.arjun.poczomato;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    //https://developers.zomato.com/api/v2.1/categories
    public static final String BASE_URL = "https://developers.zomato.com/api/v2.1/";
    public static final String auth = "8b4934620d60843ab88a5ed9f6199ab0";

    private static Retrofit retrofit;
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder();


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);


            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    okhttp3.Request request = chain.request();
                    Headers headers = request.headers().newBuilder().add("user-key", auth).build();
                    request = request.newBuilder().headers(headers).build();
                    return chain.proceed(request);
                }
            };
            builder.addInterceptor(interceptor);


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }


}
