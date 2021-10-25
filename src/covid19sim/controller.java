package covid19sim;

import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class controller {
	public static Scanner scnr = new Scanner(System.in);
	public static String filepath = "covid.txt";
	public static int status;
	public static int n;
	public static int test;
	public static double a; 
	public static double b;
	
	public static void main(String[] args) {
		
		input.validate();
	}
}
