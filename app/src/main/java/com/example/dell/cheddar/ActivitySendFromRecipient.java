package com.example.dell.cheddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.cheddar.model.Recipient;

import java.io.Serializable;

public class ActivitySendFromRecipient extends AppCompatActivity {
    Button next;
    String firstName;
    String lastName;
    String bank;
    String account;
    String countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_from_recipient);

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

        next = (Button)findViewById(R.id.next_button);
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
