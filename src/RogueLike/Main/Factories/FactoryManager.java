package RogueLike.Main.Factories;

public enum FactoryManager {
	INSTANCE();
	private static CreatureFactory creatureFactory;
	private static EffectFactory effectFactory;
	private static EnchantmentFactory enchantmentFactory;
	private static FeatFactory featFactory;
	private static ItemFactory itemFactory;
	private static ModifierFactory modifierFactory;
	private static ObjectFactory objectFactory;
	private static ParticleFactory particleFactory;
	private static SpellFactory spellFactory;
	private static WeaponFactory weaponFactory;
	
	private FactoryManager(){}

	public static CreatureFactory getCreatureFactory() {
		if (creatureFactory == null ) {
			creatureFactory = new CreatureFactory();
		}
		return creatureFactory;
	}

	public static EffectFactory getEffectFactory() {
		if (effectFactory == null ) {
			effectFactory = new EffectFactory();
		}
		return effectFactory;
	}

	public static EnchantmentFactory getEnchantmentFactory() {
		if (enchantmentFactory == null ) {
			enchantmentFactory = new EnchantmentFactory();
		}
		return enchantmentFactory;
	}

	public static FeatFactory getFeatFactory() {
		if (featFactory == null ) {
			featFactory = new FeatFactory();
		}
		return featFactory;
	}

	public static ItemFactory getItemFactory() {
		if (itemFactory == null ) {
			itemFactory = new ItemFactory();
		}
		return itemFactory;
	}

	public static ModifierFactory getModifierFactory() {
		if (modifierFactory == null ) {
			modifierFactory = new ModifierFactory();
		}
		return modifierFactory;
	}

	public static ObjectFactory getObjectFactory() {
		if (objectFactory == null ) {
			objectFactory = new ObjectFactory();
		}
		return objectFactory;
	}

	public static ParticleFactory getParticleFactory() {
		if (particleFactory == null ) {
			particleFactory = new ParticleFactory();
		}
		return particleFactory;
	}

	public static SpellFactory getSpellFactory() {
		if (spellFactory == null ) {
			spellFactory = new SpellFactory();
		}
		return spellFactory;
	}

	public static WeaponFactory getWeaponFactory() {
		if (weaponFactory == null ) {
			weaponFactory = new WeaponFactory();
		}
		return weaponFactory;
	}
	
	
}
