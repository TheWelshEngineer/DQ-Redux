package RogueLike.Main.Creatures.Containers;

import java.awt.Color;

import RogueLike.Main.ExtraColors;
import RogueLike.Main.AI.ChestAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class BasicChest extends Creature{
	
	private static int defaultMaxHP = 1;
	private static int defaultMaxMana = 1;
	private static int defaultAC = 10;
	private static int defaultStrength = 1;
	private static int defaultDexterity = 1;
	private static int defaultIntelligence = 1;
	private static int defaultVisionRadius = 1;

	public BasicChest(ObjectFactory factory, Creature player, String name, char glyph, Color color, int depth, int type) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		new ChestAI(this, factory, factory.world);
		this.modifyIsContainer(true);
		this.setHasNoCorpse(true);
		switch(type) {
		case 1: 
			Item startPotion = factory.randomPotion(0, false);
			this.inventory().add(startPotion);
			break;
		case 2: 
			Item startFood = factory.randomFood(0, 0);
			this.inventory().add(startFood);
			break;
		case 3: 
			Item startAmmo = factory.randomAmmunition(0, 0);
			this.inventory().add(startAmmo);
			break;
		case 4: 
			Item startWeapon = factory.randomWeapon(0, false);
			this.inventory().add(startWeapon);
			break;
		case 5: 
			Item startArmor = factory.randomArmor(0, false);
			this.inventory().add(startArmor);
			break;
		case 6: 
			Item startShield = factory.randomShield(0, false);
			this.inventory().add(startShield);
			break;
		case 7: 
			Item startRing = factory.randomRing(0, false);
			this.inventory().add(startRing);
			break;
		case 8: 
			Item startScroll = factory.randomScroll(0, player, false);
			this.inventory().add(startScroll);
			break;
		case 9: 
			Item startWand = factory.randomWand(0, player, false);
			this.inventory().add(startWand);
			break;
		case 10: 
			Item startGold = factory.newGold(depth, false);
			this.inventory().add(startGold);
			break;
		default: 
			Item defaultGold = factory.newGold(depth, false);
			this.inventory().add(defaultGold);
			break;
		}
	}

}