package ihm.menew;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ihm.menew.choiceOfDishes.dessert.Controller_Dessert;
import ihm.menew.choiceOfDishes.dessert.Model_Dessert;
import ihm.menew.choiceOfDishes.dessert.View_Dessert;
import ihm.menew.choiceOfDishes.dish.Controller_Dishes;
import ihm.menew.choiceOfDishes.dish.Model_Dishes;
import ihm.menew.choiceOfDishes.dish.View_Dishes;
import ihm.menew.choiceOfDishes.starter.Controller_Starter;
import ihm.menew.choiceOfDishes.starter.Model_Starter;
import ihm.menew.choiceOfDishes.starter.View_Starter;
import ihm.menew.favoris.mvc.Controller_Favoris;
import ihm.menew.favoris.mvc.Model_Favoris;
import ihm.menew.favoris.mvc.View_Favoris;
import ihm.menew.mvc.Controller_PreparerPlat;
import ihm.menew.mvc.Model_PreparerPlat;
import ihm.menew.mvc.View_PreparerPlat;
import ihm.menew.notifications.NotificationService2;
import ihm.menew.semaine.PLatEnregistre;
import ihm.menew.semaine.Plat;
import ihm.menew.semaine.PlatPrevu;

public class MeNewApplication extends Application {
    public static final String CHANNEL_DEFAULT = "Channel_Defaut";
    public static final String CHANNEL_HIGH = "Channel_High";
    public static PLatEnregistre plats;
    public static PlatPrevu mesPlat = new PlatPrevu();
    public static List<Plat> favoris = new ArrayList<>();
    public static List<Plat> platsCHoisis = new ArrayList<>();
    public static NotificationManager notificationManager;
    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.plats = new PLatEnregistre();
        Log.e(getClass().getSimpleName(),"CREATION");
        createNotificationChannelDefaut("Defaut", "Without Picture", NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannelHigh("High", "With Picture", NotificationManager.IMPORTANCE_HIGH);
        someFavoriteDish();
    }

    public void someFavoriteDish(){
        favoris.add(MeNewApplication.plats.data.get("Tartiflette"));
        favoris.add(MeNewApplication.plats.data.get("Mousse au chocolat"));
        favoris.add(MeNewApplication.plats.data.get("Salade"));
    }


    private void createNotificationChannelHigh(String name, String description, int importance) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            Log.e(getClass().getSimpleName(),"CREATIONCHANNEL");
            NotificationChannel channel = new NotificationChannel(CHANNEL_HIGH, name, importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
        Log.e(getClass().getSimpleName(), "ici");
    }

    private void createNotificationChannelDefaut(String name, String description, int importance) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_DEFAULT, name, importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    public <T extends ViewGroup> void onViewCreated(T layout){
        //create VIEW with XML layout
        System.out.println(layout);
        View_PreparerPlat view = new View_PreparerPlat( getApplicationContext(), layout );
        Model_PreparerPlat model = new Model_PreparerPlat(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW

        Controller_PreparerPlat controller = new Controller_PreparerPlat( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewFavorisCreated(T layout){
        //create VIEW with XML layout
        System.out.println(layout);
        View_Favoris view = new View_Favoris( getApplicationContext(), layout );
        Model_Favoris model = new Model_Favoris(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Favoris controller = new Controller_Favoris( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewStarterCreated(T layout) {
        //create VIEW with XML layout
        platsCHoisis = new ArrayList<>();
        System.out.println(layout);
        View_Starter view = new View_Starter( getApplicationContext(), layout );
        Model_Starter model = new Model_Starter(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Starter controller = new Controller_Starter( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewDishesCreated(T layout) {
        View_Dishes view = new View_Dishes( getApplicationContext(), layout );
        Model_Dishes model = new Model_Dishes(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Dishes controller = new Controller_Dishes( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewDessertCreated(T layout) {
        View_Dessert view = new View_Dessert( getApplicationContext(), layout );
        Model_Dessert model = new Model_Dessert(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Dessert controller = new Controller_Dessert( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

}
