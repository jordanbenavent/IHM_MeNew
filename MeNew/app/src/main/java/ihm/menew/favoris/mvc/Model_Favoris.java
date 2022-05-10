package ihm.menew.favoris.mvc;

import java.util.List;
import java.util.Observable;

import ihm.menew.MeNewApplication;
import ihm.menew.semaine.Plat;

public class Model_Favoris extends Observable {

    private Controller_Favoris controller;
    private List<Plat> favoris;

    public Model_Favoris(Controller_Favoris controller){
        super();
        this.controller = controller;
        this.favoris = MeNewApplication.favoris;
        setChanged();
    }

    public void addPlatDefault(Plat plat){
        favoris.add(MeNewApplication.plats.data.get(plat.getNomPlat()));
        favoris.add(MeNewApplication.plats.data.get("Mousse au chocolat"));
        setChanged();
        notifyObservers();
    }

    public List<Plat> getFavoris() {
        return favoris;
    }

    public void setFavoris(List<Plat> favoris) {
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

    public String getImage(int position){
        return favoris.get(position).getImage();
    }

    public Plat getPlat(int position){
        return favoris.get(position);
    }

    public void removePlat(Plat plat) {
        favoris.remove(plat);
        setChanged();
        notifyObservers();
    }

    public void triAlphabetique() {
        favoris.sort(Plat.triAlpha);
        setChanged();
        notifyObservers();
    }

    public void triDuree() {
        favoris.sort(Plat.triDuree);
        setChanged();
        notifyObservers();
    }
}
