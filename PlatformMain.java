import java.util.Scanner;

import demo.*;
import ecomm.*;

public class PlatformMain {

	public static void main(String[] args) {

		Platform pf = new DemoPlatform(); // replace with appropriate derived class

		// create a number of sellers (of different sub-types of Seller)
		// Assign a name (sellerID) to each of them.

		// replace each of the XYZ below with the derived class name of one of the
		// team members.

		Scanner sc = new Scanner(System.in);

		Seller s1 = new Nilay("Nilay");
		s1.addPlatform(pf);

		Seller s2 = new Madhav("Madhav");
		s2.addPlatform(pf);

		Seller s3 = new Shlok("Shlok");
		s3.addPlatform(pf);

		/*
		 * // keep reading from System.in
		 * // If "Check" typed in, invoke
		 * pf.processRequests();
		 */

		while (true) {
			String input = sc.next();
			if (input.equals("Check")) {
				pf.processRequests();
			} else if(input.equals("End")) {
				break;
			}
		}
		sc.close();
	}
}
