package com.example.dell.cheddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.Card;
import com.example.dell.cheddar.model.Recipient;
import com.example.dell.cheddar.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCard extends AppCompatActivity {
    private ApiInterface apiInterface;
    private ResponseData responseData;

    Button save;
    Button cancel;
    EditText cardNumber;
    EditText cardIssuer;
    EditText expiryMonth;
    EditText expiryYear;
    EditText cvv;
    TextView addAnotherCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        cardNumber = (EditText)findViewById(R.id.card_num);
        cardIssuer = (EditText)findViewById(R.id.card_issuer);
        expiryMonth = (EditText)findViewById(R.id.expiry_month);
        expiryYear = (EditText)findViewById(R.id.expiry_year);
        cvv = (EditText)findViewById(R.id.cvv);
        save = (Button) findViewById(R.id.btn_save);
        cancel = (Button)findViewById(R.id.btn_cancel);
        addAnotherCard = (TextView)findViewById(R.id.add_another);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = new Card(
                        cardIssuer.getText().toString(),
                        cardIssuer.getText().toString(),
                        expiryMonth.getText().toString(),
                        expiryYear.getText().toString(),
                        cvv.getText().toString()
                );
                Call<ResponseData> call = apiInterface.postCard(card);
                call.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        responseData = response.body();
                        Intent intent = new Intent(getApplication(), HomeActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("See, We failed","");

                    }
                });
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HomeActivity.class);
                startActivity(intent);
            }
        });
        addAnotherCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = new Card(
                        cardIssuer.getText().toString(),
                        cardIssuer.getText().toString(),
                        expiryMonth.getText().toString(),
                        expiryYear.getText().toString(),
                        cvv.getText().toString()
                );
                Call<ResponseData> call = apiInterface.postCard(card);
                call.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        responseData = response.body();
                        Intent intent = new Intent(getApplication(), AddCard.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("See, We failed","");
                    }
                });
            }
        });
    }
}
