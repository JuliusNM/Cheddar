package com.example.dell.cheddar.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.cheddar.NetworkUtil;
import com.example.dell.cheddar.R;
import com.example.dell.cheddar.model.Response;
import com.example.dell.cheddar.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.example.dell.cheddar.Validation.validateEmail;
import static com.example.dell.cheddar.Validation.validateFirstName;
import static com.example.dell.cheddar.Validation.validateLastName;
import static com.example.dell.cheddar.Validation.validatePassword;
import static com.example.dell.cheddar.Validation.validatePhoneNumber;

public class RegisterFragment extends Fragment {

    public static final String TAG = RegisterFragment.class.getSimpleName();

    private EditText fName;
    private EditText lName;
    private EditText pNumber;
    private EditText eAddress;
    private EditText pass;

    private Button btnRegister;
    private TextView tv_login;
    private ProgressBar mProgressbar;

    private TextInputLayout tLfname;
    private TextInputLayout tLlast_name;
    private TextInputLayout tLemail_address;
    private TextInputLayout tLphone_number;
    private TextInputLayout tLpassword;

    private CompositeSubscription mSubscriptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        mSubscriptions = new CompositeSubscription();
        initViews(view);
        return view;
    }

    private void initViews(View v) {

        fName = (EditText) v.findViewById(R.id.first_name);
        lName = (EditText)v.findViewById(R.id.last_name);
        pNumber = (EditText)v.findViewById(R.id.phone_number);
        eAddress = (EditText)v.findViewById(R.id.et_email);
        pass = (EditText)v.findViewById(R.id.password);

        tLfname = (TextInputLayout) v.findViewById(R.id.tLfname);
        tLlast_name = (TextInputLayout) v.findViewById(R.id.tLlast_name);
        tLemail_address = (TextInputLayout)v.findViewById(R.id.tLemail_address);
        tLphone_number = (TextInputLayout)v.findViewById(R.id.tLphone_number);
        tLpassword = (TextInputLayout)v.findViewById(R.id.tLpassword);

        btnRegister = (Button)v.findViewById(R.id.register);
        tv_login = (TextView) v.findViewById(R.id.tv_login);
        mProgressbar = (ProgressBar) v.findViewById(R.id.progress);

        btnRegister.setOnClickListener(view -> register());
        tv_login.setOnClickListener(view -> goToLogin());
    }

    private void goToLogin() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LoginFragment fragment = new LoginFragment();
        ft.replace(R.id.fragmentFrame, fragment, LoginFragment.TAG);
        ft.commit();
    }

    private void register() {

        setError();

        String firstName = fName.getText().toString();
        String lastName = lName.getText().toString();
        String emailAddress = eAddress.getText().toString();
        String phoneNumber = pNumber.getText().toString();
        String password = pass.getText().toString();

        int err = 0;

        if (!validateFirstName(firstName)) {

            err++;
            tLfname.setError("Name should not be empty !");
        }
        if (!validateLastName(lastName)) {

            err++;
            tLlast_name.setError("Last Name should not be empty !");
        }
        if (!validatePhoneNumber(phoneNumber)) {

            err++;
            tLphone_number.setError("Not a valid format!");
        } if (!validateEmail(emailAddress)) {

            err++;
            tLemail_address.setError("Not a valid format!");
        }
        if (!validatePassword(password)){

            err++;
            tLpassword.setError("Password Must not be null");
        }

        if (err == 0){
            User user = new User();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setEmailAddress(emailAddress);
            user.setPassword(password);

            mProgressbar.setVisibility(View.VISIBLE);
            registerProcess(user);
        }
        else {

            showSnackBarMessage("Enter Valid Details !");
        }
    }

    private void showSnackBarMessage(String s) {
    }

    private void setError() {
        tLfname.setError(null);
        tLlast_name.setError(null);
        tLphone_number.setError(null);
        tLemail_address.setError(null);
        tLpassword.setError(null);

    }

    private void registerProcess(User user) {


        mSubscriptions.add(NetworkUtil.getRetrofit().register(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }
    private void handleResponse(Response response) {
        mProgressbar.setVisibility(View.GONE);
        showSnackBarMessage(response.getMessage());
    }

    private void handleError(Throwable error) {
        mProgressbar.setVisibility(View.GONE);

        if (error instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {

                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody,Response.class);
                showSnackBarMessage(response.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            showSnackBarMessage("Network Error !");
        }
    }


}
