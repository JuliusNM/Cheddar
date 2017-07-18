package com.example.dell.cheddar.adapter;

import android.accounts.Account;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.cheddar.R;
import com.example.dell.cheddar.model.AccountInterface;
import com.example.dell.cheddar.model.Bank;
import com.example.dell.cheddar.model.Card;
import com.example.dell.cheddar.model.SectionTitle;

import java.util.ArrayList;

/**
 * Created by Dell on 7/15/2017.
 */

public class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public class CardHolder extends RecyclerView.ViewHolder{

        TextView cardIssuer;
        TextView cardNumber;


        public CardHolder(View itemView) {
            super(itemView);

            cardIssuer = (TextView) itemView.findViewById(R.id.card_issuer);
            cardNumber = (TextView) itemView.findViewById(R.id.card_number);
        }
    }

    public class BankHolder extends RecyclerView.ViewHolder {

        TextView bankName;
        TextView accountNumber;


        public BankHolder(View itemView) {
            super(itemView);

            bankName = (TextView)itemView.findViewById(R.id.name_of_bank);
            accountNumber = (TextView)itemView.findViewById(R.id.account_number);
        }
    }

    public class SectionHolder extends RecyclerView.ViewHolder{

        TextView title;



        public SectionHolder(View itemView) {
            super(itemView);

           title = (TextView) itemView.findViewById(R.id.section_title);
        }
    }

    final static int TYPE_BANK = 1, TYPE_CARD = 2, TYPE_SECTION = 3;

    private ArrayList<AccountInterface> accounts;

    public AccountAdapter(ArrayList<AccountInterface> _accounts) {
        this.accounts = _accounts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;

        switch(viewType){
            case AccountAdapter.TYPE_BANK:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_account_list_item, parent, false);

                holder = new BankHolder(view);
                break;
            case AccountAdapter.TYPE_SECTION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_title_item, parent, false);

                holder = new SectionHolder(view);

                break;
            case AccountAdapter.TYPE_CARD:
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_item, parent, false);

                holder = new CardHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(getItemViewType(position)){
            case AccountAdapter.TYPE_BANK:
                Bank bank = (Bank) accounts.get(position);

                ((BankHolder) holder).bankName.setText(bank.getBank());
                ((BankHolder) holder).accountNumber.setText(bank.getAccountNumber());
                break;

            case AccountAdapter.TYPE_CARD:
                Card card = (Card) accounts.get(position);

                ((CardHolder) holder).cardIssuer.setText(card.getCardIssuer());
                ((CardHolder) holder).cardNumber.setText(card.getCardNumber());
                break;

            case AccountAdapter.TYPE_SECTION:
                SectionTitle sectionTitle = (SectionTitle)accounts.get(position);
                ((SectionHolder) holder).title.setText(sectionTitle.getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_SECTION;
        AccountInterface item = accounts.get(position);

        if (item instanceof Bank){
            type = TYPE_BANK;

        }
        else if (item instanceof Card){
            type = TYPE_CARD;
        }

        return type;
    }

    @Override
    public int getItemCount() {

        return accounts.size();
    }

    public void addItems(ArrayList<AccountInterface> items){
        this.accounts.addAll(items);
    }
}
