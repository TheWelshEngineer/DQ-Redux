package RogueLike.Main.Screens.CharacterSheet;

public abstract class CharacterSheetElement {
    public abstract String header();

    public String details1() {
        return "";
    }

    public String details2() {
        return "";
    }

    public String details3() {
        return "";
    }

    public boolean isSelectable() {
        return true;
    }
}
