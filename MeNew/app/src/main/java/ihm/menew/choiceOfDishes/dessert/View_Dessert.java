package ihm.menew.choiceOfDishes.dessert;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import ihm.menew.R;
import ihm.menew.choiceOfDishes.dish.Controller_Dishes;
import ihm.menew.choiceOfDishes.dish.DishesAdapter;
import ihm.menew.choiceOfDishes.dish.Model_Dishes;

public class View_Dessert implements Observer {
    private ViewGroup layout;
    private boolean modelCreated = false;
    private Controller_Dessert controller;
    private DessertAdapter adapter;

    public <T extends ViewGroup> View_Dessert(Context context, T layout) {
        adapter = new DessertAdapter(context, this); //carrefull, model is null !
        this.layout = layout;
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public void setListener(Controller_Dessert controller) {
        this.controller = controller;
    }

    public void onClickItem(int position){
        controller.onClickItem(position);
    }

    @Override
    public void update(Observable observable, Object o) {
        Model_Dessert model = (Model_Dessert) observable;
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
