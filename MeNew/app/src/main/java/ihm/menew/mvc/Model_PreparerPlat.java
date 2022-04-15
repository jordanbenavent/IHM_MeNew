package ihm.menew.mvc;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Model_PreparerPlat extends Observable {

    private final String TAG = "frallo " + getClass().getSimpleName();
    private Controller_PreparerPlat controller;
    private Jour jour = Jour.getJour(Calendar.getInstance().getTime().getDay());
    private Jour jourAffiche = jour;
    private int indiceJour = jour.ordinal();

    public void setController(Controller_PreparerPlat controller) {
        this.controller = controller;
    }

    public void clickOnNext() {
        Log.e("test", jourAffiche+"");
        indiceJour++;
        jourAffiche = Jour.getJour(indiceJour%7);
        Log.e("ici", indiceJour+"");
        Log.e("ici", indiceJour%7+"");
        Log.e("test", jourAffiche+"");
        Log.e("test", jourAffiche+"");
        setChanged();
        notifyObservers();
    }

    public enum Jour {
        Dimanche,
        Lundi,
        Mardi,
        Mercredi,
        Jeudi,
        Vendredi,
        Samedi;

        public static Jour getJour(int jour){
            for( Jour j : values()){
                if(j.ordinal() == jour){
                    return j;
                }
            }
            return null;
        }
    }

    public Model_PreparerPlat(Controller_PreparerPlat controller){
        super();
        this.controller = controller;
        Log.d(TAG, "Model is created");
    }

    public Jour getJourAffiche() {
        return jourAffiche;
    }
}
