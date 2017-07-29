package com.example.dell.cheddar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.cheddar.adapter.RecipientAdapter;
import com.example.dell.cheddar.model.ApiClient;
import com.example.dell.cheddar.model.ApiInterface;
import com.example.dell.cheddar.model.Recipient;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dell on 6/27/2017.
 */

public class RecipientFragment extends Fragment {
    ImageView add;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ApiInterface apiInterface;
    private ArrayList<Recipient> recipients;
    private RecipientAdapter adapter;

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

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<Recipient>> call = apiInterface.getRecipients();

        call.enqueue(new Callback<ArrayList<Recipient>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipient>> call, Response<ArrayList<Recipient>> response) {
                recipients = response.body();
                adapter = new RecipientAdapter(recipients);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipient>> call, Throwable t) {
                Log.d("we failed", "see");
            }
        });

        return layout;

    }

}
