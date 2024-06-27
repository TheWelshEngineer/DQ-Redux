package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.DamageType;

public class DamageModifierElement extends CharacterSheetElement {
    private final Creature player;
    private final DamageType damageType;

    public DamageModifierElement(Creature player, DamageType damageType) {
        this.player = player;
        this.damageType = damageType;
    }

    @Override
    public String header() {
        return String.format("%s: %s", damageType, damageTypeStatus());
    }

    @Override
    public String details() {
        if (player.isImmuneTo(damageType)) {
            return String.format("You are immune to %s damage, taking no damage of this type.", damageType);
        }
        else if (player.isResistantTo(damageType)) {
            return String.format("You are resistant to %s damage, taking half damage of this type.", damageType);
        }
        else if (player.isWeakTo(damageType)) {
            return String.format("You are vulnerable to %s damage, taking double damage of this type.", damageType);
        }
        else return String.format("You are damaged normally by %s damage.", damageType);
    }

    private String damageTypeStatus() {
        if (player.isImmuneTo(damageType)) return "Immune";
        else if (player.isResistantTo(damageType)) return "Resistant";
        else if (player.isWeakTo(damageType)) return "Weakness";
        else return "No modifier";
    }
}
