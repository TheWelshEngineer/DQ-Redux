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

	

}
