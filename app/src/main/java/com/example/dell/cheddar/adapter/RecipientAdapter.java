package com.example.dell.cheddar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.cheddar.util.CustomClickListener;
import com.example.dell.cheddar.R;
import com.example.dell.cheddar.model.Recipient;
import com.example.dell.cheddar.util.CountryHelper;

import java.util.ArrayList;

/**
 * Created by Dell on 7/10/2017.
 */

public class RecipientAdapter extends RecyclerView.Adapter<RecipientAdapter.RecipientHolder> {

    public static class RecipientHolder extends RecyclerView.ViewHolder {

        TextView recipient_picture;
        TextView name;
        ImageView countryFlag;
        TextView bank;



        public RecipientHolder(View itemView) {
            super(itemView);

            recipient_picture = (TextView) itemView.findViewById(R.id.recipient_picture);
            name = (TextView) itemView.findViewById(R.id.recipient_name);
            countryFlag = (ImageView) itemView.findViewById(R.id.recipient_country);
            bank = (TextView) itemView.findViewById(R.id.recipient_bank);

        }
    }

    private ArrayList<Recipient> recipients;

    public RecipientAdapter(ArrayList<Recipient> _recipients) {
        this.recipients = _recipients;
    }

    @Override
    public RecipientHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipient_list_item, parent, false);
        RecipientHolder recipientHolder = new RecipientHolder(view);

        return recipientHolder;
    }

    @Override
    public  void onBindViewHolder(RecipientHolder holder, int position) {
        Recipient recipient = recipients.get(position);

        //holder.recipient_picture.setText(recipient.getFirstName().substring(0, 1)+" "+recipient.getLastName().substring(0, 1));
        holder.name.setText(recipient.getFirstName()+" "+recipient.getLastName());
        holder.countryFlag.setBackgroundResource(CountryHelper.getCountryImageFromStr(recipient.getCountry()));
        holder.bank.setText(recipient.getBank());
        holder.itemView.setOnClickListener(new CustomClickListener(recipient));

    }

    @Override
    public int getItemCount() {
        return recipients.size();
    }
}