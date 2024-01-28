package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class PlayerLevelUpStatsScreen implements Screen{
	
	protected Creature player;
	
	public int points;
	public void modifyPoints(int amount) {
		points += amount;
	}
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public char strLeft = '>';
	public char strRight = '<';
	public char dexLeft = '>';
	public char dexRight = '<';
	public char intLeft = '>';
	public char intRight = '<';
	
	public char strUp = '+';
	public char strDown = '-';
	public char dexUp = '+';
	public char dexDown = '-';
	public char intUp = '+';
	public char intDown = '-';
	
	public void changeMarkers(int check) {
		if(check == 0) {
			strLeft = '>';
			strRight = '<';
			dexLeft = ' ';
			dexRight = ' ';
			intLeft = ' ';
			intRight = ' ';
			if(playerStrength == player.baseStrength()) {
				strUp = '+';
				strDown = ' ';
			}else if(playerStrength == 30 || points == 0) {
				strUp = ' ';
				strDown = '-';
			}else{
				strUp = '+';
				strDown = '-';
			}
			if(playerDexterity == player.baseDexterity()) {
				dexUp = '+';
				dexDown = ' ';
			}else if(playerDexterity == 30 || points == 0) {
				dexUp = ' ';
				dexDown = '-';
			}else{
				dexUp = '+';
				dexDown = '-';
			}
			if(playerIntelligence == player.baseIntelligence()) {
				intUp = '+';
				intDown = ' ';
			}else if(playerIntelligence == 30 || points == 0) {
				intUp = ' ';
				intDown = '-';
			}else{
				intUp = '+';
				intDown = '-';
			}
		}else if(check == 1) {
			strLeft = ' ';
			strRight = ' ';
			dexLeft = '>';
			dexRight = '<';
			intLeft = ' ';
			intRight = ' ';
			if(playerStrength == player.baseStrength()) {
				strUp = '+';
				strDown = ' ';
			}else if(playerStrength == 30 || points == 0) {
				strUp = ' ';
				strDown = '-';
			}else{
				strUp = '+';
				strDown = '-';
			}
			if(playerDexterity == player.baseDexterity()) {
				dexUp = '+';
				dexDown = ' ';
			}else if(playerDexterity == 30 || points == 0) {
				dexUp = ' ';
				dexDown = '-';
			}else{
				dexUp = '+';
				dexDown = '-';
			}
			if(playerIntelligence == player.baseIntelligence()) {
				intUp = '+';
				intDown = ' ';
			}else if(playerIntelligence == 30 || points == 0) {
				intUp = ' ';
				intDown = '-';
			}else{
				intUp = '+';
				intDown = '-';
			}
		}else if(check == 2) {
			strLeft = ' ';
			strRight = ' ';
			dexLeft = ' ';
			dexRight = ' ';
			intLeft = '>';
			intRight = '<';
			if(playerStrength == player.baseStrength()) {
				strUp = '+';
				strDown = ' ';
			}else if(playerStrength == 30 || points == 0) {
				strUp = ' ';
				strDown = '-';
			}else{
				strUp = '+';
				strDown = '-';
			}
			if(playerDexterity == player.baseDexterity()) {
				dexUp = '+';
				dexDown = ' ';
			}else if(playerDexterity == 30 || points == 0) {
				dexUp = ' ';
				dexDown = '-';
			}else{
				dexUp = '+';
				dexDown = '-';
			}
			if(playerIntelligence == player.baseIntelligence()) {
				intUp = '+';
				intDown = ' ';
			}else if(playerIntelligence == 30 || points == 0) {
				intUp = ' ';
				intDown = '-';
			}else{
				intUp = '+';
				intDown = '-';
			}
		}
	}
	
	public int playerStrength;
	public void modifyStrength(int amount) {
		playerStrength += amount;
	}
	public int playerDexterity;
	public void modifyDexterity(int amount) {
		playerDexterity += amount;
	}
	public int playerIntelligence;
	public void modifyIntelligence(int amount) {
		playerIntelligence += amount;
	}
	
	public int strMod() {
		return ExtraMaths.roundModifier(playerStrength);
	}
	public int dexMod() {
		return ExtraMaths.roundModifier(playerDexterity);
	}
	public int intMod() {
		return ExtraMaths.roundModifier(playerIntelligence);
	}
	
	public PlayerLevelUpStatsScreen(Creature player) {
		this.player = player;
		this.points = player.attributePoints();
		this.playerStrength = player.baseStrength();
		this.playerDexterity = player.baseDexterity();
		this.playerIntelligence = player.baseIntelligence();
	}

	@Override
	public void displayOutput(AsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		terminal.writeCenter("== Increase your Ability Scores ==", 1);	
		int y = 3;
		/*
        terminal.writeCenter("[1]: 14(+2) Strength, 12(+1) Dexterity, 10(+0) Intelligence", y+=2);
        terminal.writeCenter("[2]: 14(+2) Strength, 10(+0) Dexterity, 12(+1) Intelligence", y+=2);
        
        terminal.writeCenter("[3]: 12(+1) Strength, 14(+2) Dexterity, 10(+0) Intelligence", y+=2);
        terminal.writeCenter("[4]: 10(+0) Strength, 14(+2) Dexterity, 12(+1) Intelligence", y+=2);
        
        terminal.writeCenter("[5]: 10(+0) Strength, 12(+1) Dexterity, 14(+2) Intelligence", y+=2);
        terminal.writeCenter("[6]: 12(+1) Strength, 10(+0) Dexterity, 14(+2) Intelligence", y+=2);
        
        terminal.writeCenter("[7]: 12(+1) Strength, 12(+1) Dexterity, 12(+1) Intelligence", y+=2);
        */
		
		//terminal.writeCenter(TEST, y+=2);
		
		terminal.writeCenter(String.format("-- Points remaining: %d --", points), y);
		
		terminal.writeCenter(String.format("%c %c Strength ( %d / +%d ) %c %c", strDown, strLeft, playerStrength, strMod(), strRight, strUp), y+=3);
		terminal.writeCenter(String.format("%c %c Dexterity ( %d / +%d ) %c %c", dexDown, dexLeft, playerDexterity, dexMod(), dexRight, dexUp), y+=2);
		terminal.writeCenter(String.format("%c %c Intelligence ( %d / +%d ) %c %c", intDown, intLeft, playerIntelligence, intMod(), intRight, intUp), y+=2);
		if(check == 0) {
			terminal.writeCenter("Strength represents the physical power of your body.", y+=6);
			terminal.writeCenter("Strength increases the damage dealt by simple and martial weapons,", y+=1);
			terminal.writeCenter("as well as the amount of health you gain from levelling up.", y+=1);
		}else if(check == 1) {
			terminal.writeCenter("Dexterity is a measure of your agility and reflexes.", y+=6);
			terminal.writeCenter("Dexterity increases the damage dealt by ranged and finesse weapons,", y+=1);
			terminal.writeCenter("as well as providing a bonus to light and medium armor.", y+=1);
		}else if(check == 2) {
			terminal.writeCenter("Intelligence represents your willpower and magical talent.", y+=6);
			terminal.writeCenter("Intelligence increases the effectiveness of wands,", y+=1);
			terminal.writeCenter("as well as the amount of mana you gain from levelling up.", y+=1);
		}
		
		
		if(points < 1) {
			terminal.writeCenter("-- [ENTER]: Confirm and Continue --", 36);
		}
		terminal.writeCenter("-- [UP / DOWN]: Move Selection | [LEFT / RIGHT]: Increase/Decrease Attribute --", 38);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 2;
			}else if(check == 1) {
				check = 0;
			}else if(check == 2) {
				check = 1;
			}
			return this;
			
		case KeybindManager.navigateMenuDown:
			if(check == 0) {
				check = 1;
			}else if(check == 1) {
				check = 2;
			}else if(check == 2) {
				check = 0;
			}
			return this;
			
		case KeybindManager.navigateMenuRight:
			if(check == 0) {
				if(points > 0 && playerStrength < 30) {
					modifyStrength(1); 
					modifyPoints(-1); 
				}
			}else if(check == 1) {
				if(points > 0 && playerDexterity < 30) {
					modifyDexterity(1); 
					modifyPoints(-1); 
				}
			}else if(check == 2) {
				if(points > 0 && playerIntelligence < 30) {
					modifyIntelligence(1); 
					modifyPoints(-1); 
				}
			}
			return this;
			
		case KeybindManager.navigateMenuLeft:
			if(check == 0) {
				if(playerStrength > player.baseStrength()) {
					modifyStrength(-1); 
					modifyPoints(1); 
				}
			}else if(check == 1) {
				if(playerDexterity > player.baseDexterity()) {
					modifyDexterity(-1); 
					modifyPoints(1); 
				}
			}else if(check == 2) {
				if(playerIntelligence > player.baseIntelligence()) {
					modifyIntelligence(-1); 
					modifyPoints(1); 
				}
			}
			return this;
		
			
		case KeybindManager.navigateMenuConfirm: 
			if(points < 1) {
				player.setStrength(playerStrength);
				player.setDexterity(playerDexterity);
				player.setIntelligence(playerIntelligence);
				player.setAttributePoints(0);
				//return new PlayerLevelUpSkillsScreen(player);
				return null;
			}else {
				return this;
			}
		
		}
		return this;
	}

}
