package demo;

import ecomm.*;
import java.util.*;

public class Nilay extends Seller {
    private ArrayList<Mobile> mobiles = new ArrayList<Mobile>();
    private ArrayList<Book> books = new ArrayList<Book>();

    Nilay(String ID) {
        super(ID);
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
