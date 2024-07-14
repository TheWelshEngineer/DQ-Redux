package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import RogueLike.Main.Creatures.Player;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

public class LoseScreen implements Screen {
	public Player player;
	public String weaponName = "None";
	public String armorName = "None";
	public String shieldName = "None";
	public String ringName = "None";
	public String ammunitionName = "None";
	public String exportCheck = "";
	
	public LoseScreen(Player player) {
		this.player = player;
	}

    public void displayOutput(ExtendedAsciiPanel terminal) {
    	Screen.generateBorders(terminal);
    	if(player.weapon() != null) {
    		weaponName = player.weapon().name();
    	}
    	
    	if(player.armor() != null) {
    		armorName = player.armor().name();
    	}
    	
    	if(player.shield() != null) {
    		shieldName = player.shield().name();
    	}
    	
    	
    	if(player.ring() != null) {
    		ringName = player.ring().name();
    	}
    	
    	
    	if(player.ammunition() != null) {
    		ammunitionName = player.ammunition().name();
    	}
    	
    	terminal.writeCenter("You died...", 3);
    	terminal.writeCenter(String.format("%s", player.playerName()), 5);
		terminal.writeCenter(player.causeOfDeath() + String.format(" on depth %d.", player.z()+1), 7);
		terminal.writeCenter(String.format("Level %d %s %s | Score: %d | Max Depth Reached: %d", player.level(), player.playerAncestry(), player.playerClass(), player.score(), player.maxDepth()+1), 9);
		terminal.writeCenter("-- Equipment --", 11);
		terminal.writeCenter("Weapon: "+weaponName, 13);
		terminal.writeCenter("Armor: "+armorName, 15);
		terminal.writeCenter("Shield: "+shieldName, 17);
		terminal.writeCenter("Ring: "+ringName, 19);
		terminal.writeCenter("Ammunition: "+ammunitionName, 21);
		terminal.writeCenter(String.format("-- [%s]: Restart your Adventure | [%s]: Export Scores to Desktop --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm), KeybindManager.keybindText(KeybindManager.navigateMenuFunction_1)), 24);
		terminal.writeCenter(exportCheck, 26);
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch(key.getKeyCode()) {
        
        case KeybindManager.navigateMenuFunction_1:
        	String playerName = player.playerName();
        	String localDate = java.time.LocalDate.now().toString();
        	String desktopPath = System.getProperty("user.home") + "\\Desktop\\";
        	String filename = String.format("%sDwarfQuest %s %s.txt", desktopPath, playerName, localDate);
        	try {
				@SuppressWarnings("unused")
				File saveScores = new File(filename);
				//System.out.println("Successfully created the file.");
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        	try {
				FileWriter writeScores = new FileWriter(filename);
				writeScores.write(String.format("%s", player.playerName()));
				writeScores.write("\n" + player.causeOfDeath() + String.format(" on depth %d.", player.z()+1));
				writeScores.write(String.format("\nLevel %d %s | Score: %d | Max Depth Reached: %d", player.level(), player.playerClass(), player.score(), player.maxDepth()+1));
				writeScores.write("\n-- Equipment --");
				writeScores.write("\nWeapon: "+weaponName);
				writeScores.write("\nArmor: "+armorName);
				writeScores.write("\nShield: "+shieldName);
				writeScores.write("\nRing: "+ringName);
				writeScores.write("\nAmmunition: "+ammunitionName);
				
				writeScores.write("");
				writeScores.close();
				//System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        	exportCheck = "-- Scores Exported --";
        	
        	return this;
        
        case KeybindManager.navigateMenuBack:
        case KeybindManager.navigateMenuConfirm:
        	return new MainMenuScreen();
        default: return this;
        
        
        
        }
    }
}
