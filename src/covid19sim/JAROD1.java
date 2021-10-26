
	infectedNeighbor = check(counter, num, prevFile);





















































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
