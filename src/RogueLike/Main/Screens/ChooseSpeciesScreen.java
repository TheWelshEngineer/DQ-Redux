/*package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class ChooseSpeciesScreen implements Screen{
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public char humanLeft = '>';
	public char humanRight = '<';
	public char elfLeft = '>';
	public char elfRight = '<';
	public char dwarfLeft = '>';
	public char dwarfRight = '<';
	public char orcLeft = '>';
	public char orcRight = '<';
	public char tieflingLeft = '>';
	public char tieflingRight = '<';
	public char dragonbornLeft = '>';
	public char dragonbornRight = '<';
	public char halflingLeft = '>';
	public char halflingRight = '<';
	public char gorgonLeft = '>';
	public char gorgonRight = '<';
	public char lycanLeft = '>';
	public char lycanRight = '<';
	public char zendariiLeft = '>';
	public char zendariiRight = '<';
	
	public char borderVertical = 186;
	public char borderHorizontal = 205;
	public char borderCorner = 206;
	public char borderCornerNW = 201;
	public char borderCornerNE = 187;
	public char borderCornerSW = 200;
	public char borderCornerSE = 188;
	
	public void changeMarkers(int check) {
		if(check == 0) {
			humanLeft = '>';
			humanRight = '<';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 1) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = '>';
			elfRight = '<';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 2) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = '>';
			dwarfRight = '<';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 3) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = '>';
			orcRight = '<';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 4) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = '>';
			tieflingRight = '<';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 5) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = '>';
			dragonbornRight = '<';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 6) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = '>';
			halflingRight = '<';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 7) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = '>';
			gorgonRight = '<';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 8) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = '>';
			lycanRight = '<';
			zendariiLeft = ' ';
			zendariiRight = ' ';
		}else if(check == 9) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			tieflingLeft = ' ';
			tieflingRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			halflingLeft = ' ';
			halflingRight = ' ';
			gorgonLeft = ' ';
			gorgonRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
			zendariiLeft = '>';
			zendariiRight = '<';
		}
	}

	public void displayOutput(AsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		terminal.writeCenter("== Select your Species ==", 1);	
		int y = 5;
		
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
		terminal.write(String.format("%c %c Human %c      %c", borderVertical, humanLeft, humanRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Elf %c        %c", borderVertical, elfLeft, elfRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Dwarf %c      %c", borderVertical, dwarfLeft, dwarfRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Orc %c        %c", borderVertical, orcLeft, orcRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Tiefling %c   %c", borderVertical, tieflingLeft, tieflingRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Dragonborn %c %c", borderVertical, dragonbornLeft, dragonbornRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Halfling %c   %c", borderVertical, halflingLeft, halflingRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Gorgon %c     %c", borderVertical, gorgonLeft, gorgonRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Lycan %c      %c", borderVertical, lycanLeft, lycanRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Zendarii %c   %c", borderVertical, zendariiLeft, zendariiRight, borderVertical), 5, y++);
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		
		y = 3;
		if(check == 0) {
			terminal.writeCenter("+||+ Human +||+", y+=4);
			y++;
			terminal.writeCenter("Hardy and adaptable, humans can be found all across", y+=1);
			terminal.writeCenter("the Shadowed Realms, and are widely known for the", y+=1);
			terminal.writeCenter("ease with which they adapt to new situations.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Adaptable: You gain an extra Ability Score point.", y+=1);
			terminal.writeCenter("Jack of all Trades: You gain 2 extra skill points.", y+=1);
			y++;
		}else if(check == 1) {
			terminal.writeCenter("+||+ Elf +||+", y+=4);
			y++;
			terminal.writeCenter("Originally from the western forests of Meira, elves", y+=1);
			terminal.writeCenter("are a common sight across the Shadowed Realms, as their", y+=1);
			terminal.writeCenter("long lives give them plenty of time to travel and adventure.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Magic Soul: Your starting maximum mana increases by 25%.", y+=1);
			terminal.writeCenter("Meiran Arcana: You begin with the Magic Missile spell.", y+=1);
			y++;
		}else if(check == 2) {
			terminal.writeCenter("+||+ Dwarf +||+", y+=4);
			y++;
			terminal.writeCenter("Renowned for their music, art, and artifice, the", y+=1);
			terminal.writeCenter("dwarves of the Krothspar Depths are covered in", y+=1);
			terminal.writeCenter("deposits of minerals, growing right out of their bodies.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Adamant: Your Armour Class increases by 2.", y+=1);
			terminal.writeCenter("Dwarvern Resilience: You are resistant to acid damage and effects.", y+=1);
			y++;
		}else if(check == 3) {
			terminal.writeCenter("+||+ Orc +||+", y+=4);
			y++;
			terminal.writeCenter("Marked by large tusks and a greenish hint to their skin,", y+=1);
			terminal.writeCenter("the orcs of the Dunline Expanse are inseperably bonded", y+=1);
			terminal.writeCenter("to nature, drawing their strength from sun and soil.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Verdant: Your starting maximum health increases by 25%.", y+=1);
			terminal.writeCenter("Natural Bond: You begin with the Tangletrap spell.", y+=1);
			y++;
		}else if(check == 4) {
			terminal.writeCenter("+||+ Tiefling +||+", y+=4);
			y++;
			terminal.writeCenter("Much like humans, tieflings are found all across the", y+=1);
			terminal.writeCenter("Shadowed Realms, and are recognisable by their horns,", y+=1);
			terminal.writeCenter("vibrant skin tones, and affinity for pyromancy.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Burning Gift: You begin with the Firebolt spell.", y+=1);
			terminal.writeCenter("Tempered Soul: You are resistant to fire damage and effects.", y+=1);
			y++;
		}else if(check == 5) {
			terminal.writeCenter("+||+ Dragonborn +||+", y+=4);
			y++;
			terminal.writeCenter("Hailing from the frozen Mavarl Ranges, the dragonborn", y+=1);
			terminal.writeCenter("are adventurous and daring, able to command freezing", y+=1);
			terminal.writeCenter("winter storms much as their draconic ancestors could.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Silver Scales: You are resistant to frost damage and effects.", y+=1);
			terminal.writeCenter("Freezing Aura: You begin with the Frost Ward spell.", y+=1);
			y++;
		}else if(check == 6) {
			terminal.writeCenter("+||+ Halfling +||+", y+=4);
			y++;
			terminal.writeCenter("Cheerful and hospitable, the halflings of the", y+=1);
			terminal.writeCenter("Franburg Baronies are often said to be decended from", y+=1);
			terminal.writeCenter("the fey, given their many and varied magical talents.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Fey-Touched: Your starting maximum mana increases by 15%.", y+=1);
			terminal.writeCenter("Halfling Gifts: You begin with the Levitate spell.", y+=1);
			y++;
		}else if(check == 7) {
			terminal.writeCenter("+||+ Gorgon +||+", y+=4);
			y++;
			terminal.writeCenter("Originating from the Leotos Peninsula, gorgons", y+=1);
			terminal.writeCenter("possess a wide, striking array of reptilian features,", y+=1);
			terminal.writeCenter("including serpentine hair and tails.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Serpentine: You are resistant to poison damage and effects.", y+=1);
			terminal.writeCenter("Fearsome Gaze: You begin with the Paralysis spell.", y+=1);
			y++;
		}else if(check == 8) {
			terminal.writeCenter("+||+ Lycan +||+", y+=4);
			y++;
			terminal.writeCenter("Descended from werebeasts, lycans often have a bestial tilt", y+=1);
			terminal.writeCenter("to their features, and while typically a solitary people, ", y+=1);
			terminal.writeCenter("they are often inseperable from their closest friends.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Hunter's Eye: Your vision radius is increased.", y+=1);
			terminal.writeCenter("Call of the Wild: You begin with the Beast Form spell.", y+=1);
			y++;
		}else if(check == 9) {
			terminal.writeCenter("+||+ Zendarii +||+", y+=4);
			y++;
			terminal.writeCenter("Elemental and ethereal, the zendarii are gestalt entities", y+=1);
			terminal.writeCenter("formed from wild magic and unchecked emotion. In many ways,", y+=1);
			terminal.writeCenter("they are the bright mirror to the Nightmare Court.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Abilities +||+", y+=1);
			y++;
			terminal.writeCenter("Boundless Emotion: You are resistant to chaos damage and effects.", y+=1);
			terminal.writeCenter("Mindwarp: You begin with the Confusion spell.", y+=1);
			y++;
		}

		terminal.writeCenter("-- [UP / DOWN]: Move Selection | [ENTER]: Confirm and Continue --", 36);
		terminal.writeCenter("-- [ESCAPE]: Return to Main Menu --", 38);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = 9;
			}else if(check == 1) {
				check = 0;
			}else if(check == 2) {
				check = 1;
			}else if(check == 3) {
				check = 2;
			}else if(check == 4) {
				check = 3;
			}else if(check == 5) {
				check = 4;
			}else if(check == 6) {
				check = 5;
			}else if(check == 7) {
				check = 6;
			}else if(check == 8) {
				check = 7;
			}else if(check == 9) {
				check = 8;
			}
			return this;
			
		case KeyEvent.VK_DOWN:
			if(check == 0) {
				check = 1;
			}else if(check == 1) {
				check = 2;
			}else if(check == 2) {
				check = 3;
			}else if(check == 3) {
				check = 4;
			}else if(check == 4) {
				check = 5;
			}else if(check == 5) {
				check = 6;
			}else if(check == 6) {
				check = 7;
			}else if(check == 7) {
				check = 8;
			}else if(check == 8) {
				check = 9;
			}else if(check == 9) {
				check = 0;
			}
			return this;
			
		case KeyEvent.VK_ENTER:
			if(check == 0) {
				return new ChooseClassScreen("Human");
			}else if(check == 1) {
				return new ChooseClassScreen("Elf");
			}else if(check == 2) {
				return new ChooseClassScreen("Dwarf");
			}else if(check == 3) {
				return new ChooseClassScreen("Orc");
			}else if(check == 4) {
				return new ChooseClassScreen("Tiefling");
			}else if(check == 5) {
				return new ChooseClassScreen("Dragonborn");
			}else if(check == 6) {
				return new ChooseClassScreen("Halfling");
			}else if(check == 7) {
				return new ChooseClassScreen("Gorgon");
			}else if(check == 8) {
				return new ChooseClassScreen("Lycan");
			}else if(check == 9) {
				return new ChooseClassScreen("Zendarii");
			}
		case KeyEvent.VK_ESCAPE: return new MainMenuScreen();
		}
		
		return this;
		
		
	}
	

}
*/