package com.example.dell.cheddar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dell.cheddar.model.AccountInterface;
import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.Bank;
import com.example.dell.cheddar.model.Card;
import com.example.dell.cheddar.model.Recipient;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySendFromRecipient extends AppCompatActivity {
    Button next;
    String firstName;
    String lastName;
    String bank;
    String account;
    String countryName;
    private ApiInterface apiInterface;
    private RadioButton btnbank;
    private RadioButton btncard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_from_recipient);

        final RadioGroup bankRadioGroup = (RadioGroup) findViewById(R.id.card_group_accounts);
        final RadioGroup cardRadioGroup = (RadioGroup) findViewById(R.id.bank_group_accounts);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        final Call<ArrayList<Card>> cardCall = apiInterface.getCards();
        Call<ArrayList<Bank>> bankCall = apiInterface.getBankAccounts();

        btnbank = (RadioButton) findViewById(R.id.button_bank);
        btncard = (RadioButton) findViewById(R.id.button_card);

        btnbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankRadioGroup.setVisibility(View.GONE);
                cardRadioGroup.setVisibility(View.VISIBLE);
            }
        });

        btncard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankRadioGroup.setVisibility(View.VISIBLE);
                cardRadioGroup.setVisibility(View.GONE);
            }
        });

        SegmentedGroup segmented2 = (SegmentedGroup)findViewById(R.id.segmented_button);
        segmented2.setTintColor(Color.DKGRAY);

        cardCall.enqueue(new Callback<ArrayList<Card>>(){

            @Override
            public void onResponse(Call<ArrayList<Card>> cardCall, Response<ArrayList<Card>> response) {
                ArrayList<Card> cards = response.body();
                ArrayList<AccountInterface> accounts = new ArrayList<AccountInterface>();
                accounts.addAll(cards);

                LayoutInflater li = getLayoutInflater();

                RadioGroup rgp = (RadioGroup) findViewById(R.id.card_group_accounts);
                for (int i = 0; i < cards.size(); i++) {
                    Card card = cards.get(i);

                    RadioButton customButton = (RadioButton) li.inflate(R.layout.blank_button, null);

                    customButton.setText(card.toString());
                    customButton.setTag(i);

                    rgp.addView(customButton);
                }
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
                accounts.addAll(banks);

                LayoutInflater li = getLayoutInflater();
                RadioGroup rgp = (RadioGroup) findViewById(R.id.bank_group_accounts);

                for (int i = 0; i < banks.size(); i++) {
                    Bank bank = banks.get(i);

                    RadioButton customButton = (RadioButton) li.inflate(R.layout.blank_button, null);

                    customButton.setText(bank.toString());
                    customButton.setTag(i);

                    rgp.addView(customButton);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Bank>> accountCall, Throwable t) {

            }
        });

        TextView name = (TextView)findViewById(R.id.name);
        TextView bankName = (TextView)findViewById(R.id.bank_name);
        TextView accountNumber = (TextView)findViewById(R.id.bank_account);
        TextView country = (TextView)findViewById(R.id.country);


        Bundle extras = getIntent().getExtras();

        Recipient recipient = (Recipient) extras.getSerializable("recipient");

        firstName = recipient.getFirstName();
        lastName = recipient.getLastName();
        bank = recipient.getBank();
        account = recipient.getAccountNumber();
        countryName = recipient.getCountry();

        name.setText(firstName+" "+lastName);
        bankName.setText(bank);
        accountNumber.setText(account);
        country.setText(countryName);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convert = new Intent(getApplicationContext(), ConvertActivity.class);
                startActivity(convert);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
