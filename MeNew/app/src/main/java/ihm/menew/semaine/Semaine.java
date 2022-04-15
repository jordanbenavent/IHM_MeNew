package ihm.menew.semaine;

import java.util.ArrayList;

public class Semaine {

    private ArrayList<MyJour> semaine;

    public Semaine() {
        this.semaine = new ArrayList<>();
    }

    public ArrayList<MyJour> getSemaine() {
        return semaine;
    }

    public MyJour getJour(int jour){
        return semaine.get(jour);
    }
}
