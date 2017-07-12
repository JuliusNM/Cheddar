package com.example.dell.cheddar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.dell.cheddar.adapter.CountrySpinnerAdapter;
import com.example.dell.cheddar.model.CountryData;

import java.util.ArrayList;

public class AddRecipientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipient);

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
}
