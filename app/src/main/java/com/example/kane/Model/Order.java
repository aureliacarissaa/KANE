package com.example.kane.Model;

public class Order {
    private String ProductID;
    private String ProductName;
    private String Qty;
    private String Price;

    public Order() {

    }

    public Order(String productID, String productName, String qty, String price) {
        ProductID = productID;
        ProductName = productName;
        Qty = qty;
        Price = price;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
