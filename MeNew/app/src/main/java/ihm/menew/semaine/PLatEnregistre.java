package ihm.menew.semaine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ihm.menew.R;

public class PLatEnregistre {

    public HashMap<String, Plat> data;

    public PLatEnregistre(){
        this.data = new HashMap<>();
        this.data.put("Salade", new Starter("Salade", "Melanger les ingrédients", 10, "https://images.pexels.com/photos/4989069/pexels-photo-4989069.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"));
        this.data.put("Salade de riz", new Starter("Salade de riz", "Melanger les ingrédients", 10, "https://images.pexels.com/photos/4519053/pexels-photo-4519053.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        this.data.put("Tartiflette", new Dish("Tartiflette", "Couper les pommes de terre", 60, "https://media.istockphoto.com/photos/tartiflette-picture-id511932310?k=20&m=511932310&s=612x612&w=0&h=kiGT-mVt9JYo1aAgzccVnHSulGJTUz7op-jTkfcBlgk="));
        this.data.put("Mousse au chocolat", new Dessert("Mousse au chocolat", "Fondre le chocolat", 25, "https://images.pexels.com/photos/4109996/pexels-photo-4109996.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        this.data.put("Fraisier", new Dessert("Fraisier", "Couper les fraises", 20, "https://media.istockphoto.com/photos/festive-cake-with-fresh-strawberries-cream-decorated-with-mint-leaves-picture-id802832778?k=20&m=802832778&s=612x612&w=0&h=Swuv3A4ekEALccx5eoa_fwK_1QXTTxM_MtO5OmKECvQ="));
        this.data.put("Crème au citron", new Dessert("Creme au citron", "Couper les citrons", 35, "https://images.pexels.com/photos/8085289/pexels-photo-8085289.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
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
            if(plat instanceof Dish){
                starter.add(plat);
            }
        });
        return starter;
    }

    public List<Plat> getDesserts() {
        List<Plat> starter = new ArrayList<>();
        data.forEach((string, plat) -> {
            if(plat instanceof Dessert){
                starter.add(plat);
            }
        });
        return starter;
    }
}
