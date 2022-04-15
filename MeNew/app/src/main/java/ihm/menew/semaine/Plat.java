package ihm.menew.semaine;

public class Plat {

    private String nomPlat;
    private String preparation;
    private int tempsPreparation;

    Plat(String nomPlat, String preparation, int tempsPreparation){
        this.nomPlat=nomPlat;
        this.preparation=preparation;
        this.tempsPreparation=tempsPreparation;
    }

    public int getTempsPreparation() {
        return tempsPreparation;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public String getPreparation() {
        return preparation;
    }

    @Override
    public String toString() {
        return nomPlat;
    }
}
