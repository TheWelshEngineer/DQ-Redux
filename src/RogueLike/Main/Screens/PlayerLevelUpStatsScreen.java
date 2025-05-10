package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

public class PlayerLevelUpStatsScreen implements Screen, Serializable{
	
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
	public void displayOutput() {
		changeMarkers(check);
		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		ExtendedAsciiPanel.writeCenter("== Increase your Ability Scores ==", 1);	
		int y = 3;
		
		ExtendedAsciiPanel.writeCenter(String.format("-- Points remaining: %d --", points), y);
		
		ExtendedAsciiPanel.writeCenter(String.format("%c %c Strength ( %d / +%d ) %c %c", strDown, strLeft, playerStrength, strMod(), strRight, strUp), y+=3);
		ExtendedAsciiPanel.writeCenter(String.format("%c %c Dexterity ( %d / +%d ) %c %c", dexDown, dexLeft, playerDexterity, dexMod(), dexRight, dexUp), y+=2);
		ExtendedAsciiPanel.writeCenter(String.format("%c %c Intelligence ( %d / +%d ) %c %c", intDown, intLeft, playerIntelligence, intMod(), intRight, intUp), y+=2);
		if(check == 0) {
			ExtendedAsciiPanel.writeCenter("Strength represents the physical power of your body.", y+=6);
			ExtendedAsciiPanel.writeCenter("Strength increases the damage dealt by simple and martial weapons,", y+=1);
			ExtendedAsciiPanel.writeCenter("as well as the amount of health you gain from levelling up.", y+=1);
		}else if(check == 1) {
			ExtendedAsciiPanel.writeCenter("Dexterity is a measure of your agility and reflexes.", y+=6);
			ExtendedAsciiPanel.writeCenter("Dexterity increases the damage dealt by ranged and finesse weapons,", y+=1);
			ExtendedAsciiPanel.writeCenter("as well as providing a bonus to light and medium armor.", y+=1);
		}else if(check == 2) {
			ExtendedAsciiPanel.writeCenter("Intelligence represents your willpower and magical talent.", y+=6);
			ExtendedAsciiPanel.writeCenter("Intelligence increases the effectiveness of wands,", y+=1);
			ExtendedAsciiPanel.writeCenter("as well as the amount of mana you gain from levelling up.", y+=1);
		}
		
		
		if(points < 1) {
			ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 38);
		}
		ExtendedAsciiPanel.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s / %s]: Increase/Decrease Attribute Point Allocation --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 36);
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
				return new PlayerLevelUpSkillsScreen(player);
				//return null;
			}else {
				return this;
			}
		
		}
		return this;
	}

}
