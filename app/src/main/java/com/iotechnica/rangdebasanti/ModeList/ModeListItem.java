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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModeListItem other = (ModeListItem) obj;
        return modeNumber == other.modeNumber;
    }

}
