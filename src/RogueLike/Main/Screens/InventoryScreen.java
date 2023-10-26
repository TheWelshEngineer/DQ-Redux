package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Creature;
import RogueLike.Main.Effect;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Spell;
import RogueLike.Main.Items.Dice;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Inventory;
import asciiPanel.AsciiPanel;

public class InventoryScreen implements Screen{
	
	protected PlayScreen playScreen;
	protected Creature player;
	protected List<Effect> effects;
	protected Inventory inventory;
	private int sx;
	private int sy;
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public int itemsPerPage = 30;
	public int pageNumber = 1;
	
	public InventoryScreen(PlayScreen playScreen, Creature player, int sx, int sy) {
		this.playScreen = playScreen;
		this.player = player;
		this.effects = player.effects();
		this.inventory= player.inventory();
		this.sx = sx;
		this.sy = sy;
	}
	
	public boolean checkIfSelected(int index, int check) {
		if(index == check) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		//
		//
		terminal.clear();
		terminal.writeCenter("== Inventory ==", 1);	
		int y = 3;
		//
		pageNumber = ExtraMaths.spellbookPageNumber(check);
		//
		for(int i = (itemsPerPage*(pageNumber-1)); i < itemsPerPage*pageNumber; i++) {
			//System.out.println(String.format("i = %d", i));
			if(i < inventory.getItems().size()) {//spellbook.get(0).name()
				if(checkIfSelected(i, check)) {
					
					Item item = inventory.get(i);
					
					String equipped = "";
					if(item == player.weapon() || item == player.armor() || item == player.shield() || item == player.ring() || item == player.ammunition()) {
						equipped = " (equipped)";
					}
					
					terminal.write(String.format(">> %c %s x%d%s", item.glyph(), player.nameOf(item), item.stackAmount(), equipped, check), 5, y);
					terminal.write(String.format("%c", item.glyph()), 8, y++, item.color());
					int z = 3;
					int x = 42;
					terminal.write(String.format(">> %c %s", item.glyph(), player.nameOf(item)), x, z);
					terminal.write(String.format("%c", item.glyph()), x+3, z++, item.color());
					z++;
					if(item.isWeapon()) {
						if(item.isIdentified()) {
							String damage = "";
							Integer bonus = 0;
							if(item.isFinesse()) {
								bonus = item.upgradeLevel() + player.dexterityModifier();
							}else {
								bonus = item.upgradeLevel() + player.strengthModifier();
							}
							String symbol = "";
							if(bonus >= 0) {
								symbol = "+";
							}
							if(item.isVersatile()) {
								damage = String.format("%s%s%d / %s%s%d", item.damageDice().toString(), symbol, bonus, item.versatileDamageDice().toString(), symbol, bonus);
							}else {
								damage = String.format("%s%s%d", item.damageDice().toString(), symbol, bonus);
							}
							if(item.damageDice() == Dice.d1) {
								Integer amount = bonus+1;
								if(amount < 1) {
									amount = 1;
								}
								damage = String.format("%d", amount);
							}
							terminal.write(String.format("Damage: %s", damage), x, z++);
							if(item.thrownDamageDice() != null && item.thrownDamageDice() != Dice.d1) {
								damage = String.format("%s%s%d", item.thrownDamageDice().toString(), symbol, bonus);
								terminal.write(String.format("Thrown Damage: %s", damage), x, z++);
							}
							if(item.rangedDamageDice() != null) {
								bonus = item.upgradeLevel() + player.dexterityModifier();
								if(bonus >= 0) {
									symbol = "+";
								}
								damage = String.format("%s%s%d", item.rangedDamageDice().toString(), symbol, bonus);
								terminal.write(String.format("Ranged Damage: %s", damage), x, z++);
							}
							Integer attackbonus = 0;
							if(item.usesStrength()) {
								attackbonus = item.upgradeLevel() + player.strengthModifier() /*+ player.skillSimpleWeapons()*/;
							}
							if(item.usesIntelligence()) {
								attackbonus = item.upgradeLevel() + player.intelligenceModifier() /*+ player.skillMartialWeapons()*/;
							}
							if(item.usesDexterity()) {
								attackbonus = item.upgradeLevel() + player.dexterityModifier() /*+ player.skillFinesseWeapons()*/;
							}
							/*if(item.isRangedWeapon() > 0) {
								attackbonus = item.upgradeLevel() + player.dexterityModifier() + player.skillRangedWeapons();
							}*/
							symbol = "";
							if(attackbonus >= 0) {
								symbol = "+";
							}
							terminal.write(String.format("Accuracy Bonus: %s%d", symbol, attackbonus), x, z++);
							
							String type = "Physical";
							if(item.dealsFireDamage()) {
								type = "Fire";
							}
							if(item.dealsFrostDamage()) {
								type = "Frost";
							}
							if(item.dealsShockDamage()) {
								type = "Shock";
							}
							if(item.dealsPoisonDamage()) {
								type = "Poison";
							}
							if(item.dealsAcidDamage()) {
								type = "Acid";
							}
							if(item.dealsMagicDamage()) {
								type = "Magic";
							}
							if(item.dealsChaosDamage()) {
								type = "Chaos";
							}
							terminal.write(String.format("Damage Type: %s", type), x, z++);
							
							
							
							
							
							
							
							
						}else {
							String damage = "";
							Integer bonus = 0;
							if(item.usesStrength()) {
								bonus = player.strengthModifier();
							}else if(item.usesDexterity()) {
								bonus = player.dexterityModifier();
							}else if(item.usesIntelligence()) {
								bonus = player.intelligenceModifier();
							}else {
								bonus = player.strengthModifier();
							}
							String symbol = "";
							if(bonus >= 0) {
								symbol = "+";
							}
							if(item.isVersatile()) {
								damage = String.format("%s%s%d / %s%s%d (?)", item.damageDice().toString(), symbol, bonus, item.versatileDamageDice().toString(), symbol, bonus);
							}else {
								damage = String.format("%s%s%d (?)", item.damageDice().toString(), symbol, bonus);
							}
							if(item.damageDice() == Dice.d1) {
								Integer amount = bonus+1;
								if(amount < 1) {
									amount = 1;
								}
								damage = String.format("%d (?)", amount);
							}
							terminal.write(String.format("Damage: %s", damage), x, z++);
							if(item.thrownDamageDice() != null && item.thrownDamageDice() != Dice.d1) {
								damage = String.format("%s%s%d (?)", item.thrownDamageDice().toString(), symbol, bonus);
								terminal.write(String.format("Thrown Damage: %s", damage), x, z++);
							}
							if(item.rangedDamageDice() != null) {
								bonus = player.dexterityModifier();
								if(bonus >= 0) {
									symbol = "+";
								}
								damage = String.format("%s%s%d (?)", item.rangedDamageDice().toString(), symbol, bonus);
								terminal.write(String.format("Ranged Damage: %s", damage), x, z++);
							}
							Integer attackbonus = 0;
							if(item.usesStrength()) {
								attackbonus = player.strengthModifier() /*+ player.skillSimpleWeapons()*/;
							}
							if(item.usesIntelligence()) {
								attackbonus = player.intelligenceModifier() /*+ player.skillMartialWeapons()*/;
							}
							if(item.usesDexterity()) {
								attackbonus = player.dexterityModifier() /*+ player.skillRangedWeapons()*/;
							}
							symbol = "";
							if(attackbonus >= 0) {
								symbol = "+";
							}
							terminal.write(String.format("Accuracy Bonus: %s%d (?)", symbol, attackbonus), x, z++);
							
							terminal.write("Damage Type: Physical (?)", x, z++);
							
						}
						z++;
						
						String traits = "";
						String traits2 = "";
						String enchanted = "";
						if(item.isEnchanted() && item.isIdentified()) {
							enchanted = "Enchanted, ";
						}
						String upgraded = "";
						if(item.upgradeLevel() > 0 && item.isIdentified()) {
							upgraded = String.format("Upgraded (+%d), ", item.upgradeLevel());
						}
						String cursed = "";
						if(item.isCursed() && item.isCurseKnown()) {
							cursed = "Cursed, ";
						}
						String versatile = "";
						if(item.isVersatile()) {
							versatile = "Versatile, ";
						}
						String twohanded = "";
						if(item.isTwoHanded()) {
							twohanded = "Two-Handed, ";
						}
						String thrown = "";
						if(item.isThrownWeapon()) {
							thrown = "Thrown, ";
						}
						String strength = "";
						if(item.usesStrength()) {
							strength = "Uses Strength, ";
						}
						String dexterity = "";
						if(item.usesDexterity()) {
							dexterity = "Uses Dexterity, ";
						}
						String intelligence = "";
						if(item.usesIntelligence()) {
							intelligence = "Uses Intelligence, ";
						}
						String ranged = "";
						if(item.isRangedWeapon()) {
							ranged = "Ranged Weapon, ";
						}
						String flintlock = "";
						if(item.usesPowderAmmunition()) {
							flintlock = "Flintlock Weapon, ";
						}
						String arrows = "";
						if(item.usesArrowAmmunition()) {
							arrows = "Uses Arrows, ";
						}
						String bolts = "";
						if(item.usesBoltAmmunition()) {
							bolts = "Uses Bolts, ";
						}
						String powder = "";
						if(item.usesPowderAmmunition()) {
							powder = "Uses Powder, ";
						}
						
						traits = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s", enchanted, upgraded, cursed, versatile, twohanded, thrown, strength, dexterity, intelligence, ranged, flintlock, arrows, bolts, powder);
						if(x+traits.length() > 120) {
							traits = String.format("%s%s%s%s%s%s%s", enchanted, upgraded, cursed, versatile, twohanded, thrown, strength);
							traits2 = String.format("%s%s%s%s%s%s%s", dexterity, intelligence, ranged, flintlock, arrows, bolts, powder);
							terminal.write(String.format("Traits: %s", traits), x, z++);
							terminal.write(String.format("%s", traits2.replaceFirst(".$", "").replaceFirst(".$", "")), x+8, z++);
						}else {
							
							terminal.write(String.format("Traits: %s", traits.replaceFirst(".$", "").replaceFirst(".$", "")), x, z++);
						}
						z++;
					}
					
					if(item.isArmor() || item.isShield()) {
						String armor = "";
						if(item.isIdentified()) {
							armor = String.format("Armor Class: %d", item.armorClass()+item.upgradeLevel());
						}else {
							armor = String.format("Armor Class: %d (?)", item.armorClass());
						}
						terminal.write(armor, x, z++);
						
						String traits = "";
						String traits2 = "";
						String enchanted = "";
						if(item.isEnchanted() && item.isIdentified()) {
							enchanted = "Enchanted, ";
						}
						String upgraded = "";
						if(item.upgradeLevel() > 0 && item.isIdentified()) {
							upgraded = String.format("Upgraded (+%d), ", item.upgradeLevel());
						}
						String cursed = "";
						if(item.isCursed() && item.isCurseKnown()) {
							cursed = "Cursed, ";
						}
						String light = "";
						if(item.isLightArmor()) {
							light = "Light Armor, ";
						}
						String medium = "";
						if(item.isMediumArmor()) {
							medium = "Medium Armor, ";
						}
						String heavy = "";
						if(item.isHeavyArmor()) {
							heavy = "Heavy Armor, ";
						}
						String shield = "";
						if(item.isShield()) {
							shield = "Shield, ";
						}
						String tower = "";
						if(item.isTowerShield()) {
							tower = "Tower Shield, ";
						}
						
						traits = String.format("%s%s%s%s%s%s%s%s", enchanted, upgraded, cursed, light, medium, heavy, shield, tower);
						terminal.write(String.format("Traits: %s", traits.replaceFirst(".$", "").replaceFirst(".$", "")), x, z++);
						z++;
						
						
					}
					
					if(item.strengthRequirement() > 0 || item.dexterityRequirement() > 0 || item.intelligenceRequirement() > 0) {
						String skill = "";
						
						if(item.usesStrength()) {
							skill = String.format("Strength (%d)", item.strengthRequirement());
						}
						if(item.usesDexterity()) {
							skill = String.format("Dexterity (%d)", item.dexterityRequirement());
						}
						if(item.usesIntelligence()) {
							skill = String.format("Intelligence (%d)", item.intelligenceRequirement());
						}

						
						String skillprint = String.format("Prerequisites: %s", skill);
						if(skillprint.length()+x > 120){
							skillprint = String.format("Prerequisites: %s", skill);
							terminal.write(skillprint, x, z++);
						}else {
							terminal.write(skillprint, x, z++);
						}
						z++;

					}
					
					if(item.foodValue() > 0) {
						String food = "";
						if(item.foodValue() < 100) {
							food = "Disgusting..";
						}
						if(item.foodValue() < 300 && item.foodValue() >= 100) {
							food = "Tasty";
						}
						if(item.foodValue() < 600 && item.foodValue() >= 300) {
							food = "Satisfying";
						}
						if(item.foodValue() < 900 && item.foodValue() >= 600) {
							food = "Delicious!";
						}
						if(item.foodValue() >= 900) {
							food = "Incredible!";
						}
						
						terminal.write(String.format("Food Value: %s", food), x, z++);
					}
					
					if(item.quaffEffect() != null) {
						String effect = "Unknown";
						if(item.isIdentified()) {
							effect = item.potionName();
						}
						terminal.write(String.format("Potion Effect: %s", effect), x, z++);
					}
					
					
	
					
					
					
					if(item.isIdentified() || (item.foodValue() > 0 || item.isIronKey())) {
						terminal.write(String.format("Value: %s gold", item.currentGoldValue()), x, z++);
					}else {
						terminal.write(String.format("Value: %s gold (?)", item.baseGoldValue()), x, z++);
					}
					z++;
					if(item.quaffEffect() != null) {
						if(item.isIdentified()) {
							terminal.write(item.getDescription().getPotionDescriptionBase(), x, z++);
							terminal.write(item.getDescription().getPotionDescriptionKnown(), x, z++);
						}else {
							terminal.write(item.getDescription().getPotionDescriptionBase(), x, z++);
							terminal.write(item.getDescription().getPotionDescriptionUnknown(), x, z++);
						}
					}
					
					
					
					
					
					
					
					
					
					
					
				}else {

					String equipped = "";
					if(inventory.get(i) == player.weapon() || inventory.get(i) == player.armor() || inventory.get(i) == player.shield() || inventory.get(i) == player.ring() || inventory.get(i) == player.ammunition()) {
						equipped = " (equipped)";
					}
					
					terminal.write(String.format("%c %s x%d%s", inventory.get(i).glyph(), player.nameOf(inventory.get(i)), inventory.get(i).stackAmount(), equipped, check), 5, y);
					terminal.write(String.format("%c", inventory.get(i).glyph()), 5, y++, inventory.get(i).color());
				}
				if(checkIfSelected(i, check) && inventory.get(i).equippable()) {
					terminal.writeCenter("-- [X]: Equip --", 36);
				}
				if(checkIfSelected(i, check) && inventory.get(i).foodValue() > 0) {
					terminal.writeCenter("-- [E]: Eat --", 36);
				}
				if(checkIfSelected(i, check) && inventory.get(i).quaffEffect() != null) {
					terminal.writeCenter("-- [Q]: Quaff --", 36);
				}
				if(checkIfSelected(i, check) && inventory.get(i).writtenSpells().size() > 0) {
					terminal.writeCenter("-- [R]: Read --", 36);
				}
				
				
				
			}
		}
		//

		terminal.writeCenter("-- [D]: Drop --", 38);
        terminal.writeCenter("-- [UP / DOWN]: Move Selection | [ESCAPE]: Cancel --", 40);
		
	}
	
