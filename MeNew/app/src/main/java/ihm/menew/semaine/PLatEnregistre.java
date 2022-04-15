package ihm.menew.semaine;

import java.util.HashMap;

public class PLatEnregistre {

    public HashMap<String, Plat> data;

    public PLatEnregistre(){
        this.data = new HashMap<>();
        this.data.put("Salade", new Plat("Salade", "Melange ingédrient", 10));
        this.data.put("Tartiflette", new Plat("Tartiflette", "Couper les pommes de terre", 60));
        this.data.put("Mousse au chocolat", new Plat("Mousse au chocolat", "Fondre le chocolat", 25));
    }
}
