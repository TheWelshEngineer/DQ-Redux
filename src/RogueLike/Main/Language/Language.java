package RogueLike.Main.Language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import RogueLike.Main.Managers.KeybindManager;

public abstract class Language {

	public ArrayList<String> languageData = new ArrayList<String>();
	
	public Language(String filepath) {
		setUpLanguage(filepath);
	}
	
	public void setUpLanguage(String filepath) {
		try(BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
		    for(String line; (line = reader.readLine()) != null; ) {
		        String[] split = line.split(":");
		        languageData.add(split[1]);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Title Screen
	public String titleScreen_Subtitle() {
		return languageData.get(0);
	}
	public String titleScreen_Controls() {
		return String.format(languageData.get(1), KeybindManager.keybindText(KeybindManager.navigateMenuConfirm), KeybindManager.keybindText(KeybindManager.navigateMenuBack));
	}
	
	//Main Menu
	public String mainMenu_Subtitle() {
		return titleScreen_Subtitle();
	}
	public String mainMenu_Heading() {
		return languageData.get(2);
	}
	public String mainMenu_NewGame() {
		return languageData.get(3);
	}
	public String mainMenu_Help() {
		return languageData.get(4);
	}
	public String mainMenu_Credits() {
		return languageData.get(5);
	}
	public String mainMenu_Controls1() {
		return String.format(languageData.get(6), KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuConfirm));
	}
	public String mainMenu_Controls2() {
		return String.format(languageData.get(7), KeybindManager.keybindText(KeybindManager.navigateMenuBack));
	}
	
	//Credits
	public String credits_Heading() {
		return languageData.get(8);
	}
	public String credits_Design() {
		return languageData.get(9);
	}
	public String credits_Ascii() {
		return languageData.get(10);
	}
	public String credits_Review() {
		return languageData.get(11);
	}
	public String credits_Controls() {
		return String.format(languageData.get(12), KeybindManager.keybindText(KeybindManager.navigateMenuBack));
	}
	

}
