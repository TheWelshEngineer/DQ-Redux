package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class BasicRangedWeapon extends Item{
	
	/**
	 * 
	 * @param glyph A Code Page 437 char which visually represents the Weapon.
	 * @param name A string with the first letter of each word capitalised.
	 * @param appearance Set to null.
	 * @param damageDice A Dice static that represents the damage dealt by the Weapon when used at range. 
	 * @param goldValue The base value in gold of the Weapon.
	 * @param id The unique identifying integer of the item.
	 */
	public BasicRangedWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance);
		this.setDamageDice(Dice.d1);
		this.setRangedDamageDice(damageDice);
		this.setIsWeapon(true);
		this.setIsRangedWeapon(true);
		this.setIsTwoHanded(true);
		this.setIsEquippable(true);
		this.setBaseGoldValue(goldValue);
		this.setCurrentGoldValue(this.baseGoldValue());
		this.setID(id);	
	}

}
