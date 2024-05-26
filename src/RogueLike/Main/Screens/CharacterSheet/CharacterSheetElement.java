package RogueLike.Main.Screens.CharacterSheet;

public abstract class CharacterSheetElement {
    public abstract String header();

    public abstract String details();

    public boolean isSelectable() {
        return true;
    }
}
