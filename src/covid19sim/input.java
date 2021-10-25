package covid19sim;

import java.util.Scanner;

public class input {
	static int n = controller.n;
	static double a = controller.a;
	static double b = controller.b;
	static Scanner scnr = controller.scnr;
	
	public static void validate() {
		System.out.println("Enter number of people: ");
		n = scnr.nextInt();
		while (Math.pow((int) Math.sqrt(n), 2) != n) {
			System.out.println("Error value must be an integer perfect square.");
			System.out.println("Enter number of people: ");
			n = scnr.nextInt();
			}
		
		System.out.println("Enter Infection Rate: ");
		a = scnr.nextDouble();
		while (a < 0 || a > 1) {
			System.out.println("Error value must be an a value between 0 and 1.");
			System.out.println("Enter Infection Rate: ");
			a = scnr.nextDouble();
			}
		
		System.out.println("Enter Recovery Rate: ");
		b = scnr.nextDouble();
		while (b < 0 || b > 1) {
			System.out.println("Error value must be an a value between 0 and 1.");
			System.out.println("Enter Recovery Rate: ");
			b = scnr.nextDouble();
			}
		
	}
}
