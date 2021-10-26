package covid19sim;

import java.io.*;
import java.util.Scanner;

public class write {
	//Calls variables from Controller Class
	public static String filepath = controller.filepath;
	public static int step = controller.step;
	public static int n = controller.n;
	
	public static void writeFile() {
		try {
			FileWriter fw = new FileWriter(filepath+"1.txt",true);//Opens a file writer.
			BufferedWriter bw = new BufferedWriter(fw);
			step++; // Records step number.
			int infected = probability.randomInt(n); //Generates a random integer from probability class.
			// Generates grid.
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
			
		} catch (Exception E) {
			//Produces ERROR if file can't be found.
			System.out.println("ERROR");
		}
		
	}
	public static void createNextTimeStepFile(int timeStepCount, int numOfIndividuals) {
		int num = (int)Math.sqrt(numOfIndividuals);
		int counter = 1;
		for (int p = 0; p < timeStepCount; p++) {
			try {
				File file = new File("EpidemicData" + (p + 1) + ".txt");
				if(!file.exists()) {
					file.createNewFile();
				}
				
				Scanner inputFile = new Scanner(file);
				while(inputFile.hasNextInt()) {
					int i = inputFile.nextInt();
					if (i == 0) //if individual is susceptible
					{
						if (counter % num == 1) { //if individual is in the first column
							if (counter == 1) { //and if individual is in the top left corner
								//look at right and bottom
							}
							else if (counter == (numOfIndividuals - num)) { //and if individual is in the bottom left corner
								//look at top and right
							}
							else { //1st column boundary individuals
								//look at top, right, and bottom
							}
						}
						
						else if (counter % num == 0) { //if individual is in the last column
							if (counter == num) { //and if individual is in the top right corner
								//look at left and bottom
							}
							
							else if (counter == numOfIndividuals) { //and if individual is in the bottom right corner
								//look at top and left
							}
							
							else {
								//look at top, left, and bottom
							}
						}
						
						else if (counter < num) { //boundary individuals in the top row
							//look at left, bottom, and right
						}
						
						else if ((counter > (numOfIndividuals - num)) && (counter < numOfIndividuals)) { //boundary individuals in the last row
							//look at left, top, and right
						}
					}
					
					else if (i == 1) { //if individual is infected
						//calculate recovery rate
					}
					
					counter++;
				
				}
				
				inputFile.close();
				/*//generate data inside file
				PrintWriter pw = new PrintWriter(file); 
				for(int i = 0; i < num; i++) {
					for (int j = 0; j < num; j++) {
						pw.print(i + ", " + j + ", S   ");
					}
					pw.println("");
				}
				pw.close();
				
				System.out.println("Done");*/
			}
				
			catch (IOException ioe) {
				System.out.println("Error");
			}
			
		}
	}
}
