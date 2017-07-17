package com.example.dell.cheddar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.dell.cheddar.adapter.AccountAdapter;
import com.example.dell.cheddar.model.AccountInterface;
import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.Bank;
import com.example.dell.cheddar.model.Card;
import com.example.dell.cheddar.model.SectionTitle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProfile extends AppCompatActivity {

    private ApiInterface apiInterface;
    private AccountAdapter accountAdapter;
    private RecyclerView accountsRecyclerView;
    private LinearLayoutManager accountsLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        accountsRecyclerView = (RecyclerView)findViewById(R.id.accounts_recycle_view);
        accountsLinearLayoutManager = new LinearLayoutManager(this);
        accountsRecyclerView.setLayoutManager(accountsLinearLayoutManager);
        accountsRecyclerView.setHasFixedSize(true);

        accountAdapter = new AccountAdapter(new ArrayList<AccountInterface>());
        accountsRecyclerView.setAdapter(accountAdapter);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ArrayList<Card>> cardCall = apiInterface.getCards();
        Call<ArrayList<Bank>> bankCall = apiInterface.getBankAccounts();

        cardCall.enqueue(new Callback<ArrayList<Card>>(){

            @Override
            public void onResponse(Call<ArrayList<Card>> cardCall, Response<ArrayList<Card>> response) {
                ArrayList<Card> cards = response.body();

                ArrayList<AccountInterface> accounts = new ArrayList<AccountInterface>();
                accounts.add(new SectionTitle("Bank Accounts"));
                accounts.addAll(cards);

                accountAdapter.addItems(accounts);
                accountAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Card>> cardCall, Throwable t) {
                Log.d("Crashed", "here");

            }
        });


        bankCall.enqueue(new Callback<ArrayList<Bank>>() {
            @Override
            public void onResponse(Call<ArrayList<Bank>> accountCall, Response<ArrayList<Bank>> response) {
                ArrayList<Bank> banks = response.body();

                ArrayList<AccountInterface> accounts = new ArrayList<AccountInterface>();
                accounts.add(new SectionTitle("Card Accounts"));
                accounts.addAll(banks);
                accountAdapter.addItems(accounts);

            }

            @Override
            public void onFailure(Call<ArrayList<Bank>> accountCall, Throwable t) {

            }
        });
    }

}
