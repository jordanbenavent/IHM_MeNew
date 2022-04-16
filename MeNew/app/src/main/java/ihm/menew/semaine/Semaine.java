package ihm.menew.semaine;

import java.util.ArrayList;

import ihm.menew.mvc.Model_PreparerPlat;

public class Semaine {

    private ArrayList<MyJour> semaine;

    public Semaine() {
        this.semaine = new ArrayList<>(7);
        semaine.add(new MyJour());
        semaine.add(new MyJour());
        semaine.add(new MyJour());
        semaine.add(new MyJour());
        semaine.add(new MyJour());
        semaine.add(new MyJour());
        semaine.add(new MyJour());
    }

    public ArrayList<MyJour> getSemaine() {
        return semaine;
    }

    public MyJour getJour(int jour){
        return semaine.get(jour);
    }
}
