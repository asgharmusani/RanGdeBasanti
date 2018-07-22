package com.iotechnica.rangdebasanti.ModeList;

public class ModeListItem {

    private String modeName;
    private String description;
    private int modeNumber;


    public ModeListItem(String modeName, String description, int modeNumber) {
        this.modeName = modeName;
        this.description = description;
        this.modeNumber = modeNumber;
    }

    public String getModeName() {
        return modeName;
    }

    public String getDescription() {
        return description;
    }

    public int getModeNumber() {
        return modeNumber;
    }
}
