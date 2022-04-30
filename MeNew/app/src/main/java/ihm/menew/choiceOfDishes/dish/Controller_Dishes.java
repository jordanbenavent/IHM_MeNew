package ihm.menew.choiceOfDishes.dish;

import ihm.menew.choiceOfDishes.starter.Model_Starter;
import ihm.menew.choiceOfDishes.starter.View_Starter;

public class Controller_Dishes {

    private Model_Dishes model;
    private View_Dishes view;

    public Controller_Dishes(View_Dishes view, Model_Dishes model){
        this.model = model;
        this.view = view;
    }

    public void onClickItem(int position) {
        System.out.println("CLIQUE " + position);
    }
}
