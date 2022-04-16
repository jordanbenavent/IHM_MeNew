package ihm.menew.semaine;

import java.util.ArrayList;
import java.util.List;

public class MyJour {

    private Plat midi;
    private List<Plat> soir = new ArrayList<>();

    public MyJour(){
    }

    public Plat getMidi() {
        return midi;
    }

    public List<Plat> getSoir() {
        return soir;
    }

    public void setMidi(Plat midi) {
        this.midi = midi;
    }

    public void setSoir(List<Plat> soir) {
        this.soir = soir;
    }
}
