package com.example.dell.cheddar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private String finalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        amountToSend = (EditText) findViewById(R.id.amount_send);
        exchangeRate = (TextView) findViewById(R.id.ex_rate_value);
        cheddarCharges = (TextView) findViewById(R.id.cheddar_charges);
        amountToReceive = (TextView) findViewById(R.id.amount_receive);
        btnsend = (Button) findViewById(R.id.btn_send);
        total = (TextView) findViewById(R.id.total);

        exchangeRate.setText("70");
        cheddarCharges.setText("10");

        amountToSend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {

                    double amountToSend = s.length() > 0 ? Double.parseDouble(s.toString()) : 0;
                    double conversionRate = Double.parseDouble(exchangeRate.getText().toString());
                    double cheddarRate = Double.parseDouble(cheddarCharges.getText().toString())/100 * amountToSend;

                    String finalAmount = NumberFormat.getNumberInstance(Locale.getDefault()).format((amountToSend * conversionRate)- (cheddarRate));

                    amountToReceive.setText(finalAmount);
                    total.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format((amountToSend + cheddarRate)));
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
         btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setMessage("Are you sure you wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("+233265231476", null, "You have received NGN" +finalAmount+"From Julius Mburu, please check with your bank for more information", null, null);
                        Toast.makeText(ConvertActivity.this,"Transaction successful! ",Toast.LENGTH_LONG).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

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
