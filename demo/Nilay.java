package demo;

import ecomm.*;
import ecomm.Globals.Category;

import java.util.*;

public class Nilay extends Seller {
    private ArrayList<Mobile> mobiles = new ArrayList<Mobile>();
    private ArrayList<Book> books = new ArrayList<Book>();

    public Nilay(String ID) {
        super(ID);
        addProduct();
    }

    public void addProduct() {
        Book b1 = new Book(Category.Book, "b1", "Nilay-b1", 30, 5);
        Book b2 = new Book(Category.Book, "b2", "Nilay-b2", 20, 4);
        Mobile m1 = new Mobile(Category.Mobile, "m1", "Nilay-m1", 25, 9);
        Mobile m2 = new Mobile(Category.Mobile, "m2", "Nilay-m2", 35, 6);

        books.add(b1);
        books.add(b2);
        mobiles.add(m1);
        mobiles.add(m2);
    }

    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);
    };

    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        ArrayList<Product> temp = null;
        Globals g = new Globals();
        if (g.getCategoryName(whichOne).equals("Mobile")) {
            temp = new ArrayList<Product>(mobiles);
        } else if (g.getCategoryName(whichOne).equals("Book")) {
            temp = new ArrayList<Product>(books);
        }
        return temp;
    };

    public boolean buyProduct(String productID, int quantity) {
        for (int i = 0; i < mobiles.size(); i++) {
            if (mobiles.get(i).getProductID().equals(productID)) {
                if (mobiles.get(i).getQuantity() >= quantity) {
                    mobiles.get(i).changeQty(quantity);
                    return true;
                }
            }
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getProductID().equals(productID)) {
                if (books.get(i).getQuantity() >= quantity) {
                    books.get(i).changeQty(quantity);
                    return true;
                }
            }
        }
        return false;
    };
}
