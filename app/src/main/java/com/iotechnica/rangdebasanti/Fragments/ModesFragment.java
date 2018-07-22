package com.iotechnica.rangdebasanti.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iotechnica.rangdebasanti.Connection.GetRequest;
import com.iotechnica.rangdebasanti.ModeList.ModeListItem;
import com.iotechnica.rangdebasanti.ModeList.ModeListRecyclerViewAdapter;
import com.iotechnica.rangdebasanti.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModesFragment extends Fragment{
    private static final String TAG = "ModesFragment";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<ModeListItem> mListItems;

    private Button btnSendModeNumber, btnRandom;
    private GetRequest makeRequest;
    private String modeNumber, address;
    private TextView textModeNumber;
    private Random random;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modes_fragment,container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mListItems = new ArrayList<>();

        addModeList();

        mAdapter = new ModeListRecyclerViewAdapter(mListItems, getActivity());

        recyclerView.setAdapter(mAdapter);


        return view;

    }

    private void addModeList(){

        mListItems.add(new ModeListItem("Blink","Description ", 1));
        mListItems.add(new ModeListItem("Breath","Description ", 2));
        mListItems.add(new ModeListItem("Color Wipe","Description ", 3));
        mListItems.add(new ModeListItem("Color Wipe Inverse","Description ", 4));
        mListItems.add(new ModeListItem("Color Wipe Reverse","Description ", 5));
        mListItems.add(new ModeListItem("Color Wipe Reverse Inverse","Description ", 6));
        mListItems.add(new ModeListItem("Color Wipe Random","Description ", 7));
        mListItems.add(new ModeListItem("Random Color","Description ", 8));
        mListItems.add(new ModeListItem("Single Dynamic","Description ", 9));
        mListItems.add(new ModeListItem("Multi Dynamic","Description ", 10));
        mListItems.add(new ModeListItem("Rainbow","Description ", 11));
        mListItems.add(new ModeListItem("Rainbow Cycle","Description ", 12));
        mListItems.add(new ModeListItem("Scan","Description ", 13));
        mListItems.add(new ModeListItem("Dual Scan","Description ", 14));
        mListItems.add(new ModeListItem("Fade","Description ", 15));
        mListItems.add(new ModeListItem("Theater Chase","Description ", 16));
        mListItems.add(new ModeListItem("Theater Chase Rainbow","Description ", 17));
        mListItems.add(new ModeListItem("Running Lights","Description ", 18));
        mListItems.add(new ModeListItem("Twinkle","Description ", 19));
        mListItems.add(new ModeListItem("Twinkle Random","Description ", 20));
        mListItems.add(new ModeListItem("Twinkle Fade","Description ", 21));
        mListItems.add(new ModeListItem("Twinkle Fade Random","Description ", 22));
        mListItems.add(new ModeListItem("Sparkle","Description ", 23));
        mListItems.add(new ModeListItem("Flash Sparkle","Description ", 24));
        mListItems.add(new ModeListItem("Hyper Sparkle","Description ", 25));
        mListItems.add(new ModeListItem("Strobe","Description ", 26));
        mListItems.add(new ModeListItem("Strobe Rainbow","Description ", 27));
        mListItems.add(new ModeListItem("Multi Strobe","Description ", 28));
        mListItems.add(new ModeListItem("Blink Rainbow","Description ", 29));
        mListItems.add(new ModeListItem("Chase White","Description ", 30));
        mListItems.add(new ModeListItem("Chase Color","Description ", 31));
        mListItems.add(new ModeListItem("Chase Random","Description ", 32));
        mListItems.add(new ModeListItem("Chase Rainbow","Description ", 33));
        mListItems.add(new ModeListItem("Chase Flash","Description ", 34));
        mListItems.add(new ModeListItem("Chase Flash Random","Description ", 35));
        mListItems.add(new ModeListItem("Chase Rainbow White","Description ", 36));
        mListItems.add(new ModeListItem("Chase Blackout","Description ", 37));
        mListItems.add(new ModeListItem("Chase Blackout Rainbow","Description ", 38));
        mListItems.add(new ModeListItem("Color Sweep Random","Description ", 39));
        mListItems.add(new ModeListItem("Running Color","Description ", 40));
        mListItems.add(new ModeListItem("Running Red Blue","Description ", 41));
        mListItems.add(new ModeListItem("Running Random","Description ", 42));
        mListItems.add(new ModeListItem("Larson Scanner","Description ", 43));
        mListItems.add(new ModeListItem("Comet","Description ", 44));
        mListItems.add(new ModeListItem("Fireworks","Description ", 45));
        mListItems.add(new ModeListItem("Fireworks Random","Description ", 46));
        mListItems.add(new ModeListItem("Merry Christmas","Description ", 47));
        mListItems.add(new ModeListItem("Halloween","Description ", 48));
        mListItems.add(new ModeListItem("Fire Flicker","Description ", 49));
        mListItems.add(new ModeListItem("Fire Flicker soft","Description ", 50));
        mListItems.add(new ModeListItem("Fire Flicker intense","Description ", 51));
        mListItems.add(new ModeListItem("Circus Combustus","Description ", 52));
        mListItems.add(new ModeListItem("Bicolor Chase","Description ", 53));
        mListItems.add(new ModeListItem("Tricolor Chase","Description ", 54));
        mListItems.add(new ModeListItem("ICU","Description ", 55));



    }
}
