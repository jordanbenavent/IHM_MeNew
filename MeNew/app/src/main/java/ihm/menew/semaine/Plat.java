package ihm.menew.semaine;

import android.app.Application;
import android.os.Parcel;

public class Plat extends Application {

    private String nomPlat;
    private String preparation;
    private int tempsPreparation;
    private int Image;

    Plat(String nomPlat, String preparation, int tempsPreparation, int image){
        this.nomPlat=nomPlat;
        this.preparation=preparation;
        this.tempsPreparation=tempsPreparation;
        this.Image = image ;
    }

    public  Plat(Parcel in){
        this.nomPlat = in.readString();
        this.preparation = in.readString();
        this.tempsPreparation = in.readInt();
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

    public int getImage() {
        return Image;
    }

    @Override
    public String toString() {
        return nomPlat;
    }
}
