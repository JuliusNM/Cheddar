package com.example.dell.cheddar;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by Dell on 7/22/2017.
 */

public class Validation {

    public static boolean validateFirstName(String firstName){

        if (TextUtils.isEmpty(firstName)){
            return false;
        }
        else
            return true;

    }
    public static boolean validatePassword(String password){
        if (TextUtils.isEmpty(password)){
            return false;
        }
        else
            return true;
    }
    public static boolean validateLastName(String lastName){

        if(TextUtils.isEmpty(lastName)){
            return false;
        }
        else
            return true;
    }

    public static boolean validatePhoneNumber(String phoneNumber){

        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

        if (phoneNumber.matches(regex)){
            return true;
        }
        else
            return false;
    }
    public static boolean validateEmail(String emailAddress) {

        if (TextUtils.isEmpty(emailAddress) || !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {

            return false;

        } else {

            return  true;
        }
    }
}
