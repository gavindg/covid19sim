package covid19sim;

import java.util.Random;

public class probability {
	public static int randomInt(int up) {
		/*Random rand = new Random();
		int num = 1+rand.nextInt(up - 1);
		return num;*/
		return 4;
	}
	
	public static boolean getTrueFalse(double probability) 
	{
		if(probability < 1 && probability >= 0) {
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
		} else {
			return true;
		}
	}
}
