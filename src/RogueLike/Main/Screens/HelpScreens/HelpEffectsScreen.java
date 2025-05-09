package RogueLike.Main.Screens.HelpScreens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.Screen;

public class HelpEffectsScreen implements Screen{
	
	private boolean fromMenu;
	
	private int checkX = 0;
	private int checkY = 0;
	
	public char borderVertical = 186;
	public char borderHorizontal = 205;
	public char borderCorner = 206;
	public char borderCornerNW = 201;
	public char borderCornerNE = 187;
	public char borderCornerSW = 200;
	public char borderCornerSE = 188;
	
	//Positive markers
	public char beastLeft = '>';
	public char beastRight = '<';
	public char electroLeft = '>';
	public char electroRight = '<';
	public char giantLeft = '>';
	public char giantRight = '<';
	public char hasteLeft = '>';
	public char hasteRight = '<';
	public char illuminatedLeft = '>';
	public char illuminatedRight = '<';
	public char invisibleLeft = '>';
	public char invisibleRight = '<';
	public char levitatingLeft = '>';
	public char levitatingRight = '<';
	public char mindLeft = '>';
	public char mindRight = '<';
	
	//Positive Wards
	public char bladeLeft = '>';
	public char bladeRight = '<';
	public char magmaLeft = '>';
	public char magmaRight = '<';
	public char chillLeft = '>';
	public char chillRight = '<';
	public char arcLeft = '>';
	public char arcRight = '<';
	public char venomLeft = '>';
	public char venomRight = '<';
	public char causticLeft = '>';
	public char causticRight = '<';
	public char arcaneLeft = '>';
	public char arcaneRight = '<';
	public char eldritchLeft = '>';
	public char eldritchRight = '<';
	
	//Negative markers
	public char bleedingLeft = '>';
	public char bleedingRight = '<';
	public char blindedLeft = '>';
	public char blindedRight = '<';
	public char confusedLeft = '>';
	public char confusedRight = '<';
	public char corrodedLeft = '>';
	public char corrodedRight = '<';
	public char devouredLeft = '>';
	public char devouredRight = '<';
	public char electrifiedLeft = '>';
	public char electrifiedRight = '<';
	public char frozenLeft = '>';
	public char frozenRight = '<';
	public char ignitedLeft = '>';
	public char ignitedRight = '<';
	public char paralysedLeft = '>';
	public char paralysedRight = '<';
	public char poisonedLeft = '>';
	public char poisonedRight = '<';
	public char sunderedLeft = '>';
	public char sunderedRight = '<';
	
