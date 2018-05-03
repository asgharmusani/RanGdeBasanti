package com.iotechnica.rangdebasanti;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ModesFragment extends Fragment{
    private static final String TAG = "ModesFragment";

    private Button btnSendModeNumber, btnRandom;
    private GetRequest makeRequest;
    private String modeNumber, address;
    private TextView textModeNumber;
    private Random random;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modes_fragment,container, false);
        btnSendModeNumber = view.findViewById(R.id.btnSendModeNumber);
        btnRandom = view.findViewById(R.id.btnRandom);
        textModeNumber = view.findViewById(R.id.modeNumber);

        btnRandom.setOnClickListener(v -> {
            random = new Random();
            textModeNumber.setText(Integer.toString(random.nextInt(40)+1))  ;
        });

        btnSendModeNumber.setOnClickListener(v -> {

            address = "192.168.4.1";


            System.out.println("this is the mode number " + textModeNumber.getText().toString());
            modeNumber = "m=" + textModeNumber.getText().toString();
            makeRequest = new GetRequest();
            Boolean response = makeRequest.initiateRequest(address, modeNumber);
            if (response)
            {
                System.out.println("THE LIGHT IS WORKING");
            }
            InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(textModeNumber.getWindowToken(), 0);




        });

        return view;

    }
}
