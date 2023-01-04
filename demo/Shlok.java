package demo;

import ecomm.*;
import ecomm.Globals.Category;
import java.util.*;

public class Shlok extends Seller {
    private ArrayList<Mobile> mobiles = new ArrayList<Mobile>();
    private ArrayList<Book> books = new ArrayList<Book>();

    public Shlok(String ID) {
        super(ID);
        addProduct();
    }

    public void addProduct() {
        Book b5 = new Book(Category.Book, "b5", "Shlok-b5", 30, 5);
        Book b6 = new Book(Category.Book, "b6", "Shlok-b6", 20, 4);
        Mobile m5 = new Mobile(Category.Mobile, "m5", "Shlok-m5", 25, 9);
        Mobile m6 = new Mobile(Category.Mobile, "m6", "Shlok-m6", 35, 6);

        books.add(b5);
        books.add(b6);
        mobiles.add(m5);
        mobiles.add(m6);
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
