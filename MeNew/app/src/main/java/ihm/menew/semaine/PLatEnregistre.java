package ihm.menew.semaine;

import java.util.HashMap;

import ihm.menew.R;

public class PLatEnregistre {

    public HashMap<String, Plat> data;

    public PLatEnregistre(){
        this.data = new HashMap<>();
        this.data.put("Salade", new Plat("Salade", "Melanger les ingrédients", 10, R.drawable.salade));
        this.data.put("Tartiflette", new Plat("Tartiflette", "Couper les pommes de terre", 60, R.drawable.tartiflette));
        this.data.put("Mousse au chocolat", new Plat("Mousse au chocolat", "Fondre le chocolat", 25, R.drawable.moussechoco));
    }
}
