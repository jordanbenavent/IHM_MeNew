package ihm.menew.choiceOfDishes.dessert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.widget.Toast;

import ihm.menew.CalendarActivity;
import ihm.menew.MainActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.choiceOfDishes.dish.Model_Dishes;
import ihm.menew.choiceOfDishes.dish.View_Dishes;

public abstract class Controller_Dessert extends Context {

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
        if(jour==-1){
            Intent intent =(new Intent(view.getLayout().getContext(), CalendarActivity.class));
            intent.putExtra("jour", jour);
            view.getLayout().getContext().startActivity(intent);
        }
        if("midi".equals(quand)){
            System.out.println("add planning");
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(jour).setMidi(MeNewApplication.platsCHoisis);
            view.getLayout().getContext().startActivity(new Intent(view.getLayout().getContext(), MainActivity.class));
        }
        if("soir".equals(quand)){
            System.out.println("add planning");
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(jour).setSoir(MeNewApplication.platsCHoisis);
            view.getLayout().getContext().startActivity(new Intent(view.getLayout().getContext(), MainActivity.class));
        }
    }
}
