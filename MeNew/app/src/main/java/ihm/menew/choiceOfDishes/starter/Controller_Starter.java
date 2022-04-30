package ihm.menew.choiceOfDishes.starter;

import ihm.menew.favoris.mvc.Model_Favoris;
import ihm.menew.favoris.mvc.View_Favoris;

public class Controller_Starter {
    private Model_Starter model;
    private View_Starter view;

    public Controller_Starter(View_Starter view, Model_Starter model){
        this.model = model;
        this.view = view;
    }

    public void onClickItem(int position) {
        System.out.println("CLIQUE " + position);
    }
}
