package com.example.kane.Model;

public class Menu {
    private String Name;
    private String Image;
    private String Info;
    private String Price;
    private String RestaurantID;

    public Menu(){

    }

    public Menu(String name, String image, String info, String price, String restaurantID) {
        Name = name;
        Image = image;
        Info = info;
        Price = price;
        RestaurantID = restaurantID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRestaurantID() {
        return RestaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        RestaurantID = restaurantID;
    }
}
