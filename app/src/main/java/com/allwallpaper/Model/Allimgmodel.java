package com.allwallpaper.Model;

public class Allimgmodel {

    int image;
    String key_id;
    String favStatus;

    int item_image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Allimgmodel(int image) {
        this.image = image;
    }

    public Allimgmodel(String key_id, String favStatus, int item_image) {
        this.key_id = key_id;
        this.favStatus = favStatus;
        this.item_image = item_image;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }
}
