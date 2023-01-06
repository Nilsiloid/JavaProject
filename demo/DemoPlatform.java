package demo;

import java.util.*;
import java.io.*;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;
import ecomm.Globals.Category;

public class DemoPlatform extends Platform {
	private ArrayList<Seller> sellers = new ArrayList<Seller>(); // ArrayList of sellers

	public DemoPlatform() {
		// Following is done so that whenever we execute the code, both the files are created and emptied 
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("PortalToPlatform.txt")); // fileWriter for PortalToPlatform.txt
			bw.write(""); // Done to empty the file
			bw.close(); // closing BufferedWriter

			BufferedWriter bwTemp = new BufferedWriter(new FileWriter("PlatformToPortal.txt"));  // fileWriter for PlatformToPortal.txt
			bwTemp.write(""); // Done to empty the file
			bwTemp.close();// closing BufferedWriter
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addSeller(Seller aSeller) { // Method to add Seller to platform i.e add seller to arrayList of sellers
		sellers.add(aSeller);
		return false;
	}

	@Override
	public void processRequests() {
		File tempFile = new File("PortalToPlatform.txt"); // Creating new File object
		if (tempFile.exists()) { // if PortalToPlatform.txt exists

			try {

				BufferedReader br = new BufferedReader(new FileReader("PortalToPlatform.txt")); // Reader for PortalToPlatform.txt
				BufferedWriter bw = new BufferedWriter(new FileWriter("PlatformToPortal.txt", true)); // Writer for  PlatformToPortal.txt
				String line; // line denotes the variable into which each command from PortalToPlatform will be read and stored into
				Globals g = new Globals(); // Globals object

				while ((line = br.readLine()) != null) {
					String[] order = line.split(" "); // Array order stores the string of command using split

					String output = order[0] + " " + order[1] + " "; // Portal ID + Request ID
					boolean flg2 = true; // Added so that non-existent product can't be bought (Used in "Buy" else if statement)

					if (order[2].equals("Start")) 
					{
						for (Category c: Category.values()){
							output += g.getCategoryName(c) + " ";
						}
						// output += g.getCategoryName(Category.Mobile) + " " + g.getCategoryName(Category.Book); 
						bw.write(output + "\n");
					} 

					else if (order[2].equals("List")) 
					{
						for (int i = 0; i < sellers.size(); i++) 
						{
							ArrayList<Product> temp = new ArrayList<Product>();
							// if(order[3].equals("Book")){
							// 	temp=sellers.get(i).findProducts(Category.Book);
							// }
							// else if(order[3].equals("Mobile")){
							// 	temp=sellers.get(i).findProducts(Category.Mobile);
							// }

							for (Category c: Category.values()){ 
								if (order[3].equals(g.getCategoryName(c))){
									temp = sellers.get(i).findProducts(c);
								}
							} 

							for (int j = 0; j < temp.size(); j++) {
								String product = output + temp.get(j).getName() + " " + temp.get(j).getProductID() + " "
										+ temp.get(j).getPrice() + " " + temp.get(j).getQuantity();
								bw.write(product + "\n");
							}
						}
						
					} 
					else if (order[2].equals("Buy")) 
					{
						for (int i = 0; i < sellers.size(); i++) 
						{
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
