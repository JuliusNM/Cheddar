package com.example.dell.cheddar.model;

import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

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


    @POST("users")
    Observable<Response> register(@Body User user);

    @POST("authenticate")
    Observable<Response> login();

    @GET("users/{emailAddress}")
    Observable<User> getProfile(@Path("emailAddress") String emailAddress);

    @PUT("users/{emailAddress")
    Observable<Response> changePassword(@Path("emailAddress") String emailAddress, @Body User user);

    @POST("users/{emailAddress}/password")
    Observable<Response> resetPasswordInit(@Path("emailAddress") String emailAddress);

    @POST("users/{emailAddress}/password")
    Observable<Response> resetPasswordFinish(@Path("emailAddress") String emailAddress, @Body User user);


}
