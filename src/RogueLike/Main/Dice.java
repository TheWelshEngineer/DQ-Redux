package RogueLike.Main;

public class Dice {
	
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
	
	public static Dice d1 = new Dice(1,1);
	public static Dice d4 = new Dice(1,4);
	public static Dice d6 = new Dice(1,6);
	public static Dice d8 = new Dice(1,8);
	public static Dice d10 = new Dice(1,10);
	public static Dice d12 = new Dice(1,12);
	public static Dice d20 = new Dice(1,20);
	public static Dice d100 = new Dice(1,100);
	
	public Dice(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public int roll() {
		return ExtraMaths.diceRoll(lowerBound, upperBound);
	}
	
	public String toString() {
		return String.format("%dd%d", lowerBound, upperBound);
	}
	
	public int toInt() {
		return upperBound;
	}

}
