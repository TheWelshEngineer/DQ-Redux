package RogueLike.Main.Factories;

import RogueLike.Main.Creatures.CreatureModifier;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Dice;
import RogueLike.Main.ExtraMaths;

public class ModifierFactory {

    public CreatureModifier randomCreatureModifier() {
        switch (ExtraMaths.diceRoll(1, 7)) {
            case 1:
                return fireModifier();
            case 2:
                return frostModifier();
            case 3:
                return shockModifier();
            case 4:
                return poisonModifier();
            case 5:
                return acidModifier();
            case 6:
                return magicModifier();
            case 7:
                return chaosModifier();
            default:
                return fireModifier();
        }
    }

    private CreatureModifier fireModifier() {
        switch (Dice.d8.roll()) {
            case 1:
                return new CreatureModifier("Blazing", "of Fire Resistance", DamageType.FIRE);
            case 2:
                return new CreatureModifier("Scorching", "of Fire Resistance", DamageType.FIRE);
            case 3:
                return new CreatureModifier("Burning", "of Fire Resistance", DamageType.FIRE);
            case 4:
                return new CreatureModifier("Flaming", "of Fire Resistance", DamageType.FIRE);
            case 5:
                return new CreatureModifier("Incandescent", "of Fire Resistance", DamageType.FIRE);
            case 6:
                return new CreatureModifier("Ignited", "of Fire Resistance", DamageType.FIRE);
            case 7:
                return new CreatureModifier("Volcanic", "of Fire Resistance", DamageType.FIRE);
            case 8:
                return new CreatureModifier("Molten", "of Fire Resistance", DamageType.FIRE);
            default:
                return new CreatureModifier("Blazing", "of Fire Resistance", DamageType.FIRE);
        }
    }

    private CreatureModifier frostModifier() {
        switch (Dice.d8.roll()) {
            case 1:
                return new CreatureModifier("Freezing", "of Frost Resistance", DamageType.FROST);
            case 2:
                return new CreatureModifier("Chilling", "of Frost Resistance", DamageType.FROST);
            case 3:
                return new CreatureModifier("Gelid", "of Frost Resistance", DamageType.FROST);
            case 4:
                return new CreatureModifier("Arctic", "of Frost Resistance", DamageType.FROST);
            case 5:
                return new CreatureModifier("Glacial", "of Frost Resistance", DamageType.FROST);
            case 6:
                return new CreatureModifier("Boreal", "of Frost Resistance", DamageType.FROST);
            case 7:
                return new CreatureModifier("Sub-zero", "of Frost Resistance", DamageType.FROST);
            case 8:
                return new CreatureModifier("Wintry", "of Frost Resistance", DamageType.FROST);
            default:
                return new CreatureModifier("Freezing", "of Frost Resistance", DamageType.FROST);
        }
    }

    private CreatureModifier shockModifier() {
        switch (Dice.d8.roll()) {
            case 1:
                return new CreatureModifier("Shocking", "of Shock Resistance", DamageType.SHOCK);
            case 2:
                return new CreatureModifier("Sparking", "of Shock Resistance", DamageType.SHOCK);
            case 3:
                return new CreatureModifier("Voltaic", "of Shock Resistance", DamageType.SHOCK);
            case 4:
                return new CreatureModifier("Overcharged", "of Shock Resistance", DamageType.SHOCK);
            case 5:
                return new CreatureModifier("Thundering", "of Shock Resistance", DamageType.SHOCK);
            case 6:
                return new CreatureModifier("Static", "of Shock Resistance", DamageType.SHOCK);
            case 7:
                return new CreatureModifier("Electrified", "of Shock Resistance", DamageType.SHOCK);
            case 8:
                return new CreatureModifier("Storming", "of Shock Resistance", DamageType.SHOCK);
            default:
                return new CreatureModifier("Shocking", "of Shock Resistance", DamageType.SHOCK);
        }
    }

