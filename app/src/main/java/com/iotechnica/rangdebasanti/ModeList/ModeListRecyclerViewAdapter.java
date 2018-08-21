package com.iotechnica.rangdebasanti.ModeList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.iotechnica.rangdebasanti.Connection.GetRequest;
import com.iotechnica.rangdebasanti.R;

import java.util.List;


public class ModeListRecyclerViewAdapter extends RecyclerView.Adapter<ModeListRecyclerViewAdapter.ViewHolder> {

    private List<ModeListItem> listItems;
    private Context context;
    private String address, modeNumber;
    private GetRequest makeRequest;
    private StoreData storeData;


    private static final String TAG = "ModeListAdapter";

    public ModeListRecyclerViewAdapter(List<ModeListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        storeData = new StoreData(context);
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
        holder.materialFavoriteButton.setFavorite(isFavorite(listItem), false);

        holder.cardView.setOnClickListener(v -> {

            address = "192.168.4.1";
            modeNumber = "m=" + listItem.getModeNumber();
            makeRequest = new GetRequest();
            Boolean response = makeRequest.initiateRequest(address, modeNumber);
            if (response)
            {
                System.out.println("THE LIGHT IS WORKING");
            }

        });

        holder.materialFavoriteButton.setOnClickListener(v -> {
            holder.materialFavoriteButton.toggleFavorite();
            if(holder.materialFavoriteButton.isFavorite()){

                if(storeData.getFavorites()!=null){
                    if(storeData.getFavorites().size() <=2){

                        Log.d(TAG,""+storeData.getFavorites().size());
                        //limit of data allowed is 3
                        storeData.addFavorite(listItem);
                        Log.d(TAG, "add fav");
                    }
                    else {
                        Toast.makeText(context,"Can't choose more than 3 fav",Toast.LENGTH_SHORT).show();
                        holder.materialFavoriteButton.toggleFavorite();
                    }
                }
                else {
                    //will only work if the sharedPreferences is null
                    //that means if it's not created
                    //will work the first time only
                    storeData.addFavorite(listItem);
                    Log.d(TAG, "add fav");
                }

            }
            else {
                storeData.removeFavorite(listItem);
                Log.d(TAG, "remove fav");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewModeName;
        TextView textViewDescription;
        CardView cardView;
        MaterialFavoriteButton materialFavoriteButton;

        ViewHolder(View itemView) {
            super(itemView);

            textViewModeName = itemView.findViewById(R.id.textViewModeName);
            textViewDescription = itemView.findViewById(R.id.textViewModeDescription);
            cardView = itemView.findViewById(R.id.modeCardView);
            materialFavoriteButton = itemView.findViewById(R.id.btnFavourite);


        }
    }

    public boolean isFavorite(ModeListItem checkProduct) {
        boolean check = false;
        List<ModeListItem> favorites = storeData.getFavorites();
        if (favorites != null) {
            for (ModeListItem item : favorites) {
                if (item.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}
