package ihm.menew.mvc;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private int indiceJourSemaine = jour.ordinal();
    private List<Plat> midi;
    private List<Plat> soir;

    public Model_PreparerPlat(Controller_PreparerPlat controller){
        super();
        this.controller = controller;
        this.midi = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getMidi();
        this.soir = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getSoir();
        setChanged();
    }


    public Jour getJourAffiche() {
        return jourAffiche;
    }

    public List<Plat> getSoir() { return soir; }

    public List<Plat> getMidi() {
        return midi;
    }

    public int getIndiceJour() { return indiceJour; }

    public int getIndiceJourSemaine() { return indiceJourSemaine; }

    public void setController(Controller_PreparerPlat controller) {
        this.controller = controller;
    }

    public void clickOnNext() {
        this.indiceJour++;
        this.indiceJourSemaine = indiceJour%7;
        this.jourAffiche = Jour.getJour(indiceJourSemaine);
        this.midi = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getMidi();
        this.soir = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getSoir();
        setChanged();
        notifyObservers();
    }

    public void clickOnPrevious() {
        this.indiceJour--;
        if(indiceJour<0) indiceJour+=7;
        this.indiceJourSemaine = indiceJour%7;
        Log.e(indiceJour+"", indiceJourSemaine+"");
        this.jourAffiche = Jour.getJour(indiceJourSemaine);
        this.midi = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getMidi();;
        this.soir = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getSoir();
        setChanged();
        notifyObservers();
    }

    public void clickOnButtonPlus2() {
        this.soir = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getSoir();
        setChanged();
        notifyObservers();
    }

    public void clickOnButtonPlus1() {
        this.midi = MeNewApplication.mesPlat.getEmploieDutemps().get(0).getJour(indiceJourSemaine).getMidi();
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


}
