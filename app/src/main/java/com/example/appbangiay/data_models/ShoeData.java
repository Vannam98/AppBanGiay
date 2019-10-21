package com.example.appbangiay.data_models;

public class ShoeData {

    private String itemName;
    private String itemDescription;
    private String itemSize;
    private String itemSoluong;
    private String itemPrice;
    private String itemImage;

    public ShoeData() {
    }

    public ShoeData(String itemName, String itemDescription, String itemSize, String itemSoluong, String itemPrice, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemSize = itemSize;
        this.itemSoluong = itemSoluong;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemSize() {
        return itemSize;
    }

    public String getItemSoluong() {
        return itemSoluong;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }
}
