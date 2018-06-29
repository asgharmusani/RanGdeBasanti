package com.iotechnica.rangdebasanti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.util.Log;

import static android.content.ContentValues.TAG;


public class WifiConnection extends BroadcastReceiver{

    /**
     * Notifies the receiver to turn wifi on
     */
    private static final String ACTION_WIFI_ON = "android.intent.action.WIFI_ON";

    /**
     * Notifies the receiver to turn wifi off
     */
    private static final String ACTION_WIFI_OFF = "android.intent.action.WIFI_OFF";

    /**
     * Notifies the receiver to connect to a specified wifi
     */
    private static final String ACTION_CONNECT_TO_WIFI = "android.intent.action.CONNECT_TO_WIFI";

    /**
     * Notifies the receiver to connect to a previous connected wifi
     */
    private static final String ACTION_CONNECT_TO_PREVIOUS_WIFI = "android.intent.action.CONNECT_TO_PREVIOUS_WIFI";

    private WifiManager wifiManager;

    private static int previousNetworkID;

    public WifiConnection() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: intent = [" + intent + "]");

        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        final String action = intent.getAction();

        if (isTextNullOrEmpty(action)) {
            Log.d(TAG, "action is not empty");
            switch (action) {
                case ACTION_WIFI_ON:
                    // Turns wifi on
                    wifiManager.setWifiEnabled(true);
                    break;
                case ACTION_WIFI_OFF:
                    // Turns wifi off
                    wifiManager.setWifiEnabled(false);
                    break;

                case ACTION_CONNECT_TO_WIFI:
                    // Connects to a specific wifi network

                    if (!wifiManager.isWifiEnabled()) {
                        wifiManager.setWifiEnabled(true);
                        while (!wifiManager.isWifiEnabled()) {
                            //keep looping until it is activated
                        }
                    }

                    final String networkSSID = intent.getStringExtra("ssid");
                    final String networkPassword = intent.getStringExtra("password");

                    previousNetworkID = getCurrentNetID(context);
                    if (isTextNullOrEmpty(networkSSID) && isTextNullOrEmpty(networkPassword)) {
                        connectToWifi(networkSSID, networkPassword);
                    } else {
                        Log.d(TAG, "onReceive: cannot use " + ACTION_CONNECT_TO_WIFI +
                                "without passing in a proper wifi SSID and password.");
                    }
                    break;
                case ACTION_CONNECT_TO_PREVIOUS_WIFI:
                    //connects to a previously connected wifi
                    Log.d("Variable Check", "NetID is " + previousNetworkID);
                    wifiManager.disconnect();
                    if(previousNetworkID != -1){
                        connectToPreviousWifi(previousNetworkID);
                    }
                    else {
                        wifiManager.setWifiEnabled(false);
                    }

            }
        }
    }

    private boolean isTextNullOrEmpty(final String text) {
        return (text != null && !text.isEmpty());
    }

    /**
     * Connect to the specified wifi network.
     *
     * @param networkSSID     - The wifi network SSID
     * @param networkPassword - the wifi password
     */
    private void connectToWifi(final String networkSSID, final String networkPassword) {

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = String.format("\"%s\"", networkSSID);
        conf.preSharedKey = String.format("\"%s\"", networkPassword);

        int netId = wifiManager.addNetwork(conf);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }

    private void connectToPreviousWifi (final int netId){
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }

    private int getCurrentNetID(Context context) {
        int netID = -1;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }
        else networkInfo = null;

        if (networkInfo == null) {
            return -1;
        }

        if (networkInfo.isConnected()) {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && isTextNullOrEmpty(connectionInfo.getSSID())) {
                Log.d("Previous netID", connectionInfo.getNetworkId()+" is the previous netID " + connectionInfo.getSSID() + " is the SSID");
                netID = connectionInfo.getNetworkId();
            }
        }

        return netID;
    }

    /**
     * This is for Context-Based Registering of the receiver
     * We are using Manifest-Based Registering of the receiver
     */
    @NonNull
    public static IntentFilter getIntentFilterForWifiConnectionReceiver() {
        final IntentFilter randomIntentFilter = new IntentFilter(ACTION_WIFI_ON);
        randomIntentFilter.addAction(ACTION_WIFI_OFF);
        randomIntentFilter.addAction(ACTION_CONNECT_TO_WIFI);
        randomIntentFilter.addAction(ACTION_CONNECT_TO_PREVIOUS_WIFI);
        return randomIntentFilter;
    }
}
