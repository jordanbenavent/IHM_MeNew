package ihm.menew.semaine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ihm.menew.R;

public class PLatEnregistre {

    public HashMap<String, Plat> data;

    public PLatEnregistre(){
        this.data = new HashMap<>();
        this.data.put("Salade", new Starter("Salade", "Melanger les ingrédients", 10, R.drawable.salade));
        this.data.put("Salade de riz", new Starter("Salade de riz", "Melanger les ingrédients", 10, R.drawable.salade));
        this.data.put("Tartiflette", new Plat("Tartiflette", "Couper les pommes de terre", 60, R.drawable.tartiflette));
        this.data.put("Mousse au chocolat", new Dessert("Mousse au chocolat", "Fondre le chocolat", 25, R.drawable.moussechoco));
    }

    public List<Plat> getStarter() {
        List<Plat> starter = new ArrayList<>();
        data.forEach((string, plat) -> {
            if(plat instanceof Starter){
                starter.add(plat);
            }
        });
        return starter;
    }

    public List<Plat> getDishes() {
        List<Plat> starter = new ArrayList<>();
        data.forEach((string, plat) -> {
            if(plat instanceof Plat){
                starter.add(plat);
            }
        });
        return starter;
    }
}
