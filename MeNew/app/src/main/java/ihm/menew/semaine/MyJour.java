package ihm.menew.semaine;

public class MyJour {

    private Plat midi;
    private Plat soir;

    public MyJour(){
    }

    public Plat getMidi() {
        return midi;
    }

    public Plat getSoir() {
        return soir;
    }

    public void setMidi(Plat midi) {
        this.midi = midi;
    }

    public void setSoir(Plat soir) {
        this.soir = soir;
    }
}
