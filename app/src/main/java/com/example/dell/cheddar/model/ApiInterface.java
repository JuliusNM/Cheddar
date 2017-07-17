package com.example.dell.cheddar.model;

import android.accounts.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Dell on 7/12/2017.
 */

public interface ApiInterface {

    @GET("recipients")
    Call<ArrayList<Recipient>> getRecipients();

    @POST("recipients")
    Call<ResponseData> postRecipient(@Body Recipient body);

    @GET("cards")
    Call<ArrayList<Card>> getCards();

    @GET("accounts")
    Call<ArrayList<Bank>> getBankAccounts();


}
