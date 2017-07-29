package com.example.dell.cheddar.model;

import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 7/12/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "https://glacial-fortress-32716.herokuapp.com/api/v1/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;

    }
}
