package ihm.menew.choiceOfDishes.dessert;

import java.util.List;
import java.util.Observable;

import ihm.menew.MeNewApplication;
import ihm.menew.choiceOfDishes.dish.Controller_Dishes;
import ihm.menew.semaine.Plat;

public class Model_Dessert extends Observable {


    private Controller_Dessert controller;
    private List<Plat> desserts;

    public Model_Dessert(Controller_Dessert controller){
        super();
        this.controller = controller;
        this.desserts = MeNewApplication.plats.getDesserts();
        setChanged();
    }

    public void addPlatDefault(Plat plat){
        desserts.add(MeNewApplication.plats.data.get(plat.getNomPlat()));
        desserts.add(MeNewApplication.plats.data.get("Mousse au chocolat"));
        setChanged();
        notifyObservers();
    }

    public List<Plat> getDesserts() {
        return desserts;
    }

    public void setDesserts(List<Plat> desserts) {
        this.desserts = desserts;
    }

    public Controller_Dessert getController() {
        return controller;
    }

    public void setController(Controller_Dessert controller) {
        this.controller = controller;
    }

    public String getNomPlat(int position){
        return desserts.get(position).getNomPlat();
    }
    public String getTempsPreparationPlat(int position){
        return desserts.get(position).getTempsPreparation()+"";
    }

    public int getImage(int position){
        return desserts.get(position).getImage();
    }

    public Plat getPlat(int position){
        return desserts.get(position);
    }

    public Plat getDish(int i) {
        return desserts.get(i);
    }
}
