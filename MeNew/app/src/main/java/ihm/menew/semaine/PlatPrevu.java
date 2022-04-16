package ihm.menew.semaine;

import java.util.ArrayList;

public class PlatPrevu {

    private ArrayList<Semaine> emploieDutemps;

    public PlatPrevu(){
        this.emploieDutemps = new ArrayList<>(1);
        this.emploieDutemps.add(new Semaine());
    }

    public ArrayList<Semaine> getEmploieDutemps() {
        return emploieDutemps;
    }

    public Semaine getSemaine(int semaine){
        return emploieDutemps.get(0);
    }
}
