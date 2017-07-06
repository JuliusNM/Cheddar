package com.example.dell.cheddar;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class SendMoney extends AppCompatActivity  {

//    private RadioGroup bankwallet;
    private EditText accountNumber;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        accountNumber = (EditText) findViewById(R.id.account_number);

//        bankwallet = (RadioGroup) findViewById(R.id.radioSendTo);
//        bankwallet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int buttonId) {
//                RadioButton selectedButton = (RadioButton) findViewById(buttonId);
//                accountNumber.setHint((String) selectedButton.getTag());
//            }
//        });

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convert = new Intent(getApplicationContext(), Convert.class);
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
}
