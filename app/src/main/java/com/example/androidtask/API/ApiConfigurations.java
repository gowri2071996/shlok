package com.example.androidtask.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfigurations {

   public static String BaseUrl="http://www.json-generator.com/api/json/";
    private static ApiConfigurations ourInstance = new ApiConfigurations();
    private Retrofit mRetrofit;

    private ApiConfigurations() {
    }
    public static ApiConfigurations getInstance() {
        if (ourInstance == null) {
            synchronized (ApiConfigurations.class) {
                if (ourInstance == null)
                    ourInstance = new ApiConfigurations();
            }
        }
        ourInstance.config();
        return ourInstance;
    }

    private void config() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
//              .addInterceptor(new ConnectivityInterceptor(MyApplication.context))
                 .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("accept-language","").build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public Retrofit getApiBuilder() {
        return mRetrofit;
    }
}
