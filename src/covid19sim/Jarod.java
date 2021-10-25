package covid19sim;

import java.util.Scanner;
import java.util.Random;
import java.io.*;


public class Jarod {
	
	static Scanner scnr = new Scanner(System.in);
	static String filepath = "covid0.txt";
	static int status;
	static int numPeople;
	static int test;
	static int numSteps;
	static double a;
	static double b;
	
	
	public static void main(String [] args) {
	
		input();
		writeFile();
		output();
		spread();
	}
	
	public static void input() {
		System.out.println("Enter number of people: ");
		numPeople = scnr.nextInt();
		while (Math.pow((int) Math.sqrt(numPeople), 2) != numPeople) {
			System.out.println("Error value must be an integer perfect square.");
			System.out.println("Enter numer of people: ");
			numPeople = scnr.nextInt();
		}
		
		System.out.println("Enter number of timesteps to be simulated: ");
		numSteps = scnr.nextInt();
		while (numSteps < 1) {
			System.out.println("Error value must be an integer perfect square.");
			System.out.println("Enter numer of people: ");
			numSteps = scnr.nextInt();
		}
		
		System.out.println("Enter Infection Rate: ");
		a = scnr.nextDouble();
		while (a < 0 || a > 1) {
			System.out.println("Error value must be an a value between 0 and 1.");
			System.out.println("Enter Infection Rate: ");
			a = scnr.nextDouble();
		}
		
		System.out.println("Enter Recovery Rate: ");
		b = scnr.nextDouble();
		while (b < 0 || b > 1) {
			System.out.println("Error value must be an a value between 0 and 1.");
			System.out.println("Enter Recovery Rate: ");
			b = scnr.nextDouble();
		}
	}
	
	
	public static void writeFile() {
		try {
			FileWriter fw = new FileWriter(filepath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			test++;
			int infected = randomInt(numPeople);
			
			for (int i = 1; i <= numPeople; i++) {
				if (i % (Math.sqrt(numPeople)) != 0 && i != infected) {
					bw.write(0+"\t");
				} else {
					if (i == infected)
					{
						if (i % (Math.sqrt(numPeople)) != 0 ) {
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
			System.out.println("ERROR");
		}	
	}
	
	
	public static void output() {
		try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception E) {
			System.out.println("ERROR");
		}
	}
	
	// runs the simulation at timeStep `step`
	public static void runSimulation(int step) 
	{
		String current = "covid" + step + ".txt";
		String previous = "covid" + (step - 1) + ".txt";
		
		try 
		{	
			FileWriter currentFW = new FileWriter(current, true);
			BufferedWriter currentBW = new BufferedWriter(currentFW);
			
			for (int i = 0; i < numPeople; i++) 
			{
				/* check at position i. if infected, continue. else ... */
				
				/* check at position i - 1. if infected, 
				 * run `getTrueFalse` to see if position i gets infected*/
				
				/* check at position i + 1. if infected, 
				 * run `getTrueFalse` to see if position i gets infected*/
				
				/* check at position i - sqrt(numPeople). if infected, 
				 * run `getTrueFalse` to see if position i gets infected*/
				
				/* check at position i + sqrt(numPeople). if infected,
				 * run `getTrueFalse` to see if position i gets infected. */
			}
		}
		catch (Exception E) 
		{
			System.out.println("ERROR");
		}
	}
	
	/* checks the position @ pos of file @ directory `fileName` 
	 * return values:
	 * 0 = susceptible
	 * 1 = infected
	 * 2 = recovered
	 * -1 = error ...
	 * */
	public static int checkPosition(int pos, String fileName) 
	{
		try 
		{
			Scanner sc = new Scanner(new File(fileName));
			int status = -1;
			
			for (int i = 0; i <= pos; i++)
			{
				status = sc.nextInt();
			}
			
			return status;
		}
		catch (Exception E) 
		{
			return -1;
		}
	}
	
	public static void spread() {
		try {
			
			Scanner sc = new Scanner(new File(filepath));
			int i = 0;
			int num;
			while (sc.hasNext()) {
				i++;
				if (sc.nextInt() == 1) {
					System.out.println("Infected:"+i);
					infect(i);
				}
			}
			System.out.println("Done"+i);
			
			
		} catch(Exception e) {
			System.out.print("Error");
		}
		
	}
	public static void infect(int inf) {
		try {
			Scanner sc = new Scanner(new File(filepath));
			FileWriter fw = new FileWriter(filepath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
				
			bw.flush();
			bw.close();
			
		} catch (Exception E) {
			System.out.println("ERROR");
		}
	}

	public static int randomInt(int up) {
		Random rand = new Random();
		int num = 1+rand.nextInt(up - 1);
		return num;
	}

	public static boolean getTrueFalse(double probability) 
	{
		Random rand = new Random();
		double test = rand.nextDouble();
		
		if (test < probability) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
