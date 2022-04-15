package ihm.menew.mvc;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import ihm.menew.R;

import java.util.Calendar;
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
    }

    private void onClickNext() {
        layout.findViewById(R.id.next).setOnClickListener( click -> {
            controller.clickOnNext();
        });
    }

    public ViewGroup getLayout() {
        return layout;
    }

    @Override
    public void update(Observable observable, Object o) {
        Model_PreparerPlat model = (Model_PreparerPlat) observable;
        ((TextView)layout.findViewById(R.id.jour)).setText(model.getJourAffiche().toString());
    }

    public void setListener(Controller_PreparerPlat controller) {
        this.controller = controller;
    }
}
