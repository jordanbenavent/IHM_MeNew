package ihm.menew.mvc;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import ihm.menew.ActivityUtilisateur;
import ihm.menew.R;
import ihm.menew.semaine.Plat;

import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class View_PreparerPlat implements Observer {

    private final String TAG = "frallo " + getClass().getSimpleName();
    //private  ViewAdapter adapter;
    private ViewGroup layout;
    private boolean modelCreated = false;
    Controller_PreparerPlat controller;

    public <T extends ViewGroup> View_PreparerPlat(Context context, T layout) {
        //adapter = new ViewAdapter(context, this); //carrefull, model is null !
        this.layout = layout;
        ((TextView)layout.findViewById(R.id.jour)).setText(Model_PreparerPlat.Jour.getJour(Calendar.getInstance().getTime().getDay()).toString());
        Log.d(TAG, "View is created" );
        onClickNext();
        onClickPrevious();
        onClickPlusSoir();
        onClickPlusMidi();
    }

    private void onClickNext() {
        layout.findViewById(R.id.next).setOnClickListener( click -> {
            controller.clickOnNext();
        });
    }

    private void onClickPlusSoir() {
        layout.findViewById(R.id.buttonpllus2).setOnClickListener( click -> {
            controller.clickOnButtonPlus2();
        });
    }

    private void onClickPlusMidi() {
        layout.findViewById(R.id.buttonpllus1).setOnClickListener( click -> {
            controller.clickOnButtonPlus1();
        });
    }

    private void onClickPrevious() {
        layout.findViewById(R.id.previous).setOnClickListener( click -> {
            controller.clickOnPrevious();
        });
    }

    public ViewGroup getLayout() {
        return layout;
    }

    @Override
    public void update(Observable observable, Object o) {
        Model_PreparerPlat model = (Model_PreparerPlat) observable;
        ((TextView)layout.findViewById(R.id.jour)).setText(model.getJourAffiche().toString());

        if(model.getSoir() != null){
            ((TextView)layout.findViewById(R.id.platSoir)).setText(stringPlats(model.getSoir()));
        } else {
            ((TextView)layout.findViewById(R.id.platSoir)).setText("");
        }
        if(model.getMidi() != null){
            ((TextView)layout.findViewById(R.id.platMidi)).setText(stringPlats(model.getMidi()));
        } else {
            ((TextView)layout.findViewById(R.id.platMidi)).setText("");
        }
    }

    public void setListener(Controller_PreparerPlat controller) {
        this.controller = controller;
    }

    public String stringPlats(List<Plat> plats){
        StringBuilder string = new StringBuilder();
        for(Plat plat : plats){
            string.append(plat + ", ");
        }
        return string.toString();
    }
}
