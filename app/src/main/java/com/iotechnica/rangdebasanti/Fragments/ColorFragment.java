package com.iotechnica.rangdebasanti.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iotechnica.rangdebasanti.Connection.GetRequest;
import com.iotechnica.rangdebasanti.R;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;


public class ColorFragment extends Fragment{
    private static final String TAG = "ColorFragment";


    private GetRequest makeRequest;
    private String hexColor, staticColor, address;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.color_fragment,container, false);


        ColorPicker colorPicker = view.findViewById(R.id.colorPicker);
        SVBar svBar = view.findViewById(R.id.svBar);

        colorPicker.addSVBar(svBar);
        colorPicker.setShowOldCenterColor(false);


        colorPicker.setOnColorSelectedListener(color -> {

            address = "192.168.4.1";

            hexColor = String.format("%06X", (0xFFFFFF & color));
            System.out.println("Color is " + hexColor);

            staticColor = "c=" + hexColor;

            makeRequest = new GetRequest();
            makeRequest.initiateRequest(address, "m=0");
            Boolean response = makeRequest.initiateRequest(address, staticColor);
            if (response)
            {
                System.out.println("THE LIGHT IS WORKING");
            }

        });



        return view;
    }
}
