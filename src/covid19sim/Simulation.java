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
	public static int n = Controller.n;
	
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
			System.out.println(infected+" is infected.");
			for (int i = 1; i <= n; i++) {
				if (i % (Math.sqrt(n)) != 0 && i != infected) {
					bw.write(0+"\t");
				} else {
					if (i == infected)
					{
						if (i % (Math.sqrt(n)) != 0 ) {
							bw.write(1+"\t");
						}else {
							bw.write(1+"\n");	
						}
					}else {
						bw.write(0+"\n");	
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
		int status = 0;
		int infNum = 0;
		try {
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
			for(int i=1;i<=sqr*sqr;i++) {
				status = inputFile.nextInt();
				if (i == pos-sqr && status == 1)
					++infNum;
				if (i == pos+sqr && status == 1)
					++infNum;
				if (i == pos-1 && status == 1 && pos%sqr!=1)
					++infNum;
				if (i == pos+1 && status == 1 && pos%sqr!=0)
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
				PrintWriter pw = new PrintWriter(file);
				while(inputFile.hasNextInt()) {
					int infectedNeighbor = 0;
					int i = inputFile.nextInt();
					if (i == 0) //if individual is susceptible
					{
						infectedNeighbor = check(counter, num, prevFile);
						double prob = infectedNeighbor * infectionRate;
						
						if (Probability.getTrueFalse(prob)){
							pw.print(1 + "\t");
						}
						else {
							pw.print(0 + "\t");
						}
					}
					else if (i == 1) { //if individual is infected
						if (Probability.getTrueFalse(recoveryRate)) {
							pw.print(2 + "\t");
						}
						else {
							pw.print(1 + "\t");
						}
					}else if (i==2) {
						pw.print(2 + "\t");
					}
						
						
					if (counter % num == 0)
						pw.println();
					counter++;
				}
				counter = 1;
				pw.close();
				inputFile.close();
			}	
			catch (IOException ioe) {
				System.out.println("Error");
			}
		}
	}
}
