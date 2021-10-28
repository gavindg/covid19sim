package covid19sim;

import java.io.*;
import java.util.Scanner;

// This Simulation Class Handles all the code
// for the actual simulation code such as file 
// writing, positional calculations, and creating
// the data for each time step.

public class Simulation {
	//Calls variables from Controller Class
	public static String filepath = Controller.filepath;
	public static int step = Controller.step;
	public static int n = Controller.population;
	
	/* This function is used to clear any files from a previous simulation that may be left over.
	 * It is called by `Controller.java` just before the simulation begins. */
	public static void clearFiles() {
        int counter = 1;
        File dataFile;
        while(true) {
            dataFile = new File(Controller.filepath+counter+".txt");
            if(dataFile.delete()) {
                counter++;
            }
            else {
                System.out.println("Finished clearing data files.");
                break;
            }
        }
    }
	
	/* This function generates the initial board of tiles.  It will also output the initial board 
	 * to the console by calling `output()` from `Output.java`. */
	public static void firstFile() {
		try {
			FileWriter fw = new FileWriter(filepath+"1.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			int infected = Probability.randomInt(n);
			System.out.println("Character #"+infected+" has been randomly infected.");
			for (int i = 1; i <= n; i++) {
				if (i % (Math.sqrt(n)) != 0 && i != infected) {
					bw.write("s\t");
				} else {
					if (i == infected)
					{
						if (i % (Math.sqrt(n)) != 0 ) {
							bw.write("i\t");
						}else {
							bw.write("i\n");	
						}
					}else {
						bw.write("s\n");	
					}
				}
			}
			bw.flush();
			bw.close();
			
			Output.output(1);
			
		} catch (Exception E) {
			System.out.println("ERROR");
		}
	}
	
	/* This function scans through the file `fileName` and checks all of the neighboring tiles to
	 * position `pos` to see if they are infected. It returns the number of adjacent infected tiles. */
	public static int check(int pos, int sqr, String fileName) {
		char status;
		int infNum = 0;
		try {
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
			for(int i=1;i<=sqr*sqr;i++) {
				status = inputFile.next().charAt(0);
				if (i == pos-sqr && status == 'i')
					++infNum;
				if (i == pos+sqr && status == 'i')
					++infNum;
				if (i == pos-1 && status == 'i' && pos%sqr!=1)
					++infNum;
				if (i == pos+1 && status == 'i' && pos%sqr!=0)
					++infNum;
			}
		
				inputFile.close();
		
		} catch (Exception E) {
			System.out.println("Error 2");
		}
		return infNum;
	}
	
	/* This function controls the main flow of the simulation. It will finish after generating
	 * `timeStepCount` number of files (including the initial file). */
	public static void simulate(int timeStepCount, int numOfIndividuals, double infectionRate, double recoveryRate) {
		int num = (int)Math.sqrt(numOfIndividuals);
		int counter = 1;
		
		// main loop
		for (int p = 1; p < timeStepCount; p++) {
			try {
				// initializing file I/O
				File file = new File(filepath + (p + 1) + ".txt");
				String prevFile = filepath + p + ".txt";
				File previousFile = new File(prevFile);
				Scanner inputFile = new Scanner(previousFile);
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				
				// this loop iterates through the previous file and generate the next.
				while(inputFile.hasNext()) {
					int infectedNeighbor = 0;
					char i = inputFile.next().charAt(0);
					if (i == 's') //if individual is susceptible, test if they are infected
					{
						infectedNeighbor = check(counter, num, prevFile);
						double prob = infectedNeighbor * infectionRate;
						
						if (Probability.getTrueFalse(prob)){
							bw.write("i\t");
						}
						else {
							bw.write("s\t");
						}
					}
					else if (i == 'i') { // if individual is infected, test if they recover
						if (Probability.getTrueFalse(recoveryRate)) {
							bw.write("r\t");
						}
						else {
							bw.write("i\t");
						}
					}else if (i=='r') { // if the individual is recovered, nothing happens
						bw.write("r\t");
					}
					
					// writes a newline at the end of a row
					if (counter % num == 0)
						bw.write("\n");
					counter++;
				}
				counter = 1;
				bw.close();
				inputFile.close();
			}	
			catch (IOException ioe) {
				System.out.println("Error");
			}
		}
	}
}
