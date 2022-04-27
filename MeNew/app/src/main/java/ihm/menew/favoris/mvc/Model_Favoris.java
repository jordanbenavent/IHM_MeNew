package ihm.menew.favoris.mvc;

import android.graphics.Bitmap;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ihm.menew.MeNewApplication;
import ihm.menew.mvc.Controller_PreparerPlat;
import ihm.menew.semaine.Plat;

public class Model_Favoris extends Observable {

    private Controller_Favoris controller;
    private ArrayList<Plat> favoris = new ArrayList<>();

    public Model_Favoris(Controller_Favoris controller){
        super();
        this.controller = controller;
    }

    public void addPlatDefault(){
        favoris.add(MeNewApplication.plats.data.get("Tartiflette"));
        favoris.add(MeNewApplication.plats.data.get("Mousse au chocolat"));
        favoris.add(MeNewApplication.plats.data.get("Salade"));
        setChanged();
        notifyObservers();
    }

    public ArrayList<Plat> getFavoris() {
        return favoris;
    }

    public void setFavoris(ArrayList<Plat> favoris) {
        this.favoris = favoris;
    }

    public Controller_Favoris getController() {
        return controller;
    }

    public void setController(Controller_Favoris controller) {
        this.controller = controller;
    }

    public String getNomPlat(int position){
        return favoris.get(position).getNomPlat();
    }
    public String getTempsPreparationPlat(int position){
        return favoris.get(position).getTempsPreparation()+"";
    }

    public int getImage(int position){
        return favoris.get(position).getImage();
    }

    public Plat getPlat(int position){
        return favoris.get(position);
    }
}
