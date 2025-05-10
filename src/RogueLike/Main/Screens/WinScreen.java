package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Player;
import RogueLike.Main.Managers.KeybindManager;

public class WinScreen implements Screen, Serializable {
	private Player player;
	public String weaponName = "None";
	public String armorName = "None";
	public String shieldName = "None";
	public String ringName = "None";
	public String ammunitionName = "None";
	public String exportCheck = "";
	
	public WinScreen(Player player) {
		this.player = player;
	}

    public void displayOutput() {
    	Screen.generateBorders();;
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
    	
        ExtendedAsciiPanel.writeCenter("Victory!", 3);
        ExtendedAsciiPanel.writeCenter(String.format("%s", player.playerName()), 5);
        ExtendedAsciiPanel.writeCenter(String.format("Level %d %s %s | Score: %d", player.level(), player.playerAncestry(), player.playerClass(), player.score()), 7);
        ExtendedAsciiPanel.writeCenter("-- Equipment --", 10);
        ExtendedAsciiPanel.writeCenter("Weapon: "+weaponName, 12);
		ExtendedAsciiPanel.writeCenter("Armor: "+armorName, 14);
		ExtendedAsciiPanel.writeCenter("Shield: "+shieldName, 16);
		ExtendedAsciiPanel.writeCenter("Ring: "+ringName, 18);
		ExtendedAsciiPanel.writeCenter("Ammunition: "+ammunitionName, 20);
		ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Restart your Adventure | [%s]: Export Scores to Desktop --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm), KeybindManager.keybindText(KeybindManager.navigateMenuFunction_1)), 24);
        ExtendedAsciiPanel.writeCenter(exportCheck, 26);
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
