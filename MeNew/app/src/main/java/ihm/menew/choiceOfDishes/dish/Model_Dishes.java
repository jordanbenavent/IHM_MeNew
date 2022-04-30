package ihm.menew.choiceOfDishes.dish;

import java.util.List;
import java.util.Observable;

import ihm.menew.MeNewApplication;
import ihm.menew.choiceOfDishes.starter.Controller_Starter;
import ihm.menew.semaine.Plat;

public class Model_Dishes extends Observable {


    private Controller_Dishes controller;
    private List<Plat> dishes;

    public Model_Dishes(Controller_Dishes controller){
        super();
        this.controller = controller;
        this.dishes = MeNewApplication.plats.getDishes();
        setChanged();
    }

    public void addPlatDefault(Plat plat){
        dishes.add(MeNewApplication.plats.data.get(plat.getNomPlat()));
        dishes.add(MeNewApplication.plats.data.get("Mousse au chocolat"));
        setChanged();
        notifyObservers();
    }

    public List<Plat> getDishes() {
        return dishes;
    }

    public void setDishes(List<Plat> dishes) {
        this.dishes = dishes;
    }

    public Controller_Dishes getController() {
        return controller;
    }

    public void setController(Controller_Dishes controller) {
        this.controller = controller;
    }

    public String getNomPlat(int position){
        return dishes.get(position).getNomPlat();
    }
    public String getTempsPreparationPlat(int position){
        return dishes.get(position).getTempsPreparation()+"";
    }

    public int getImage(int position){
        return dishes.get(position).getImage();
    }

    public Plat getPlat(int position){
        return dishes.get(position);
    }

    public Plat getDish(int i) {
        return dishes.get(i);
    }
}
