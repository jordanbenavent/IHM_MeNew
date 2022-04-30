package ihm.menew.choiceOfDishes.dish;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import ihm.menew.R;
import ihm.menew.choiceOfDishes.starter.Controller_Starter;
import ihm.menew.choiceOfDishes.starter.Model_Starter;
import ihm.menew.choiceOfDishes.starter.StarterAdapter;

public class View_Dishes implements Observer {
    private ViewGroup layout;
    private boolean modelCreated = false;
    private Controller_Dishes controller;
    private DishesAdapter adapter;

    public <T extends ViewGroup> View_Dishes(Context context, T layout) {
        adapter = new DishesAdapter(context, this); //carrefull, model is null !
        this.layout = layout;
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public void setListener(Controller_Dishes controller) {
        this.controller = controller;
    }

    public void onClickItem(int position){
        controller.onClickItem(position);
    }

    @Override
    public void update(Observable observable, Object o) {
        Model_Dishes model = (Model_Dishes) observable;
        if (!modelCreated) {        //fist time only
            adapter.updateModel(model);
            System.out.println(layout);
            ListView listView = ((ListView)layout.findViewById(R.id.listStarter));
            listView.setAdapter( adapter );
            modelCreated = true;
        }
        else {
            adapter.refresh(model);
        }
    }
}
