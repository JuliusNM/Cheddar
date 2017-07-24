package com.example.dell.cheddar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.dell.cheddar.adapter.AccountAdapter;
import com.example.dell.cheddar.model.AccountInterface;
import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.Bank;
import com.example.dell.cheddar.model.Card;
import com.example.dell.cheddar.model.SectionTitle;
import com.example.dell.cheddar.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ActivityProfile extends AppCompatActivity implements ResetPasswordDialog.Listener {

    public static final String TAG = ActivityProfile.class.getSimpleName();

    private Button mBtChangePassword;
    private Button mBtLogout;

    private ProgressBar mProgressbar;

    private ApiInterface apiInterface;
    private AccountAdapter accountAdapter;
    private RecyclerView accountsRecyclerView;
    private LinearLayoutManager accountsLinearLayoutManager;

    private SharedPreferences mSharedPreferences;
    private String mToken;
    private String mEmail;


    private TextView email;
    private TextView name;

    private CompositeSubscription mSubscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        accountsRecyclerView = (RecyclerView) findViewById(R.id.accounts_recycle_view);
        accountsLinearLayoutManager = new LinearLayoutManager(this);
        accountsRecyclerView.setLayoutManager(accountsLinearLayoutManager);
        accountsRecyclerView.setHasFixedSize(true);

        accountAdapter = new AccountAdapter(new ArrayList<AccountInterface>());
        accountsRecyclerView.setAdapter(accountAdapter);

        mSubscriptions = new CompositeSubscription();
        initViews();
        initSharedPreferences();
        loadProfile();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ArrayList<Card>> cardCall = apiInterface.getCards();
        Call<ArrayList<Bank>> bankCall = apiInterface.getBankAccounts();

        cardCall.enqueue(new Callback<ArrayList<Card>>(){

            @Override
            public void onResponse(Call<ArrayList<Card>> cardCall, Response<ArrayList<Card>> response) {
                ArrayList<Card> cards = response.body();

                ArrayList<AccountInterface> accounts = new ArrayList<AccountInterface>();
                accounts.add(new SectionTitle("Card Accounts"));
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
                accounts.add(new SectionTitle("Bank Accounts"));
                accounts.addAll(banks);
                accountAdapter.addItems(accounts);

            }

            @Override
            public void onFailure(Call<ArrayList<Bank>> accountCall, Throwable t) {

            }
        });

    }

    private void initViews() {
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        mBtChangePassword = (Button) findViewById(R.id.btn_change_password);
        mBtLogout = (Button) findViewById(R.id.btn_logout);

    }

    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");
    }

    private void logout() {

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constants.EMAIL,"");
        editor.putString(Constants.TOKEN,"");
        editor.apply();
        finish();
    }

    private void showDialog(){

        ChangePasswordDialog fragment = new ChangePasswordDialog();

        Bundle bundle = new Bundle();
        bundle.putString(Constants.EMAIL, mEmail);
        bundle.putString(Constants.TOKEN,mToken);
        fragment.setArguments(bundle);

        fragment.show(getFragmentManager(), ChangePasswordDialog.TAG);
    }


    private void loadProfile() {
        mSubscriptions.add(NetworkUtil.getRetrofit(mToken).getProfile(mEmail)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleError(Throwable error) {


        mProgressbar.setVisibility(View.GONE);

        if (error instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {

                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody,Response.class);
                showSnackBarMessage(response.message());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            showSnackBarMessage("Network Error !");
        }
    }

    private void showSnackBarMessage(String message) {
        Snackbar.make(findViewById(R.id.activity_profile),message,Snackbar.LENGTH_SHORT).show();
    }

    private void handleResponse(User user) {
        mProgressbar.setVisibility(View.GONE);
        name.setText(user.getFirstName());
        email.setText(user.getEmailAddress());
        
    }

    // Options Menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public void onPasswordReset( String message) {

        showSnackBarMessage("Password Changed Successfully !");
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                Intent settings = new Intent(this, ActivityProfile.class);
                startActivity(settings);

        }
        return true;
    }

}