	public void changeMarkers(int checkX, int checkY) {
		if(checkX == 0) {
			if(checkY == 0) {
				beastLeft = '>';
				beastRight = '<';
				electroLeft = ' ';
				electroRight = ' ';
				giantLeft = ' ';
				giantRight = ' ';
				hasteLeft = ' ';
				hasteRight = ' ';
				illuminatedLeft = ' ';
				illuminatedRight = ' ';
				invisibleLeft = ' ';
				invisibleRight = ' ';
				levitatingLeft = ' ';
				levitatingRight = ' ';
				mindLeft = ' ';
				mindRight = ' ';
			}else if(checkY == 1) {
				beastLeft = ' ';
				beastRight = ' ';
				electroLeft = '>';
				electroRight = '<';
				giantLeft = ' ';
				giantRight = ' ';
				hasteLeft = ' ';
				hasteRight = ' ';
				illuminatedLeft = ' ';
				illuminatedRight = ' ';
				invisibleLeft = ' ';
				invisibleRight = ' ';
				levitatingLeft = ' ';
				levitatingRight = ' ';
				mindLeft = ' ';
				mindRight = ' ';
			}else if(checkY == 2) {
				beastLeft = ' ';
				beastRight = ' ';
				electroLeft = ' ';
				electroRight = ' ';
				giantLeft = '>';
				giantRight = '<';
				hasteLeft = ' ';
				hasteRight = ' ';
				illuminatedLeft = ' ';
				illuminatedRight = ' ';
				invisibleLeft = ' ';
				invisibleRight = ' ';
				levitatingLeft = ' ';
				levitatingRight = ' ';
				mindLeft = ' ';
				mindRight = ' ';
			}else if(checkY == 3) {
				beastLeft = ' ';
				beastRight = ' ';
				electroLeft = ' ';
				electroRight = ' ';
				giantLeft = ' ';
				giantRight = ' ';
				hasteLeft = '>';
				hasteRight = '<';
				illuminatedLeft = ' ';
				illuminatedRight = ' ';
				invisibleLeft = ' ';
				invisibleRight = ' ';
				levitatingLeft = ' ';
				levitatingRight = ' ';
				mindLeft = ' ';
				mindRight = ' ';
			}else if(checkY == 4) {
				beastLeft = ' ';
				beastRight = ' ';
				electroLeft = ' ';
				electroRight = ' ';
				giantLeft = ' ';
				giantRight = ' ';
				hasteLeft = ' ';
				hasteRight = ' ';
				illuminatedLeft = '>';
				illuminatedRight = '<';
				invisibleLeft = ' ';
				invisibleRight = ' ';
				levitatingLeft = ' ';
				levitatingRight = ' ';
				mindLeft = ' ';
				mindRight = ' ';
			}else if(checkY == 5) {
				beastLeft = ' ';
				beastRight = ' ';
				electroLeft = ' ';
				electroRight = ' ';
				giantLeft = ' ';
				giantRight = ' ';
				hasteLeft = ' ';
				hasteRight = ' ';
				illuminatedLeft = ' ';
				illuminatedRight = ' ';
				invisibleLeft = '>';
				invisibleRight = '<';
				levitatingLeft = ' ';
				levitatingRight = ' ';
				mindLeft = ' ';
				mindRight = ' ';
			}else if(checkY == 6) {
				beastLeft = ' ';
				beastRight = ' ';
				electroLeft = ' ';
				electroRight = ' ';
				giantLeft = ' ';
				giantRight = ' ';
				hasteLeft = ' ';
				hasteRight = ' ';
				illuminatedLeft = ' ';
				illuminatedRight = ' ';
				invisibleLeft = ' ';
				invisibleRight = ' ';
				levitatingLeft = '>';
				levitatingRight = '<';
				mindLeft = ' ';
				mindRight = ' ';
			}else if(checkY == 7) {
				beastLeft = ' ';
				beastRight = ' ';
				electroLeft = ' ';
				electroRight = ' ';
				giantLeft = ' ';
				giantRight = ' ';
				hasteLeft = ' ';
				hasteRight = ' ';
				illuminatedLeft = ' ';
				illuminatedRight = ' ';
				invisibleLeft = ' ';
				invisibleRight = ' ';
				levitatingLeft = ' ';
				levitatingRight = ' ';
				mindLeft = '>';
				mindRight = '<';
			}
		}else if(checkX == 1) {
			if(checkY == 0) {
				bleedingLeft = '>';
				bleedingRight = '<';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 1) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = '>';
				blindedRight = '<';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 2) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = '>';
				confusedRight = '<';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 3) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = '>';
				corrodedRight = '<';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 4) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = '>';
				devouredRight = '<';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 5) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = '>';
				electrifiedRight = '<';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 6) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = '>';
				frozenRight = '<';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 7) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = '>';
			    ignitedRight = '<';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 8) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = '>';
				paralysedRight = '<';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 9) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = '>';
				poisonedRight = '<';
				sunderedLeft = ' ';
				sunderedRight = ' ';
			}else if(checkY == 10) {
				bleedingLeft = ' ';
				bleedingRight = ' ';
				blindedLeft = ' ';
				blindedRight = ' ';
				confusedLeft = ' ';
				confusedRight = ' ';
				corrodedLeft = ' ';
				corrodedRight = ' ';
				devouredLeft = ' ';
				devouredRight = ' ';
				electrifiedLeft = ' ';
				electrifiedRight = ' ';
				frozenLeft = ' ';
				frozenRight = ' ';
				ignitedLeft = ' ';
			    ignitedRight = ' ';
				paralysedLeft = ' ';
				paralysedRight = ' ';
				poisonedLeft = ' ';
				poisonedRight = ' ';
				sunderedLeft = '>';
				sunderedRight = '<';
			}
		}else if(checkX == 2) {
			if(checkY == 0) {
				bladeLeft = '>';
				bladeRight = '<';
				magmaLeft = ' ';
				magmaRight = ' ';
				chillLeft = ' ';
				chillRight = ' ';
				arcLeft = ' ';
				arcRight = ' ';
				venomLeft = ' ';
				venomRight = ' ';
				causticLeft = ' ';
				causticRight = ' ';
				arcaneLeft = ' ';
				arcaneRight = ' ';
				eldritchLeft = ' ';
				eldritchRight = ' ';
			}else if(checkY == 1) {
				bladeLeft = ' ';
				bladeRight = ' ';
				magmaLeft = '>';
				magmaRight = '<';
				chillLeft = ' ';
				chillRight = ' ';
				arcLeft = ' ';
				arcRight = ' ';
				venomLeft = ' ';
				venomRight = ' ';
				causticLeft = ' ';
				causticRight = ' ';
				arcaneLeft = ' ';
				arcaneRight = ' ';
				eldritchLeft = ' ';
				eldritchRight = ' ';
			}else if(checkY == 2) {
				bladeLeft = ' ';
				bladeRight = ' ';
				magmaLeft = ' ';
				magmaRight = ' ';
				chillLeft = '>';
				chillRight = '<';
				arcLeft = ' ';
				arcRight = ' ';
				venomLeft = ' ';
				venomRight = ' ';
				causticLeft = ' ';
				causticRight = ' ';
				arcaneLeft = ' ';
				arcaneRight = ' ';
				eldritchLeft = ' ';
				eldritchRight = ' ';
			}else if(checkY == 3) {
				bladeLeft = ' ';
				bladeRight = ' ';
				magmaLeft = ' ';
				magmaRight = ' ';
				chillLeft = ' ';
				chillRight = ' ';
				arcLeft = '>';
				arcRight = '<';
				venomLeft = ' ';
				venomRight = ' ';
				causticLeft = ' ';
				causticRight = ' ';
				arcaneLeft = ' ';
				arcaneRight = ' ';
				eldritchLeft = ' ';
				eldritchRight = ' ';
			}else if(checkY == 4) {
				bladeLeft = ' ';
				bladeRight = ' ';
				magmaLeft = ' ';
				magmaRight = ' ';
				chillLeft = ' ';
				chillRight = ' ';
				arcLeft = ' ';
				arcRight = ' ';
				venomLeft = '>';
				venomRight = '<';
				causticLeft = ' ';
				causticRight = ' ';
				arcaneLeft = ' ';
				arcaneRight = ' ';
				eldritchLeft = ' ';
				eldritchRight = ' ';
			}else if(checkY == 5) {
				bladeLeft = ' ';
				bladeRight = ' ';
				magmaLeft = ' ';
				magmaRight = ' ';
				chillLeft = ' ';
				chillRight = ' ';
				arcLeft = ' ';
				arcRight = ' ';
				venomLeft = ' ';
				venomRight = ' ';
				causticLeft = '>';
				causticRight = '<';
				arcaneLeft = ' ';
				arcaneRight = ' ';
				eldritchLeft = ' ';
				eldritchRight = ' ';
			}else if(checkY == 6) {
				bladeLeft = ' ';
				bladeRight = ' ';
				magmaLeft = ' ';
				magmaRight = ' ';
				chillLeft = ' ';
				chillRight = ' ';
				arcLeft = ' ';
				arcRight = ' ';
				venomLeft = ' ';
				venomRight = ' ';
				causticLeft = ' ';
				causticRight = ' ';
				arcaneLeft = '>';
				arcaneRight = '<';
				eldritchLeft = ' ';
				eldritchRight = ' ';
			}else if(checkY == 7) {
				bladeLeft = ' ';
				bladeRight = ' ';
				magmaLeft = ' ';
				magmaRight = ' ';
				chillLeft = ' ';
				chillRight = ' ';
				arcLeft = ' ';
				arcRight = ' ';
				venomLeft = ' ';
				venomRight = ' ';
				causticLeft = ' ';
				causticRight = ' ';
				arcaneLeft = ' ';
				arcaneRight = ' ';
				eldritchLeft = '>';
				eldritchRight = '<';
			}
		}
	}
	
	public HelpEffectsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput() {
		changeMarkers(checkX, checkY);
		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		ExtendedAsciiPanel.writeCenter("== Help: Status Effects ==", 1);
		
		int y = 3;
		
		if(checkX == 0) {
			ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Beast Form %c     %c", borderVertical, beastLeft, beastRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Electrocharged %c %c", borderVertical, electroLeft, electroRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Giant Strength %c %c", borderVertical, giantLeft, giantRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Haste %c          %c", borderVertical, hasteLeft, hasteRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Illuminated %c    %c", borderVertical, illuminatedLeft, illuminatedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Invisible %c      %c", borderVertical, invisibleLeft, invisibleRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Levitating %c     %c", borderVertical, levitatingLeft, levitatingRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Mind Vision %c    %c", borderVertical, mindLeft, mindRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
			
			y = 0;
			ExtendedAsciiPanel.write("+||+ Positive Effects +||+", 29, y+=4);
			y++;
			y++;
			ExtendedAsciiPanel.write("Positive status effects can grant a range of benefits, making your", 34, y++);
			ExtendedAsciiPanel.write("exploration of the Caves of Chaos easier for a short time.", 34, y++);
			y++;
			y++;
			if(checkY == 0) {
				ExtendedAsciiPanel.write("+||+ Beast Form +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("The ferocity of beasts suffuses your body and mind, temporarily", 34, y++);
				ExtendedAsciiPanel.write("granting you a +4 bonus to your Dexterity and Vision Radius.", 34, y++);
			}else if(checkY == 1) {
				ExtendedAsciiPanel.write("+||+ Electrocharged +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are infused with electrical energy, dealing additional", 34, y++);
				ExtendedAsciiPanel.write("Shock damage to your targets on successful attacks.", 34, y++);
			}else if(checkY == 2) {
				ExtendedAsciiPanel.write("+||+ Giant Strength +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("The strength of giants flows through your muscles, temporarily", 34, y++);
				ExtendedAsciiPanel.write("granting you a +4 bonus to your Strength and Armor Class.", 34, y++);
			}else if(checkY == 3) {
				ExtendedAsciiPanel.write("+||+ Haste +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("Wild energy flows through you, doubling the speed at which you", 34, y++);
				ExtendedAsciiPanel.write("move, attack, and take actions.", 34, y++);
			}else if(checkY == 4) {
				ExtendedAsciiPanel.write("+||+ Illuminated +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are glowing with a bright light, illuminating your surroundings", 34, y++);
				ExtendedAsciiPanel.write("and granting a +10 bonus to your Vision Radius.", 34, y++);
			}else if(checkY == 5) {
				ExtendedAsciiPanel.write("+||+ Invisible +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You have become transparent, making you much harder to target.", 34, y++);
				ExtendedAsciiPanel.write("Attacking or casting a spell will break this effect, however.", 34, y++);
			}else if(checkY == 6) {
				ExtendedAsciiPanel.write("+||+ Levitating +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are floating in the air, allowing you to glide across", 34, y++);
				ExtendedAsciiPanel.write("pits and trapped floors safely.", 34, y++);
			}else if(checkY == 7) {
				ExtendedAsciiPanel.write("+||+ Mind Vision +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You can sense the presence of minds, temporarily allowing you to", 34, y++);
				ExtendedAsciiPanel.write("see creatures at any distance regardless of intervening obstacles.", 34, y++);
			}
			
		}else if(checkX == 1){
			ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Bleeding %c    %c", borderVertical, bleedingLeft, bleedingRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Blinded %c     %c", borderVertical, blindedLeft, blindedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Confused %c    %c", borderVertical, confusedLeft, confusedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Corroded %c    %c", borderVertical, corrodedLeft, corrodedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Devoured %c    %c", borderVertical, devouredLeft, devouredRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Electrified %c %c", borderVertical, electrifiedLeft, electrifiedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Frozen %c      %c", borderVertical, frozenLeft, frozenRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Ignited %c     %c", borderVertical, ignitedLeft, ignitedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Paralysed %c   %c", borderVertical, paralysedLeft, paralysedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Poisoned %c    %c", borderVertical, poisonedLeft, poisonedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Sundered %c    %c", borderVertical, sunderedLeft, sunderedRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
			
			y = 0;
			ExtendedAsciiPanel.write("+||+ Negative Effects +||+", 29, y+=4);
			y++;
			y++;
			ExtendedAsciiPanel.write("Negative status effects can hinder you in a variety of", 34, y++);
			ExtendedAsciiPanel.write("ways, adding new threats and challenges to your adventure.", 34, y++);
			y++;
			y++;
			if(checkY == 0) {
				ExtendedAsciiPanel.write("+||+ Bleeding +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are bleeding - you take 1d4 Physical damage each turn you are affected.", 34, y++);
			}else if(checkY == 1) {
				ExtendedAsciiPanel.write("+||+ Blinded +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You've been temporarily blinded, setting your base Vision Radius to 2.", 34, y++);
			}else if(checkY == 2) {
				ExtendedAsciiPanel.write("+||+ Confused +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are disoriented, and can't control the direction you move or attack in.", 34, y++);
			}else if(checkY == 3) {
				ExtendedAsciiPanel.write("+||+ Corroded +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are coated in acid - you take 1d4 Acid damage each turn you are affected", 34, y++);
				ExtendedAsciiPanel.write("and suffer a -2 penalty to your Armor Class whilst affected.", 34, y++);
			}else if(checkY == 4) {
				ExtendedAsciiPanel.write("+||+ Devoured +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("A devouring curse is eating away at your life force, draining 1d4", 34, y++);
				ExtendedAsciiPanel.write("Health and Mana each turn you are affected.", 34, y++);
			}else if(checkY == 5) {
				ExtendedAsciiPanel.write("+||+ Electrified +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("An electric shock has disrupted your magical reserves - you immediately", 34, y++);
				ExtendedAsciiPanel.write("take 1d6 Shock damage, then lose 1d4 Mana each turn you are affected.", 34, y++);
			}else if(checkY == 6) {
				ExtendedAsciiPanel.write("+||+ Frozen +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are frozen solid, and are completely unable to move.", 34, y++);
			}else if(checkY == 7) {
				ExtendedAsciiPanel.write("+||+ Ignited +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You have been set on fire, taking 1d4 Fire damage each turn you are affected.", 34, y++);
			}else if(checkY == 8) {
				ExtendedAsciiPanel.write("+||+ Paralysed +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("Your muscles have seized up, preventing you from moving.", 34, y++);
			}else if(checkY == 9) {
				ExtendedAsciiPanel.write("+||+ Poisoned +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You have been poisoned - you take 1d4 Poison damage each turn you are affected.", 34, y++);
			}else if(checkY == 10) {
				ExtendedAsciiPanel.write("+||+ Sundered +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("Your armor has been sundered, limiting your Armor Class", 34, y++);
				ExtendedAsciiPanel.write("to a maximum of 10 for the duration.", 34, y++);
			}
			
		}else if(checkX == 2) {
			ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Blade Ward %c    %c", borderVertical, bladeLeft, bladeRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Magma Ward %c    %c", borderVertical, magmaLeft, magmaRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Chill Ward %c    %c", borderVertical, chillLeft, chillRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Arc Ward %c      %c", borderVertical, arcLeft, arcRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Venomous Ward %c %c", borderVertical, venomLeft, venomRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Caustic Ward %c  %c", borderVertical, causticLeft, causticRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Arcane Ward %c   %c", borderVertical, arcaneLeft, arcaneRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c %c Eldritch Ward %c %c", borderVertical, eldritchLeft, eldritchRight, borderVertical), 5, y++);
			ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
			
			y = 0;
			ExtendedAsciiPanel.write("+||+ Ward Effects +||+", 29, y+=4);
			y++;
			y++;
			ExtendedAsciiPanel.write("Ward status effects provide both an immediate defensive effect", 34, y++);
			ExtendedAsciiPanel.write("and an ongoing protection from a given damage type, granting", 34, y++);
			ExtendedAsciiPanel.write("you increased defenses against the perils of the Caves.", 34, y++);
			y++;
			if(checkY == 0) {
				ExtendedAsciiPanel.write("+||+ Blade Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("A storm of metal shards shields you, immediately applying", 34, y++);
				ExtendedAsciiPanel.write("Bleeding to adjacent foes and granting you Resistance to", 34, y++);
				ExtendedAsciiPanel.write("Physical damage for the duration.", 34, y++);
			}else if(checkY == 1) {
				ExtendedAsciiPanel.write("+||+ Magma Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("A shield of flames surrounds you, immediately applying", 34, y++);
				ExtendedAsciiPanel.write("Ignited to adjacent foes and granting you Resistance to", 34, y++);
				ExtendedAsciiPanel.write("Fire damage for the duration.", 34, y++);
			}else if(checkY == 2) {
				ExtendedAsciiPanel.write("+||+ Chill Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("You are shielded by freezing winds, immediately applying", 34, y++);
				ExtendedAsciiPanel.write("Frozen to adjacent foes and granting you Resistance to", 34, y++);
				ExtendedAsciiPanel.write("Frost damage for the duration.", 34, y++);
			}else if(checkY == 3) {
				ExtendedAsciiPanel.write("+||+ Arc Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("A shroud of crackling lightning protects you, immediately dealing", 34, y++);
				ExtendedAsciiPanel.write("1d4 Shock damage and draining 1d4 Mana from adjacent foes and granting", 34, y++);
				ExtendedAsciiPanel.write("you Resistance to Shock damage for the duration.", 34, y++);
			}else if(checkY == 4) {
				ExtendedAsciiPanel.write("+||+ Venomous Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("A shield of protective poison surrounds you, immediately applying", 34, y++);
				ExtendedAsciiPanel.write("Poisoned to adjacent foes and granting you Resistance to", 34, y++);
				ExtendedAsciiPanel.write("Poison damage for the duration.", 34, y++);
			}else if(checkY == 5) {
				ExtendedAsciiPanel.write("+||+ Caustic Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("A veil of protective acid shields you, immediately applying", 34, y++);
				ExtendedAsciiPanel.write("Corroded to adjacent foes and granting you Resistance to", 34, y++);
				ExtendedAsciiPanel.write("Acid damage for the duration.", 34, y++);
			}else if(checkY == 6) {
				ExtendedAsciiPanel.write("+||+ Arcane Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("An arcane shield surrounds you, immediately", 34, y++);
				ExtendedAsciiPanel.write("teleporting adjacent foes away from you and granting you", 34, y++);
				ExtendedAsciiPanel.write("Resistance to Magic damage for the duration.", 34, y++);
			}else if(checkY == 7) {
				ExtendedAsciiPanel.write("+||+ Eldritch Ward +||+", 29, y++);
				y++;
				ExtendedAsciiPanel.write("A shroud of chaotic energy protects you, immediately applying", 34, y++);
				ExtendedAsciiPanel.write("Devoured to adjacent foes and granting you Resistance to", 34, y++);
				ExtendedAsciiPanel.write("Chaos damage for the duration.", 34, y++);
			}
			
		}

        ExtendedAsciiPanel.writeCenter(String.format("-- [%s / %s]: Move Selection --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown)), 34);
        if(checkX == 0) {
        	ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Ward Effects | [%s]: Negative Effects --", KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 36);
        }else if(checkX == 1) {
        	ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Positive Effects | [%s]: Ward Effects --", KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 36);
        }else if(checkX == 2) {
        	ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Negative Effects | [%s]: Positive Effects --", KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 36);
        }
        ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
        
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuLeft:
			checkY = 0;
			if(checkX == 0) {
				checkX = 2;
			}else {
				checkX--;
			}
			break;
			
		case KeybindManager.navigateMenuRight:
			checkY = 0;
			if(checkX == 2) {
				checkX = 0;
			}else {
				checkX++;
			}
			break;
			
		case KeybindManager.navigateMenuUp:
			if(checkX == 0) {
				if(checkY == 0) {
					checkY = 7;
				}else {
					checkY--;
				}
			}else if(checkX == 1) {
				if(checkY == 0) {
					checkY = 10;
				}else {
					checkY--;
				}
			}else if(checkX == 2) {
				if(checkY == 0) {
					checkY = 7;
				}else {
					checkY--;
				}
			}
			break;
			
		case KeybindManager.navigateMenuDown:
			if(checkX == 0) {
				if(checkY == 7) {
					checkY = 0;
				}else {
					checkY++;
				}
			}else if(checkX == 1) {
				if(checkY == 10) {
					checkY = 0;
				}else {
					checkY++;
				}
			}else if(checkX == 2) {
				if(checkY == 7) {
					checkY = 0;
				}else {
					checkY++;
				}
			}
			break;
		
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
