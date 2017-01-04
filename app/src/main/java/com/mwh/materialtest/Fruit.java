package com.mwh.materialtest;

/**
 * Created by Youg on 2017/1/4 14:36
 */

public class Fruit {
    private String name;
    private int ImageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        ImageId = imageId;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
