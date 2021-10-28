package covid19sim;

import java.util.Scanner;

// The Input Class takes in all the inputs
// and then returns them back to the
// Controller class.

public class Input {
	
	// initializes variables
	static int n; // number of tiles (must be perfect square)
	static int step; // number of time steps to be simulated
	static double a; // infection rate
	static double b; // recovery rate
	static Scanner scnr = Controller.scnr;
	
	
	/* This function takes input for the parameters of the simulation and confirms that they
	 * are valid. */
	public static void validate() {
		// Determines if the number of people is a perfect square.
		System.out.println("*Caution numbers greater then 10000 take much longer to generate.");
		System.out.println("Enter number of people: ");
		n = scnr.nextInt();
		while (Math.pow((int) Math.sqrt(n), 2) != n) {
			System.out.println("Error value must be an integer and perfect square.");
			System.out.println("Enter number of people: ");
			n = scnr.nextInt();
			}
		// Makes sure the infection rate is between 0 and 1.
		System.out.println("Enter Infection Rate: ");
		a = scnr.nextDouble();
		while (a < 0 || a > 1) {
			System.out.println("Error value must be a value between 0 and 1.");
			System.out.println("Enter Infection Rate: ");
			a = scnr.nextDouble();
			}
		// Makes sure the recovery rate is between 0 and 1.
		System.out.println("Enter Recovery Rate: ");
		b = scnr.nextDouble();
		while (b < 0 || b > 1) {
			System.out.println("Error value must be a value between 0 and 1.");
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
		Controller.population = n;
		Controller.infRate = a;
		Controller.recRate = b;
		Controller.step = step;
		
	}
}
