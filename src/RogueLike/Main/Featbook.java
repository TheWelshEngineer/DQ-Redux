package RogueLike.Main;

import java.util.ArrayList;

public class Featbook {

    private ArrayList<Feat> feats;

    public ArrayList<Feat> getFeats() {
        return feats;
    }

    private ArrayList<Integer> featIDs;

    public ArrayList<Integer> getFeatIDs() {
        return featIDs;
    }

    public Feat get(int i) {
        return feats.get(i);
    }

    public Featbook(int max) {
        feats = new ArrayList<Feat>(max);
        featIDs = new ArrayList<Integer>();
    }

    public void add(Feat feat) {
        boolean prerequisitesMet = false;
        boolean incompatibilitiesMet = false;

        int countPrerequisites = 0;
        for (int j = 0; j < feat.getPrerequisites().size(); j++) {
            if (featIDs.contains(feat.getPrerequisites().get(j))) {
                countPrerequisites++;
            }
        }
        if (countPrerequisites == feat.getPrerequisites().size()) {
            prerequisitesMet = true;
        } else {
            System.out.print("prerecs not met");
        }

        int countincompatibilities = 0;
        for (int j = 0; j < feat.getIncompatibilities().size(); j++) {
            if (featIDs.contains(feat.getIncompatibilities().get(j))) {
                countincompatibilities++;
            }
        }
        if (countincompatibilities == 0) {
            incompatibilitiesMet = true;
        } else {
            System.out.print("incomps not met");
        }

        if (prerequisitesMet == true
                && incompatibilitiesMet == true
                && isFeatKnown(feat) == false) {
            feats.add(feat);
            featIDs.add(feat.id());
        } else {
            System.out.print("feat already known");
        }

        // feats.add(feat);
        // featIDs.add(feat.id());
    }

    public void remove(Feat feat) {
        feats.remove(feat);
        featIDs.remove(feat.id());
    }

    public boolean isFeatKnown(Feat feat) {
        if (featIDs.contains(feat.id())) {
            return true;
        } else {
            return false;
        }
    }
}
