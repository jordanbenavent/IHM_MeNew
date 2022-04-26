package ihm.menew.semaine;

import java.util.ArrayList;
import java.util.List;

public class MyJour {

    private List<Plat> midi = new ArrayList<>();
    private List<Plat> soir = new ArrayList<>();

    public MyJour(){
    }

    public List<Plat> getMidi() {
        return midi;
    }

    public List<Plat> getSoir() {
        return soir;
    }

    public void setSoir(List<Plat> soir) {
        this.soir = soir;
    }

    public void setMidi(List<Plat> plats) {
        this.midi = plats;
    }
}
