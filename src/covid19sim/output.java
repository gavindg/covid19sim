package covid19sim;

import java.io.*;

public class output {
	
	public static String filepath = controller.filepath;
	
	public static void output(int step) {
		try {
			FileReader fr = new FileReader(filepath+step+".txt");
			BufferedReader br = new BufferedReader(fr);
			System.out.println("Test #"+step);
			System.out.println("------------------------");
			
			String s;
			while((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception E) {
			System.out.println("ERROR");
		}
			
	}

}