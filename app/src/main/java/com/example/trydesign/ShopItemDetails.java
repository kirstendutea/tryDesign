package com.example.trydesign;

import java.util.ArrayList;

public class ShopItemDetails {
    //has the detials of a shop item and contains the getters and setters to access the items

    public static ArrayList<ShopItemDetails> items = new ArrayList<>();
    public int itemID;
    public int imageID;
    public String itemName;
    public String itemDescription;
    public int itemPrice;
    public int itemAmount;
    public static int idCounter = 0;
    private int id;

    //getters
    public int getId() {
        return itemID;
    }
    public String getName() {
        return itemName;
    }
    public int getImageID() {
        return imageID;
    }
    public String getDescription() {
        return itemDescription;
    }
    public int getPrice() {
        return itemPrice;
    }
    public int getAmount(){
        return itemAmount;
    }

    //setters
    public void setId(int itemID){
        this.itemID = itemID;
    }
    public void setName(String itemName){
        this.itemName = itemName;
    }
    public void setImageID(int imageID){
        this.imageID = imageID;
    }
    public void setDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }
    public void setPrice(int itemPrice){
        this.itemPrice = itemPrice;
    }
    public void setAmount(int itemAmount){
        this.itemAmount = itemAmount;
    }

    public ShopItemDetails(String itemName, int imageID, int itemPrice, String itemDescription, int itemAmount) {
        //constructor method with info needed for the item properties
        this.itemName = itemName;
        this.imageID = imageID;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemAmount = itemAmount;
        this.itemID = idCounter++;
    }

    public static void init() {
        // initialises the items list and adds items to it
        items.add(new ShopItemDetails("popp0", R.drawable.poppy, 0, "a popp0", 0));
        items.add(new ShopItemDetails("popp1", R.drawable.poppy, 10, "a popp1", 10));
        items.add(new ShopItemDetails("popp2", R.drawable.poppy, 20, "a popp2", 20));
        items.add(new ShopItemDetails("popp3", R.drawable.poppy, 30, "a popp3", 30));
    }

    public static ShopItemDetails findItemById(int id) {
        //Returns the information for an item when given the id of the item
        for (int i = 0; i < ShopItemDetails.items.size(); i++) {
            if (items.get(i).itemID == id){
                return items.get(i);
            }
        }
        return null;
    }
}