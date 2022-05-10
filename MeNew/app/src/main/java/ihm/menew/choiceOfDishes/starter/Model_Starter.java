package ihm.menew.choiceOfDishes.starter;

import java.util.List;
import java.util.Observable;

import ihm.menew.MeNewApplication;
import ihm.menew.semaine.Plat;

public class Model_Starter extends Observable {


    private Controller_Starter controller;
    private List<Plat> starters;

    public Model_Starter(Controller_Starter controller){
        super();
        this.controller = controller;
        this.starters = MeNewApplication.plats.getStarter();
        setChanged();
    }

    public void addPlatDefault(Plat plat){
        starters.add(MeNewApplication.plats.data.get(plat.getNomPlat()));
        starters.add(MeNewApplication.plats.data.get("Mousse au chocolat"));
        setChanged();
        notifyObservers();
    }

    public List<Plat> getStarters() {
        return starters;
    }

    public void setStarters(List<Plat> starters) {
        this.starters = starters;
    }

    public Controller_Starter getController() {
        return controller;
    }

    public void setController(Controller_Starter controller) {
        this.controller = controller;
    }

    public String getNomPlat(int position){
        return starters.get(position).getNomPlat();
    }
    public String getTempsPreparationPlat(int position){
        return starters.get(position).getTempsPreparation()+"";
    }

    public String getImage(int position){
        return starters.get(position).getImage();
    }

    public Plat getPlat(int position){
        return starters.get(position);
    }

    public Plat getStarter(int i) {
        return starters.get(i);
    }
}
