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
		// Following is done so that whenever we execute the code, both the files are
		// created and emptied
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("PortalToPlatform.txt")); // fileWriter for
																							// PortalToPlatform.txt
			bw.write(""); // Overwrites an empty string into the file, used to empty the file contents
			bw.close(); // closing BufferedWriter

			BufferedWriter bwTemp = new BufferedWriter(new FileWriter("PlatformToPortal.txt")); // fileWriter for
																								// PlatformToPortal.txt
			bwTemp.write(""); // Overwrites an empty string into the file, used to empty the file contents
			bwTemp.close();// closing BufferedWriter
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	// Method to add Seller to platform i.e add seller to arrayList of sellers
	public boolean addSeller(Seller aSeller) {
		sellers.add(aSeller);
		return false;
	}

	@Override
	// Method to process the commands given by user.
	public void processRequests() {
		File tempFile = new File("PortalToPlatform.txt"); // Creating new File object
		if (tempFile.exists()) { // condition to check if PortalToPlatform.txt exists
			try {
				BufferedReader br = new BufferedReader(new FileReader("PortalToPlatform.txt"));
				// Reader for PortalToPlatform.txt
				BufferedWriter bw = new BufferedWriter(new FileWriter("PlatformToPortal.txt", true));
				// Writer for PlatformToPortal.txt
				String line; // line denotes the variable into which each command from PortalToPlatform will
								// be read and stored into
				Globals g = new Globals(); // Globals object

				while ((line = br.readLine()) != null) {
					String[] order = line.split(" "); // Array order stores the string of command using split

					String output = order[0] + " " + order[1] + " "; // Portal ID + Request ID
					boolean flg2 = true; // Added so that non-existent product can't be bought (Used in the else if
											// statement of "Buy")
					if (order[2].equals("Start")) { // Writes the List of Categories of products available in the file
													// PlatformToPortal.txt
						for (Category c : Category.values()) { // iterating through category
							output += g.getCategoryName(c) + " ";
						}
						bw.write(output + "\n"); // final output
					}

					else if (order[2].equals("List")) { // Lists the products inn the specified category. Uses
														// findProducts() function from the seller classes(eg.
														// Nilay.java) to implement the same.

						for (int i = 0; i < sellers.size(); i++) { // iterating through sellers	
							ArrayList<Product> temp = new ArrayList<Product>();

							for (Category c : Category.values()) {
								if (order[3].equals(g.getCategoryName(c))) {
									// if the category exists, temp is an arraylist used to store the products in
									// that category.
									temp = sellers.get(i).findProducts(c);
								}
							}

							// for loop to write the details of each such product stored in temp into
							// PlatformToPortal.txt
							for (int j = 0; j < temp.size(); j++) {
								String product = output + temp.get(j).getName() + " " + temp.get(j).getProductID() + " "
										+ temp.get(j).getPrice() + " " + temp.get(j).getQuantity();
								bw.write(product + "\n");
							}
						}

					} else if (order[2].equals("Buy")) { // Function to process the "Buy" command
						for (int i = 0; i < sellers.size(); i++) {
							if (order[3].contains(sellers.get(i).getID())) { // finds out from which seller the user is
																				// trying to buy the product
								flg2 = false;
								// uses buyProduct() function in seller class(eg. Nilay.java) to check for
								// possibility of the transaction
								if (sellers.get(i).buyProduct(order[3], Integer.valueOf(order[4])) == true) {
									output += "Success";
								} else {
									output += "Failure";
								}
							}
						}
						if (flg2 == false) {
							bw.write(output + "\n");
						} else { // if the product doesn't exist, then we print Failure.
							bw.write(output + "Failure\n");
						}
					}
				}
				br.close(); // closing BufferedReader
				bw.close(); // closing BufferedWriter
				BufferedWriter bw1 = new BufferedWriter(new FileWriter("PortalToPlatform.txt"));
				bw1.write(""); // Overwrites an empty string into the file, used to empty the file contents
				bw1.close(); // closing BufferedWriter

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
