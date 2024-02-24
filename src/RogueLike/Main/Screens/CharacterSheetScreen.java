package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Effect;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Managers.MenuDecorationManager;
import asciiPanel.AsciiPanel;

public class CharacterSheetScreen implements Screen{
	
	protected Creature player;
	protected List<Effect> effects;
	
	public CharacterSheetScreen(Creature player) {
		this.player = player;
		this.effects = player.effects();
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		
		Screen.generateBorders(terminal);
		
		terminal.writeCenter("== Character Sheet ==", 1);	
		int y = 3;
		int y2 = 3;
		int y3 = 3;
		int y4 = 3;
		
		int x = 5;
		int x2 = 32;
		int x3 = 59;
		int x4 = 86;
		
		terminal.write("== Stats ==", x, y++);	
		terminal.write(String.format("%s", player.playerName()), x, y++);
		terminal.write(String.format("Level %d %s %s", player.level(), player.playerAncestry(), player.playerClass()), x, y++);
		terminal.write(String.format("XP: %d/%d", player.xp(), player.xpToNextLevel()), x, y++);
		terminal.write(String.format("Gold: %d gold", player.gold()), x, y++);
		y++;
        terminal.write(String.format("Health: %d/%d", player.hp(), player.maxHP()), x, y++);
        terminal.write(String.format("Mana: %d/%d", player.mana(), player.maxMana()), x, y++);
        terminal.write(String.format("Hunger: %s", player.hungerAsString()), x, y++);
        y++;
        terminal.write(String.format("Strength: %d (%d)", player.strength(), player.strengthModifier()), x, y++);
        terminal.write(String.format("Dexterity: %d (%d)", player.dexterity(), player.dexterityModifier()), x, y++);
        terminal.write(String.format("Intelligence: %d (%d)", player.intelligence(), player.intelligenceModifier()), x, y++);
        y++;
        terminal.write(String.format("Armor Class: %d", player.armorClass()), x, y++);
        terminal.write(String.format("Proficiency Bonus: %s", player.proficiencyBonus()), x, y++);
        terminal.write(String.format("Vision Radius: %d tiles", player.visionRadius()), x, y++);
        y++;
        terminal.write("== Skills ==", x2, y2++);
        for(int i = 0; i < 14; i++) {
        	terminal.write(String.format("%s", player.skills()[i].toStringCharacterSheet()), x2, y2++);
        }
        y++;
        y++;
        terminal.write("== Status Effects ==", x3, y3++);
        y++;
        for(Effect effect : effects) {
        	if(effect.name() != null) {
        		String turns = "turn";
        		if(effect.duration() > 1) {
        			turns = "turns";
        		}
        		char effectIcon = (char)30;
        		if(effect.isNegative()) {
        			effectIcon = (char)31;
        		}
        		if(effect.showInMenu()) {
        			terminal.write(String.format("%c %s: %d %s remaining", effectIcon, effect.name(), effect.duration(), turns), x3, y3++);
        		}
        		
        	}
		}
        
        
        
        terminal.write("== Damage Resistances ==", x4, y4++);
        terminal.write(String.format("Physical: %s", physicalStatus()), x4, y4++);
        terminal.write(String.format("Fire: %s", fireStatus()), x4, y4++);
        terminal.write(String.format("Frost: %s", frostStatus()), x4, y4++);
        terminal.write(String.format("Shock: %s", shockStatus()), x4, y4++);
        terminal.write(String.format("Poison: %s", poisonStatus()), x4, y4++);
        terminal.write(String.format("Acid: %s", acidStatus()), x4, y4++);
        terminal.write(String.format("Magic: %s", magicStatus()), x4, y4++);
        terminal.write(String.format("Chaos: %s", chaosStatus()), x4, y4++);
    
        terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
		
	}
	
	public String physicalStatus() {
		if(player.immunePhysicalDamage()) {
			return "Immune";
		}else if(player.resistsPhysicalDamage()) {
			return "Resistant";
		}else if(player.weakToPhysicalDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String fireStatus() {
		if(player.immuneFireDamage()) {
			return "Immune";
		}else if(player.resistsFireDamage()) {
			return "Resistant";
		}else if(player.weakToFireDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String frostStatus() {
		if(player.immuneFrostDamage()) {
			return "Immune";
		}else if(player.resistsFrostDamage()) {
			return "Resistant";
		}else if(player.weakToFrostDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String shockStatus() {
		if(player.immuneShockDamage()) {
			return "Immune";
		}else if(player.resistsShockDamage()) {
			return "Resistant";
		}else if(player.weakToShockDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String poisonStatus() {
		if(player.immunePoisonDamage()) {
			return "Immune";
		}else if(player.resistsPoisonDamage()) {
			return "Resistant";
		}else if(player.weakToPoisonDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String acidStatus() {
		if(player.immuneAcidDamage()) {
			return "Immune";
		}else if(player.resistsAcidDamage()) {
			return "Resistant";
		}else if(player.weakToAcidDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String magicStatus() {
		if(player.immuneMagicDamage()) {
			return "Immune";
		}else if(player.resistsMagicDamage()) {
			return "Resistant";
		}else if(player.weakToMagicDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String chaosStatus() {
		if(player.immuneChaosDamage()) {
			return "Immune";
		}else if(player.resistsChaosDamage()) {
			return "Resistant";
		}else if(player.weakToChaosDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		/*case KeyEvent.VK_LEFT:
			return new SkillsScreen(player);
		case KeyEvent.VK_RIGHT:
			return new SkillsScreen(player);*/
		case KeybindManager.navigateMenuBack:
			return null;
		default: return this;
		}
	}

}
