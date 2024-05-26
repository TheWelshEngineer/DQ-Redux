package RogueLike.Main.Screens.CharacterSheet;

public abstract class CharacterSheetElement {
    public abstract String header();

    public String details() {
        return String.format("%s\n%s\n%s", details1(), details2(), details3());
    }

    public String details1() {return "";}
    public String details2() {return "";}
    public String details3() {return "";}

    public boolean isSelectable() {
        return true;
    }
}
