package RogueLike.Main.Creatures.Containers;

import java.awt.Color;

import RogueLike.Main.AI.ChestAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class BasicChest extends Creature{
	
	private static final long serialVersionUID = -4244960738342742493L;
	private static int defaultMaxHP = 1;
	private static int defaultMaxMana = 1;
	private static int defaultAC = 10;
	private static int defaultStrength = 1;
	private static int defaultDexterity = 1;
	private static int defaultIntelligence = 1;
	private static int defaultVisionRadius = 1;

	public BasicChest( Creature player, String name, char glyph, Color color, int depth, int type) {
		super(name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		new ChestAI(this);
		this.modifyIsContainer(true);
		this.setHasNoCorpse(true);
		switch(type) {
		case 1: 
			Item startPotion = FactoryManager.getObjectFactory().randomPotion(0, false);
			this.inventory().add(startPotion);
			break;
		case 2: 
			Item startFood = FactoryManager.getObjectFactory().randomFood(0, 0);
			this.inventory().add(startFood);
			break;
		case 3: 
			Item startAmmo = FactoryManager.getObjectFactory().randomAmmunition(0, 0);
			this.inventory().add(startAmmo);
			break;
		case 4: 
			Item startWeapon = FactoryManager.getObjectFactory().randomWeapon(0, false, true, true, true);
			this.inventory().add(startWeapon);
			break;
		case 5: 
			Item startArmor = FactoryManager.getObjectFactory().randomArmor(0, false, true, true, true);
			this.inventory().add(startArmor);
			break;
		case 6: 
			Item startShield = FactoryManager.getObjectFactory().randomShield(0, false, true, true, true);
			this.inventory().add(startShield);
			break;
		case 7: 
			Item startRing = FactoryManager.getObjectFactory().randomRing(0, false, true);
			this.inventory().add(startRing);
			break;
		case 8: 
			Item startScroll = FactoryManager.getObjectFactory().randomScroll(0, player, false);
			this.inventory().add(startScroll);
			break;
		case 9: 
			Item startWand = FactoryManager.getObjectFactory().randomWand(0, player, false);
			this.inventory().add(startWand);
			break;
		case 10: 
			Item startGold = FactoryManager.getItemFactory().newGold(depth, false);
			this.inventory().add(startGold);
			break;
		default: 
			Item defaultGold = FactoryManager.getItemFactory().newGold(depth, false);
			this.inventory().add(defaultGold);
			break;
		}
	}

}