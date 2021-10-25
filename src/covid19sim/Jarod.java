package covid19sim;

import java.util.Scanner;
import java.util.Random;
import java.io.*;


public class Jarod {
	
	static Scanner scnr = new Scanner(System.in);
	static String filepath = "covid.txt";
	static int status;
	static int n;
	static int test;
	static double a;
	static double b;
	
	
	public static void main(String [] args) {
	
		input();
		writeFile();
		output();
		spread();
	}
	public static void input() {
		System.out.println("Enter numer of people: ");
		n = scnr.nextInt();
		while (Math.pow((int) Math.sqrt(n), 2) != n) {
			System.out.println("Error value must be an integer perfect square.");
			System.out.println("Enter numer of people: ");
			n = scnr.nextInt();
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
			int infected = randomInt(n);
			
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
		int num = 1+rand.nextInt(up);
		return num;
	}
}
