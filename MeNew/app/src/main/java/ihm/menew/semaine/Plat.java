package ihm.menew.semaine;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

public class Plat extends Application implements Parcelable {

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

    public Plat(Parcel in){
        this.nomPlat = in.readString();
        this.preparation = in.readString();
        this.tempsPreparation = in.readInt();
        this.Image = in.readInt();
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

    public int getImage() {
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
        parcel.writeInt(Image);
    }
}
