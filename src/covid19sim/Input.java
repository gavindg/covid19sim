package covid19sim;

import java.util.Scanner;

// The Input Class takes in all the inputs
// and then returns them back to the
// Controller class.

public class Input {
	// intializes variables
	static int n;
	static int step;
	static double a;
	static double b;
	static Scanner scnr = Controller.scnr;
	
	public static void validate() {
		// Determines if the number of people is a perfect square.
		System.out.println("*caution larger numbers take much longer*");
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
		while (step <= 0) {
			System.out.println("Error value must be greater then 0.");
			System.out.println("Enter number of steps: ");
			step = scnr.nextInt();
			}
		// Returns the variables to controller file.
		Controller.n = n;
		Controller.a = a;
		Controller.b = b;
		Controller.step = step;
		
	}
}
