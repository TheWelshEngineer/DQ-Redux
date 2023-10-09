package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Creature;
import RogueLike.Main.Effect;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Spell;
import RogueLike.Main.Inventory;
import RogueLike.Main.Item;
import asciiPanel.AsciiPanel;

public class InventoryScreen implements Screen{
	
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
	
	public InventoryScreen(Creature player, int sx, int sy) {
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
					
					terminal.write(String.format(">> %c %s x%d%s", item.glyph(), player.nameOf(item), item.stackAmount(), equipped, check), 5, y++);
					int z = 3;
					int x = 42;
					terminal.write(String.format(">> %c %s", item.glyph(), player.nameOf(item)), x, z++);
					z++;
					if(item.isWeapon()) {
						if(item.isIdentified() > 0) {
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
								damage = String.format("1d%d%s%d / 1d%d%s%d", item.damageDice(), symbol, bonus, item.versatileDamageDice(), symbol, bonus);
							}else {
								damage = String.format("1d%d%s%d", item.damageDice(), symbol, bonus);
							}
							if(item.damageDice() == 1) {
								Integer amount = bonus+1;
								if(amount < 1) {
									amount = 1;
								}
								damage = String.format("%d", amount);
							}
							terminal.write(String.format("Damage: %s", damage), x, z++);
							if(item.thrownDamageDice() > 1) {
								damage = String.format("1d%d%s%d", item.thrownDamageDice(), symbol, bonus);
								terminal.write(String.format("Thrown Damage: %s", damage), x, z++);
							}
							if(item.rangedDamageDice() > 0) {
								bonus = item.upgradeLevel() + player.dexterityModifier();
								if(bonus >= 0) {
									symbol = "+";
								}
								damage = String.format("1d%d%s%d", item.rangedDamageDice(), symbol, bonus);
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
							if(item.dealsFire() > 0) {
								type = "Fire";
							}
							if(item.dealsFrost() > 0) {
								type = "Frost";
							}
							if(item.dealsShock() > 0) {
								type = "Shock";
							}
							if(item.dealsPoison() > 0) {
								type = "Poison";
							}
							if(item.dealsAcid() > 0) {
								type = "Acid";
							}
							if(item.dealsMagic() > 0) {
								type = "Magic";
							}
							if(item.dealsChaos() > 0) {
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
								damage = String.format("1d%d%s%d / 1d%d%s%d (?)", item.damageDice(), symbol, bonus, item.versatileDamageDice(), symbol, bonus);
							}else {
								damage = String.format("1d%d%s%d (?)", item.damageDice(), symbol, bonus);
							}
							if(item.damageDice() == 1) {
								Integer amount = bonus+1;
								if(amount < 1) {
									amount = 1;
								}
								damage = String.format("%d (?)", amount);
							}
							terminal.write(String.format("Damage: %s", damage), x, z++);
							if(item.thrownDamageDice() > 1) {
								damage = String.format("1d%d%s%d (?)", item.thrownDamageDice(), symbol, bonus);
								terminal.write(String.format("Thrown Damage: %s", damage), x, z++);
							}
							if(item.rangedDamageDice() > 0) {
								bonus = player.dexterityModifier();
								if(bonus >= 0) {
									symbol = "+";
								}
								damage = String.format("1d%d%s%d (?)", item.rangedDamageDice(), symbol, bonus);
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
						if(item.isEnchanted() > 0 && item.isIdentified() > 0) {
							enchanted = "Enchanted, ";
						}
						String upgraded = "";
						if(item.upgradeLevel() > 0 && item.isIdentified() > 0) {
							upgraded = String.format("Upgraded (+%d), ", item.upgradeLevel());
						}
						String cursed = "";
						if(item.isCursed() > 0 && item.curseKnown() > 0) {
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
						if(item.usesPowder() > 0) {
							flintlock = "Flintlock Weapon, ";
						}
						String arrows = "";
						if(item.usesArrows() > 0) {
							arrows = "Uses Arrows, ";
						}
						String bolts = "";
						if(item.usesBolts() > 0) {
							bolts = "Uses Bolts, ";
						}
						String powder = "";
						if(item.usesPowder() > 0) {
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
					
					if(item.isArmor() || item.isShield() > 0) {
						String armor = "";
						if(item.isIdentified() > 0) {
							armor = String.format("Armor Class: %d", item.armorClass()+item.upgradeLevel());
						}else {
							armor = String.format("Armor Class: %d (?)", item.armorClass());
						}
						terminal.write(armor, x, z++);
						
						String traits = "";
						String traits2 = "";
						String enchanted = "";
						if(item.isEnchanted() > 0 && item.isIdentified() > 0) {
							enchanted = "Enchanted, ";
						}
						String upgraded = "";
						if(item.upgradeLevel() > 0 && item.isIdentified() > 0) {
							upgraded = String.format("Upgraded (+%d), ", item.upgradeLevel());
						}
						String cursed = "";
						if(item.isCursed() > 0 && item.curseKnown() > 0) {
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
						if(item.isShield() > 0) {
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
						if(item.isIdentified() > 0) {
							effect = item.potionName();
						}
						terminal.write(String.format("Potion Effect: %s", effect), x, z++);
					}
					
					
	
					
					
					
					if(item.isIdentified() > 0 || (item.foodValue() > 0 || item.isKey() > 0)) {
						terminal.write(String.format("Value: %s gold", item.currentGoldValue()), x, z++);
					}else {
						terminal.write(String.format("Value: %s gold (?)", item.baseGoldValue()), x, z++);
					}
					z++;
					if(item.quaffEffect() != null) {
						if(item.isIdentified() > 0) {
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
					
					terminal.write(String.format("%c %s x%d%s", inventory.get(i).glyph(), player.nameOf(inventory.get(i)), inventory.get(i).stackAmount(), equipped, check), 5, y++);
				}
				if(checkIfSelected(i, check) && inventory.get(i).equippable() > 0) {
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
			if(inventory.get(check).equippable() > 0) {
				player.equip(inventory.get(check));
				return null;
			}else {
				return this;
			}
		
		case KeyEvent.VK_D:
			player.drop(inventory.get(check));
			return null;
			
		case KeyEvent.VK_E:
			if(inventory.get(check).foodValue() > 0) {
				player.eat(inventory.get(check));
				return null;
			}else {
				return this;
			}
		
		case KeyEvent.VK_Q:
			if(inventory.get(check).quaffEffect() != null) {
				player.quaff(inventory.get(check));
				return null;
			}else {
				return this;
			}
			
		case KeyEvent.VK_R:
			if(inventory.get(check).writtenSpells().size() > 0) {
				if(inventory.get(check).isSpellbook() > 0){
					player.learnSpell(inventory.get(check).writtenSpells().get(0), inventory.get(check));
					return null;
				}else {
					if(inventory.get(check).writtenSpells().size() == 1 && !inventory.get(check).writtenSpells().get(0).isSelfCast()) {
						return new CastSpellScreen(player, "Cast spell at?", sx, sy, inventory.get(check).writtenSpells().get(0), inventory.get(check));
					}else if(inventory.get(check).writtenSpells().size() == 1 && inventory.get(check).writtenSpells().get(0).isSelfCast()){
						player.castSpell(inventory.get(check).writtenSpells().get(0), player.x(), player.y(), inventory.get(check));
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


