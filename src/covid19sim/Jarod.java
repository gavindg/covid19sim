package covid19sim;
import java.util.Scanner;

public class Jarod {
	
	static Scanner scnr = new Scanner(System.in);
	int status;
	static int n;
	static double a;
	static double b;
	
	
	public static void main(String [] args) {
	
		input();
		
	}
	public static void input() {
		System.out.println("Enter numer of people: ");
		n = scnr.nextInt();
		while (Math.pow((int) Math.sqrt(n), 2) != n) {
			System.out.println("Error value must be an integer perfect square.");
			System.out.println("Enter numer of people: ");
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
		b = scnr.nextInt();
		while (b < 0 || b > 1) {
			System.out.println("Error value must be an a value between 0 and 1.");
			System.out.println("Enter Recovery Rate: ");
			b = scnr.nextDouble();
			}
	}

}
