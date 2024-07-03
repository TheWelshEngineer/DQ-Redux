package RogueLike.Main.Screens;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.*;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Managers.KeybindManager;

public class InventoryScreen implements Screen{
	
	protected GameplayScreen playScreen;
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
	
	public InventoryScreen(GameplayScreen playScreen, Creature player, int sx, int sy) {
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
	public void displayOutput(ExtendedAsciiPanel terminal) {
		//
		//
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Inventory ==", 1);	
		int y = 3;
		//
		pageNumber = ExtraMaths.scrollingScreenPageNumber(check);
		//
		for(int i = (itemsPerPage*(pageNumber-1)); i < itemsPerPage*pageNumber; i++) {
			//System.out.println(String.format("i = %d", i));
			if(i < inventory.getItems().size()) {//spellbook.get(0).name()
				if(checkIfSelected(i, check)) {
					
					Item item = inventory.get(i);
					
					String equipped = "";
					if(item == player.weapon() || item == player.armor() || item == player.shield() || item == player.ring() || item == player.ammunition()) {
						equipped = " (equipped)";
						if(inventory.get(i) == player.quickslot_1()) {
							equipped = " (equipped - QS1)";
						}
						if(inventory.get(i) == player.quickslot_2()) {
							equipped = " (equipped - QS2)";
						}
						if(inventory.get(i) == player.quickslot_3()) {
							equipped = " (equipped - QS3)";
						}
						if(inventory.get(i) == player.quickslot_4()) {
							equipped = " (equipped - QS4)";
						}
						if(inventory.get(i) == player.quickslot_5()) {
							equipped = " (equipped - QS5)";
						}
						if(inventory.get(i) == player.quickslot_6()) {
							equipped = " (equipped - QS6)";
						}

					}else {
						if(inventory.get(i) == player.quickslot_1()) {
							equipped = " (quickslot 1)";
						}
						if(inventory.get(i) == player.quickslot_2()) {
							equipped = " (quickslot 2)";
						}
						if(inventory.get(i) == player.quickslot_3()) {
							equipped = " (quickslot 3)";
						}
						if(inventory.get(i) == player.quickslot_4()) {
							equipped = " (quickslot 4)";
						}
						if(inventory.get(i) == player.quickslot_5()) {
							equipped = " (quickslot 5)";
						}
						if(inventory.get(i) == player.quickslot_6()) {
							equipped = " (quickslot 6)";
						}

					}
					
					String itemDetails = String.format(">> %c %s x%d%s", item.glyph(), player.nameOf(item), item.stackAmount(), equipped, check);
					if(item.enchantment() != null && item.curse() == null && item.isWeapon() && item.isIdentified() && (item.defaultName().length() > 8) && item == player.weapon()) {
						itemDetails = String.format(">> %c %s%cx%d%s", inventory.get(i).glyph(), player.nameOf(inventory.get(i)), (char)255, inventory.get(i).stackAmount(), equipped, check);
					}
					CharSequence nonBreakingSpace = (CharSequence)(String.format("%c", (char)255));
					if(itemDetails.contains(nonBreakingSpace)) {
						String[] detailsList = itemDetails.split(String.format("%c", (char)255));
						terminal.write(detailsList[0], 5, y);
						terminal.write(String.format("%c", item.glyph()), 8, y++, item.color());
						terminal.write(String.format("     %s", detailsList[1]), 5, y);
						y++;
					}else {
						terminal.write(itemDetails, 5, y);
						terminal.write(String.format("%c", item.glyph()), 8, y++, item.color());
					}
					
					//terminal.write(itemDetails, 5, y);
					//terminal.write(String.format("%c", item.glyph()), 8, y++, item.color());
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
							terminal.write(String.format("Damage Type: %s", item.effectiveDamageType()), x, z++);

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

						ArrayList<String> traits = new ArrayList<>();

						if(item.enchantment() != null && item.isIdentified()) {
							traits.add("Enchanted");
						}
						if(item.upgradeLevel() > 0 && item.isIdentified()) {
							traits.add(String.format("Upgraded (+%d), ", item.upgradeLevel()));
						}
						if(item.curse() != null && item.isCurseKnown()) {
							traits.add("Cursed");
						}
						if (item.isSimple()) {
							traits.add("Simple");
						}
						if (item.isMartial()) {
							traits.add("Martial");
						}
						if (item.isFinesse()) {
							traits.add("Finesse");
						}
						if (item.isRangedWeapon()) {
							traits.add("Ranged");
						}
						if(item.isVersatile()) {
							traits.add("Versatile");
						}
						if(item.isTwoHanded()) {
							traits.add("Two-Handed");
						}
						if(item.isThrownWeapon()) {
							traits.add("Thrown");
						}
						if(item.usesStrength()) {
							traits.add("Uses Strength");
						}
						if(item.usesDexterity()) {
							traits.add("Uses Dexterity");
						}
						if(item.usesIntelligence()) {
							traits.add("Uses Intelligence");
						}
						if(item.usesPowderAmmunition()) {
							traits.add("Flintlock Weapon");
						}
						if(item.usesArrowAmmunition()) {
							traits.add("Uses Arrows");
						}
						if(item.usesBoltAmmunition()) {
							traits.add("Uses Bolts");
						}
						if(item.usesPowderAmmunition()) {
							traits.add("Uses Powder");
						}

						// write out the traits, if any
						if (!traits.isEmpty()) {
							final int traits_indent = "Traits: ".length();
							int maxLength = 120 - (x + traits_indent);
							ArrayList<String> traits_lines = TextUtils.joinStringsWithLineBreaks(traits, ", ", maxLength);

							for (int j=0; j<traits_lines.size(); j++) {
								if (j == 0) {
									terminal.write("Traits: " + traits_lines.get(j), x, z++);
								}
								else {
									// write the line indented
									terminal.write(traits_lines.get(j), x+traits_indent, z++);
								}
							}
							z++;
						}
					}
					
					if(item.isWand()) {
						String spell;
						if(item.isIdentified()) {
							spell = String.format("Spell: %s", item.writtenSpells().get(0).name());
						}else {
							spell = "Spell: Unknown";
						}
						terminal.write(spell, x, z++);
						
						String range;
						if(item.isIdentified()) {
							Spell wandSpell = item.writtenSpells().get(0);
							if(wandSpell.aoe() != null) {
								if(wandSpell.aoe().size() > 0) {
									range = String.format("Range: %s (%d)", wandSpell.aoe().sizeWord(), wandSpell.aoe().size());
								}else {
									range = String.format("Range: %s (Sight)", wandSpell.aoe().sizeWord());
								}
							}else if(wandSpell.isSelfCast()) {
								range = "Range: Self";
							}else {
								range = "Range: Single Target (Sight)";
							}
						}else {
							range = "Range: Unknown";
						}
						terminal.write(range, x, z++);
						
						String targets;
						if(item.isIdentified()) {
							Spell wandSpell = item.writtenSpells().get(0);
							if(wandSpell.castOnTile()) {
								targets = "Targets: Empty Tiles";
							}else if(!wandSpell.isSelfCast()) {
								targets = "Targets: Creatures";
							}else {
								targets = "Targets: Self";
							}
						}else {
							targets = "Targets: Unknown";
						}
						terminal.write(targets, x, z++);
						
					}
					
					if(item.isArmor() || item.isShield()) {
						String armor;
						if(item.isIdentified()) {
							armor = String.format("Armor Class: %d", item.armorClass()+item.upgradeLevel());
						}else {
							armor = String.format("Armor Class: %d (?)", item.armorClass());
						}
						terminal.write(armor, x, z++);
						
						ArrayList<String> traits = new ArrayList<>();
						if(item.enchantment() != null && item.isIdentified()) {
							traits.add("Enchanted");
						}
						if(item.upgradeLevel() > 0 && item.isIdentified()) {
							traits.add(String.format("Upgraded (+%d)", item.upgradeLevel()));
						}
						if(item.curse() != null && item.isCurseKnown()) {
							traits.add("Cursed");
						}
						if(item.isLightArmor()) {
							traits.add("Light Armor");
						}
						if(item.isMediumArmor()) {
							traits.add("Medium Armor");
						}
						if(item.isHeavyArmor()) {
							traits.add("Heavy Armor");
						}
						if(item.isShield()) {
							traits.add("Shield");
						}
						if(item.isTowerShield()) {
							traits.add("Tower Shield");
						}

						// write out the traits, if any
						if (!traits.isEmpty()) {
							final int traits_indent = "Traits: ".length();
							int maxLength = 120 - (x + traits_indent);
							ArrayList<String> traits_lines = TextUtils.joinStringsWithLineBreaks(traits, ", ", maxLength);

							for (int j=0; j<traits_lines.size(); j++) {
								if (j == 0) {
									terminal.write("Traits: " + traits_lines.get(j), x, z++);
								}
								else {
									// write the line indented
									terminal.write(traits_lines.get(j), x+traits_indent, z++);
								}
							}
						}
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
					if(item.isWand()) {
						if(item.isIdentified()) {
							terminal.write(item.getDescription().getWandDescriptionBase(), x, z++);
							terminal.write(item.getDescription().getWandDescriptionKnown(), x, z++);
						}else {
							terminal.write(item.getDescription().getWandDescriptionBase(), x, z++);
							terminal.write(item.getDescription().getWandDescriptionUnknown(), x, z++);
						}
					}
					
					
					
					
					
					
					
					
					
					
					
				}else {

					String equipped = "";
					if(inventory.get(i) == player.weapon() || inventory.get(i) == player.armor() || inventory.get(i) == player.shield() || inventory.get(i) == player.ring() || inventory.get(i) == player.ammunition()) {
						equipped = " (equipped)";
						if(inventory.get(i) == player.quickslot_1()) {
							equipped = " (equipped - QS1)";
						}
						if(inventory.get(i) == player.quickslot_2()) {
							equipped = " (equipped - QS2)";
						}
						if(inventory.get(i) == player.quickslot_3()) {
							equipped = " (equipped - QS3)";
						}
						if(inventory.get(i) == player.quickslot_4()) {
							equipped = " (equipped - QS4)";
						}
						if(inventory.get(i) == player.quickslot_5()) {
							equipped = " (equipped - QS5)";
						}
						if(inventory.get(i) == player.quickslot_6()) {
							equipped = " (equipped - QS6)";
						}

					}else {
						if(inventory.get(i) == player.quickslot_1()) {
							equipped = " (quickslot 1)";
						}
						if(inventory.get(i) == player.quickslot_2()) {
							equipped = " (quickslot 2)";
						}
						if(inventory.get(i) == player.quickslot_3()) {
							equipped = " (quickslot 3)";
						}
						if(inventory.get(i) == player.quickslot_4()) {
							equipped = " (quickslot 4)";
						}
						if(inventory.get(i) == player.quickslot_5()) {
							equipped = " (quickslot 5)";
						}
						if(inventory.get(i) == player.quickslot_6()) {
							equipped = " (quickslot 6)";
						}

					}
					
					
					String itemDetails = String.format("%c %s x%d%s", inventory.get(i).glyph(), player.nameOf(inventory.get(i)), inventory.get(i).stackAmount(), equipped, check);
					if(inventory.get(i).enchantment() != null && inventory.get(i).curse() == null && inventory.get(i).isWeapon() && inventory.get(i).isIdentified() && (inventory.get(i).defaultName().length() > 8) && inventory.get(i) == player.weapon()) {
						itemDetails = String.format("%c %s%cx%d%s", inventory.get(i).glyph(), player.nameOf(inventory.get(i)), (char)255, inventory.get(i).stackAmount(), equipped, check);
					}
					CharSequence nonBreakingSpace = (CharSequence)(String.format("%c", (char)255));
					if(itemDetails.contains(nonBreakingSpace)) {
						String[] detailsList = itemDetails.split(String.format("%c", (char)255));
						terminal.write(detailsList[0], 5, y);
						terminal.write(String.format("%c", inventory.get(i).glyph()), 5, y++, inventory.get(i).color());
						terminal.write(String.format("  %s", detailsList[1]), 5, y);
						y++;
					}else {
						terminal.write(itemDetails, 5, y);
						terminal.write(String.format("%c", inventory.get(i).glyph()), 5, y++, inventory.get(i).color());
					}
				}
				if(checkIfSelected(i, check) && inventory.get(i).isEquippable()) {
					terminal.writeCenter(String.format("-- [%s]: Equip --", KeybindManager.keybindText(KeybindManager.interactionEquipItem)), 36);
				}
				if(checkIfSelected(i, check) && inventory.get(i).foodValue() > 0) {
					terminal.writeCenter(String.format("-- [%s]: Eat --", KeybindManager.keybindText(KeybindManager.interactionEatFood)), 36);
				}
				if(checkIfSelected(i, check) && inventory.get(i).quaffEffect() != null) {
					terminal.writeCenter(String.format("-- [%s]: Quaff --", KeybindManager.keybindText(KeybindManager.interactionDrinkPotion)), 36);
				}
				if(checkIfSelected(i, check) && inventory.get(i).writtenSpells().size() > 0) {
					terminal.writeCenter(String.format("-- [%s]: Read --", KeybindManager.keybindText(KeybindManager.interactionReadSpell)), 36);
				}
				
				if(checkIfSelected(i, check) && inventory.get(i).isQuickslottable()) {
					terminal.writeCenter(String.format("-- [%s-%s]: Equip to quickslot 1-6 | [%s]: Drop | [%s]: Throw --", KeybindManager.keybindText(KeybindManager.interactionQuickslot_1), KeybindManager.keybindText(KeybindManager.interactionQuickslot_6), KeybindManager.keybindText(KeybindManager.interactionDropItem), KeybindManager.keybindText(KeybindManager.interactionThrowItem)), 38);
				}else if(checkIfSelected(i, check)){
					terminal.writeCenter(String.format("-- [%s]: Drop | [%s]: Throw --", KeybindManager.keybindText(KeybindManager.interactionDropItem), KeybindManager.keybindText(KeybindManager.interactionThrowItem)), 38);
				}
				
				
			}
		}
		//
		
		
        terminal.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s]: Cancel --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 40);
		
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
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = inventory.getItems().size()-1;
			}else {
				check--;
			}
			return this;
		case KeybindManager.navigateMenuDown:
			if(check == inventory.getItems().size()-1) {
				check = 0;
			}else {
				check++;
			}
			return this;
		case KeybindManager.navigateMenuBack:
			return null;
		/*case KeyEvent.VK_ENTER:
			if(spellbook.getSpells().size() > 0) {
				return use(spellbook.get(check));
			}
			return null;*/
		case KeybindManager.interactionEquipItem:
			if(inventory.get(check).isEquippable()) {
				//player.equip(inventory.get(check));
				//.setInputAccepted(true);
				player.ai().playerAIEquipItem(inventory.get(check));
				return null;
			}else {
				return this;
			}
		
		case KeybindManager.interactionDropItem:
			//player.drop(inventory.get(check));
			player.ai().playerAIDropItem(inventory.get(check));
			playScreen.setInputAccepted(true);
			return null;
			
		case KeybindManager.interactionEatFood:
			if(inventory.get(check).foodValue() > 0) {
				//player.eat(inventory.get(check));
				//playScreen.setInputAccepted(true);
				player.ai().playerAIEatItem(inventory.get(check));
				return null;
			}else {
				return this;
			}
		
		case KeybindManager.interactionDrinkPotion:
			if(inventory.get(check).quaffEffect() != null) {
				//player.quaff(inventory.get(check));
				//playScreen.setInputAccepted(true);
				player.ai().playerAIQuaffItem(inventory.get(check));
				return null;
			}else {
				return this;
			}
			
		case KeybindManager.interactionQuickslot_1:
			if(inventory.get(check).isQuickslottable()) {
				player.equipToQuickslot(inventory.get(check), 1);
				return this;
			}else {
				return this;
			}
			
		case KeybindManager.interactionQuickslot_2:
			if(inventory.get(check).isQuickslottable()) {
				player.equipToQuickslot(inventory.get(check), 2);
				return this;
			}else {
				return this;
			}
			
		case KeybindManager.interactionQuickslot_3:
			if(inventory.get(check).isQuickslottable()) {
				player.equipToQuickslot(inventory.get(check), 3);
				return this;
			}else {
				return this;
			}
			
		case KeybindManager.interactionQuickslot_4:
			if(inventory.get(check).isQuickslottable()) {
				player.equipToQuickslot(inventory.get(check), 4);
				return this;
			}else {
				return this;
			}
			
		case KeybindManager.interactionQuickslot_5:
			if(inventory.get(check).isQuickslottable()) {
				player.equipToQuickslot(inventory.get(check), 5);
				return this;
			}else {
				return this;
			}
			
		case KeybindManager.interactionQuickslot_6:
			if(inventory.get(check).isQuickslottable()) {
				player.equipToQuickslot(inventory.get(check), 6);
				return this;
			}else {
				return this;
			}
			
		case KeybindManager.interactionThrowItem:
			return new ThrowAtScreen(player, sx, sy, inventory.get(check));
			
		case KeybindManager.interactionReadSpell:
			if(inventory.get(check).writtenSpells().size() > 0) {
				if(inventory.get(check).isSpellbook()){
					player.learnSpell(inventory.get(check).writtenSpells().get(0), inventory.get(check));
					playScreen.setInputAccepted(true);
					return null;
				}else {
					if(inventory.get(check).writtenSpells().size() == 1 && !inventory.get(check).writtenSpells().get(0).isSelfCast()) {
						return new SpellTargetingScreen(player, String.format("Cast %s at?", inventory.get(check).writtenSpells().get(0).name()), sx, sy, inventory.get(check).writtenSpells().get(0), inventory.get(check));
					}else if(inventory.get(check).writtenSpells().size() == 1 && inventory.get(check).writtenSpells().get(0).isSelfCast()){
						//player.castSpell(inventory.get(check).writtenSpells().get(0), player.x(), player.y(), inventory.get(check));
						//playScreen.setInputAccepted(true);
						player.ai().playerAICastSpell(inventory.get(check), inventory.get(check).writtenSpells().get(0), player.x(), player.y());
						return null;
					}else {
						return new SpellSelectScreen(player, sx, sy, inventory.get(check));
					}
				}
			}else {
				return this;
			}
		
		default: return this;
		}
	}

}


