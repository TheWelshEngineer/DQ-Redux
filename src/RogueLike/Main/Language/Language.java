package RogueLike.Main.Language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	

}
