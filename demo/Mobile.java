package demo;

import ecomm.*;

public class Mobile extends Product {
    private String name, ProductID; // Name and ProductID
    private int Qty; // Quantity
    private float price; // Price
    private Globals.Category category; // Category of Product

    public Mobile(Globals.Category category, String name, String ProdID, float price, int qty){ // Constructor
        this.ProductID=ProdID;
        this.category = category;
        this.name = name;
        this.price = price;
        this.Qty = qty;
    }

    public Globals.Category getCategory(){ // Getter for category
        return category;
    }
    public String getName(){ // Getter for name
        return name;
    }
    public String getProductID(){ // Getter for productID
        return ProductID;
    }
    public float getPrice(){ // Getter for price
        return price;
    }
    public int getQuantity(){ // Getter for Quantity
        return Qty;
    }
    public void changeQty(int quantity){ // Method to reduce quantity available of the product
        this.Qty-=quantity;
    }
}
