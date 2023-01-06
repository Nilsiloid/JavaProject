package demo;

import ecomm.*;
import ecomm.Globals.Category;
import java.util.*;

public class Madhav extends Seller { // IMT2021009 seller
    private ArrayList<Mobile> mobiles = new ArrayList<Mobile>(); // Arraylist to store Mobile Products
    private ArrayList<Book> books = new ArrayList<Book>(); // Arraylist to store Book Products

    public Madhav(String ID) { // Constructor
        super(ID); // Calling constructor of class Seller
        addProduct(); // Calling addProduct method
    }

    public void addProduct() { // Method to set the products that each seller sells
        Book b3 = new Book(Category.Book, "b3", "Madhav-b3", 349, 53); // Book product
        Book b4 = new Book(Category.Book, "b4", "Madhav-b4", 100, 25); // Book product
        Mobile m3 = new Mobile(Category.Mobile, "m3", "Madhav-m3", 10000, 12); // Mobile product
        Mobile m4 = new Mobile(Category.Mobile, "m4", "Madhav-m4", 90000, 45); // Mobile Product

        books.add(b3); // Adding to books ArrayList
        books.add(b4); // Adding to books ArrayList
        mobiles.add(m3); // Adding to mobiles ArrayList
        mobiles.add(m4); // Adding to mobiles ArrayList
    }

    public void addPlatform(Platform thePlatform) { // Method to add seller to the platform
        thePlatform.addSeller(this); // Calling addSeller method of Platform class
    };

    public ArrayList<Product> findProducts(Globals.Category whichOne) { // findProducts Method returns ArrayList of
                                                                        // Products of appropriate Category type
        ArrayList<Product> temp = null;
        Globals g = new Globals(); // Globals object
        if (g.getCategoryName(whichOne).equals("Mobile")) { // If category parameter is Mobile
            temp = new ArrayList<Product>(mobiles); // temp will be a copy of mobiles arrayList
        } else if (g.getCategoryName(whichOne).equals("Book")) { // If category parameter is Book
            temp = new ArrayList<Product>(books); // temp will be a copy of books arrayList
        }
        return temp; // returning the appropriate arrayList
    };

    public boolean buyProduct(String productID, int quantity) { // buyProduct Method to buy a product from seller and
                                                                // return boolean
        for (int i = 0; i < mobiles.size(); i++) { // iterating through mobiles arrayList
            if (mobiles.get(i).getProductID().equals(productID)) { // if productID matches
                if (mobiles.get(i).getQuantity() >= quantity) {
                    mobiles.get(i).changeQty(quantity); // return true only if the amount of quantity required is
                                                        // actually available and also change the quantity
                    return true;
                }
            }
        }
        for (int i = 0; i < books.size(); i++) { // iterating through books arrayList
            if (books.get(i).getProductID().equals(productID)) { // if productID matches
                if (books.get(i).getQuantity() >= quantity) {
                    books.get(i).changeQty(quantity); // return true only if the amount of quantity required is actually
                                                      // available and also change the quantity
                    return true;
                }
            }
        }
        return false; // default returning false if buying specified product is not possible
    };
}
