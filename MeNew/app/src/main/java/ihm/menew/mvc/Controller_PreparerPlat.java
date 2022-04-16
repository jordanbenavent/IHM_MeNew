package ihm.menew.mvc;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.semaine.MyJour;
import ihm.menew.semaine.Plat;
import ihm.menew.semaine.Semaine;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Controller_PreparerPlat {
    private String TAG = "Jordan" + getClass().getSimpleName();
    private Model_PreparerPlat model;
    private View_PreparerPlat view;

    public Controller_PreparerPlat(View_PreparerPlat view, Model_PreparerPlat model) {
        Log.d(TAG, "Controller is created" );
        this.view = view;
        this.model = model;
        TextView activity_main = ((ConstraintLayout)view.getLayout()).findViewById(R.id.jour);
        //manage.findViewById(R.id.addTeam1).setOnClickListener( click ->  addPersonInTeam( manage, Model_Kindergarten.Team.TEAM1));
    }

    public void clickOnNext() {
        model.clickOnNext();
    }

    public void clickOnPrevious() { model.clickOnPrevious();
    }

    public void clickOnButtonPlus2() {
        MyJour jour = new MyJour();
        List<Plat> plats = new ArrayList<>();
        plats.add((MeNewApplication.plats.data.get("Salade")));
        plats.add((MeNewApplication.plats.data.get("Tartiflette")));
        plats.add((MeNewApplication.plats.data.get("Mousse au chocolat")));
        jour.setSoir(plats);
        MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().set(model.getIndiceJourSemaine(),jour);
        model.clickOnButtonPlus2();
    }
}
