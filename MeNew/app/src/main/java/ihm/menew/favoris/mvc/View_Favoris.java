package ihm.menew.favoris.mvc;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import ihm.menew.ApplicationPréparerPlat;
import ihm.menew.MeNewApplication;
import ihm.menew.R;

import ihm.menew.mvc.Model_PreparerPlat;
import ihm.menew.plat.PlatAdapter;
import ihm.menew.semaine.Plat;

public class View_Favoris implements Observer {

    private ViewGroup layout;
    private boolean modelCreated = false;
    private Controller_Favoris controller;
    private PlatAdapter adapter;

    public <T extends ViewGroup> View_Favoris(Context context, T layout) {
        adapter = new PlatAdapter(context, this); //carrefull, model is null !
        this.layout = layout;
        onClickTriAlphabetique();
        onClickTriDuree();
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public void setListener(Controller_Favoris controller) {
        this.controller = controller;
    }

    public void onClickItem(int position){
        controller.onClickItem(position);
    }

    @Override
    public void update(Observable observable, Object o) {
        Model_Favoris model = (Model_Favoris) observable;
        if (!modelCreated) {        //fist time only
            adapter.updateModel(model);
            System.out.println(layout);
            ListView listView = ((ListView)layout.findViewById(R.id.listPlat));
            listView.setAdapter( adapter );
            modelCreated = true;
        }
        else {
            adapter.refresh(model);
        }
    }

    public void onClickTriAlphabetique() {
        layout.findViewById(R.id.triNom).setOnClickListener( click -> {
            controller.clickOnTriAlphabetique();
        });
    }

    public void onClickTriDuree() {
        layout.findViewById(R.id.triTemps).setOnClickListener( click -> {
            controller.clickOnTriDuree();
        });
    }
}
