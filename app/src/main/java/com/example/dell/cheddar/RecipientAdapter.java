package com.example.dell.cheddar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dell on 7/10/2017.
 */

public class RecipientAdapter extends RecyclerView.Adapter<RecipientAdapter.RecipientHolder> {

    public RecipientAdapter() {
    }

    @Override
    public RecipientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecipientHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipientHolder extends RecyclerView.ViewHolder {

        public RecipientHolder(View itemView) {
            super(itemView);
        }
    }
}
