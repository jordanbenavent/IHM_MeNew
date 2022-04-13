package ihm.menew;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;

public class GenerationNotification extends Application {

    private Bitmap myBitMap;
    GenerationNotification(){}

    public void sendNotification(Context applicationContext){
        myBitMap = BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.plat);
        Log.e(getClass().getSimpleName(),"LAA");
        NotificationCompat.Builder notification = new NotificationCompat.Builder(applicationContext, MeNewApplication.CHANNEL_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24_v2)
                .setContentTitle("Pense à créer ton plat !")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(myBitMap))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        MeNewApplication.getNotificationManager().notify(1, notification.build());

    }

}
