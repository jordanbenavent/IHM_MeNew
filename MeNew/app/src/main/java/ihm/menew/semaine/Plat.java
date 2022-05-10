package ihm.menew.semaine;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Plat extends Application implements Parcelable {

    private String nomPlat;
    private String preparation;
    private int tempsPreparation;
    private String Image;

    public static Comparator<Plat> triAlpha = new Comparator<Plat>() {
        @Override
        public int compare(Plat plat, Plat plat2) {
            System.out.println(plat.nomPlat.compareTo(plat2.nomPlat));
            return plat.nomPlat.compareTo(plat2.nomPlat);
        }
    };

    public static Comparator<Plat> triDuree = new Comparator<Plat>() {
        @Override
        public int compare(Plat plat, Plat plat2) {
            System.out.println(plat.nomPlat.compareTo(plat2.nomPlat));
            return plat.tempsPreparation-plat2.tempsPreparation;
        }
    };

    public Plat(String nomPlat, String preparation, int tempsPreparation, String image){
        this.nomPlat=nomPlat;
        this.preparation=preparation;
        this.tempsPreparation=tempsPreparation;
        this.Image = image ;
    }

    public Plat(Parcel in){
        this.nomPlat = in.readString();
        this.preparation = in.readString();
        this.tempsPreparation = in.readInt();
        this.Image = in.readString();
    }

    public static final Parcelable.Creator<Plat> CREATOR = new Parcelable.Creator<Plat>() {
        @Override
        public Plat createFromParcel(Parcel parcel) {
            return new Plat(parcel);
        }

        @Override
        public Plat[] newArray(int i) {
            return new Plat[i];
        }
    };

    public int getTempsPreparation() {
        return tempsPreparation;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getImage() {
        return Image;
    }

    @Override
    public String toString() {
        return nomPlat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nomPlat);
        parcel.writeString(preparation);
        parcel.writeInt(tempsPreparation);
        parcel.writeString(Image);
    }
}
