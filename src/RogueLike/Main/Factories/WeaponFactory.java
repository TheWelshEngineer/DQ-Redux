package RogueLike.Main.Factories;

import RogueLike.Main.Dice;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Items.BasicFinesseWeapon;
import RogueLike.Main.Items.BasicMeleeWeapon;
import RogueLike.Main.Items.BasicThrownWeapon;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Items.ThrownFinesseWeapon;
import RogueLike.Main.Items.ThrownVersatileWeapon;
import RogueLike.Main.Items.TwoHandedMeleeWeapon;
import RogueLike.Main.Items.VersatileMeleeWeapon;

public class WeaponFactory {

    // Simple
    // Tier 1
    public Item club() {
        Item item =
                new BasicMeleeWeapon(
                        ')', ExtendedAsciiPanel.brightWhite, "Club", null, Dice.d4, 10, 9);
        item.setIsSimple(true);
        return item;
    }

    // Tier 2
    public Item quarterstaff() {
        Item item =
                new VersatileMeleeWeapon(
                        ')',
                        ExtendedAsciiPanel.brightWhite,
                        "Quarterstaff",
                        null,
                        Dice.d6,
                        Dice.d8,
                        20,
                        15);
        item.setIsSimple(true);
        return item;
    }

    // Tier 3
    public Item spear(int depth, boolean addToWorld) {
        Item item =
                new ThrownVersatileWeapon(
                        ')',
                        ExtendedAsciiPanel.brightWhite,
                        "Spear",
                        null,
                        Dice.d4,
                        Dice.d6,
                        Dice.d8,
                        10,
                        16);
        item.setIsSimple(true);
        return item;
    }

    // Tier 4
    public Item throwingAxe() {
        Item item =
                new BasicThrownWeapon(
                        ')',
                        ExtendedAsciiPanel.brightWhite,
                        "Throwing Axe",
                        null,
                        Dice.d6,
                        Dice.d8,
                        50,
                        11);
        item.setIsSimple(true);
        return item;
    }

    // Tier 5
    public Item greatclub() {
        Item item =
                new TwoHandedMeleeWeapon(
                        ')', ExtendedAsciiPanel.brightWhite, "Greatclub", null, Dice.d10, 20, 10);
        item.setIsSimple(true);
        return item;
    }

    // Martial
    // Tier 1
    public Item shortsword(int depth, boolean addToWorld) {
        Item item =
                new BasicMeleeWeapon(
                        ')', ExtendedAsciiPanel.brightWhite, "Shortsword", null, Dice.d6, 100, 17);
        item.setIsMartial(true);
        return item;
    }

    // Tier 2
    public Item mace(int depth, boolean addToWorld) {
        Item item =
                new VersatileMeleeWeapon(
                        ')',
                        ExtendedAsciiPanel.brightWhite,
                        "Mace",
                        null,
                        Dice.d6,
                        Dice.d8,
                        50,
                        13);
        item.setIsMartial(true);
        return item;
    }

    public Item throwingHammer() {
        Item item =
                new BasicThrownWeapon(
                        ')',
                        ExtendedAsciiPanel.brightWhite,
                        "Throwing Hammer",
                        null,
                        Dice.d4,
                        Dice.d6,
                        20,
                        12);
        item.setIsMartial(true);

        return item;
    }

    // Finesse
    // Tier 1
    public Item dagger() {
        Item item =
                new ThrownFinesseWeapon(
                        ')',
                        ExtendedAsciiPanel.brightWhite,
                        "Dagger",
                        null,
                        Dice.d4,
                        Dice.d4,
                        20,
                        8);
        return item;
    }

    // Tier 2
    public Item rapier(int depth, boolean addToWorld) {
        Item item =
                new BasicFinesseWeapon(
                        ')', ExtendedAsciiPanel.brightWhite, "Rapier", null, Dice.d8, 250, 18);
        return item;
    }

    // Ranged

}
