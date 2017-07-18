package com.example.dell.cheddar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.dell.cheddar.adapter.AccountAdapter;
import com.example.dell.cheddar.adapter.CountrySpinnerAdapter;
import com.example.dell.cheddar.model.AccountInterface;
import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.Bank;
import com.example.dell.cheddar.model.Card;
import com.example.dell.cheddar.model.CountryData;
import com.example.dell.cheddar.model.SectionTitle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMoneyActivity extends AppCompatActivity  {


    private EditText accountNumber;
    private Button next;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ArrayList<Card>> cardCall = apiInterface.getCards();
        Call<ArrayList<Bank>> bankCall = apiInterface.getBankAccounts();



        cardCall.enqueue(new Callback<ArrayList<Card>>(){

            @Override
            public void onResponse(Call<ArrayList<Card>> cardCall, Response<ArrayList<Card>> response) {
                ArrayList<Card> cards = response.body();

                ArrayList<AccountInterface> accounts = new ArrayList<AccountInterface>();
                accounts.addAll(cards);

                for (int i = 0; i < accounts.size(); i++)
                {
                    RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_group_accounts);
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
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convert = new Intent(getApplicationContext(), ConvertActivity.class);
                startActivity(convert);
            }
        });

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
