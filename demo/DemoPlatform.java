package demo;

import java.util.*;
import java.io.*;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;
import ecomm.Globals.Category;

public class DemoPlatform extends Platform {
	private ArrayList<Seller> sellers = new ArrayList<Seller>();

	public DemoPlatform() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("PortalToPlatform.txt"));
			bw.write("");
			bw.close();

			BufferedWriter bwTemp = new BufferedWriter(new FileWriter("PlatformToPortal.txt"));
			bwTemp.write("");
			bwTemp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addSeller(Seller aSeller) {
		sellers.add(aSeller);
		return false;
	}

	@Override
	public void processRequests() {
		File tempFile = new File("PortalToPlatform.txt");
		if (tempFile.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader("PortalToPlatform.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("PlatformToPortal.txt", true));
				String line;
				Globals g = new Globals();
				while ((line = br.readLine()) != null) {
					String[] order = line.split(" ");

					String output = order[0] + " " + order[1] + " "; // Portal ID + Request ID
					boolean flg2 = true; // Added so that non-existent product can't be bought

					if (order[2].equals("Start")) {
						output += g.getCategoryName(Category.Mobile) + " " + g.getCategoryName(Category.Book);
						bw.write(output + "\n");
					} else if (order[2].equals("List")) {
						for (int i = 0; i < sellers.size(); i++) {
							ArrayList<Product> temp = new ArrayList<Product>();
							if(order[3].equals("Book")){
								temp=sellers.get(i).findProducts(Category.Book);
							}
							else if(order[3].equals("Mobile")){
								temp=sellers.get(i).findProducts(Category.Mobile);
							}

							for (int j = 0; j < temp.size(); j++) {
								String product = output + temp.get(j).getName() + " " + temp.get(j).getProductID() + " "
										+ temp.get(j).getPrice() + " " + temp.get(j).getQuantity();
								bw.write(product + "\n");
							}
						}
						
					} else if (order[2].equals("Buy")) {
						for (int i = 0; i < sellers.size(); i++) {
							if (order[3].contains(sellers.get(i).getID())) {
								flg2 = false;
								if (sellers.get(i).buyProduct(order[3], Integer.valueOf(order[4])) == true) {
									output += "Success";
								} else {
									output += "Failure";
								}
							}
						}
						if (flg2 == false){
							bw.write(output + "\n");
						}
						else{
							bw.write(output + "Failure\n");
						}
					}
				}
				br.close();
				bw.close();
				BufferedWriter bw1 = new BufferedWriter(new FileWriter("PortalToPlatform.txt"));
				bw1.write("");
				bw1.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
