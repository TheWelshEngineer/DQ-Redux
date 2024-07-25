package RogueLike.Main;

import RogueLike.Main.AoE.AoE;
import RogueLike.Main.Creatures.Creature;

public class Spell implements Cloneable {

    private String name;

    public String name() {
        return name;
    }

    private int manaCost;

    public int manaCost() {
        return manaCost;
    }

    private int id;

    public int id() {
        return id;
    }

    private Creature caster;

    public Creature caster() {
        return caster;
    }

    private boolean isSelfCast;

    public boolean isSelfCast() {
        return isSelfCast;
    }

    public void setSelfCast(boolean value) {
        isSelfCast = value;
    }

    private boolean castOnTile;

    public boolean castOnTile() {
        return castOnTile;
    }

    public void setCastOnTile(boolean value) {
        castOnTile = value;
    }

    private Effect effect;

    public Effect effect() {
        return (Effect) effect.clone();
    }

    private AoE aoe;

    public AoE aoe() {
        return aoe;
    }

    public void makeEffectNegative() {
        effect.setIsNegative(true);
    }

    public void setSpellEffectType(String type) {
        effect.setType(type);
    }

    public Spell(
            String name,
            int id,
            int manaCost,
            Creature caster,
            Effect effect,
            boolean selfCast,
            boolean tileCast,
            AoE aoe) {
        this.name = name;
        this.id = id;
        this.manaCost = manaCost;
        this.caster = caster;
        this.effect = effect;
        this.isSelfCast = selfCast;
        this.castOnTile = tileCast;
        this.aoe = aoe;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
