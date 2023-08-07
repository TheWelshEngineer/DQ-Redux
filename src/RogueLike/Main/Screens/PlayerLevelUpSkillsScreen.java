/*package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Creature;
import asciiPanel.AsciiPanel;

public class PlayerLevelUpSkillsScreen implements Screen{
	
	protected Creature player;
	
	public int points;
	public void modifyPoints(int amount) {
		points += amount;
	}
	 
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public int playerSimpleWeapons;
	public void modifySimpleWeapons(int amount) {
		playerSimpleWeapons += amount;
	}
	public int playerMartialWeapons;
	public void modifyMartialWeapons(int amount) {
		playerMartialWeapons += amount;
	}
	public int playerFinesseWeapons;
	public void modifyFinesseWeapons(int amount) {
		playerFinesseWeapons += amount;
	}
	public int playerRangedWeapons;
	public void modifyRangedWeapons(int amount) {
		playerRangedWeapons += amount;
	}
	public int playerFortitude;
	public void modifyFortitude(int amount) {
		playerFortitude += amount;
	}
	public int playerPerception;
	public void modifyPerception(int amount) {
		playerPerception += amount;
	}
	public int playerStealth = 0;
	public void modifyStealth(int amount) {
		playerStealth += amount;
	}
	public int playerEvocation;
	public void modifyEvocation(int amount) {
		playerEvocation += amount;
	}
	public int playerPyromancy;
	public void modifyPyromancy(int amount) {
		playerPyromancy += amount;
	}
	public int playerCryomancy;
	public void modifyCryomancy(int amount) {
		playerCryomancy += amount;
	}
	public int playerElectromancy;
	public void modifyElectromancy(int amount) {
		playerElectromancy += amount;
	}
	public int playerAlchemancy;
	public void modifyAlchemancy(int amount) {
		playerAlchemancy += amount;
	}
	
	public char simpleLeft = '>';
	public char simpleRight = '<';
	public char martialLeft = '>';
	public char martialRight = '<';
	public char finesseLeft = '>';
	public char finesseRight = '<';
	public char rangedLeft = '>';
	public char rangedRight = '<';
	public char fortitudeLeft = '>';
	public char fortitudeRight = '<';
	public char percepLeft = '>';
	public char percepRight = '<';
	public char stealthLeft = '>';
	public char stealthRight = '<';
	public char evoLeft = '>';
	public char evoRight = '<';
	public char pyroLeft = '>';
	public char pyroRight = '<';
	public char cryoLeft = '>';
	public char cryoRight = '<';
	public char electroLeft = '>';
	public char electroRight = '<';
	public char alchLeft = '>';
	public char alchRight = '<';
	
	public void changeMarkers(int check) {
		if(check == 0) {
			simpleLeft = '>';
			simpleRight = '<';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 1) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = '>';
			martialRight = '<';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 2) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = '>';
			finesseRight = '<';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 3) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = '>';
			rangedRight = '<';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 4) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = '>';
			fortitudeRight = '<';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 5) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = '>';
			percepRight = '<';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 6) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = '>';
			stealthRight = '<';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 7) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = '>';
			evoRight = '<';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 8) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = '>';
			pyroRight = '<';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 9) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = '>';
			cryoRight = '<';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 10) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = '>';
			electroRight = '<';
			alchLeft = ' ';
			alchRight = ' ';
		}else if(check == 11) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = '>';
			alchRight = '<';
		}
	}
	
	public PlayerLevelUpSkillsScreen(Creature player) {
		this.player = player;
		this.points = player.skillPoints();
		this.playerSimpleWeapons = player.skillSimpleWeapons();
		this.playerMartialWeapons = player.skillMartialWeapons();
		this.playerFinesseWeapons = player.skillFinesseWeapons();
		this.playerRangedWeapons = player.skillRangedWeapons();
		this.playerFortitude = player.skillFortitude();
		this.playerPerception = player.skillPerception();
		this.playerStealth = player.skillStealth();
		this.playerEvocation = player.skillEvocation();
		this.playerPyromancy = player.skillPyromancy();
		this.playerCryomancy = player.skillCryomancy();
		this.playerElectromancy = player.skillElectromancy();
		this.playerAlchemancy = player.skillAlchemancy();
	}

	@Override
	public void displayOutput(AsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		terminal.writeCenter("== Increase your Skills ==", 1);	
		int y = 3;
		
		terminal.writeCenter(String.format("-- Points remaining: %d --", points), y);
		
		terminal.writeCenter(String.format("%c Simple Weapons ( %d ) %c", simpleLeft, playerSimpleWeapons, simpleRight), y+=3);
		terminal.writeCenter(String.format("%c Martial Weapons ( %d ) %c", martialLeft, playerMartialWeapons, martialRight), y+=2);
		terminal.writeCenter(String.format("%c Finesse Weapons ( %d ) %c", finesseLeft, playerFinesseWeapons, finesseRight), y+=2);
		terminal.writeCenter(String.format("%c Ranged Weapons ( %d ) %c", rangedLeft, playerRangedWeapons, rangedRight), y+=2);
		terminal.writeCenter(String.format("%c Fortitude ( %d ) %c", fortitudeLeft, playerFortitude, fortitudeRight), y+=2);
		terminal.writeCenter(String.format("%c Perception ( %d ) %c", percepLeft, playerPerception, percepRight), y+=2);
		terminal.writeCenter(String.format("%c Stealth ( %d ) %c", stealthLeft, playerStealth, stealthRight), y+=2);
		terminal.writeCenter(String.format("%c Evocation ( %d ) %c", evoLeft, playerEvocation, evoRight), y+=2);
		terminal.writeCenter(String.format("%c Pyromancy ( %d ) %c", pyroLeft, playerPyromancy, pyroRight), y+=2);
		terminal.writeCenter(String.format("%c Cryomancy ( %d ) %c", cryoLeft, playerCryomancy, cryoRight), y+=2);
		terminal.writeCenter(String.format("%c Electromancy ( %d ) %c", electroLeft, playerElectromancy, electroRight), y+=2);
		terminal.writeCenter(String.format("%c Alchemancy ( %d ) %c", alchLeft, playerAlchemancy, alchRight), y+=2);
		
		if(check == 0) {
			terminal.writeCenter("Simple Weapons represents your skill with simple weapons", y+=4);
			terminal.writeCenter("such as clubs and handaxes. A higher Simple Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful simple weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 1) {
			terminal.writeCenter("Martial Weapons represents your skill with martial weapons", y+=4);
			terminal.writeCenter("such as longswords and battleaxes. A higher Martial Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful martial weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 2) {
			terminal.writeCenter("Finesse Weapons represents your skill with finesse weapons", y+=4);
			terminal.writeCenter("such as daggers and rapiers. A higher Finesse Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful finesse weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 3) {
			terminal.writeCenter("Ranged Weapons represents your skill with ranged weapons", y+=4);
			terminal.writeCenter("such as bows and crossbows. A higher Ranged Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful ranged weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 4) {
			terminal.writeCenter("Fortitude is a measure of your stamina and endurance.", y+=4);
			terminal.writeCenter("A higher Fortitude skill allows you to wear heavier armor,", y+=1);
			terminal.writeCenter("and increases the amount of time you can go without eating.", y+=1);
		}else if(check == 5) {
			terminal.writeCenter("Perception is a measure of your awareness and eye for detail.", y+=4);
			terminal.writeCenter("A higher Perception skill allows you to spot traps and other", y+=1);
			terminal.writeCenter("hidden things with greater ease, as well as improving", y+=1);
			terminal.writeCenter("your chances of identifying unknown items.", y+=1);
		}else if(check == 6) {
			terminal.writeCenter("Stealth represents your ability to move unnoticed.", y+=4);
			terminal.writeCenter("A higher Stealth skill allows you to sneak past sleeping", y+=1);
			terminal.writeCenter("and unalerted foes more reliably, giving you the upper", y+=1);
			terminal.writeCenter("hand in combat and making it easier to avoid a fight.", y+=1);
		}else if(check == 7) {
			terminal.writeCenter("Evocation is a school of magic focused mainly on the", y+=4);
			terminal.writeCenter("manipulation of raw magic. A higher Evocation skill", y+=1);
			terminal.writeCenter("allows you to use more powerful evocation wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your evocation wands.", y+=1);
		}else if(check == 8) {
			terminal.writeCenter("Pyromancy is a school of magic focused mainly on the", y+=4);
			terminal.writeCenter("manipulation of fire and heat. A higher Pyromancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful evocation wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your evocation wands.", y+=1);
		}else if(check == 9) {
			terminal.writeCenter("Cryomancy is a school of magic focused mainly on the", y+=4);
			terminal.writeCenter("manipulation of water and ice. A higher Cryomancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful cryomancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your cryomancy wands.", y+=1);
		}else if(check == 10) {
			terminal.writeCenter("Electromancy is a school of magic focused mainly on the", y+=4);
			terminal.writeCenter("manipulation of electrical energy. A higher Electromancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful electromancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your electromancy wands.", y+=1);
		}else if(check == 11) {
			terminal.writeCenter("Alchemancy is a school of magic focused mainly on the", y+=4);
			terminal.writeCenter("manipulation of chemical processes. A higher Alchemancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful alchemancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your alchemancy wands.", y+=1);
		}
		
		
		if(points < 1) {
			terminal.writeCenter("-- [ENTER]: Confirm and Continue --", 38);
		}
		terminal.writeCenter("-- [UP / DOWN]: Move Selection | [LEFT / RIGHT]: Increase/Decrease Skill --", 40);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = 11;
			}else if(check == 1) {
				check = 0;
			}else if(check == 2) {
				check = 1;
			}else if(check == 3) {
				check = 2;
			}else if(check == 4) {
				check = 3;
			}else if(check == 5) {
				check = 4;
			}else if(check == 6) {
				check = 5;
			}else if(check == 7) {
				check = 6;
			}else if(check == 8) {
				check = 7;
			}else if(check == 9) {
				check = 8;
			}else if(check == 10) {
				check = 11;
			}else if(check == 11) {
				check = 0;
			}
			return this;
			
		case KeyEvent.VK_DOWN:
			if(check == 0) {
				check = 1;
			}else if(check == 1) {
				check = 2;
			}else if(check == 2) {
				check = 3;
			}else if(check == 3) {
				check = 4;
			}else if(check == 4) {
				check = 5;
			}else if(check == 5) {
				check = 6;
			}else if(check == 6) {
				check = 7;
			}else if(check == 7) {
				check = 8;
			}else if(check == 8) {
				check = 9;
			}else if(check == 9) {
				check = 10;
			}else if(check == 10) {
				check = 11;
			}else if(check == 11) {
				check = 0;
			}
			return this;
		
		case KeyEvent.VK_RIGHT:
			if(check == 0) {
				if(points > 0) {
					modifySimpleWeapons(1); 
					modifyPoints(-1); 
				}
			}else if(check == 1) {
				if(points > 0) {
					modifyMartialWeapons(1); 
					modifyPoints(-1); 
				}
			}else if(check == 2) {
				if(points > 0) {
					modifyFinesseWeapons(1); 
					modifyPoints(-1); 
				}
			}else if(check == 3) {
				if(points > 0) {
					modifyRangedWeapons(1); 
					modifyPoints(-1); 
				}
			}else if(check == 4) {
				if(points > 0) {
					modifyFortitude(1); 
					modifyPoints(-1); 
				}
			}else if(check == 5) {
				if(points > 0) {
					modifyPerception(1); 
					modifyPoints(-1); 
				}
			}else if(check == 6) {
				if(points > 0) {
					modifyStealth(1); 
					modifyPoints(-1); 
				} 
			}else if(check == 7) {
				if(points > 0) {
					modifyEvocation(1); 
					modifyPoints(-1); 
				} 
			}else if(check == 8) {
				if(points > 0) {
					modifyPyromancy(1); 
					modifyPoints(-1); 
				}
			}else if(check == 9) {
				if(points > 0) {
					modifyCryomancy(1); 
					modifyPoints(-1); 
				}
			}else if(check == 10) {
				if(points > 0) {
					modifyElectromancy(1); 
					modifyPoints(-1); 
				}
			}else if(check == 11) {
				if(points > 0) {
					modifyAlchemancy(1); 
					modifyPoints(-1); 
				}
			}	
			return this;
			
		case KeyEvent.VK_LEFT:
			if(check == 0) {
				if(playerSimpleWeapons > player.skillSimpleWeapons()) {
					modifySimpleWeapons(-1); 
					modifyPoints(1); 
				}
			}else if(check == 1) {
				if(playerMartialWeapons > player.skillMartialWeapons()) {
					modifyMartialWeapons(-1); 
					modifyPoints(1); 
				} 
			}else if(check == 2) {
				if(playerFinesseWeapons > player.skillFinesseWeapons()) {
					modifyFinesseWeapons(-1); 
					modifyPoints(1); 
				}
			}else if(check == 3) {
				if(playerRangedWeapons > player.skillRangedWeapons()) {
					modifyRangedWeapons(-1); 
					modifyPoints(1); 
				}
			}else if(check == 4) {
				if(playerFortitude > player.skillFortitude()) {
					modifyFortitude(-1); 
					modifyPoints(1); 
				}
			}else if(check == 5) {
				if(playerPerception > player.skillPerception()) {
					modifyPerception(-1); 
					modifyPoints(1); 
				}
			}else if(check == 6) {
				if(playerStealth > player.skillStealth()) {
					modifyStealth(-1); 
					modifyPoints(1); 
				}
			}else if(check == 7) {
				if(playerEvocation > player.skillEvocation()) {
					modifyEvocation(-1); 
					modifyPoints(1); 
				}
			}else if(check == 8) {
				if(playerPyromancy > player.skillPyromancy()) {
					modifyPyromancy(-1); 
					modifyPoints(1); 
				} 
			}else if(check == 9) {
				if(playerCryomancy > player.skillCryomancy()) {
					modifyCryomancy(-1); 
					modifyPoints(1); 
				} 
			}else if(check == 10) {
				if(playerElectromancy > player.skillElectromancy()) {
					modifyElectromancy(-1); 
					modifyPoints(1); 
				}
			}else if(check == 11) {
				if(playerAlchemancy > player.skillAlchemancy()) {
					modifyAlchemancy(-1); 
					modifyPoints(1); 
				} 
			}
				
				
			return this;
		
			
		case KeyEvent.VK_ENTER: 
			if(points < 1) {
				player.setSkillSimpleWeapons(playerSimpleWeapons);
				player.setSkillMartialWeapons(playerMartialWeapons);
				player.setSkillFinesseWeapons(playerFinesseWeapons);
				player.setSkillRangedWeapons(playerRangedWeapons);
				player.setSkillFortitude(playerFortitude);
				player.setSkillPerception(playerPerception);
				player.setSkillStealth(playerStealth);
				player.setSkillEvocation(playerEvocation);
				player.setSkillPyromancy(playerPyromancy);
				player.setSkillCryomancy(playerCryomancy);
				player.setSkillElectromancy(playerElectromancy);
				player.setSkillAlchemancy(playerAlchemancy);
				player.setSkillPoints(0);
				return null;
			}else {
				return this;
			}
		
		}
		
		return this;
	}

}

*/