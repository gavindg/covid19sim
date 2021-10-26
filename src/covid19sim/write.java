package covid19sim;

import java.io.*;
import java.util.Scanner;

public class write {
	//Calls variables from Controller Class
	public static String filepath = controller.filepath;
	public static int step = controller.step;
	public static int n = controller.n;
	
	public static void clearFiles() {
        int counter = 1;
        File dataFile;
        while(true) {
            dataFile = new File(controller.filepath+counter+".txt");
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
			int infected = probability.randomInt(n);
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
			
			output.output(1);
			
		} catch (Exception E) {
			System.out.println("ERROR");
		}
	}
	
	public static void generateFiles() {
		try {
				for (int p = 1; p <= step; p++) {
				File file = new File(filepath + (p) + ".txt");
				if(!file.exists()) {
					file.createNewFile();
					}
				}
		} catch (Exception E) {
			System.out.println("ERROR");
		}
	}
	
	public static boolean checkNeighbor(int position, int sqr, char neighbor, String fileName) {
		try {
			File file = new File(fileName);
			Scanner inputFile = new Scanner(file);
			int status = 0;
			
			if (neighbor == 'T') {
				for(int i = 0; i < (position - sqr); i++) {
					status = inputFile.nextInt();
				}
			}
			
			if (neighbor == 'R') {
				for(int i = 0; i < (position + 1); i++) {
					status = inputFile.nextInt();
				}
			}
			
			if (neighbor == 'B') {
				for(int i = 0; i < (position + sqr); i++) {
					status = inputFile.nextInt();
				}
			}
			
			if (neighbor == 'L') {
				for(int i = 0; i < (position - 1); i++) {
					status = inputFile.nextInt();
				}
			}
			
			inputFile.close();
			
			if (status == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception E) {
			return false; //idk
		}
		
	}
	
	/* public static int check(int pos, int sqr, String fileName) {
		int status = 0;
		int infNum = 0;
		try {
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
		
		for(int i = 1; i <= pos + sqr; i++) {
			status = inputFile.nextInt();
			if (i== pos - sqr && status == 1)
				++infNum;
			if (i== pos - sqr && status == 1)
				++infNum;
			
		}
			
			
			inputFile.close();
		
		} catch (Exception E) {
			System.out.println("Error");
		}
		return status;
	} */
	
	public static void simulation(int timeStepCount, int numOfIndividuals, double infectionRate, double recoveryRate) {
		int num = (int)Math.sqrt(numOfIndividuals);
		int counter = 1;
		int newLine = 0;
		boolean isInfected;
		boolean isRecovered;
		for (int p = 1; p <= timeStepCount; p++) {
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
						if (counter % num == 1) { //if individual is in the first column
							if (counter == 1) { //and if individual is in the top left corner
								//look at right and bottom
								if(checkNeighbor(counter, num, 'R', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'B', prevFile)) {
									infectedNeighbor++; }
							}
							else if (counter == (numOfIndividuals - num)) { //and if individual is in the bottom left corner
								//look at top and right
								if(checkNeighbor(counter, num, 'T', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'R', prevFile)) {
									infectedNeighbor++; }
							}
							else { //1st column boundary individuals
								//look at top, right, and bottom
								if(checkNeighbor(counter, num, 'T', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'R', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'B', prevFile)) {
									infectedNeighbor++; }
							}
						}
						
						else if (counter % num == 0) { //if individual is in the last column
							if (counter == num) { //and if individual is in the top right corner
								//look at left and bottom
								if(checkNeighbor(counter, num, 'L', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'B', prevFile)) {
									infectedNeighbor++; }
							}
							
							else if (counter == numOfIndividuals) { //and if individual is in the bottom right corner
								//look at top and left
								if(checkNeighbor(counter, num, 'T', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'L', prevFile)) {
									infectedNeighbor++; }
							}
							
							else {
								//look at top, left, and bottom
								if(checkNeighbor(counter, num, 'T', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'L', prevFile)) {
									infectedNeighbor++; }
								if(checkNeighbor(counter, num, 'B', prevFile)) {
									infectedNeighbor++; }
							}
						}
						
						else if (counter < num) { //boundary individuals in the top row
							//look at left, bottom, and right
							if(checkNeighbor(counter, num, 'L', prevFile)) {
								infectedNeighbor++; }
							if(checkNeighbor(counter, num, 'B', prevFile)) {
								infectedNeighbor++; }
							if(checkNeighbor(counter, num, 'R', prevFile)) {
								infectedNeighbor++; }
						}
						
						else if ((counter > (numOfIndividuals - num)) && (counter < numOfIndividuals)) { //boundary individuals in the last row
							//look at left, top, and right
							if(checkNeighbor(counter, num, 'L', prevFile)) {
								infectedNeighbor++; }
							if(checkNeighbor(counter, num, 'T', prevFile)) {
								infectedNeighbor++; }
							if(checkNeighbor(counter, num, 'R', prevFile)) {
								infectedNeighbor++; }
						}
						
						double prob = infectedNeighbor * infectionRate;
						
						if (probability.getTrueFalse(prob)){
							pw.print(1 + "\t");
						}
						else {
							pw.print(0 + "\t");
						}
					}
					else if (i == 1) { //if individual is infected
						if (probability.getTrueFalse(recoveryRate)) {
							pw.print(2 + "\t");
						}
						else {
							pw.print(1 + "\t");
						}
					}
					if (counter % num == 0)
						pw.println();
					counter++;
				}
				
				pw.close();
				inputFile.close();
				
			}	
			catch (IOException ioe) {
				System.out.println("Error");
			}
		}
	}
}
