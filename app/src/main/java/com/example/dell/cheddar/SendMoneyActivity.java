package com.example.dell.cheddar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.dell.cheddar.adapter.CountrySpinnerAdapter;
import com.example.dell.cheddar.model.AccountInterface;
import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.Bank;
import com.example.dell.cheddar.model.Card;
import com.example.dell.cheddar.model.CountryData;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMoneyActivity extends AppCompatActivity  {


    private EditText accountNumber;
    private Button next;
    private ApiInterface apiInterface;
    private RadioButton btnbank;
    private RadioButton btncard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        final RadioGroup bankRadioGroup = (RadioGroup) findViewById(R.id.radio_group_accounts);
        final RadioGroup cardRadioGroup = (RadioGroup) findViewById(R.id.radio_group_accounts_bank);

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

                RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_group_accounts);
                for (int i = 0; i < cards.size(); i++) {
                    Card card = cards.get(i);

                    RadioButton tempButton = (RadioButton) li.inflate(R.layout.blank_button, null);

                    tempButton.setText(card.toString());
                    tempButton.setTag(i);

                    rgp.addView(tempButton);
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



                for (int i = 0; i < accounts.size(); i++)
                {
                    RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_group_accounts_bank);
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    String x = String.valueOf(accounts.get(i));
                    radioButton.setText(x);

                    radioButton.setId(i);
                    RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                    rgp.addView(radioButton, rprms);
                    radioButton.setTextColor(Color.parseColor("#757575"));
                    radioButton.setTypeface(null, Typeface.BOLD);
                }

            }
            @Override
            public void onFailure(Call<ArrayList<Bank>> accountCall, Throwable t) {

            }
        });

        accountNumber = (EditText) findViewById(R.id.account_number);

        ArrayList<CountryData>countries = new ArrayList<>();
        countries.add(new CountryData("Nigeria", R.drawable.nigeria));
        countries.add(new CountryData("Ghana", R.drawable.ghana));

        Spinner sp = (Spinner)findViewById(R.id.countries_spinner);
        CountrySpinnerAdapter adapter=new CountrySpinnerAdapter(this, 2, countries);
        sp.setAdapter(adapter);

        Spinner banksList = (Spinner) findViewById(R.id.banks_spinner);

        ArrayAdapter<CharSequence> bankAdapter = ArrayAdapter.createFromResource(this, R.array.banks_list,
                android.R.layout.simple_spinner_item);

        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        banksList.setAdapter(bankAdapter);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConvertActivity.class);
                startActivity(intent);
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
