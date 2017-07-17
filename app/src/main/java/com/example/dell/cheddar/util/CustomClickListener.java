package com.example.dell.cheddar.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.dell.cheddar.ActivitySendFromRecipient;
import com.example.dell.cheddar.model.Recipient;

/**
 * Created by Dell on 7/13/2017.
 */

public class CustomClickListener implements View.OnClickListener {

    private Recipient recipient;

    public CustomClickListener(Recipient recipient) {
        this.recipient = recipient;
    }

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, ActivitySendFromRecipient.class);
        intent.putExtra("recipient", recipient);

        context.startActivity(intent);
    }
}
