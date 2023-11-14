package br.edu.ifsp.dmo.sitesinteressantes.model;

import androidx.annotation.NonNull;

public class TagSite {

    private String tag;

    public TagSite(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @NonNull
    @Override
    public String toString() {
        return getTag();
    }
}