package ihm.menew;

import android.app.Application;
import android.view.ViewGroup;

import ihm.menew.mvc.Controller_PreparerPlat;
import ihm.menew.mvc.Model_PreparerPlat;
import ihm.menew.mvc.View_PreparerPlat;

public class ApplicationPréparerPlat extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public <T extends ViewGroup> void onViewCreated(T layout){
        //create VIEW with XML layout
        View_PreparerPlat view = new View_PreparerPlat( getApplicationContext(), layout );
        Model_PreparerPlat model = new Model_PreparerPlat(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW

        Controller_PreparerPlat controller = new Controller_PreparerPlat( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
    }
}
