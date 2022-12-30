package demo;

import ecomm.*;

public class Mobile extends Product {
    private String name, ProductID;
    private int Qty;
    private float price;
    private Globals.Category category;

    public Globals.Category getCategory(){
        return category;
    }
    public String getName(){
        return name;
    }
    public String getProductID(){
        return ProductID;
    }
    public float getPrice(){
        return price;
    }
    public int getQuantity(){
        return Qty;
    }
    public void changeQty(int quantity){
        this.Qty-=quantity;
    }
}
