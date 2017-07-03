package com.example.dell.cheddar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Dell on 6/27/2017.
 */

public class ContactDetails extends Fragment {
    ImageView add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.contact_details, container, false);

        ImageView add = (ImageView) layout.findViewById(R.id.imagea_addrecipient);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addrecipient = new Intent(getActivity(), AddRecipient.class);
                startActivity(addrecipient);
            }
        });
        return layout;

    }

}
