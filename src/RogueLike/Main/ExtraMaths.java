package RogueLike.Main;

import java.math.BigDecimal;
import java.util.Random;

public class ExtraMaths {
	
	public static int roundModifier(int input) {
		double temp = ((input - 10));
		double temporary = temp/2;
		int output = 0;
		if(temporary > 0) {
			output = (int)Math.floor(temporary);
			//System.out.println(output);
		}else {
			BigDecimal processing = new BigDecimal(String.valueOf(temporary));
			BigDecimal processInt = new BigDecimal(processing.intValue());
			BigDecimal processDec = processing.subtract(processInt);
			double constant = -0.5;
			BigDecimal check = BigDecimal.valueOf(constant);
			if(processDec.compareTo(check) == 0) {
				double replacement = -1;
				check = BigDecimal.valueOf(replacement);
			}else {
				
			}
			output = (processInt.add(check)).intValue();
			//System.out.println(output);
		}
		


		return output;
	}
	
	public static int diceRoll(int lowerBound, int upperBound) {
		Random rand = new Random();
		int diceRoll = rand.nextInt((upperBound - lowerBound)+1)+lowerBound;
		return diceRoll;
	}
	
	public static int d4() {
		Random rand = new Random();
		int diceRoll = rand.nextInt((4 - 1)+1)+1;
		return diceRoll;
	}
	
	public static int d6() {
		Random rand = new Random();
		int diceRoll = rand.nextInt((6 - 1)+1)+1;
		return diceRoll;
	}
	
	public static int d8() {
		Random rand = new Random();
		int diceRoll = rand.nextInt((8 - 1)+1)+1;
		return diceRoll;
	}
	
	public static int d10() {
		Random rand = new Random();
		int diceRoll = rand.nextInt((10 - 1)+1)+1;
		return diceRoll;
	}
	
	public static int d12() {
		Random rand = new Random();
		int diceRoll = rand.nextInt((12 - 1)+1)+1;
		return diceRoll;
	}
	
	public static int d20() {
		Random rand = new Random();
		int diceRoll = rand.nextInt((20 - 1)+1)+1;
		return diceRoll;
	}
	
	public static int d100() {
		Random rand = new Random();
		int diceRoll = rand.nextInt((100 - 1)+1)+1;
		return diceRoll;
	}
	
	public static int scrollingScreenPageNumber(int input) {
		int temp = input%30;
		int process = 30-temp;
		int returnValue = (input+process)/30;
		
		return returnValue;		
	}


}
