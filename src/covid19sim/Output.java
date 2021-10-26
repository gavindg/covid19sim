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
	
	public static void log() {
		System.out.println("There are "+Controller.step+" steps avalible.");
		System.out.println("Enter step# you would like to see: ");
		int logNum = scnr.nextInt();
		while (logNum > Controller.step || logNum <= 0) {
			System.out.println("Error value must be between 1 and "+ Controller.step +".");
			System.out.println("Enter test# you would like to see: ");
			logNum = scnr.nextInt();
			}
		Output.output(logNum);
		Output.log();
	}
	
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