package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class StartScreen implements Screen{

	public void displayOutput(AsciiPanel terminal) {
		int y = 1;
		terminal.writeCenter(" _____                      __  ____                  _   ", y++);
		terminal.writeCenter("|  __ \\                    / _|/ __ \\                | |  ", y++);
		terminal.writeCenter("| |  | |_      ____ _ _ __| |_| |  | |_   _  ___  ___| |_ ", y++);
		terminal.writeCenter("| |  | \\ \\ /\\ / / _` | '__|  _| |  | | | | |/ _ \\/ __| __|", y++);
		terminal.writeCenter("| |__| |\\ V  V / (_| | |  | | | |__| | |_| |  __/\\__ \\ |_ ", y++);
		terminal.writeCenter("|_____/  \\_/\\_/ \\__,_|_|  |_|  \\___\\_\\\\__,_|\\___||___/\\__|", y++);
		terminal.writeCenter("", y++);
		terminal.writeCenter("+|=|+ Realm of the Nightmare Queen +|=|+", y++);
		terminal.writeCenter("", y++);
		terminal.writeCenter("                                   /////                                       ", y++);
		terminal.writeCenter("                                   /////                                       ", y++);
		terminal.writeCenter("                                   /////                                       ", y++);
		terminal.writeCenter("                         /////     ///////////////                             ", y++);
		terminal.writeCenter("                         /////     ///////////////                             ", y++);
		terminal.writeCenter("                         //////////////////////////////                        ", y++);
		terminal.writeCenter("                         //////////////////////////////                        ", y++);
		terminal.writeCenter("                         //////////////////////////////                        ", y++);
		terminal.writeCenter("                         /////%&&&&&&&&&&&&&&&&&&&/////                        ", y++);
		terminal.writeCenter("                         /////%&&&&&&&&&&&&&&&&&&&/////                        ", y++);
		terminal.writeCenter("                         /////%&&&&&&&&&&&&&&&&&&&/////                        ", y++);
		terminal.writeCenter("                         //////////%&&&&&&&&&//////////                        ", y++);
		terminal.writeCenter("                         //////////%&&&&&&&&&//////////                        ", y++);
		terminal.writeCenter("                         *****     %&&&&&&&&&     %%%%%                        ", y++);
		terminal.writeCenter("                         *****     %&&&&&&&&&     %%%%%                        ", y++);
		terminal.writeCenter("                         *****     %&&&&&&&&&     %%%%%                        ", y++);
		terminal.writeCenter("                         ***************%&&&&&&&&&&&&&&                        ", y++);
		terminal.writeCenter("                         ***************%&&&&&&&&&&&&&&                        ", y++);
		terminal.writeCenter("                         ***************%&&&&&&&&&&&&&&                        ", y++);
		terminal.writeCenter("                    ##########%%%%%%&&&&&&&&&%%%%%##########                   ", y++);
		terminal.writeCenter("                    ##########%%%%%%&&&&&&&&&%%%%%##########                   ", y++);
		terminal.writeCenter("                    ##########%%%%%%&&&&&&&&&%%%%%##########                   ", y++);
		terminal.writeCenter("               %&&&&%%%%%((((((((((((((((((((((((((((((%%%%%%&&&&              ", y++);
		terminal.writeCenter("               %&&&&%%%%%((((((((((((((((((((((((((((((%%%%%%&&&&              ", y++);
		terminal.writeCenter("               %&&&&%%%%%((((((((((((((((((((((((((((((%%%%%%&&&&              ", y++);
		terminal.writeCenter("               *****     ,,,,,,,,,,((((((((((*,,,,,,,,,     /////              ", y++);
		terminal.writeCenter("               *****     ,,,,,,,,,,((((((((((*,,,,,,,,,     /////              ", y++);
		terminal.writeCenter("               *****     ,,,,,,,,,,((((((((((*,,,,,,,,,     /////              ", y++);
		terminal.writeCenter("               %&&&&          ,,,,,,,,,,,,,,,*****          %&&&&              ", y++);
		terminal.writeCenter("               %&&&&          ,,,,,,,,,,,,,,,*****          %&&&&              ", y++);
		terminal.writeCenter("               %&&&&          ,,,,,,,,,,,,,,,*****          %&&&&              ", y++);
		terminal.writeCenter("                         /////*****          /////(((((                        ", y++);
		terminal.writeCenter("                         /////*****          /////(((((                        ", y++);
		terminal.writeCenter("                         /////*****          /////(((((                        ", y++);
		terminal.writeCenter("-- beta v16202022 -- Press [ENTER] to Start --", 45);
	}

	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new MainMenuScreen() : this;
	}
	

}
