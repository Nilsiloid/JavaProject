package demo;

import ecomm.*;
import ecomm.Globals.Category;
import java.util.*;

public class Madhav extends Seller {
    private ArrayList<Mobile> mobiles = new ArrayList<Mobile>();
    private ArrayList<Book> books = new ArrayList<Book>();

    public Madhav(String ID) {
        super(ID);
        addProduct();
    }

    public void addProduct() {
        Book b3 = new Book(Category.Book, "b3", "Madhav-b3", 30, 5);
        Book b4 = new Book(Category.Book, "b4", "Madhav-b4", 20, 4);
        Mobile m3 = new Mobile(Category.Mobile, "m3", "Madhav-m3", 25, 9);
        Mobile m4 = new Mobile(Category.Mobile, "m4", "Madhav-m4", 35, 6);

        books.add(b3);
        books.add(b4);
        mobiles.add(m3);
        mobiles.add(m4);
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
