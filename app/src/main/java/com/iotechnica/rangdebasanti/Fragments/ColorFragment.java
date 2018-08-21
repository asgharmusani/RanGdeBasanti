package com.iotechnica.rangdebasanti.Fragments;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iotechnica.rangdebasanti.Connection.GetRequest;
import com.iotechnica.rangdebasanti.ModeList.ModeListItem;
import com.iotechnica.rangdebasanti.ModeList.StoreData;
import com.iotechnica.rangdebasanti.R;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;

import java.util.ArrayList;


public class ColorFragment extends Fragment{

    private static final String TAG = "ColorFragment";


    private StoreData getData;
    private GetRequest makeRequest;
    private String hexColor, staticColor, address;
    private SharedPreferences.OnSharedPreferenceChangeListener changeListener;
    private Button[] favTV = new Button[3];

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.color_fragment,container, false);


        ColorPicker colorPicker = view.findViewById(R.id.colorPicker);
        SVBar svBar = view.findViewById(R.id.svBar);

        colorPicker.addSVBar(svBar);
        colorPicker.setShowOldCenterColor(false);

        Log.d(TAG, "On create");

        getData = new StoreData(getActivity());
        refreshFavorites(view);

        address = "192.168.4.1";
        colorPicker.setOnColorSelectedListener(color -> {

            hexColor = String.format("%06X", (0xFFFFFF & color));
            System.out.println("Color is " + hexColor);

            staticColor = "c=" + hexColor;

            makeRequest = new GetRequest();
            makeRequest.initiateRequest(address, "m=0");
            Boolean response = makeRequest.initiateRequest(address, staticColor);
            if (response)
            {
                Log.d(TAG,"THE LIGHT IS WORKING");
            }

        });


        View.OnClickListener onClickListener = v -> {
            ModeListItem favorite = (ModeListItem) v.getTag();
            if (favorite != null){
                address = "192.168.4.1";
                String modeNumber = "m=" + favorite.getModeNumber();
                makeRequest = new GetRequest();
                Boolean response = makeRequest.initiateRequest(address, modeNumber);
                if (response)
                {
                    System.out.println("THE LIGHT IS WORKING");
                }
            }
        };
        favTV[0].setOnClickListener(onClickListener);
        favTV[1].setOnClickListener(onClickListener);
        favTV[2].setOnClickListener(onClickListener);


        SharedPreferences preferences = getData.settings;

        changeListener = (sharedPreferences, key) -> {
            Log.d(TAG,"Listener is working");
            refreshFavorites(view);
        };
        preferences.registerOnSharedPreferenceChangeListener(changeListener);
        return view;
    }

    public void refreshFavorites(View view){

        favTV[0] = view.findViewById(R.id.label_a);
        favTV[1] = view.findViewById(R.id.label_b);
        favTV[2] = view.findViewById(R.id.label_c);

        ArrayList<ModeListItem> dataFavorites = getData.getFavorites();

        int i=0;
        if (dataFavorites!=null){
            for(ModeListItem favorite:dataFavorites)
            {
                favTV[i].setTag(favorite);
                favTV[i].setText(favorite.getModeName());
                i++;
            }
        }
        while (i != 3){
            favTV[i].setText("");
            favTV[i].setTag(null);
            i++;
        }
    }

}
