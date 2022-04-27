package ihm.menew.favoris.mvc;

public class Controller_Favoris {

    private Model_Favoris model;
    private View_Favoris view;

    public Controller_Favoris(View_Favoris view, Model_Favoris model){
        this.model = model;
        this.view = view;
    }

    public void onClickItem(int position) {
        System.out.println("CLIQUE " + position);
    }
}
