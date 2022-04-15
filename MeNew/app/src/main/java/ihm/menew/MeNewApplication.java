package ihm.menew;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import android.view.ViewGroup;

import java.util.Objects;

import ihm.menew.mvc.Controller_PreparerPlat;
import ihm.menew.mvc.Model_PreparerPlat;
import ihm.menew.mvc.View_PreparerPlat;
import ihm.menew.semaine.PLatEnregistre;
import ihm.menew.semaine.PlatPrevu;

public class MeNewApplication extends Application {
    public static final String CHANNEL_DEFAULT = "Channel_Defaut";
    public static final String CHANNEL_HIGH = "Channel_High";
    public static PLatEnregistre plats = new PLatEnregistre();
    public static PlatPrevu mesPlat = new PlatPrevu();
    public static NotificationManager notificationManager;
    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(getClass().getSimpleName(),"CREATION");
        createNotificationChannelDefaut("Defaut", "Without Picture", NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannelHigh("High", "With Picture", NotificationManager.IMPORTANCE_HIGH);
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
        View_PreparerPlat view = new View_PreparerPlat( getApplicationContext(), layout );
        Model_PreparerPlat model = new Model_PreparerPlat(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW

        Controller_PreparerPlat controller = new Controller_PreparerPlat( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
    }
}