package com.example.kane.Model;

public class Cuisine {
    private String Name;
    private String Count;
    private String Image;

    public Cuisine() {

    }

    public Cuisine(String name, String count, String image) {
        Name = name;
        Count = count;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
