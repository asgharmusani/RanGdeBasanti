package com.iotechnica.rangdebasanti.ModeList;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iotechnica.rangdebasanti.Connection.GetRequest;
import com.iotechnica.rangdebasanti.R;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ModeListRecyclerViewAdapter extends RecyclerView.Adapter<ModeListRecyclerViewAdapter.ViewHolder> {

    private List<ModeListItem> listItems;
    private Context context;
    private String address, modeNumber;
    private GetRequest makeRequest;


    public ModeListRecyclerViewAdapter(List<ModeListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.modes_list_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        ModeListItem listItem = listItems.get(position);

        holder.textViewModeName.setText(listItem.getModeName());
        holder.textViewDescription.setText(listItem.getDescription());

        holder.linearLayout.setOnClickListener(v -> {

            address = "192.168.4.1";
            modeNumber = "m=" + listItem.getModeNumber();
            makeRequest = new GetRequest();
            Boolean response = makeRequest.initiateRequest(address, modeNumber);
            if (response)
            {
                System.out.println("THE LIGHT IS WORKING");
            }

            Toast.makeText(context, "Click on " + listItem.getModeName(), Toast.LENGTH_LONG).show();

        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewModeName;
        TextView textViewDescription;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewModeName = itemView.findViewById(R.id.textViewModeName);
            textViewDescription = itemView.findViewById(R.id.textViewModeDescription);
            linearLayout = itemView.findViewById(R.id.modeListLinearLayout);

        }
    }
}
