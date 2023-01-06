package demo;

import ecomm.*;
import ecomm.Globals.Category;
import java.util.*;

public class Shlok extends Seller { // IMT2021103 seller
    private ArrayList<Mobile> mobiles = new ArrayList<Mobile>(); // Arraylist to store Mobile Products
    private ArrayList<Book> books = new ArrayList<Book>(); // Arraylist to store Book Products

    public Shlok(String ID) { // Constructor
        super(ID); // Calling constructor of class Seller
        addProduct(); // Calling addProduct method
    }

    public void addProduct() { // Method to set the products that each seller sells
        Book b5 = new Book(Category.Book, "b5", "Shlok-b5", 299, 15); // Book product
        Book b6 = new Book(Category.Book, "b6", "Shlok-b6", 699, 24); // Book product
        Mobile m5 = new Mobile(Category.Mobile, "m5", "Shlok-m5", 25000, 15); // Mobile product
        Mobile m6 = new Mobile(Category.Mobile, "m6", "Shlok-m6", 54000, 60); // Mobile product

        books.add(b5); // Adding to books ArrayList
        books.add(b6); // Adding to books ArrayList
        mobiles.add(m5); // Adding to mobiles ArrayList
        mobiles.add(m6); // Adding to mobiles ArrayList
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
