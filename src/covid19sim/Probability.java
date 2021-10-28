package covid19sim;

import java.util.Random;

// Probability Class handles the random
// number generator, as well as the 
// probability calculator.

public class Probability {
	
	// This function generates a random integer between 1 and the upper limit, `up`.
	public static int randomInt(int up) {
		Random rand = new Random();
		int num = 1+rand.nextInt(up - 1);
		return num;
	}
	
	/* This function takes in a percentage probability (between 0 and 1) and uses and random number
	 * generator to decide the result. the function will return true a% of the time and false b% of
	 * the time, given that a = 100 * probability, and b = 100 * (1 - a). */
	public static boolean getTrueFalse(double probability) 
	{
		if(probability < 1 && probability >= 0) {
		Random rand = new Random();
		double test = rand.nextDouble();
			if (test < probability) 
				return true;
			else 
				return false;
		} else {
			return true;
		}
	}
}
