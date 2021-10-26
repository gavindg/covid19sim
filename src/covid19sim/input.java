package covid19sim;

import java.util.Scanner;

public class input {
	// intializes variables
	static int n;
	static int step;
	static double a;
	static double b;
	static Scanner scnr = controller.scnr;
	
	public static void validate() {
		// Determines if the number of people is a perfect square.
		System.out.println("Enter number of people: ");
		n = scnr.nextInt();
		while (Math.pow((int) Math.sqrt(n), 2) != n) {
			System.out.println("Error value must be an integer perfect square.");
			System.out.println("Enter number of people: ");
			n = scnr.nextInt();
			}
		// Makes sure the infection rate is between 0 and 1.
		System.out.println("Enter Infection Rate: ");
		a = scnr.nextDouble();
		while (a < 0 || a > 1) {
			System.out.println("Error value must be an a value between 0 and 1.");
			System.out.println("Enter Infection Rate: ");
			a = scnr.nextDouble();
			}
		// Makes sure the recovery rate is between 0 and 1.
		System.out.println("Enter Recovery Rate: ");
		b = scnr.nextDouble();
		while (b < 0 || b > 1) {
			System.out.println("Error value must be an a value between 0 and 1.");
			System.out.println("Enter Recovery Rate: ");
			b = scnr.nextDouble();
			}
		//Asks for number of steps.
		System.out.println("Enter number of steps: ");
		step = scnr.nextInt();
		// Returns the variables to controller file.
		controller.n = n;
		controller.a = a;
		controller.b = b;
		controller.step = step;
		
	}
}
