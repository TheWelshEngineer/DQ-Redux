package RogueLike.Main;

public enum Speech {
	INSTANCE;
	public static String sentenceStarter() {
		switch(ExtraMaths.d10()) {
		case 1: return "G'varg";
		case 2: return "Hurak";
		case 3: return "Lavar";
		case 4: return "Hu'uh";
		case 5: return "Brork";
		case 6: return "Varagak";
		case 7: return "Heylj";
		case 8: return "Vorn";
		case 9: return "Kat'h";
		case 10: return "Ryne";
		}
		return "G'varg";
	}
	
	public static String sentenceMiddle() {
		switch(ExtraMaths.d20()) {
		case 1: return "vokk";
		case 2: return "mag";
		case 3: return "heff";
		case 4: return "vorogar";
		case 5: return "ma'ri";
		case 6: return "au-a";
		case 7: return "rorva";
		case 8: return "dornt";
		case 9: return "aral";
		case 10: return "quui";
		case 11: return "@#!~&*";
		case 12: return "vygrer";
		case 13: return "elod";
		case 14: return "oshicyl";
		case 15: return "dyblok";
		case 16: return "tekthe";
		case 17: return "ghart";
		case 18: return "kutt";
		case 19: return "demn";
		case 20: return "~#£%?";
		}
		return "vokk";
	}
	
	public static String punctuation() {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return ".";
		case 2: return "!";
		case 3: return "?";
		}
		return ".";
	}
	
	public static String passiveConversation() {
		String opener = sentenceStarter()+" ";
		String mid1 = sentenceMiddle();
		String mid2 = "";
		if(ExtraMaths.d10() >= 5) {
			mid2 = " "+sentenceMiddle();
		}
		String mid3 = "";
		if(ExtraMaths.d10() >= 5) {
			mid3 = " "+sentenceMiddle();
		}
		String punctuation = punctuation();
		String returnSentence = opener+mid1+mid2+mid3+punctuation;
		return returnSentence;
	}

}
