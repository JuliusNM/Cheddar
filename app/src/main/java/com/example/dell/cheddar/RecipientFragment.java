package com.example.dell.cheddar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.cheddar.adapter.RecipientAdapter;
import com.example.dell.cheddar.model.Recipient;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Dell on 6/27/2017.
 */

public class RecipientFragment extends Fragment {
    ImageView add;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.recipients, container, false);

        ImageView add = (ImageView) layout.findViewById(R.id.imagea_addrecipient);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecipient = new Intent(getActivity(), AddRecipientActivity.class);
                startActivity(addRecipient);
            }
        });

        recyclerView = (RecyclerView) layout.findViewById(R.id.recipients_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ArrayList<Recipient> recipients = new ArrayList<>(Arrays.asList(
                new Recipient("Julius", "Ngigi", "Too poor 4 bank", "Ghana", "134567890", R.drawable.julius),
                new Recipient("Julius", "Ngigi", "Too poor 4 bank", "Nigeria", "134567890", R.drawable.julius),
                new Recipient("Julius", "Ngigi", "Too poor 4 bank", "Ghana", "134567890", R.drawable.julius)

        ));

        RecipientAdapter adapter = new RecipientAdapter(recipients);
        recyclerView.setAdapter(adapter);

        return layout;

    }

}
