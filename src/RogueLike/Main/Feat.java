package RogueLike.Main;

import java.util.ArrayList;

public class Feat {

    private String name;

    public String name() {
        return name;
    }

    private int id;

    public int id() {
        return id;
    }

    private ArrayList<Integer> prerequisites;

    public ArrayList<Integer> getPrerequisites() {
        return prerequisites;
    }

    public void addPrerequisite(int id) {
        prerequisites.add(id);
    }

    private ArrayList<Integer> incompatibilites;

    public ArrayList<Integer> getIncompatibilities() {
        return incompatibilites;
    }

    public void addIncompatibiliy(int id) {
        incompatibilites.add(id);
    }

    private boolean isActivated;

    public boolean isActivated() {
        return isActivated;
    }

    private int charges;

    public int charges() {
        return charges;
    }

    public void setCharges(int input) {
        charges = input;
    }

    public void modifyCharges(int input) {
        charges += input;
    }

    public Feat(String name, int id, boolean activated, int charges) {
        this.name = name;
        this.id = id;
        this.isActivated = activated;
        this.charges = charges;
        this.prerequisites = new ArrayList<Integer>();
        this.incompatibilites = new ArrayList<Integer>();
    }
}
