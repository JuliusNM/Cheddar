package com.example.dell.cheddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.Double.valueOf;

public class ConvertActivity extends AppCompatActivity {
    private Button btnsend;
    private TextView exchangeRate;
    private TextView cheddarCharges;
    private EditText amountToSend;
    private TextView amountToReceive;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        amountToSend = (EditText) findViewById(R.id.amount_send);
        exchangeRate = (TextView) findViewById(R.id.ex_rate_value);
        cheddarCharges = (TextView) findViewById(R.id.cheddar_charges);
        amountToReceive = (TextView) findViewById(R.id.amount_receive);
        total = (TextView) findViewById(R.id.total);

        exchangeRate.setText("70");
        cheddarCharges.setText("10");

        amountToSend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Tag1", "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {

                    int amountToSend = s.length() > 0 ? Integer.parseInt(s.toString()) : 0;
                    int conversionRate = Integer.parseInt(exchangeRate.getText().toString());

                    amountToReceive.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(amountToSend * conversionRate));
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
