package com.iotechnica.rangdebasanti.ModeList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreData {
    public StoreData() {
        super();
    }

    private static final String PREFS_NAME = "Data";
    private static final String FAVORITES = "Favorite_Selected";

    // This four methods are used for maintaining favorites.
    public void saveFavorite(Context context, List<ModeListItem> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();

        editor.putString(FAVORITES, gson.toJson(favorites));

        editor.apply();
    }

    public void addFavorite(Context context, ModeListItem product) {
        List<ModeListItem> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<>();
        favorites.add(product);
        saveFavorite(context, favorites);
    }

    public void removeFavorite(Context context, ModeListItem product) {
        ArrayList<ModeListItem> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorite(context, favorites);
        }
    }

    public ArrayList<ModeListItem> getFavorites(Context context) {
        SharedPreferences settings;
        List<ModeListItem> favorites;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

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
