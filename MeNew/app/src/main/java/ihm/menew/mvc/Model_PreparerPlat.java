package ihm.menew.mvc;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import ihm.menew.MeNewApplication;
import ihm.menew.semaine.Plat;

public class Model_PreparerPlat extends Observable {

    private final String TAG = "frallo " + getClass().getSimpleName();
    private Controller_PreparerPlat controller;
    private Jour jour = Jour.getJour(Calendar.getInstance().getTime().getDay());
    private Jour jourAffiche = jour;
    private int indiceJour = jour.ordinal();
    private Plat midi;
    private Plat soir;

    public void setController(Controller_PreparerPlat controller) {
        this.controller = controller;
    }

    public void clickOnNext() {
        this.indiceJour++;
        this.jourAffiche = Jour.getJour(indiceJour%7);
        this.midi = null;
        this.soir = null;
        setChanged();
        notifyObservers();
    }

    public void clickOnButtonPlus2() {
        this.soir = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(0).getSoir();
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

    public Plat getSoir() {
        return soir;
    }

    public Plat getMidi() {
        return midi;
    }
}
