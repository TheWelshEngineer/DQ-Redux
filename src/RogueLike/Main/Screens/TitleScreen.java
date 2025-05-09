package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Languages.Language;
import RogueLike.Main.Managers.KeybindManager;

public class TitleScreen implements Screen{
	
	private Language language = new Language("lang_EN.txt");

	public void displayOutput() {
		Screen.generateBorders();;
		int y = 1;
		ExtendedAsciiPanel.writeCenter(" _____                      __  ____                  _   ", y++);
		ExtendedAsciiPanel.writeCenter("|  __ \\                    / _|/ __ \\                | |  ", y++);
		ExtendedAsciiPanel.writeCenter("| |  | |_      ____ _ _ __| |_| |  | |_   _  ___  ___| |_ ", y++);
		ExtendedAsciiPanel.writeCenter("| |  | \\ \\ /\\ / / _` | '__|  _| |  | | | | |/ _ \\/ __| __|", y++);
		ExtendedAsciiPanel.writeCenter("| |__| |\\ V  V / (_| | |  | | | |__| | |_| |  __/\\__ \\ |_ ", y++);
		ExtendedAsciiPanel.writeCenter("|_____/  \\_/\\_/ \\__,_|_|  |_|  \\___\\_\\\\__,_|\\___||___/\\__|", y++);
		ExtendedAsciiPanel.writeCenter("", y++);
		//ExtendedAsciiPanel.writeCenter("+|=|+ Magic and Madness in the Caves of Chaos! +|=|+", y++);
		ExtendedAsciiPanel.writeCenter(language.titleScreen_Subtitle(), y++);
		ExtendedAsciiPanel.writeCenter("", y++);
		ExtendedAsciiPanel.writeCenter("                                   /////                                       ", y++);
		ExtendedAsciiPanel.writeCenter("                                   /////                                       ", y++);
		ExtendedAsciiPanel.writeCenter("                                   /////                                       ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////     ///////////////                             ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////     ///////////////                             ", y++);
		ExtendedAsciiPanel.writeCenter("                         //////////////////////////////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         //////////////////////////////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         //////////////////////////////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////%&&&&&&&&&&&&&&&&&&&/////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////%&&&&&&&&&&&&&&&&&&&/////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////%&&&&&&&&&&&&&&&&&&&/////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         //////////%&&&&&&&&&//////////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         //////////%&&&&&&&&&//////////                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         *****     %&&&&&&&&&     %%%%%                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         *****     %&&&&&&&&&     %%%%%                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         *****     %&&&&&&&&&     %%%%%                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         ***************%&&&&&&&&&&&&&&                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         ***************%&&&&&&&&&&&&&&                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         ***************%&&&&&&&&&&&&&&                        ", y++);
		ExtendedAsciiPanel.writeCenter("                    ##########%%%%%%&&&&&&&&&%%%%%##########                   ", y++);
		ExtendedAsciiPanel.writeCenter("                    ##########%%%%%%&&&&&&&&&%%%%%##########                   ", y++);
		ExtendedAsciiPanel.writeCenter("                    ##########%%%%%%&&&&&&&&&%%%%%##########                   ", y++);
		ExtendedAsciiPanel.writeCenter("               %&&&&%%%%%((((((((((((((((((((((((((((((%%%%%%&&&&              ", y++);
		ExtendedAsciiPanel.writeCenter("               %&&&&%%%%%((((((((((((((((((((((((((((((%%%%%%&&&&              ", y++);
		ExtendedAsciiPanel.writeCenter("               %&&&&%%%%%((((((((((((((((((((((((((((((%%%%%%&&&&              ", y++);
		ExtendedAsciiPanel.writeCenter("               *****     ,,,,,,,,,,((((((((((*,,,,,,,,,     /////              ", y++);
		ExtendedAsciiPanel.writeCenter("               *****     ,,,,,,,,,,((((((((((*,,,,,,,,,     /////              ", y++);
		ExtendedAsciiPanel.writeCenter("               *****     ,,,,,,,,,,((((((((((*,,,,,,,,,     /////              ", y++);
		ExtendedAsciiPanel.writeCenter("               %&&&&          ,,,,,,,,,,,,,,,*****          %&&&&              ", y++);
		ExtendedAsciiPanel.writeCenter("               %&&&&          ,,,,,,,,,,,,,,,*****          %&&&&              ", y++);
		ExtendedAsciiPanel.writeCenter("               %&&&&          ,,,,,,,,,,,,,,,*****          %&&&&              ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////*****          /////(((((                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////*****          /////(((((                        ", y++);
		ExtendedAsciiPanel.writeCenter("                         /////*****          /////(((((                        ", y++);
		ExtendedAsciiPanel.writeCenter(String.format("-- Version: rc-7-24092024 -- Press [%s] to Start -- Press [%s] to Exit --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm), KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 45);
		
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()) {
			case KeybindManager.navigateMenuConfirm: return new MainMenuScreen();
			case KeybindManager.navigateMenuBack: System.exit(0);
			default: return this;
		}
	}
	

}
