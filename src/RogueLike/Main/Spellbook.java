package RogueLike.Main;

import java.util.ArrayList;

public class Spellbook {

    private ArrayList<Spell> spells;

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    private ArrayList<Integer> spellIDs;

    public ArrayList<Integer> getSpellIDs() {
        return spellIDs;
    }

    public Spell get(int i) {
        return spells.get(i);
    }

    public Spellbook(int max) {
        spells = new ArrayList<Spell>(max);
        spellIDs = new ArrayList<Integer>();
    }

    public void add(Spell spell) {
        spells.add(spell);
        spellIDs.add(spell.id());
    }

    public void remove(Spell spell) {
        spells.remove(spell);
        spellIDs.remove(spell.id());
    }

    public boolean isSpellKnown(Spell spell) {
        if (spellIDs.contains(spell.id())) {
            return true;
        } else {
            return false;
        }
    }
}
