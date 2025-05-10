package RogueLike.Main.Items;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.FactoryManager;

public class Ring extends Item{
	
	private static final long serialVersionUID = -9137555790786578799L;

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
	public Ring( char glyph, String name, int appearance, int goldValue, int id) {
		super(glyph, ExtendedAsciiPanel.white, name, null);
		this.setAppearance(FactoryManager.getObjectFactory().ringAppearances.get(appearance));
		this.setColor(FactoryManager.getObjectFactory().ringColors.get(this.getAppearance()));
		this.setBaseGoldValue(goldValue);
		this.setCurrentGoldValue(this.baseGoldValue());
		this.setIsEquippable(true);
		this.setIsRing(true);
		this.setID(id);
	}
	

}
