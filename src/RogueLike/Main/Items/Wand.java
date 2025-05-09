package RogueLike.Main.Items;

import RogueLike.Main.Description;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Spell;
import RogueLike.Main.Factories.FactoryManager;

public class Wand extends Item{
	
	private static final long serialVersionUID = 2013882620139633897L;

	/**
	 * 
	 * @param factory The ObjectFactory from which to reference the Scroll's appearances.
	 * @param spellFactory The SpellFactory from which to reference the Scroll's spell.
	 * @param glyph A Code Page 437 char which visually represents the Scroll.
	 * @param name A string with the first letter of each word capitalised.
	 * @param appearance An integer representing a key in FactoryManager.getObjectFactory().scrollAppearances.
	 * @param spell The Spell to be contained within the Scroll.
	 * @param goldValue The base value in gold of the Scroll.
	 * @param id The unique identifying integer of the item.
	 */
	public Wand( char glyph, String name, int appearance, Spell spell, int goldValue, int id) {
		super(glyph, ExtendedAsciiPanel.white, name, null);
		this.setAppearance(FactoryManager.getObjectFactory().wandAppearances.get(appearance));
		this.addWrittenSpell(spell);
		Description description = FactoryManager.getObjectFactory().wandColors.get(this.getAppearance());
		description.setWandSpellName(spell.name().toLowerCase());
		this.setDescription(description);
		this.setColor(description.getColor());
		this.setBaseGoldValue(goldValue);
		this.setCurrentGoldValue(this.baseGoldValue());
		this.setIsWand(true);
		this.setQuickslottable(true);
		this.setID(id);
	}
	

}
