package com.iotechnica.rangdebasanti.ModeList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreData {


    private static final String PREFS_NAME = "Data";
    private static final String FAVORITES = "Favorite_Selected";
    public SharedPreferences settings;

    public StoreData(Context context) {
        this.settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // This four methods are used for maintaining favorites.
    private void saveFavorite(List<ModeListItem> favorites) {
        Editor editor;

        editor = settings.edit();

        Gson gson = new Gson();

        editor.putString(FAVORITES, gson.toJson(favorites));

        editor.apply();
    }

    public void addFavorite(ModeListItem product) {
        List<ModeListItem> favorites = getFavorites();
        if (favorites == null)
            favorites = new ArrayList<>();
        favorites.add(product);
        saveFavorite(favorites);
    }

    public void removeFavorite(ModeListItem product) {
        ArrayList<ModeListItem> favorites = getFavorites();
        if (favorites != null) {
            favorites.remove(product);
            saveFavorite(favorites);
        }
    }

    public ArrayList<ModeListItem> getFavorites() {
        List<ModeListItem> favorites;

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            ModeListItem[] favoriteItems = gson.fromJson(jsonFavorites,
                    ModeListItem[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<>(favorites);
        } else
            return null;

        return (ArrayList<ModeListItem>) favorites;
    }
}
