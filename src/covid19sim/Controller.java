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
	
	/* The main function controls the overall flow of the program. It begins with some input validation,
	 * initial file generation and garbage removal of previous files, and then runs the simulation.
	 * Afterwards, it gives the user options to view the results of the simulation. */
	public static void main(String[] args) {
		
		Output.intro();
		Input.validate();
		Simulation.clearFiles();
		Simulation.firstFile();
		Simulation.simulate(step, population, infRate, recRate);
		Output.output(step);
		Output.scan(step);
		Output.log();
	}
}