    private CreatureModifier poisonModifier() {
        switch (Dice.d8.roll()) {
            case 1:
                return new CreatureModifier("Poisonous", "of Poison Resistance", DamageType.POISON);
            case 2:
                return new CreatureModifier("Venomous", "of Poison Resistance", DamageType.POISON);
            case 3:
                return new CreatureModifier("Noxious", "of Poison Resistance", DamageType.POISON);
            case 4:
                return new CreatureModifier("Envenomed", "of Poison Resistance", DamageType.POISON);
            case 5:
                return new CreatureModifier("Toxic", "of Poison Resistance", DamageType.POISON);
            case 6:
                return new CreatureModifier(
                        "Infectious", "of Poison Resistance", DamageType.POISON);
            case 7:
                return new CreatureModifier("Virulent", "of Poison Resistance", DamageType.POISON);
            case 8:
                return new CreatureModifier("Malignant", "of Poison Resistance", DamageType.POISON);
            default:
                return new CreatureModifier("Poisonous", "of Poison Resistance", DamageType.POISON);
        }
    }

    private CreatureModifier acidModifier() {
        switch (Dice.d8.roll()) {
            case 1:
                return new CreatureModifier("Acidic", "of Acid Resistance", DamageType.ACID);
            case 2:
                return new CreatureModifier("Caustic", "of Acid Resistance", DamageType.ACID);
            case 3:
                return new CreatureModifier("Corrosive", "of Acid Resistance", DamageType.ACID);
            case 4:
                return new CreatureModifier("Eroding", "of Acid Resistance", DamageType.ACID);
            case 5:
                return new CreatureModifier("Melting", "of Acid Resistance", DamageType.ACID);
            case 6:
                return new CreatureModifier("Dissolved", "of Acid Resistance", DamageType.ACID);
            case 7:
                return new CreatureModifier("Pungent", "of Acid Resistance", DamageType.ACID);
            case 8:
                return new CreatureModifier("Acerbic", "of Acid Resistance", DamageType.ACID);
            default:
                return new CreatureModifier("Acidic", "of Acid Resistance", DamageType.ACID);
        }
    }

    private CreatureModifier magicModifier() {
        switch (Dice.d8.roll()) {
            case 1:
                return new CreatureModifier("Arcane", "of Magic Resistance", DamageType.MAGIC);
            case 2:
                return new CreatureModifier("Magical", "of Magic Resistance", DamageType.MAGIC);
            case 3:
                return new CreatureModifier("Sparkling", "of Magic Resistance", DamageType.MAGIC);
            case 4:
                return new CreatureModifier("Glittering", "of Magic Resistance", DamageType.MAGIC);
            case 5:
                return new CreatureModifier(
                        "Knowledgable", "of Magic Resistance", DamageType.MAGIC);
            case 6:
                return new CreatureModifier("Ancient", "of Magic Resistance", DamageType.MAGIC);
            case 7:
                return new CreatureModifier("Empowered", "of Magic Resistance", DamageType.MAGIC);
            case 8:
                return new CreatureModifier("Stellar", "of Magic Resistance", DamageType.MAGIC);
            default:
                return new CreatureModifier("Arcane", "of Magic Resistance", DamageType.MAGIC);
        }
    }

    private CreatureModifier chaosModifier() {
        switch (Dice.d8.roll()) {
            case 1:
                return new CreatureModifier("Chaotic", "of Chaos Resistance", DamageType.CHAOS);
            case 2:
                return new CreatureModifier("Warped", "of Chaos Resistance", DamageType.CHAOS);
            case 3:
                return new CreatureModifier("Twisted", "of Chaos Resistance", DamageType.CHAOS);
            case 4:
                return new CreatureModifier("Gibbering", "of Chaos Resistance", DamageType.CHAOS);
            case 5:
                return new CreatureModifier("Unknowable", "of Chaos Resistance", DamageType.CHAOS);
            case 6:
                return new CreatureModifier("Howling", "of Chaos Resistance", DamageType.CHAOS);
            case 7:
                return new CreatureModifier("Screaming", "of Chaos Resistance", DamageType.CHAOS);
            case 8:
                return new CreatureModifier("Devouring", "of Chaos Resistance", DamageType.CHAOS);
            default:
                return new CreatureModifier("Chaotic", "of Chaos Resistance", DamageType.CHAOS);
        }
    }

    public ModifierFactory() {}
}
