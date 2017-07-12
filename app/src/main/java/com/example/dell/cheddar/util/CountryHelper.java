package com.example.dell.cheddar.util;

import com.example.dell.cheddar.R;

/**
 * Created by Dell on 7/10/2017.
 */

public class CountryHelper {
    public static int getCountryImageFromStr(String country){
        int resource = 0;

        switch(country){
            default:
            case "Ghana":
                resource = R.drawable.ghana;
                break;
            case "Nigeria":
                resource = R.drawable.nigeria;
                break;
        }

        return resource;
    }
}
