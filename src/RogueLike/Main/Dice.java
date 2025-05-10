package RogueLike.Main;

import java.io.Serializable;

public class Dice implements Serializable {
	
	private static final long serialVersionUID = -9073823943053273301L;
	private int lowerBound = 0;
	public void setLowerBound(int value) {
		lowerBound = value;
	}
	public int getLowerBound() {
		return lowerBound;
	}
	
	private int upperBound = 0;
	public void setUpperBound(int value) {
		upperBound = value;
	}
	public int getUpperBound() {
		return upperBound;
	}
	
	private int amount = 0;
	public void setAmount(int value) {
		amount = value;
	}
	public int getAmount() {
		return amount;
	}
	
	public static Dice d1 = new Dice(1,1,1);
	public static Dice d4 = new Dice(1,4,1);
	public static Dice d6 = new Dice(1,6,1);
	public static Dice d8 = new Dice(1,8,1);
	public static Dice d10 = new Dice(1,10,1);
	public static Dice d12 = new Dice(1,12,1);
	public static Dice d20 = new Dice(1,20,1);
	public static Dice d100 = new Dice(1,100,1);
	
	public static Dice d4x2 = new Dice(1,4,2);
	public static Dice d6x2 = new Dice(1,6,2);
	public static Dice d8x2 = new Dice(1,8,2);
	public static Dice d10x2 = new Dice(1,10,2);
	public static Dice d12x2 = new Dice(1,12,2);
	
	public static Dice d4x3 = new Dice(1,4,3);
	public static Dice d6x3 = new Dice(1,6,3);
	public static Dice d8x3 = new Dice(1,8,3);
	public static Dice d10x3 = new Dice(1,10,3);
	public static Dice d12x3 = new Dice(1,12,3);
	
	public Dice(int lowerBound, int upperBound, int amount) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.amount = amount;
	}
	
	public int roll() {
		int result = 0;
		for(int i = 0; i < amount; i++) {
			result += ExtraMaths.diceRoll(lowerBound, upperBound);
		}
		return result;
	}
	
	public String toString() {
		return String.format("%dd%d", lowerBound, upperBound);
	}
	
	public int toInt() {
		return upperBound;
	}

}
