package RogueLike.Main;

import java.awt.Color;

public class Description {

    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private String mainAdjective;

    public String getMainAdjective() {
        return mainAdjective;
    }

    public void setMainAdjective(String adjective) {
        this.mainAdjective = adjective;
    }

    private String potionAdjective;

    public String getPotionAdjective() {
        return potionAdjective;
    }

    public void setPotionAdjective(String adjective) {
        this.potionAdjective = adjective;
    }

    private String wandAdjective;

    public String getWandAdjective() {
        return wandAdjective;
    }

    public void setWandAdjective(String adjective) {
        this.wandAdjective = adjective;
    }

    private String potionBottle;

    public String getPotionBottle() {
        return potionBottle;
    }

    public void setPotionBottle(String bottle) {
        this.potionBottle = bottle;
    }

    private String wandDecoration;

    public String getWandDecoration() {
        return wandDecoration;
    }

    public void setWandDecoration(String decoration) {
        this.wandDecoration = decoration;
    }

    private String potionEffectName;

    public String getPotionEffectName() {
        return potionEffectName;
    }

    public void setPotionEffectName(String effectName) {
        this.potionEffectName = effectName;
    }

    private String wandSpellName;

    public String getWandSpellName() {
        return wandSpellName;
    }

    public void setWandSpellName(String spellName) {
        this.wandSpellName = spellName;
    }

    public Description(String adjective, Color color) {
        this.mainAdjective = adjective;
        this.color = color;
        this.potionAdjective = randomPotionAdjective();
        this.potionBottle = randomPotionBottle();
        this.wandAdjective = randomWandAdjective();
        this.wandDecoration = randomWandDecoration();
    }

    public String getPotionDescriptionBase() {
        return String.format(
                "This is a %s %s potion held within a %s flask.",
                potionAdjective, mainAdjective, potionBottle);
    }

    public String getPotionDescriptionUnknown() {
        return "Who knows what it'll do if you drink it?";
    }

    public String getPotionDescriptionKnown() {
        return String.format("You know this to be a potion of %s.", potionEffectName);
    }

    public String getWandDescriptionBase() {
        return String.format(
                "This is a %s %s wand inlaid with %s.",
                wandAdjective, mainAdjective, wandDecoration);
    }

    public String getWandDescriptionUnknown() {
        return "Who knows what spell it holds?";
    }

    public String getWandDescriptionKnown() {
        return String.format("You know this to be a wand of %s.", wandSpellName);
    }

    public String randomPotionAdjective() {
        switch (ExtraMaths.diceRoll(1, 8)) {
            case 1:
                return "sparkling";
            case 2:
                return "fizzy";
            case 3:
                return "bubbling";
            case 4:
                return "still";
            case 5:
                return "flat";
            case 6:
                return "murky";
            case 7:
                return "sludgy";
            case 8:
                return "thick";
            default:
                return "sparkling";
        }
    }

    public String randomWandAdjective() {
        switch (ExtraMaths.diceRoll(1, 8)) {
            case 1:
                return "chipped";
            case 2:
                return "frazzled";
            case 3:
                return "pristine";
            case 4:
                return "intricate";
            case 5:
                return "threadbare";
            case 6:
                return "simple";
            case 7:
                return "homely";
            case 8:
                return "jagged";
            default:
                return "chipped";
        }
    }

    public String randomPotionBottle() {
        switch (Dice.d8.roll()) {
            case 1:
                return "round";
            case 2:
                return "square";
            case 3:
                return "conical";
            case 4:
                return "intricate";
            case 5:
                return "simple";
            case 6:
                return "chipped";
            case 7:
                return "leaky";
            case 8:
                return "pristine";
            default:
                return "round";
        }
    }

    public String randomWandDecoration() {
        switch (Dice.d8.roll()) {
            case 1:
                return "amethyst crystals";
            case 2:
                return "golden runes";
            case 3:
                return "silver runes";
            case 4:
                return "bismuth clusters";
            case 5:
                return "copper wire";
            case 6:
                return "living fire";
            case 7:
                return "polished seaglass";
            case 8:
                return "silver wire";
            default:
                return "amethyst crystals";
        }
    }
}
