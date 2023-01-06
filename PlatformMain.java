import java.util.Scanner;

import demo.*;
import ecomm.*;

public class PlatformMain {

	public static void main(String[] args) {

		Platform pf = new DemoPlatform(); // creating object of DemoPlatform class to invoke function processRequests()
											// when we receive "Check" input.

		Scanner sc = new Scanner(System.in); // Scanner class object sc for user input.

		Seller s1 = new Nilay("Nilay"); // Creating seller objects s1, s2 and s3 corresponding to sellerIDs - Nilay,
										// Madhav and Shlok.
		s1.addPlatform(pf);

		Seller s2 = new Madhav("Madhav");
		s2.addPlatform(pf);

		Seller s3 = new Shlok("Shlok");
		s3.addPlatform(pf);

		while (true) {
			String input = sc.next();
			if (input.equals("Check")) { // We read from the commandline and if the input is "Check", we invoke the
											// processRequests() function.
				pf.processRequests();
			} else if (input.equals("End")) { // Terminating condition for the program. If user input is "End", we end
												// the execution of the code.
				break;
			}
		}
		sc.close();
	}
}
