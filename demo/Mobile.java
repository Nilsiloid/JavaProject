package demo;

import ecomm.*;

public class Mobile extends Product {
    private String name, ProductID;
    private int Qty;
    private float price;
    private Globals.Category category;

    public Mobile(Globals.Category category, String name, String ProdID, float price, int qty){
        this.ProductID=ProdID;
        this.category = category;
        this.name = name;
        this.price = price;
        this.Qty = qty;
    }

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
