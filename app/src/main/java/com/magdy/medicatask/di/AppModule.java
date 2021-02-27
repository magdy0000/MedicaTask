package com.magdy.medicatask.di;


import com.magdy.medicatask.network.ApiCalls;
import com.magdy.medicatask.utils.NetworkContract;
import com.magdy.medicatask.data.preference.MyPreference;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {


    @Singleton
    @Provides
    public OkHttpClient getInterceptor (){
     return     new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request newRequest;

                        newRequest = request.newBuilder()
                                .addHeader(NetworkContract.LANGUAGE, "en")
                                .addHeader(NetworkContract.FROM , "c213348c8e34e7dd")
                                .addHeader(NetworkContract.USER_AGENT , "android")
                                .addHeader(NetworkContract.AUTHORIZATION, MyPreference.getUserToken())
                                .build();


                        return chain.proceed(newRequest);
                    }
                })
                .build();

    }
    @Singleton
    @Provides
    public Retrofit getRetrofit(OkHttpClient client){
      return   new Retrofit.Builder()
                .client(client)
                .baseUrl(NetworkContract.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    public ApiCalls getCalls (Retrofit retrofit ){
        return retrofit.create(ApiCalls.class);


    }





}