    /*protected Screen use(Spell spell){
    	if(spell.isSelfCast()) {
    		//player.doAction("check");
    		player.castSpell(spell, player.x(), player.y(), null);
    		return null;
    	}else {
    		//return null;
    		return new CastSpellScreen(player, "Cast spell at?", sx, sy, spell, null);
    	}
        //return new CastSpellScreen(player, "Cast spell at?", sx, sy, spell, item);
    }*/

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = inventory.getItems().size()-1;
			}else {
				check--;
			}
			return this;
		case KeyEvent.VK_DOWN:
			if(check == inventory.getItems().size()-1) {
				check = 0;
			}else {
				check++;
			}
			return this;
		case KeyEvent.VK_ESCAPE:
			return null;
		/*case KeyEvent.VK_ENTER:
			if(spellbook.getSpells().size() > 0) {
				return use(spellbook.get(check));
			}
			return null;*/
		case KeyEvent.VK_X:
			if(inventory.get(check).equippable()) {
				player.equip(inventory.get(check));
				playScreen.setInputAccepted(true);
				return null;
			}else {
				return this;
			}
		
		case KeyEvent.VK_D:
			player.drop(inventory.get(check));
			playScreen.setInputAccepted(true);
			return null;
			
		case KeyEvent.VK_E:
			if(inventory.get(check).foodValue() > 0) {
				player.eat(inventory.get(check));
				playScreen.setInputAccepted(true);
				return null;
			}else {
				return this;
			}
		
		case KeyEvent.VK_Q:
			if(inventory.get(check).quaffEffect() != null) {
				player.quaff(inventory.get(check));
				playScreen.setInputAccepted(true);
				return null;
			}else {
				return this;
			}
			
		case KeyEvent.VK_R:
			if(inventory.get(check).writtenSpells().size() > 0) {
				if(inventory.get(check).isSpellbook()){
					player.learnSpell(inventory.get(check).writtenSpells().get(0), inventory.get(check));
					playScreen.setInputAccepted(true);
					return null;
				}else {
					if(inventory.get(check).writtenSpells().size() == 1 && !inventory.get(check).writtenSpells().get(0).isSelfCast()) {
						return new CastSpellScreen(player, "Cast spell at?", sx, sy, inventory.get(check).writtenSpells().get(0), inventory.get(check));
					}else if(inventory.get(check).writtenSpells().size() == 1 && inventory.get(check).writtenSpells().get(0).isSelfCast()){
						player.castSpell(inventory.get(check).writtenSpells().get(0), player.x(), player.y(), inventory.get(check));
						playScreen.setInputAccepted(true);
						return null;
					}else {
						return new ReadSpellScreen(player, sx, sy, inventory.get(check));
					}
				}
			}else {
				return this;
			}
		
		default: return this;
		}
	}

}


