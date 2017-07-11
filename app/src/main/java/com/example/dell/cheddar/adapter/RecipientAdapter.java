package com.example.dell.cheddar.adapter;

import android.content.Context;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.cheddar.R;
import com.example.dell.cheddar.model.Recipient;
import com.example.dell.cheddar.util.CountryHelper;

import java.util.ArrayList;

/**
 * Created by Dell on 7/10/2017.
 */

public class RecipientAdapter extends RecyclerView.Adapter<RecipientAdapter.RecipientHolder> {

    public static class RecipientHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name;
        MenuPopupWindow.MenuDropDownListView menuDropDown;
        ImageView countryflag;
        TextView bank;

        public RecipientHolder(View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.recipient_picture);
            name = (TextView) itemView.findViewById(R.id.recipient_name);
//            menuDropDown = (MenuPopupWindow.MenuDropDownListView)itemView.findViewById(R.id.recipient_options);
            countryflag = (ImageView) itemView.findViewById(R.id.recipient_country);
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

        holder.profile.setBackgroundResource(recipient.getProfile());
        holder.name.setText(recipient.getFirstName());
//        holder.menuDropDown
        holder.countryflag.setBackgroundResource(CountryHelper.getCountryImageFromStr(recipient.getCountry()));
        holder.bank.setText(recipient.getBank());
    }

    @Override
    public int getItemCount() {
        return recipients.size();
    }
}
