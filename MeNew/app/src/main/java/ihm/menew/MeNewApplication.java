package ihm.menew;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import java.util.Objects;

public class MeNewApplication extends Application {
    public static final String CHANNEL_DEFAULT = "Channel_Defaut";
    public static final String CHANNEL_HIGH = "Channel_High";

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
}
