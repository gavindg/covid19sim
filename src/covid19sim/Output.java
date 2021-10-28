package covid19sim;

import java.io.*;
import java.util.Scanner;

// The Output Class handles most of the
// the text seen in the console, and is
// also responsible for displaying step
// results.

public class Output {
	
	public static String filepath = Controller.filepath;
	static Scanner scnr = Controller.scnr;
	
	// This function outputs the result of the simulation at time step `timeStep`.
	public static void output(int step) {
		try {
			FileReader fr = new FileReader(filepath+step+".txt");
			BufferedReader br = new BufferedReader(fr);
			System.out.println("Step #"+step);
			System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
			
			String s;
			while((s = br.readLine()) != null) {
				System.out.println(s);
			}
			
			br.close();
		} catch (Exception E) {
			System.out.println("ERROR");
		}
	}
	
	/* This function scans the file generated of the given time step `step` and prints information 
	 * about it to the console, such as the ratio of infected people and whether the whole of the 
	 * population ended as one status. */
	public static void scan(int step) {
		try {
			File inputFile = new File(filepath+step+".txt");
			Scanner scanFile = new Scanner(inputFile);
			char x;
			int inf = 0;
			int rec = 0;
			int sus = 0;
			while (scanFile.hasNext()) {
				x = scanFile.next().charAt(0);
				if (x == 's')
					sus++;
				if (x == 'r')
					rec++;
				if (x == 'i')
					inf++;
			}
			System.out.printf("Infected: %d Recovered: %d Susceptible: %d %n", inf, rec, sus);
			System.out.println("Ratio of Infected to Population: "+((double)inf)/((double)Controller.population));
			if (inf == Controller.population)
				System.out.println("The whole population is Infected.");
			if (sus == Controller.population)
				System.out.println("The whole population is Susceptible.");
			if (rec == Controller.population)
				System.out.println("The whole population is Recovered.");
			scanFile.close();
		} catch (Exception E) {
			
		}
	}
	
	/* This function allows the user to have any step in the simulation printed to the console.
	 * It is called by `Controller.java` after the simulation is finished. */
	public static void log() {
		System.out.println("There are "+Controller.step+" steps avalible.");
		System.out.println("Enter step# you would like to see: ");
		int logNum = scnr.nextInt();
		while (logNum > Controller.step || logNum <= 0) {
			System.out.println("Error value must be between 1 and "+ Controller.step +".");
			System.out.println("Enter step# you would like to see: ");
			logNum = scnr.nextInt();
			}
		Output.output(logNum);
		Output.scan(logNum);
		Output.log();
	}
	
	// This function outputs the intro credits of the program.
	public static void intro() {
		System.out.println("••••••••••••••••••••••••••••••••••••••••••••");
		System.out.println("•	Team Number 9 Presents		   •");
		System.out.println("•	COVID-19 Simulator		   •");
		System.out.println("•	the Game!!!!!			   •");
		System.out.println("•	By: Jarod, Gavin, Rosalu,	   •");
		System.out.println("•	and Richard			   •");
		System.out.println("••••••••••••••••••••••••••••••••••••••••••••");
	}

}