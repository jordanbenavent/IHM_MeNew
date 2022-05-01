package ihm.menew.choiceOfDishes.dessert;

import android.content.Intent;

import ihm.menew.MainActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.choiceOfDishes.dish.Model_Dishes;
import ihm.menew.choiceOfDishes.dish.View_Dishes;

public class Controller_Dessert {

    private Model_Dessert model;
    private View_Dessert view;

    public Controller_Dessert(View_Dessert view, Model_Dessert model){
        this.model = model;
        this.view = view;
    }

    public void onClickItem(int position) {
        System.out.println("CLIQUE " + position);
    }

    public void onClickAddPlanning(int jour, String quand) {
        if("midi".equals(quand)){
            System.out.println("add planning");
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(jour).setMidi(MeNewApplication.platsCHoisis);
            view.getLayout().getContext().startActivity(new Intent(view.getLayout().getContext(), MainActivity.class));
        }
    }
}
