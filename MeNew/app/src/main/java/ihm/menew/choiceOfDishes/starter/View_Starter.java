package ihm.menew.choiceOfDishes.starter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import ihm.menew.R;
import ihm.menew.favoris.mvc.Controller_Favoris;
import ihm.menew.favoris.mvc.Model_Favoris;
import ihm.menew.plat.PlatAdapter;

public class View_Starter implements Observer {
    private ViewGroup layout;
    private boolean modelCreated = false;
    private Controller_Starter controller;
    private StarterAdapter adapter;

    public <T extends ViewGroup> View_Starter(Context context, T layout) {
        adapter = new StarterAdapter(context, this); //carrefull, model is null !
        this.layout = layout;
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public void setListener(Controller_Starter controller) {
        this.controller = controller;
    }

    public void onClickItem(int position){
        controller.onClickItem(position);
    }

    @Override
    public void update(Observable observable, Object o) {
        Model_Starter model = (Model_Starter) observable;
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
