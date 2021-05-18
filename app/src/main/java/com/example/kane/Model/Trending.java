package com.example.kane.Model;

public class Trending {
    private String Name;
    private String PriceTag;
    private String Image;
    private String Stars;
    private String Reviews;
    private String Type;
    private String Location;
    private String Info;

    public Trending() {
    }

    public Trending(String name, String priceTag, String image, String stars, String reviews, String type, String location, String info) {
        Name = name;
        PriceTag = priceTag;
        Image = image;
        Stars = stars;
        Reviews = reviews;
        Type = type;
        Location = location;
        Info = info;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPriceTag() {
        return PriceTag;
    }

    public void setPriceTag(String priceTag) {
        PriceTag = priceTag;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getStars() {
        return Stars;
    }

    public void setStars(String stars) {
        Stars = stars;
    }

    public String getReviews() {
        return Reviews;
    }

    public void setReviews(String reviews) {
        Reviews = reviews;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }
}
