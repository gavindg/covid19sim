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
	
	public static void simulate(int timeStepCount, int numOfIndividuals, double infectionRate, double recoveryRate) {
		int num = (int)Math.sqrt(numOfIndividuals);
		int counter = 1;
		for (int p = 1; p < timeStepCount; p++) {
			try {
				File file = new File(filepath + (p + 1) + ".txt");
				String prevFile = filepath + p + ".txt";
				File previousFile = new File(prevFile);
				Scanner inputFile = new Scanner(previousFile);
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				while(inputFile.hasNext()) {
					int infectedNeighbor = 0;
					char i = inputFile.next().charAt(0);
					if (i == 's') //if individual is susceptible
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
					else if (i == 'i') { //if individual is infected
						if (Probability.getTrueFalse(recoveryRate)) {
							bw.write("r\t");
						}
						else {
							bw.write("i\t");
						}
					}else if (i=='r') {
						bw.write("r\t");
					}
						
						
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
