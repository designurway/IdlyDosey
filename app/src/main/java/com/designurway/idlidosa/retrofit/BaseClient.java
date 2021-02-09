package com.designurway.idlidosa.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {

    final static String BaseUrl = "http://idlydosey.com/API/";
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
