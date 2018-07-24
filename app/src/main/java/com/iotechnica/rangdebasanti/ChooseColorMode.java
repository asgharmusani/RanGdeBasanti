package com.iotechnica.rangdebasanti;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;

import com.iotechnica.rangdebasanti.Fragments.ColorFragment;
import com.iotechnica.rangdebasanti.Fragments.ModesFragment;
import com.iotechnica.rangdebasanti.TabLayout.NonSwipeableViewPager;
import com.iotechnica.rangdebasanti.TabLayout.SectionsPageAdapter;


public class ChooseColorMode extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String ssid = "RGBeast";
    private static final String password = "redbeast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_color_mode);
        Log.d(TAG, "onCreate: Starting.");

        SectionsPageAdapter mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        NonSwipeableViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onResume(){
        Log.d(TAG, "onResume: Starting.");
        super.onResume();
        intentConnectToRGB();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        intentConnectToPreviousWifi();

    }

    @Override
    protected void onPause(){
        super.onPause();
        intentConnectToPreviousWifi();
    }

    private void intentConnectToRGB(){
        Intent intent = new Intent("android.intent.action.CONNECT_TO_WIFI");
        intent.putExtra("ssid", ssid);
        intent.putExtra("password", password);
        sendBroadcast(intent);
    }

    private void intentConnectToPreviousWifi(){
        Intent intent = new Intent("android.intent.action.CONNECT_TO_PREVIOUS_WIFI");
        sendBroadcast(intent);
    }



    private void setupViewPager(NonSwipeableViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ColorFragment(), "COLOR");
        adapter.addFragment(new ModesFragment(), "MODES");
        viewPager.setAdapter(adapter);
    }


}
