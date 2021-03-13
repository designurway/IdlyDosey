package com.designurway.idlidosa.a.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {

    final static String BaseUrl = "https://idlydosey.com/API/";
    public  static Retrofit retrofgitEndPoint = null;


    public static Retrofit getClient(){
        if (retrofgitEndPoint == null){
            retrofgitEndPoint = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofgitEndPoint;
    }
}
