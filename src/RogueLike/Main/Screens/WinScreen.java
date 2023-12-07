package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import RogueLike.Main.Creature;
import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class WinScreen implements Screen {
	private Creature player;
	public String weaponName = "None";
	public String armorName = "None";
	public String shieldName = "None";
	public String ringName = "None";
	public String ammunitionName = "None";
	public String exportCheck = "";
	
	public WinScreen(Creature player) {
		this.player = player;
	}

    public void displayOutput(AsciiPanel terminal) {
    	if(player.weaponName() != null) {
    		weaponName = player.weaponName();
    	}
    	
    	if(player.armorName() != null) {
    		armorName = player.armorName();
    	}
    	
    	if(player.shieldName() != null) {
    		shieldName = player.shieldName();
    	}
    	
    	if(player.ringName() != null) {
    		ringName = player.ringName();
    	}
    	
    	if(player.ammunitionName() != null) {
    		ammunitionName = player.ammunitionName();
    	}
    	
        terminal.writeCenter("Victory!", 3);
        terminal.writeCenter(String.format("%s", player.playerName()), 5);
        terminal.writeCenter(String.format("Level %d %s | Score: %d", player.level(), player.playerClass(), player.score()), 7);
        terminal.writeCenter("-- Equipment --", 10);
        terminal.writeCenter("Weapon: "+weaponName, 12);
		terminal.writeCenter("Armor: "+armorName, 14);
		terminal.writeCenter("Shield: "+shieldName, 16);
		terminal.writeCenter("Ring: "+ringName, 18);
		terminal.writeCenter("Ammunition: "+ammunitionName, 20);
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
				File saveScores = new File(filename);
				//System.out.println("Successfully created the file.");
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        	try {
				FileWriter writeScores = new FileWriter(filename);
				writeScores.write(String.format("%s", player.playerName()));
				writeScores.write(String.format("\nLevel %d %s | Score: %d\", player.level(), player.playerClass(), player.score()"));
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
        
        case KeybindManager.navigateMenuConfirm:
        	return new MainMenuScreen();
        default: return this;
        
        
        
        }
    }
}
