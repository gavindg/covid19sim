package covid19sim;

import java.util.Scanner;

// The controller Class is the brains of the
// operation. It initializes universal variables
// and calls functions from each class in order
// to produce the completed program.

public class Controller {
	public static Scanner scnr = new Scanner(System.in);
	
	public static String filepath = "EpidemicData";
	public static int status;
	public static int population;
	public static int step;
	public static double infRate; 
	public static double recRate;
	
	public static void main(String[] args) {
		
		Output.intro();
		Input.validate();
		Simulation.clearFiles();
		Simulation.firstFile();
		Simulation.simulate(step, population, infRate, recRate);
		Output.output(step);
		Output.log();
	}
}
