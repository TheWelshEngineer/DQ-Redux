package RogueLike.Main;

import java.math.BigDecimal;
import java.util.Random;
import java.util.TreeMap;

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
	
	public static String modifierToString(int modifier) {
		if(modifier >= 0) {
			return String.format("+%d", modifier);
		}else {
			return String.format("%d", modifier);
		}
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
	
	//https://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java
    private final static TreeMap<Integer, String> romanNumeralMap = new TreeMap<Integer, String>();
    static {
        romanNumeralMap.put(1000, "M");
        romanNumeralMap.put(900, "CM");
        romanNumeralMap.put(500, "D");
        romanNumeralMap.put(400, "CD");
        romanNumeralMap.put(100, "C");
        romanNumeralMap.put(90, "XC");
        romanNumeralMap.put(50, "L");
        romanNumeralMap.put(40, "XL");
        romanNumeralMap.put(10, "X");
        romanNumeralMap.put(9, "IX");
        romanNumeralMap.put(5, "V");
        romanNumeralMap.put(4, "IV");
        romanNumeralMap.put(1, "I");
    }
    public final static String toRomanNumerals(int value) {
        Integer i = romanNumeralMap.floorKey(value);
        if(value == 0) {
        	return "0";
        }
        if(value == i) {
            return romanNumeralMap.get(value);
        }
        return romanNumeralMap.get(i)+toRomanNumerals(value-i);
    }


}
