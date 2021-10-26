package covid19sim;

import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class controller {
	public static Scanner scnr = new Scanner(System.in);
	
	public static String filepath = "EpidemicData";
	public static int status;
	public static int n;
	public static int step;
	public static double a; 
	public static double b;
	
	public static void main(String[] args) {
		
		input.validate();
		write.writeFile();
	}
}
