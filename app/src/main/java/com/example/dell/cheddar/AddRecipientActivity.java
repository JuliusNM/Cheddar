package com.example.dell.cheddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dell.cheddar.adapter.CountrySpinnerAdapter;
import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.CountryData;
import com.example.dell.cheddar.model.Recipient;
import com.example.dell.cheddar.model.ResponseData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecipientActivity extends AppCompatActivity {
    EditText accountNumber;
    EditText firstName;
    EditText lastName;
    Spinner bank;
    Spinner country;
  //  EditText phoneNumber;
    Button btnsaveRecipient;
    Button btncancel;
    private ApiInterface apiInterface;
    private ResponseData responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipient);

        accountNumber = (EditText)findViewById(R.id.add_recipient_account_number);
        firstName = (EditText)findViewById(R.id.add_recipient_first_name);
        lastName = (EditText)findViewById(R.id.add_recipient_last_name);
        bank = (Spinner)findViewById(R.id.bank_spinner);
        country = (Spinner)findViewById(R.id.country_spinner);
     //   phoneNumber = (EditText)findViewById(R.id.add_recipient_phone_number);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        btnsaveRecipient = (Button)findViewById(R.id.btn_save_recipient);
        btnsaveRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Recipient recipient = new Recipient(

                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        bank.getSelectedItem().toString(),
                        country.getSelectedItem().toString(),
                        accountNumber.getText().toString()
//                      phoneNumber.getText().toString()
                );

                Call<ResponseData> call = apiInterface.postRecipient(recipient);
                call.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        responseData = response.body();

                        Intent intent = new Intent (getApplication(),HomeActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {

                    }
                });
            }
        });

        btncancel = (Button)findViewById(R.id.btn_cancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });


        ArrayList<CountryData> countries = new ArrayList<>();
        countries.add(new CountryData("Nigeria", R.drawable.nigeria));
        countries.add(new CountryData("Ghana", R.drawable.ghana));

        Spinner sp = (Spinner)findViewById(R.id.country_spinner);
        CountrySpinnerAdapter adapter=new CountrySpinnerAdapter(this, 2, countries);
        sp.setAdapter(adapter);

        Spinner banksList = (Spinner) findViewById(R.id.bank_spinner);

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
