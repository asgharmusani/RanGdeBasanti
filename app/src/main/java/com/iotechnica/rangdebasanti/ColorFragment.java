package com.iotechnica.rangdebasanti;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;



public class ColorFragment extends Fragment{
    private static final String TAG = "ColorFragment";

    private ImageView indicatorCircle;
    private HSLColorPicker colorPicker;
    private GetRequest makeRequest;
    private String hexColor, staticColor, address;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.color_fragment,container, false);

        indicatorCircle = view.findViewById(R.id.imageView);

        colorPicker = view.findViewById(R.id.colorPicker);
        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelectionEnd(int color) {

                address = "192.168.4.1";

                hexColor = String.format("%06X", (0xFFFFFF & color));
                staticColor = "c=" + hexColor;

                makeRequest = new GetRequest();
                makeRequest.initiateRequest(address, "m=0");
                Boolean response = makeRequest.initiateRequest(address, staticColor);
                if (response)
                {
                    System.out.println("THE LIGHT IS WORKING");
                }
            }

            @Override
            public void onColorSelected(int color){
                indicatorCircle.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
        });

        return view;
    }
}
