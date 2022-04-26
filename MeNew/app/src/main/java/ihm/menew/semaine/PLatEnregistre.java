package ihm.menew.semaine;

import java.util.HashMap;

import ihm.menew.R;

public class PLatEnregistre {

    public static HashMap<String, Plat> data;

    public PLatEnregistre(){
        data = new HashMap<>();
        data.put("Salade", new Plat("Salade", "Melange ingédrient", 10, R.drawable.salade));
        data.put("Tartiflette", new Plat("Tartiflette", "Couper les pommes de terre", 60, R.drawable.tartiflette));
        data.put("Mousse au chocolat", new Plat("Mousse au chocolat", "Fondre le chocolat", 25, R.drawable.moussechoco));
    }

    public static Plat get(String nom) {
        return data.get(nom);
    }
}
